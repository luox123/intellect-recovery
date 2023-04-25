package com.intellectrecovery.controller;

import com.intellectrecovery.domain.Question;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.service.QuestionService;
import com.intellectrecovery.service.UserDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.intellectrecovery.constant.CacheKey.TOKEN_CACHE;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    UserDataService userDataService;

    @Resource
    QuestionService questionService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Value("${admin.username}")
    String adminUsername;
    @Value("${admin.password}")
    String adminPassword;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> msg) {
        String username = msg.get("username");
        String password = msg.get("password");
        if(Objects.equals(adminUsername, username) && Objects.equals(adminPassword, password)) {
            String code = DigestUtils.md5DigestAsHex(username.getBytes());
            stringRedisTemplate.opsForValue().set(TOKEN_CACHE + username, code, 2, TimeUnit.HOURS);
            return Result.success("登录成功", code);
        } else {
            return Result.success("登陆失败");
        }
    }

    @GetMapping("/getDoctorNum")
    public Result getDoctorNum(@RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return userDataService.getDoctorNum();
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @GetMapping("/getUserNum")
    public Result getUserNum(@RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return userDataService.getUserNum();
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @GetMapping("/getScaleNum")
    public Result getScaleNum(@RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return userDataService.getScaleNum();
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }


    @GetMapping("/getUsersAdd/{month}")
    public Result getUsersByMonth(@PathVariable("month") String month, @RequestHeader("token") String token) {
        // 传入的month为月份字符串：其中四月为 04， 十月为 10
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return userDataService.getUsersByMonth(month);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @GetMapping("/getQuestion")
    public Result getAllQuestion(@RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return questionService.getAll();
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @GetMapping("/getQuestion/{type}")
    public Result getQuestionByType(@PathVariable String type, @RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return questionService.getQuestionByType(type);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @GetMapping("/getQuestionById/{id}")
    public Result getQuestionById(@PathVariable int id, @RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return questionService.getQuestionById(id);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public Result removeQuestionById(@PathVariable int id, @RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return questionService.removeQuestionById(id);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody Question question, @RequestHeader("token") String token) {
        String mode = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + adminUsername);
        if(Objects.equals(mode, token)) {
            return questionService.addQuestion(question);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

}
