package com.openjudge.backend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by zy on 2020/1/20
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorcode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public ResponseResult() {
        this.success = false;
        this.message = "服务器错误";
        this.data = null;
        this.errorcode = "500";
    }
}

