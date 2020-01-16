package com.example.wsbp.page.data;

import java.io.Serializable;

//wicketのModelに使う可能性のあるクラスはSerializableを実装する
//メモリ上のインスタンスの情報をそのままファイルに出力できるようにする
public class Chat implements Serializable {

    private String userName;
    private String msgBody;

    public Chat() {
        userName = "";
        msgBody = "";
    }

    public String getUserName() {
        return this.userName;
    }

    public String getMsgBody() {
        return this.msgBody;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
}
