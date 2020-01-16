package com.example.wsbp.repository;

import com.example.wsbp.page.data.Chat;

import java.util.List;

public interface IChatRepository {

    /**
     * チャット
     * ユーザー名と書き込み内容をchatテーブルに記録する
     *
     *
     * @param msgBody　書き込み内容
     * @return 　データベースの更新行数
     */
    public int insertChat(String msgBody);

    /**
     * Chatテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Chat} の {@link List} で返す
     */
    public List<Chat> find();

}
