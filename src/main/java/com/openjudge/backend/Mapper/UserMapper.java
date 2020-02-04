package com.openjudge.backend.Mapper;

import com.openjudge.backend.Domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * Created by zy on 2020/1/20
 */
@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM USER WHERE USERNAME = #{username}")
    int usernameIsUsed(@Param("username") String username);


    @Select("SELECT * FROM USER WHERE USERNAME = #{usn} AND PASSWORD = #{pwd}")
    User login(@Param("usn") String username, @Param("pwd") String password);

    @Update("UPDATE USER SET TOKEN = #{token} WHERE ID = #{id}")
    boolean updateLogin(@Param("id") Integer id, @Param("token") String token);

    @Insert("INSERT INTO USER (USERNAME, PASSWORD, GMT_CREATED, GMT_MODIFIED) VALUES (#{username}, #{password}, #{gmtCreated}, #{gmtModified})")
    int insertUser(Map<String, Object> params);
}
