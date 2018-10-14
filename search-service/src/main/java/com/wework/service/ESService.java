package com.wework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wework.vo.CompanyVo;
import com.wework.vo.HitsResult;
import com.wework.vo.SearchResultVo;
import com.wework.vo.UserVo;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author carl
 */
@Service
public class ESService {

  @Autowired
  private EntityManager entityManager;
  @Autowired
  private RestTemplate restTemplate;
  @Value("${elasticsearch.host}")
  private String esHost;
  private ObjectMapper objectMapper = new ObjectMapper();

  /**
   * 重新创建indies，慎用
   */
  @Transactional
  public void reCreateIndies() {
    try {
      FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
      fullTextEntityManager.createIndexer().startAndWait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public SearchResultVo search(String keyword) {
    SearchResultVo resultVo = new SearchResultVo();
    final int START = 0, MAX = 2;
    // user
    HitsResult<UserVo> userVoHitsResult = searchUser(keyword, START, MAX);
    resultVo.setTotalUser(userVoHitsResult.getTotal());
    resultVo.setUsers(
        userVoHitsResult.getHits().stream().map(x -> x.get_source()).collect(Collectors.toList()));
    // company
    HitsResult<CompanyVo> companyVoHitsResult = searchCompany(keyword, START, MAX);
    resultVo.setTotalCompany(companyVoHitsResult.getTotal());
    resultVo.setCompanies(companyVoHitsResult.getHits().stream().map(x -> x.get_source())
        .collect(Collectors.toList()));
    return resultVo;
  }

  /**
   * 用户
   *
   * @param from start 0
   */
  public HitsResult<UserVo> searchUser(String keyword, int from, int size) {
    String url = String
        .format("%s/com.wework.entity.user/_search?pretty&q=name.pinyin:%s&from=%s&size=%s",
            esHost, keyword, from, size);
    return search(url, new TypeReference<HitsResult<UserVo>>() {
    });
  }

  public HitsResult<CompanyVo> searchCompany(String keyword, int from, int size) {
    String url = String
        .format("%s/com.wework.entity.company/_search?pretty&q=name.pinyin:%s&from=%s&size=%s",
            esHost, keyword, from, size);
    return search(url, new TypeReference<HitsResult<CompanyVo>>() {
    });
  }

  private <T> HitsResult<T> search(String url, TypeReference<HitsResult<T>> typeReference) {
    try {
      ResponseEntity responseEntity = restTemplate.getForEntity(url, Object.class);
      if (responseEntity.getStatusCode() == HttpStatus.OK) {
        JsonNode jsonNode = objectMapper
            .readTree(objectMapper.writeValueAsString(responseEntity.getBody()));
        JsonNode hits = jsonNode.get("hits");
        return objectMapper.readValue(hits.toString(), typeReference);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new HitsResult<>();
  }
}
