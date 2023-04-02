package com.intellectrecovery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellectrecovery.domain.Doctor;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.mapper.DoctorMapper;
import com.intellectrecovery.service.DoctorService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

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
        Doctor doctor = query().eq("username", username).one();
        if(doctor == null) {
            doctor = new Doctor();
            doctor.setUsername(username);
            doctor.setPassword(password);
            save(doctor);
            return Result.success("注册成功");
        } else {
         return Result.fail("用户名已存在");
        }
    }

    @Override
    public Result changePassword(String username, String password, String newPassword) {
        Doctor doctor = query().eq("username", username).one();
        if(Objects.equals(doctor.getPassword(), password)) {
            doctor.setPassword(newPassword);
            updateById(doctor);
            return Result.success("修改成功");
        } else {
            return Result.fail("原密码错误");
        }
    }

    @Override
    public Result getDoctorByTel(String tel) {
        Doctor doctor = query().eq("tel", tel).one();
        if(doctor == null) {
            return Result.fail("未找到该用户");
        } else {
            return Result.success("查找成功", doctor);
        }
    }

    @Override
    public Result getDoctorByUsername(String username) {
        Doctor doctor = query().eq("username", username).one();
        if(doctor == null) {
            return Result.fail("未找到该用户");
        } else {
            return Result.success("查找成功", doctor);
        }
    }
}
