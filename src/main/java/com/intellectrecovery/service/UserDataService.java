package com.intellectrecovery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.domain.UserData;

public interface UserDataService extends IService<UserData> {

    /**
     * 获取医生使用人数
     * @return 医生使用人数
     */
    Result getDoctorNum();

    /**
     * 获取患者使用人数
     * @return 患者使用人数
     */
    Result getUserNum();

    /**
     * 获取量表使用人数
     * @return 量表使用人数
     */
    Result getScaleNum();

    /**
     * 通过月份获取新增用户数（包括医生和患者）
     * @param month 月份
     * @return 新增用户数
     */
    Result getUsersByMonth(String month);

}
