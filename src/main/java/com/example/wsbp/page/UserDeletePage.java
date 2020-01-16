package com.example.wsbp.page;
//ユーザーを削除するページ

import com.example.wsbp.service.IUserService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UserDelete")
public class UserDeletePage extends WebPage {

    @SpringBean
    private IUserService userService;

    public UserDeletePage(){
        var userDeleteNameModel = Model.of("");

        var toHomeLink = new BookmarkablePageLink<>("toHome",HomePage.class);
        add(toHomeLink);

        var userDeleteInfoForm = new Form<>("userDeleteInfo"){
            @Override
            protected void onSubmit(){
                var userDeleteName = userDeleteNameModel.getObject();
                var msg = "送信データ:" + userDeleteName;
                System.out.println(msg);

                userService.removeUser(userDeleteName);
                setResponsePage(new UserDeleteCompPage(userDeleteNameModel));
            }
        };
        add(userDeleteInfoForm);

        var userNameField = new TextField<>("userDeleteName", userDeleteNameModel);
        userDeleteInfoForm.add(userNameField);
    }
}
