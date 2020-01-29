package com.openjudge.backend.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by zy on 2020/1/29
 */

@Mapper
public interface SubmitMapper {

    @Insert("INSERT INTO SUBMIT(PID, UID, CODE, LANGUAGE, STATUS, RUN_TIME, RUN_MEMORY, GMT_CREATED, GMT_MODIFIED) VALUES(#{pid},#{uid},#{code},#{language},#{status},#{runTime},#{runMemory},#{gmtCreated},#{gmtModified})")
    boolean insertSubmit(Map<String, Object> params);
}
