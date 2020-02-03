package com.openjudge.backend.DTO;
import lombok.Data;

import java.util.List;

/**
 * Created by zy on 2020/1/28
 */

@Data
public class ProblemDetailDTO {
    private Integer pid;
    private String title;
    private String description;
    private String diff;
    private String input;
    private String output;
    private List<SampleDTO> samples;
    private String author;

}
