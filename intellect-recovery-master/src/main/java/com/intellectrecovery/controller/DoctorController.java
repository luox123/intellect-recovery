package com.intellectrecovery.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intellectrecovery.domain.Doctor;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    private DoctorService doctorService;

    @PostMapping("/add")
    public Boolean add(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @GetMapping("/delete/{id}")
    public Boolean delete(@PathVariable int id) {
        return doctorService.removeById(id);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody Doctor doctor) {
        return doctorService.updateById(doctor);
    }

    @GetMapping("/getById/{id}")
    public Doctor getById(@PathVariable int id) {
        return doctorService.getById(id);
    }
    @GetMapping("/getByTel/{tel}")
    public Doctor getByTel(@PathVariable String tel){
        QueryWrapper<Doctor> qw=new QueryWrapper<>();
        qw.eq("tel",tel);
        return doctorService.getOne(qw);
    }
    @GetMapping("/list")
    public List<Doctor> list() {
        return doctorService.list();
    }
}
