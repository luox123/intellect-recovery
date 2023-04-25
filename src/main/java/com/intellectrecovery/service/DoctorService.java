package com.intellectrecovery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellectrecovery.domain.Doctor;
import com.intellectrecovery.domain.Result;

public interface DoctorService extends IService<Doctor> {

    /**
     * 医生登录
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
     * 医生注册
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
     * @param tel 医生电话
     * @return 查询结果
     */
    Result getDoctorByTel(String tel);

    /**
     * 通过用户名查找
     * @param username 用户名
     * @return 查询结果
     */
    Result getDoctorByUsername(String username);

    /**
     * 修改医生信息
     * @param doctor 医生信息
     * @return 是否成功
     */
    Result updateDoctor(Doctor doctor);

    /**
     * 删除医生信息
     * @param doctor 医生信息
     * @return 是否成功
     */
    Result removeDoctor(Doctor doctor);

    /**
     * 获取所有医生信息
     * @return 所有医生信息
     */
    Result getAll();

}
