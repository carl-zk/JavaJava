package com.hero.web.domain.vo;

import com.hero.web.support.jackson.LocalDateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * @author carl
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    @NotNull(groups = ValidationId.class)
    private Long id;
    @NotBlank(groups = ValidationName.class)
    private String name;
    @LocalDateTimeFormat
    private Instant lastLoginAt;

    public interface ValidationId {
    }

    public interface ValidationName {
    }
}
