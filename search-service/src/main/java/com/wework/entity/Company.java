package com.wework.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * @author carl
 */
@Entity
@Indexed
public class Company implements Serializable {

  private static final long serialVersionUID = -8812609934604242632L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Field
  private String name;
  private String about;

  public Company(String name, String about) {
    this.name = name;
    this.about = about;
  }

  public Company() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", about='" + about + '\'' +
        '}';
  }
}
