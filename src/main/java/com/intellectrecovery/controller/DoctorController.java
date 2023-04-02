package com.intellectrecovery.controller;

import com.intellectrecovery.domain.Doctor;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.service.DoctorService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    DoctorService doctorService;

    @PostMapping("/login")
    public Result login(@RequestBody Doctor doctor) {
        return null;
    }

    @DeleteMapping("/exit")
    public Result exit(@RequestBody String username) {
        return null;
    }

    @PostMapping("/register")
    public Result register(@RequestBody Doctor doctor) {
        return null;
    }

    @GetMapping("/getUser/{username}")
    public Result getDoctorByUsername(@PathVariable String username) {
        return null;
    }

    @PutMapping("/changePassword")
    public Result changePassword() {
        return null;
    }

}
