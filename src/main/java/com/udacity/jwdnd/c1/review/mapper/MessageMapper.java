package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    @Select("SELECT * FROM MESSAGES;")
    List<ChatMessage> getMessages();

    @Insert("INSERT INTO MESSAGES (userName,messageText) VALUES (#{userName},#{messageText});")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int addMessage(ChatMessage message);


}
