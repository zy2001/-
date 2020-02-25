package com.openjudge.backend.Mapper;


import com.openjudge.backend.Domain.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by zy on 2020/1/22
 */
@Mapper
public interface ProblemMapper {


    @Select("SELECT * FROM PROBLEM ORDER BY PID LIMIT #{offset}, #{size}")
    List<Problem> selectProblemItemByPage(@Param("offset") Integer i, @Param("size") Integer size);

    @Select("SELECT COUNT(*) FROM PROBLEM")
    Integer selectCount();

    @Select("SELECT * FROM PROBLEM WHERE PID = #{pid}")
    Problem selectProblemByPid(@Param("pid") Integer pid);

    @Update("UPDATE PROBLEM SET TOTAL = TOTAL + 1 WHERE PID = #{pid}")
    void updateTotalByPid(@Param("pid") Object pid);

    @Select("SELECT TIME_LIMIT as timeLimit, MEMORY_LIMIT as memoryLimit, CASE_COUNT as caseCount FROM PROBLEM WHERE PID = #{pid}")
    Map selectRunLimit(Map<String, Object> params);

    @Update("UPDATE PROBLEM SET ACCEPT = ACCEPT + 1 WHERE PID = #{pid}")
    void updateAcceptByPid(@Param("pid") Object pid);
}
