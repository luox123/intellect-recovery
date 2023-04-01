package com.intellectrecovery.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Boolean add(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/delete/{id}")
    public Boolean delete(@PathVariable int id) {
        return userService.removeById(id);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("/getByTel/{tel}")
    public User getByTel(@PathVariable String tel) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("tel", tel);
        return userService.getOne(qw);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }
}
