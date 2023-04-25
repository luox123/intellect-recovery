package com.intellectrecovery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.UserData;
import com.intellectrecovery.mapper.UserDataMapper;
import com.intellectrecovery.service.UserDataService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements UserDataService {

    @Override
    public Result getDoctorNum() {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.select("sum(doctor_num) as num");
        Map<String, Object> map = getMap(wrapper);
        return Result.success("获取成功", map.get("num"));
    }

    @Override
    public Result getUserNum() {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.select("sum(user_num) as num");
        Map<String, Object> map = getMap(wrapper);
        return Result.success("获取成功", map.get("num"));
    }

    @Override
    public Result getScaleNum() {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.select("sum(scale_num) as num");
        Map<String, Object> map = getMap(wrapper);
        return Result.success("获取成功", map.get("num"));
    }

    @Override
    public Result getUsersByMonth(String month) {
        UserData monthData = query().eq("month", month).one();
        return Result.success("获取成功", monthData.getUserNum() + monthData.getDoctorNum());
    }

}
