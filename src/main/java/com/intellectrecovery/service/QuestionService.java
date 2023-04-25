package com.intellectrecovery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.intellectrecovery.domain.Question;
import com.intellectrecovery.domain.Result;

public interface QuestionService extends IService<Question> {

    /**
     * 获取所有题目信息
     * @return 所有题目信息
     */
    public Result getAll();

    /**
     * 通过量表类型获取题目信息
     * @param type 量表类型
     * @return 题目信息
     */
    public Result getQuestionByType(String type);

    /**
     * 通过id获取题目信息
     * @param id 题目id
     * @return 题目信息
     */
    public Result getQuestionById(int id);

    /**
     * 通过id删除题目
     * @param id 题目id
     * @return 是否成功
     */
    public Result removeQuestionById(int id);


    /**
     * 新增题目
     * @param question 题目信息
     * @return 是否成功
     */
    public Result addQuestion(Question question);

}
