package com.hero.web.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author carl
 */
@Data
@Embeddable
public class Address {
    @Column(length = 100)
    private String building;
    @Column(length = 100)
    private String street;
    @Column(length = 50)
    private String city;
    @Column(length = 50)
    private String state;
    @Column(length = 50)
    private String country;
}
