package com.openjudge.backend.Mapper;

import com.openjudge.backend.DTO.SubmitItemDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by zy on 2020/1/29
 */

@Mapper
public interface SubmitMapper {

    @Insert("INSERT INTO SUBMIT(PID, UID, CODE, LANGUAGE, STATUS, GMT_CREATED, GMT_MODIFIED) VALUES(#{pid}, #{uid}, #{code}, #{language}, #{status}, #{gmtCreated}, #{gmtModified})")
    boolean insertSubmit(Map<String, Object> params);

    @Select("SELECT RID, USERNAME, LANGUAGE, STATUS, RUN_TIME, RUN_MEMORY, SUBMIT.GMT_CREATED FROM SUBMIT, USER WHERE SUBMIT.UID = USER.ID AND SUBMIT. PID = #{pid} ORDER BY RID DESC LIMIT #{offset}, #{size}")
    List<SubmitItemDTO> selectStatusByPId(@Param("pid") Integer pid, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(*) FROM SUBMIT WHERE PID = #{pid}")
    Integer selectCountByPId(@Param("pid") Integer pid);
}
