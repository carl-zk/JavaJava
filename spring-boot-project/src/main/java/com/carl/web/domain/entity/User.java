package com.carl.web.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

/**
 * @author carl
 */
@EntityListeners(AuditingEntityListener.class)
@Audited
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity {
    @Column(length = 100)
    private String uuid;
    @Column(length = 100)
    private String name;
    @Embedded
    private Mobile mobile;

    @NotAudited
    @ManyToOne
    private Company company;

    @LastModifiedBy
    private String lastModifiedBy;
}
