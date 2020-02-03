package com.openjudge.backend.Domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zy on 2020/1/28
 */

@Data
public class Sample {
    private Integer pid;
    private Integer sid;
    private String input;
    private String output;
    private Date gmtCreated;
    private Date gmtModified;

}
