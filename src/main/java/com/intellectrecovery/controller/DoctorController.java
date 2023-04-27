package com.intellectrecovery.controller;

import com.intellectrecovery.domain.Doctor;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.service.DoctorService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

import static com.intellectrecovery.constant.CacheKey.TOKEN_CACHE;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    DoctorService doctorService;

    @PostMapping("/login")
    public Result login(@RequestBody Doctor doctor) {
        return doctorService.login(doctor.getUsername(), doctor.getPassword());
    }

    @DeleteMapping("/exit")
    public Result exit(@RequestBody String username) {
        return doctorService.exit(username);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Doctor doctor) {
        return doctorService.register(doctor.getUsername(), doctor.getPassword());
    }

    @GetMapping("/getUser")
    public Result getDoctorByUsername(@RequestHeader("token") String token) {
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            return doctorService.getDoctorByUsername(username);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @PutMapping("/changePassword")
    public Result changePassword(@RequestBody HashMap<String, String> input, @RequestHeader("token") String token) {
        String usernameInput = input.get("username");
        String password = input.get("password");
        String newPassword = input.get("newPassword");
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            return doctorService.changePassword(username, password, newPassword);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

    @PatchMapping("/update")
    public Result updateDoctor(@RequestBody Doctor doctor, @RequestHeader("token") String token) {
        String username = stringRedisTemplate.opsForValue().get(TOKEN_CACHE + token);
        if(username != null) {
            return doctorService.updateDoctor(doctor);
        } else {
            return new Result(403, "鉴权失败", null);
        }
    }

}
