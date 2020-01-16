package com.example.wsbp.repository;

import com.example.wsbp.MySession;
import com.example.wsbp.page.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //チャット機能のrepository
    @Override
    public int insertChat(String msgBody){
        var sql = "insert into chat values (?, ?)";
        var n = jdbc.update(sql, MySession.get().getUserName(), msgBody);
        return n;
    }

    @Override
    public List<Chat> find() {
        //Chatテーブルの user_name, msg_body を検索する
        String sql = "select user_name, msg_body from chat";

        List<Chat> users = jdbc.query(sql,
                new BeanPropertyRowMapper<>(Chat.class),
                new Object[]{});

        return users;
    }

}
