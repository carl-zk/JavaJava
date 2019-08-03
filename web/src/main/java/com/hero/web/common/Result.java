package com.hero.web.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carl
 */
@Getter
@Setter
@SuppressWarnings("unchecked")
public class Result<T> {
    private int code;
    private T data;
    private String message;

    public static Result success() {
        return new Result(true);
    }

    public static <T> Result success(T data) {
        return new Result(data);
    }

    public static Result error(int code, String message) {
        return new Result(code, message);
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
