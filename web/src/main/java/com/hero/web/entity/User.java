package com.hero.web.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author carl
 */
@Data
@Entity
public class User extends BaseEntity {
    @Column(length = 100)
    private String name;
    @Embedded
    private Mobile mobile;

    @ManyToOne
    private Company company;
}
