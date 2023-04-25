package com.intellectrecovery.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String month;
    private int doctorNum;
    private int userNum;
    private int scaleNum;

}
