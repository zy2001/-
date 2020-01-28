package com.openjudge.backend.DTO;
import java.util.List;

/**
 * Created by zy on 2020/1/28
 */

public class ProblemDetailDTO {
    private Integer pid;
    private String title;
    private String description;
    private String diff;
    private String input;
    private String output;
    private List<SampleDTO> samples;
    private String author;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public List<SampleDTO> getSamples() {
        return samples;
    }

    public void setSamples(List<SampleDTO> samples) {
        this.samples = samples;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
