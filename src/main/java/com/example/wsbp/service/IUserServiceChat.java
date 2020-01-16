package com.example.wsbp.service;

import com.example.wsbp.page.data.Chat;

import java.util.List;

public interface IUserServiceChat {

    public void registerChat(String msgBody);

    /**
     * ユーザー名と投稿文の一覧を、Chat型のリストで検索する
     *
     * @return Chat型の Listインスタンス
     */
    public List<Chat> findChat();

}
