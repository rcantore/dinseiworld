package com.michead.dinseiworld.ui.views.characters;

import com.michead.dinseiworld.model.Character;
import com.michead.dinseiworld.service.CharacterService;
import com.michead.dinseiworld.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "characters", layout = MainLayout.class)
@PageTitle("Dinsei World")
public class CharacterListView extends VerticalLayout {
    private Grid<Character> grid = new Grid<>(Character.class);
    private CharacterService characterService;

    public CharacterListView(CharacterService characterService) {
        this.characterService = characterService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        populateGrid();
    }

    private void populateGrid() {
        grid.setItems(characterService.getAllCharacters());
    }

    private void configureGrid() {
        grid.addClassName("standard-grid");
        grid.setSizeFull();
        grid.setColumns("name", "age", "weight", "shortBio");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        add(grid);

    }
}
