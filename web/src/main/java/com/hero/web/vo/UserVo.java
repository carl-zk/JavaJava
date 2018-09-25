package com.hero.web.vo;

import com.hero.web.support.jackson.LocalDateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author carl
 */
public class UserVo {

  private long id;
  private String name;

  @LocalDateTimeFormat
  private LocalDateTime lastLoginAt;

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

  public LocalDateTime getLastLoginAt() {
    return lastLoginAt;
  }

  public void setLastLoginAt(LocalDateTime lastLoginAt) {
    this.lastLoginAt = lastLoginAt;
  }
}
