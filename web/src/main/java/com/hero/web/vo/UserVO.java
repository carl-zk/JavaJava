package com.hero.web.vo;

import com.hero.web.support.jackson.LocalDateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author carl
 */
public class UserVO {

    @NotNull(groups = ValidationId.class)
    private Long id;
    @NotBlank(groups = ValidationName.class)
    private String name;

    @LocalDateTimeFormat
    private LocalDateTime lastLoginAt;

    public UserVO() {
    }

    public UserVO(Long id, String name, LocalDateTime lastLoginAt) {
        this.id = id;
        this.name = name;
        this.lastLoginAt = lastLoginAt;
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

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public interface ValidationId {
    }

    public interface ValidationName {
    }
}
