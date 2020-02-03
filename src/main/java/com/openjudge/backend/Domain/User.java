package com.openjudge.backend.Domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zy on 2020/1/20
 */

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String token;
    private Date gmtCreate;
    private Date gmtModified;

}
