package com.carl.web.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author carl
 */
@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"version", "createdAt", "updatedAt", "deleted"})
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -4754786072382361976L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt = Instant.now();
    @LastModifiedDate
    private Instant updatedAt;
    private Boolean deleted;

    @PreUpdate
    @PrePersist
    public void updateFields() {
        updateTimestamps();
    }

    private void updateTimestamps() {
        updatedAt = Instant.now();
        if (createdAt == null) {
            createdAt = updatedAt;
        }
    }
}
