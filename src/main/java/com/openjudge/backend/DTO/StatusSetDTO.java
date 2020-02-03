package com.openjudge.backend.DTO;

import lombok.Data;

import java.util.List;

/**
 * Created by zy on 2020/2/3
 */

@Data
public class StatusSetDTO {
    private List<SubmitItemDTO> itemList;
    private Integer total;
    private Integer size;

}
