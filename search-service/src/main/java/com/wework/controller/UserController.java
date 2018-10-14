package com.wework.controller;

import com.wework.entity.Company;
import com.wework.entity.User;
import com.wework.repository.CompanyRepository;
import com.wework.repository.UserRepository;
import com.wework.service.ESService;
import com.wework.vo.SearchResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  CompanyRepository companyRepository;
  @Autowired
  ESService esService;

  @PostMapping("/user")
  public void saveUser(String name, int age) {
    userRepository.save(new User(name, age));
  }

  @PostMapping("/company")
  public void saveCompany(String name, String about) {
    companyRepository.save(new Company(name, about));
  }

  @GetMapping("/search")
  public SearchResultVo search(String keyword) {
    return esService.search(keyword);
  }

  @PostMapping("/refresh")
  public void reCreate() {
    esService.reCreateIndies();
  }

}
