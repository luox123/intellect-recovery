package com.intellectrecovery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intellectrecovery.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
