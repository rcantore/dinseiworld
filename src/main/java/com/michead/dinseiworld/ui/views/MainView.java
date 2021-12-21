package com.michead.dinseiworld.ui.views;

import com.michead.dinseiworld.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Dinsei World")
public class MainView extends VerticalLayout {

    public MainView() {
    }
}
