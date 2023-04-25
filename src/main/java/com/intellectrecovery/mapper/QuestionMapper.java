package com.intellectrecovery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.intellectrecovery.domain.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
