package com.carl.web.common;

/**
 * @author carl
 */
public class ServiceException extends RuntimeException {
    private int errorCode;

    private ServiceException() {
    }

    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
