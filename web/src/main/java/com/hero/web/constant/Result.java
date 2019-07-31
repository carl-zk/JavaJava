package com.hero.web.constant;

/**
 * @author carl
 */
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

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
