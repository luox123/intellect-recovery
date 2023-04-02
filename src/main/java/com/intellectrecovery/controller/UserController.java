package com.intellectrecovery.controller;

import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Objects;

import static com.intellectrecovery.constant.CacheKey.TOKEN_CACHE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @DeleteMapping("/exit")
    public Result exit(@RequestBody String username) {
        return userService.exit(username);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword());
    }

    @GetMapping("/getUser/{username}")
    public Result getUserByUsername(@PathVariable String username, @RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + username);
        if(Objects.equals(mode, token)) {
            return userService.getUserByUsername(username);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody HashMap<String, String> input, @RequestHeader("token") String token) {
        String username = input.get("username");
        String password = input.get("password");
        String newPassword = input.get("newPassword");
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + username);
        if(Objects.equals(mode, token)) {
            return userService.changePassword(username, password, newPassword);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

}
