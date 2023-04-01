package com.intellectrecovery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellectrecovery.domain.User;
import com.intellectrecovery.mapper.UserMapper;
import com.intellectrecovery.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   
}
