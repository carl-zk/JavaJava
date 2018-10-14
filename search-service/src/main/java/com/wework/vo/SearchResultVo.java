package com.wework.vo;

import java.util.List;

/**
 * @author carl
 */
public class SearchResultVo {

  private long totalUser;
  private List<UserVo> users;
  private long totalCompany;
  private List<CompanyVo> companies;

  public long getTotalUser() {
    return totalUser;
  }

  public void setTotalUser(long totalUser) {
    this.totalUser = totalUser;
  }

  public List<UserVo> getUsers() {
    return users;
  }

  public void setUsers(List<UserVo> users) {
    this.users = users;
  }

  public long getTotalCompany() {
    return totalCompany;
  }

  public void setTotalCompany(long totalCompany) {
    this.totalCompany = totalCompany;
  }

  public List<CompanyVo> getCompanies() {
    return companies;
  }

  public void setCompanies(List<CompanyVo> companies) {
    this.companies = companies;
  }
}
