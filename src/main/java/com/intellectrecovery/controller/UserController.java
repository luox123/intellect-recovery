package com.intellectrecovery.controller;

import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return null;
    }

    @DeleteMapping("/exit")
    public Result exit(@RequestBody String username) {
        return userService.exit(username);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return null;
    }

    @GetMapping("/getUser/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        return null;
    }

    @PutMapping("/changePassword")
    public Result changePassword() {
        return null;
    }

}
