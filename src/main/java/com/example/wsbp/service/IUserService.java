package com.example.wsbp.service;

import com.example.wsbp.page.data.AuthUser;

import java.util.List;

public interface IUserService {

    public void registerUser(String userName, String userPass);

    public void removeUser(String deleteUserName);

    /**
     * ユーザ名とパスワードをデータベースに照合する
     *
     * @param userName ユーザー名
     * @param userPass パスワード
     * @return 照合成功であれば<code>true</code>,照合失敗は<code>false</code>
     */
    public boolean existsUser(String userName, String userPass);

    /**
     * ユーザー名とパスワードの一覧を、AuthUser型のリストで検索する
     *
     * @return AuthUser型のListインスタンス
     */
    public List<AuthUser> findAuthUsers();

}
