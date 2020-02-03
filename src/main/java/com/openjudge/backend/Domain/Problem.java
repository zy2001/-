package com.openjudge.backend.Domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by zy on 2020/1/22
 */

@Data
public class Problem {
    private Integer pid;
    private String title;
    private String description;
    private String diff;
    private String input;
    private String output;
    private String author;
    private Integer accept;
    private Integer total;
    private Date gmtCreated;
    private Date gmtModified;

}
