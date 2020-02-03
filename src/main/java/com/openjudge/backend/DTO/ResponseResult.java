package com.openjudge.backend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by zy on 2020/1/20
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorcode;

    public ResponseResult() {
        this.success = false;
        this.message = "服务器错误";
        this.data = null;
        this.errorcode = "500";
    }
}

