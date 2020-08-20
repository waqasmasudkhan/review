package com.udacity.jwdnd.c1.review.controller;


import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {
    MessageService messageService;
    public ChatController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    public String getChat(ChatForm chatForm, Model model,Authentication authentication){
        model.addAttribute("user",authentication.getName().toString());
        model.addAttribute("chatHistory","Chat History is displayed here");
        return "chat";
    }

    @PostMapping("/chat")
    public String postChat(ChatForm chatForm, Model model, Authentication auth){
        chatForm.setUserName(auth.getName().toString());
        this.messageService.addMessage(chatForm);
        chatForm.setMessageText("");
        model.addAttribute("chatHistory", this.messageService.getMessage());
        return "chat";
    }

}
