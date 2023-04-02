package com.intellectrecovery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.mapper.UserMapper;
import com.intellectrecovery.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(String username, String password) {
        return null;
    }

    @Override
    public Result exit(String username) {
        return null;
    }

    @Override
    public Result register(String username, String password) {
        User user = query().eq("username", username).one();
        if(user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            save(user);
            return Result.success("注册成功");
        } else {
            return Result.fail("用户名已存在");
        }
    }

    @Override
    public Result changePassword(String username, String password, String newPassword) {
        User user = query().eq("username", username).one();
        if(Objects.equals(user.getPassword(), password)) {
            user.setPassword(newPassword);
            updateById(user);
            return Result.success("修改成功");
        } else {
            return Result.fail("原密码错误");
        }
    }

    @Override
    public Result getUserByTel(String tel) {
        User user = query().eq("tel", tel).one();
        if(user == null) {
            return Result.fail("未找到该用户");
        } else {
            return Result.success("查找成功", user);
        }
    }

    @Override
    public Result getUserByUsername(String username) {
        User user = query().eq("username", username).one();
        if(user == null) {
            return Result.fail("未找到该用户");
        } else {
            return Result.success("查找成功", user);
        }
    }
}
