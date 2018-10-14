package com.wework.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author carl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyVo {

  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
