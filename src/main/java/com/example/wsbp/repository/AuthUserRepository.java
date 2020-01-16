package com.example.wsbp.repository;
//テーブルにユーザー情報を記録するページ(SpringのRepositoryクラスを利用する)
//データベースなどの、アプリケーション外のデータ元（データ・ソース）を使うときに、Springでは Repository というクラスを作成する。

import com.example.wsbp.page.data.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

@Repository
public class AuthUserRepository implements IAuthUserRepository{

    //SpringJDBCのデータベース用制御インスタンス(JdbcTemplete)
    //データベースを操作する様々な機能がメソッドとして提供される。
    //finalは変更できない
    private final JdbcTemplate jdbc;

    //jdbcのdi/ioc設定(Wicketとやり方が異なるので注意)
    //@Autowiread がついたコンストラクタは、 jdbc をIoC/DIする。
    //WebPageにSpringのインスタンスを IoC/DI するときと、Spring から Spring のインスタンスをIoC/DIするときで、書き方が変わる
    @Autowired
    public AuthUserRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName, String userPass){
        //auth_userテーブルに?と?の2つのデータを入れることを指示するコマンド
        var sql = "insert into auth_user values (?,?)";
        //jdbc.update... で、sql を実行するときに、
        // 最初の ? を userName 引数の値に、次の ? を userPass 引数の値に置換して実行している。
        var n = jdbc.update(sql, userName, userPass);
        return n;
    }

    @Override
    public int delete(String deleteUserName){
        var sql = "delete from auth_user where user_name = ?";
        var n = jdbc.update(sql,deleteUserName);
        return n;
    }

    @Override
    public boolean exists(String userName, String userPass){
        //ユーザ名とパスワードが一致する情報がauth_userテーブルにあれば、trueを返す
        //デーブルになければ、何も返さない
        var sql = "select true from auth_user "
                + "where user_name = ? and user_pass = ?";

        //検索用のSQLを実行する方法。検索結果をList（可変長配列)で変えす。
        var booles = jdbc.query(sql, new SingleColumnRowMapper(Boolean.class), new Object[]{userName, userPass});

        //Listにデータがある(=trueの要素のものがある):照合成功
        //Listにデータがない(要素が何もない):照合失敗
        return !booles.isEmpty();
    }

    @Override
    public List<AuthUser> find() {
        //auth_userテーブルのuser_name, user_pass を検索する
        String sql = "select user_name, user_pass from auth_user";

        //検索用のSQLを実行する方法
        //取り出したいデータの方によって、第2引数のRowMapperを切り替える。
        //?を使うSQLであれば、第3引数のObject型配列の要素に順番に設定する
        List<AuthUser> users = jdbc.query(sql, new BeanPropertyRowMapper<>(AuthUser.class), new Object[]{});

        //取り出したデータ(Listの要素)をそのまま返値とする
        return users;
    }

}
