package com.intellectrecovery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.User;

public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    Result login(String username, String password);

    /**
     * 退出登录
     * @param username 用户名
     * @return 结果
     */
    Result exit(String username);

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 注册结果
     */
    Result register(String username, String password);

    /**
     * 修改密码
     * @param username 用户名
     * @param password 密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    Result changePassword(String username, String password, String newPassword);

    /**
     * 通过电话号码查找
     * @param tel 电话号码
     * @return 查询结果
     */
    Result getUserByTel(String tel);

    /**
     * 通过用户名查找
     * @param username 用户名
     * @return 查询结果
     */
    Result getUserByUsername(String username);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 是否成功
     */
    Result updateUser(User user);

    /**
     * 删除用户信息
     * @param user 用户信息
     * @return 是否成功
     */
    Result removeUser(User user);

    /**
     * 获取所有用户信息
     * @return 所有用户信息
     */
    Result getAll();

}
