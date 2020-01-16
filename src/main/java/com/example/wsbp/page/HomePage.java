package com.example.wsbp.page;
//最初に表示するページ

import com.example.wsbp.service.ISampleService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.link.Link;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    /**
     * Springを使うために、WicketではSpring用のインターフェイスを
     * フィールド変数としてDIする
     * ISampleServiceを実装しているSampleServiceが自動インスタンス化される
     */
    @SpringBean
    private ISampleService service;

    public HomePage(){
        var youModel = Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you", youModel);
        add(youLabel);

        var gakusekiModel = Model.of("b2181740");
        var gakusekiLabel = new Label("gakuseki",gakusekiModel);
        add(gakusekiLabel);

        var nameModel = Model.of("冨樫逸希");
        var nameLabel = new Label("name",nameModel);
        add(nameLabel);

        var timeModel = Model.of(service.makeCurrentHMS());
        var timeLabel = new Label("time", timeModel);
        add(timeLabel);

        var randModel = Model.of(service.makeRandInt());
        var randLabel = new Label("rand",randModel);
        add(randLabel);

        var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
        add(toUserMakerLink);

        var toUserDeleteLink = new BookmarkablePageLink<>("toUserDelete",UserDeletePage.class);
        add(toUserDeleteLink);

        var toSignPageLink = new BookmarkablePageLink<>("toSignPage", SignPage.class);
        add(toSignPageLink);
    }

}
