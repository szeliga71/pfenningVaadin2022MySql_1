package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Lkw;
import com.example.pfenningvaadin2022mysql_1.model.Markt;
import com.example.pfenningvaadin2022mysql_1.service.LkwService;
import com.example.pfenningvaadin2022mysql_1.service.MarktService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(layout = MainLayout.class)
@PageTitle("Lkw | Vaadin Pfenning")
public class LkwList extends VerticalLayout {
    Grid<Lkw> grid = new Grid<>(Lkw.class);
    TextField filterText = new TextField();
    LkwService lkwService ;
    LkwForm lkwForm;

    public LkwList(LkwService lkwService) {
        this.lkwService=lkwService;
        addClassName("lkw-list");
        setSizeFull();
        configureGrid();
        configureLkwForm();

        //add(getToolbar(), grid);
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,lkwForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, lkwForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("lkw-grid");
        grid.setSizeFull();
        grid.setColumns("kenz", "nr_rewe","marke");
        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editLkw(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by kenz...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addLkw = new Button("Add Lkw");
        addLkw.addClickListener(click -> addLkw());
        Button deleteLkw = new Button("Delete Lkw");
        Button updateLkw = new Button("Update Lkw");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addLkw,
                deleteLkw, updateLkw);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    public void editLkw(Lkw lkw) {
        if (lkw == null) {
            closeEditor();
        } else {
            lkwForm.setLkw(lkw);
            lkwForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        lkwForm.setLkw(null);
        lkwForm.setVisible(false);
        removeClassName("editing");
    }

    private void addLkw() {
        grid.asSingleSelect().clear();
        editLkw(new Lkw());
    }

    private void updateList() {
        grid.setItems(lkwService.getAllLkw(filterText.getValue()));
    }

    private void configureLkwForm() {
        lkwForm = new LkwForm();
        lkwForm.setWidth("25em");
        lkwForm.addListener(LkwForm.SaveEvent.class, this::saveLkw);
        lkwForm.addListener(LkwForm.DeleteEvent.class, this::deleteLkw);
        lkwForm.addListener(LkwForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveLkw(LkwForm.SaveEvent event) {
        lkwService.addLkw(event.getLkw());
        updateList();
        closeEditor();
    }

    private void deleteLkw(LkwForm.DeleteEvent event) {
        lkwService.deleteLkw(event.getLkw());
        updateList();
        closeEditor();
    }
}
