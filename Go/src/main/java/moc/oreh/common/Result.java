package moc.oreh.common;

public class Result<T> {
    private Boolean success;
    private T result;
    private Integer code;
    private String msg;

    public static <R> Result<R> success(R result) {
        return new Result<>(result);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(String msg) {
        return Result.error(-1, msg);
    }

    private Result(T result) {
        this.success = true;
        this.result = result;
    }

    private Result(Integer code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
