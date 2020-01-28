package com.openjudge.backend.Controller;

import com.openjudge.backend.DTO.ResponseResult;
import com.openjudge.backend.DTO.UserDTO;
import com.openjudge.backend.Domain.User;
import com.openjudge.backend.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by zy on 2020/1/20
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        User user = userMapper.login(username, password);
        ResponseResult result = new ResponseResult();
        if (user == null) {
            result.setData(null);
            result.setErrorcode("401");
            result.setMessage("用户名或密码错误");
            result.setSuccess(false);
            return result;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        String token = UUID.randomUUID().toString();
        userDTO.setToken(token);
        boolean flag = userMapper.updateLogin(user.getId(), token);

        if (!flag) {
            result.setData(null);
            result.setErrorcode("400");
            result.setMessage("服务器错误");
            result.setSuccess(false);
            return result;
        }

        result.setData(userDTO);
        result.setSuccess(true);
        result.setMessage("登录成功");
        result.setErrorcode("200");
        return result;
    }

}
