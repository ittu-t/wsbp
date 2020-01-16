package com.example.wsbp.page;
//ユーザ認証を行うクラス

import com.example.wsbp.MySession;
import com.example.wsbp.page.data.AuthUser;
import com.example.wsbp.page.signed.SignedPage;
import com.example.wsbp.service.IUserService;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Objects;

@WicketSignInPage
@MountPath("Sign")
public class SignPage extends WebPage {

    @SpringBean
    private IUserService service;

    @SpringBean
    private IUserService userService;

    public SignPage(){

        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        var userInfoForm = new Form<>("userInfo"){
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var userPass = userPassModel.getObject();

                if(service.existsUser(userName, userPass)) {
                    MySession.get().sign(userName);
                }
                setResponsePage(new SignedPage());
            }
        };
        add(userInfoForm);

        var userNameField = new TextField<>("userName", userNameModel) {
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(StringValidator.lengthBetween(8,32));
            }
        };
        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass", userPassModel) {
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(StringValidator.lengthBetween(8,32));
            }
        };
        userInfoForm.add(userPassField);

        //Serviceからデータベースのユーザー一覧をもらい、Modelにする
        //List型のモデルはModel.ofList(...)で作成する。
        var authUsersModel = Model.ofList(userService.findAuthUsers());

        //List型のモデルを表示する ListView
        var usersLV = new ListView<>("users", authUsersModel) {
            @Override
            protected void populateItem(ListItem<AuthUser> listItem) {
                //List型のモデルから、 <li>...</li>ひとつに分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                AuthUser authUser = itemModel.getObject(); //元々のListのn番目の要素

                //インスタンスに入れ込まれたデータベースの検索結果を、列(=フィールド変数)ごとにとりだして表示する
                //addする先がListItemになることに注意
                var userNameModel = Model.of(authUser.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var userPassModel = Model.of(authUser.getUserPass());
                Label userPassLabel = new Label("userPass", userPassModel);
                listItem.add(userPassLabel);
            }
        };
        add(usersLV);

    }

}
