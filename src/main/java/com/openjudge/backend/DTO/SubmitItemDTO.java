package com.openjudge.backend.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Created by zy on 2020/2/3
 */

@Data
public class SubmitItemDTO {
    private Integer rid;
//    private Integer pid;
//    private Integer uid;
    private String username;
    private String language;
    private Integer status;
    private Integer runTime;
    private Integer runMemory;
    private Date gmtCreated;

}
