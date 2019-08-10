package com.carl.web.domain.vo;

import com.carl.web.support.jackson.LocalDateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    @NotNull(groups = WhenUpdate.class)
    @Null(groups = WhenCreate.class)
    private Long id;
    private String uuid;
    @NotBlank(groups = {WhenUpdate.class, WhenCreate.class})
    private String name;
    @LocalDateTimeFormat
    private Instant lastLoginAt;

    public interface WhenUpdate {
    }

    public interface WhenCreate {
    }
}
