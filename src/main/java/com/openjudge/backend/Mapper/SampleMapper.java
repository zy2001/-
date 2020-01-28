package com.openjudge.backend.Mapper;

import com.openjudge.backend.Domain.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zy on 2020/1/28
 */

@Mapper
public interface SampleMapper {

    @Select("SELECT * FROM SAMPLE WHERE PID = #{pid}")
    List<Sample> selectSampleByPid(@Param("pid") Integer pid);
}
