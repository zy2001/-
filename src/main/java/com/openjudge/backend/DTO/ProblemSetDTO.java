package com.openjudge.backend.DTO;

import java.util.List;

/**
 * Created by zy on 2020/1/22
 */

public class ProblemSetDTO {

    private List<ProblemItemDTO> itemList;
    private Integer total;
    private Integer size;


    public List<ProblemItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProblemItemDTO> itemList) {
        this.itemList = itemList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
