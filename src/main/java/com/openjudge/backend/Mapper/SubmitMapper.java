package com.openjudge.backend.Mapper;

import com.openjudge.backend.DTO.SubmitItemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zy on 2020/1/29
 */

@Mapper
public interface SubmitMapper {

    @Insert("INSERT INTO SUBMIT(PID, UID, CODE, LANGUAGE, STATUS, GMT_CREATED, GMT_MODIFIED) VALUES(#{pid}, #{uid}, #{code}, #{language}, #{status}, #{gmtCreated}, #{gmtModified})")
    @Options(keyColumn = "RID", keyProperty = "rid", useGeneratedKeys = true)
    int insertSubmit(Map<String, Object> params);

    @Select("SELECT RID, PID, USERNAME, LANGUAGE, STATUS, RUN_TIME, RUN_MEMORY, SUBMIT.GMT_CREATED FROM SUBMIT, USER WHERE SUBMIT.UID = USER.ID AND SUBMIT. PID = #{pid} ORDER BY RID DESC LIMIT #{offset}, #{size}")
    List<SubmitItemDTO> selectStatusByPId(@Param("pid") Integer pid, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(*) FROM SUBMIT WHERE PID = #{pid}")
    Integer selectCountByPId(@Param("pid") Integer pid);

    @Select("SELECT RID as rid, PID as pid, CODE as code, LANGUAGE as language FROM SUBMIT WHERE STATUS = 0 LIMIT 0, 1")
    Map<String, Object> selectSubmit();

    @Update("UPDATE SUBMIT SET STATUS = #{status}, RUN_TIME = #{runTime}, RUN_MEMORY = #{runMemory}, COMPILATION_MESSAGE = #{compilationMessage} WHERE RID = #{rid}")
    void updateStatusByRid(Map params);

    @Select("SELECT RID, PID, USERNAME, LANGUAGE, STATUS, RUN_TIME, RUN_MEMORY, SUBMIT.GMT_CREATED FROM SUBMIT, USER WHERE SUBMIT.UID = USER.ID ORDER BY RID DESC LIMIT #{offset}, #{size}")
    List<SubmitItemDTO> selectStatus(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(*) FROM SUBMIT")
    Integer selectCount();
}
