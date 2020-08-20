package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    List<ChatMessage> chatMessages;
    MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating message service bean");
        chatMessages = new ArrayList<ChatMessage>();
    }

    public void addMessage(ChatForm message){
        ChatMessage chatMessage = new ChatMessage();
        switch (message.getMessageType()){
            case "Say":
                chatMessage.setMessageText(message.getMessageText());
                break;
            case "Shout":
                chatMessage.setMessageText(message.getMessageText().toUpperCase());
                break;
            case "Whisper":
                chatMessage.setMessageText(message.getMessageText().toLowerCase());
                break;
        }
        chatMessage.setUserName(message.getUserName());
        messageMapper.addMessage(chatMessage);
    }

    public List<ChatMessage> getMessage(){
        chatMessages=messageMapper.getMessages();
        return chatMessages;
    }



}
