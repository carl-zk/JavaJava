package com.hero.web.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author carl
 */
@Entity
public class Company extends BaseEntity {
    private String name;
    @Embedded
    private Address address;

    @OneToMany
    private List<User> employees;
}
