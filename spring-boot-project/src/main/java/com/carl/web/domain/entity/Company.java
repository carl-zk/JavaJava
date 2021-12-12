package com.carl.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author carl
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company extends BaseEntity {
    private String name;
    @Embedded
    private Address address;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany
    private List<User> employees;
}
