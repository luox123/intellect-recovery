package com.intellectrecovery.mapper;

import com.intellectrecovery.domain.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface ComplexMapper {

    @Select("select score from score_history where u_id = ${uId}")
    Map<String, String> getHistoryScore(int uId);

    @Insert("insert into score_history (u_id, score, time) values (${uId}, ${score}, #{time})")
    void saveScore(int uId, int score, String time);

}
