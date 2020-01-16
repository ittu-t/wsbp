package com.example.wsbp.page.signed;
//認証済でなければ利用できないページ

import com.example.wsbp.MySession;
import com.example.wsbp.page.SignPage;
import com.example.wsbp.page.chat.PostChat;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage {

    public SignedPage(){
        var name = Model.of(MySession.get().getUserName());
        var userNameLabel = new Label("userName",name);
        add(userNameLabel);

        Link<Void> signoutLink = new Link<Void>("signout") {
            @Override
            public void onClick(){
                MySession.get().invalidate();
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);

        Link<Void> ChatLink = new Link<>("toChatPage") {
            @Override
            public void onClick() {
                setResponsePage(PostChat.class);
            }
        };
        add(ChatLink);
    }

}
