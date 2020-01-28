package com.openjudge.backend.Mapper;

import com.openjudge.backend.Domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by zy on 2020/1/20
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE USERNAME = #{usn} AND PASSWORD = #{pwd}")
    User login(@Param("usn") String username, @Param("pwd") String password);

    @Update("UPDATE USER SET TOKEN = #{token} WHERE ID = #{id}")
    boolean updateLogin(@Param("id") Integer id, @Param("token") String token);
}
