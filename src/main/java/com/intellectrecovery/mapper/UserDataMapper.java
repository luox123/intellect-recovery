package com.intellectrecovery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intellectrecovery.domain.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDataMapper extends BaseMapper<UserData> {
}
