package com.intellectrecovery.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 题干
     */
    private String questionStem;
    /**
     * 题目描述
     */
    private String message;
    /**
     * 所属量表
     */
    private String type;
    /**
     * 分值
     */
    private Integer score;

}
