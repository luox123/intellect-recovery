package com.intellectrecovery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intellectrecovery.domain.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
}
