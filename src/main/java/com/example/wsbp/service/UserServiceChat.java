package com.example.wsbp.service;

import com.example.wsbp.page.data.Chat;
import com.example.wsbp.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceChat implements IUserServiceChat {

    private IChatRepository chatRepors;

    @Autowired
    public UserServiceChat(IChatRepository chatRepors){
        this.chatRepors = chatRepors;
    }

    @Override
    public void registerChat(String msgBody) {
        int n = chatRepors.insertChat(msgBody);
        System.out.println("記録行数:" + n);
    }

    @Override
    public List<Chat> findChat() {
        var users = chatRepors.find();
        System.out.println("データ件数:" + users.size());
        return users;
    }

}
