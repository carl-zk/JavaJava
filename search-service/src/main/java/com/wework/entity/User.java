package com.wework.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * @author carl
 */
@Entity
@Indexed
public class User implements Serializable {

  private static final long serialVersionUID = -3193726448762509627L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Field
  private String name;
  private int age;
  @ManyToOne
  @IndexedEmbedded
  private Company company;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public User() {
  }

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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", company=" + company +
        '}';
  }
}
