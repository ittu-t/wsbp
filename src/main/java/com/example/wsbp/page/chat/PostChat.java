package com.example.wsbp.page.chat;

import com.example.wsbp.MySession;
import com.example.wsbp.page.data.Chat;
import com.example.wsbp.page.signed.SignedPage;
import com.example.wsbp.service.IUserService;
import com.example.wsbp.service.IUserServiceChat;
import com.example.wsbp.service.UserServiceChat;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("postChat")
public class PostChat extends WebPage {

    @SpringBean
    private IUserServiceChat userServiceChat;

    public PostChat(){
        var postModel = Model.of("");

        var userPostForm = new Form<>("post") {
            @Override
            protected void onSubmit() {
                var postContent = postModel.getObject();

                var msg = "送信データ:"
                        + MySession.get().getUserName() + ","
                        + postContent;

                System.out.println(msg);
                userServiceChat.registerChat(postContent);
                setResponsePage(PostChat.class);
            }
        };
        add(userPostForm);

        var userPostField = new TextField<>("userPost", postModel);
        userPostForm.add(userPostField);

        var toSignedPageLink = new BookmarkablePageLink<>("toSignedPage", SignedPage.class);

        //Link<Void> toSignedPageLink = new Link<>("toSignedPage") {
           //@Override
            //public void onClick() {
           //     setResponsePage(SignedPage.class);
            //}
        //};
        add(toSignedPageLink);

        //Serviceからデータベースのユーザー一覧、Modelにする。
        //List型のモデルは Model.ofList(...)で作成する。
        var chatModel = Model.ofList(userServiceChat.findChat());

        //List型のモデルはを表示する
        var usersLV = new ListView<>("users", chatModel) {
            @Override
            protected void populateItem(ListItem<Chat> listItem) {
                //List型のモデルから、　<li>...<li>ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                //元々のListのn番目の要素
                var chat = itemModel.getObject();

                //インスタンスに入れ込まれたデータベースの検索結果を、列ごとに取り出して表示する
                var userNameModel = Model.of(chat.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var msgBodyModel = Model.of(chat.getMsgBody());
                var msgBodyLabel = new Label("msgBody", msgBodyModel);
                listItem.add(msgBodyLabel);
            }
        };
        add(usersLV);

    }

}
