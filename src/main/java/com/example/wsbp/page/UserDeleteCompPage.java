package com.example.wsbp.page;
//ユーザーが削除されたときに表示するページ

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UserDeleteComp")
public class UserDeleteCompPage extends WebPage {

    public UserDeleteCompPage(IModel<String> DeleteUserNameModel){

        var deleteUserNameLabel = new Label("deleteUserName", DeleteUserNameModel);
        add(deleteUserNameLabel);

        var toHomeLink = new BookmarkablePageLink<>("toHome",HomePage.class);
        add(toHomeLink);
    }

}
