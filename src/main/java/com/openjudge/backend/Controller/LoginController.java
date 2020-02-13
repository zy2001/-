package com.openjudge.backend.Controller;

import com.openjudge.backend.DTO.ResponseResult;
import com.openjudge.backend.DTO.UserDTO;
import com.openjudge.backend.Domain.User;
import com.openjudge.backend.Mapper.UserMapper;
import com.openjudge.backend.Config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by zy on 2020/1/20
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    @PostMapping("/checkUsernameUsed")
    public ResponseResult checkUsernameUsed(@RequestParam("username") String username) {
        ResponseResult result = new ResponseResult();
        try {
            if (0 == userMapper.usernameIsUsed(username)) {
                result.setSuccess(true);
                result.setMessage("用户名可用");
                result.setErrorcode("200");
            } else {
                result.setSuccess(false);
                result.setMessage("用户名已被使用");
                result.setErrorcode("801");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestParam Map<String, Object> params) {
        ResponseResult result = new ResponseResult();
        try {
            String pattern = "^[0-9a-zA-Z]{6,15}$";
            if (null == params.get("username") || !Pattern.matches(pattern, params.get("username").toString())) {
                result.setErrorcode("802");
                result.setMessage("用户名不符合约束");
                return result;
            } else if (userMapper.usernameIsUsed(params.get("username").toString()) > 0) {
                result.setErrorcode("802");
                result.setMessage("用户名已被使用");
                return result;
            }
            if (null != params.get("password") && (params.get("password").toString().length() < 6 || params.get("password").toString().length() > 15)) {
                result.setErrorcode("803");
                result.setMessage("密码不符合约束");
                return result;
            }
            Date date = new Date(System.currentTimeMillis());
            params.put("gmtCreated", date);
            params.put("gmtModified", date);
            int cnt = userMapper.insertUser(params);
            if (cnt == 1) {
                result.setSuccess(true);
                result.setErrorcode("200");
                result.setMessage("注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
