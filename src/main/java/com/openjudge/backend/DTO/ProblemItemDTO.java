package com.openjudge.backend.DTO;

import lombok.Data;

/**
 * Created by zy on 2020/1/22
 */

@Data
public class ProblemItemDTO {

    private Integer pid;
    private String title;
    private String diff;
    private Integer accept;
    private Integer total;

}
