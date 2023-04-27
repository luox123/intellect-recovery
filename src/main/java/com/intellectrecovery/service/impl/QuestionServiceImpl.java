package com.intellectrecovery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.intellectrecovery.domain.Question;
import com.intellectrecovery.domain.Result;
import com.intellectrecovery.mapper.QuestionMapper;
import com.intellectrecovery.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public Result getAll() {
        return Result.success("获取成功", query().list());
    }

    @Override
    public Result getQuestionByType(String type) {
        return Result.success("获取成功", query().eq("type", type).list());
    }

    @Override
    public Result getQuestionById(int id) {
        return Result.success("获取成功", query().eq("id", id).one());
    }

    @Override
    public Result removeQuestionById(int id) {
        removeById(id);
        return Result.success("删除成功");
    }

    @Override
    public Result addQuestion(Question question) {
        save(question);
        return Result.success("新增成功");
    }

    @Override
    public boolean judge(int id, String answer) {
        Question question = query().eq("id", id).one();
        return Objects.equals(answer, question.getAnswer());
    }

}
