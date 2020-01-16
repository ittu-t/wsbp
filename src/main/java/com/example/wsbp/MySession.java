package com.example.wsbp;
//承認結果を一時保存しておくクラスの作成

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import java.util.Objects;

//ブラウザに異なる情報を一時保存できるセッション
public class MySession  extends AbstractAuthenticatedWebSession {

    //承認の際に照合したユーザ名
    private String userName;

    public MySession(Request request){
        //コンストラクタ。初期状態は認証NG(userName = null)
        super(request);
        this.userName = null;
    }

    public void sign(String userName){
        //承認したユーザを変更する
        //認証が成功したら、照合できた
        replaceSession();
        this.userName = userName;
    }

    @Override
    public Roles getRoles(){
        //承認結果OK(userName = ユーザ名)だった場合は、だれもが"USER"権限を持っているとして返す。
        //承認結果NG(userName = null)だった場合は、権限なしとして返す
        if(isSignedIn()){
            return new Roles(Roles.USER);
        }
        return new Roles();
    }

    @Override
    public boolean isSignedIn(){
        //承認結果OK(userName = ユーザ名)だった場合は、trueを返す
        //承認結果NG(userName = null)だった場合は、falseを返す
        return Objects.nonNull(this.userName);
    }

    public String getUserName(){
        //ユーザ名を返す
        return this.userName;
    }

    public static MySession get() {
        //サーバの中からSessionを取り出す
        return (MySession) Session.get();
    }
}
