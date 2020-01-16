package com.example.wsbp.repository;

import com.example.wsbp.page.data.AuthUser;

import java.util.List;

public interface IAuthUserRepository {

    /**
     * ユーザ名とパスワードAuthUserテーブルに記録する
     *
     * @param userName ユーザ名
     * @param userPass パスワード
     * @return データベースの更新行数
     */
    public int insert(String userName, String userPass);

    /**
     * ユーザ名をAuthUserテーブルから削除する
     *
     * @param deleteUserName　ユーザ名
     * @return データベースの更新行数
     */
    public int delete(String deleteUserName);

    /**
     * ユーザ名とパスワードが一致するレコードがAuthUserデーブルにあるか検索する
     *
     * @param userName　ユーザ名
     * @param userPass パスワード
     * @return レコードの有無、存在すれば<code>true</code>, それ以外は<code>false</code>
     */
    public boolean exists(String userName, String userPass);

    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @retrun レコードの内容を {@link AuthUser}　の　{@link List}　で返す
     */
    public List<AuthUser> find();

}
