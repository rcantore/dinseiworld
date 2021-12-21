package com.michead.dinseiworld.ui;

import com.michead.dinseiworld.ui.views.MainView;
import com.michead.dinseiworld.ui.views.characters.CharacterListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

@PWA(
        name = "Dinsei World",
        shortName = "Dinsei",
        offlineResources = {
                "./styles/offline.css",
                "./images/offline.png"
        },
        enableInstallPrompt = false
)
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {


        H1 logo = new H1(getTranslation("app.title"));

        Anchor logout = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {
        ContextMenu contextMenu = new ContextMenu();

        RouterLink inicio = new RouterLink("Inicio", MainView.class);
        inicio.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink characters = new RouterLink("Characters", CharacterListView.class);
        characters.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                inicio,
                characters
        ));
    }


}
