package com.hero.web.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author carl
 */
@Data
@Embeddable
public class Mobile {
    @Column(length = 10)
    private String countryCode;
    @Column(length = 50)
    private String number;
}
