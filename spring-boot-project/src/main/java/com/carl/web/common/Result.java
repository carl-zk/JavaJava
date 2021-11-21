package com.carl.web.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author carl
 */
@Getter
@Setter
@ToString
@SuppressWarnings("unchecked")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1481686592037868939L;

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
