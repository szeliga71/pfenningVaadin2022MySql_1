package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Markt;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
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
@PageTitle("Markt | Vaadin Pfenning")
public class MarktList extends VerticalLayout {
    Grid<Markt> grid = new Grid<>(Markt.class);
    TextField filterText = new TextField();
    MarktService marktService ;
    MarktForm marktForm;

    public MarktList(MarktService marktService) {
        this.marktService = marktService;
        addClassName("markt-list");
        setSizeFull();
        configureGrid();
        configureMarktForm();

        //add(getToolbar(), grid);
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,marktForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, marktForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("markt-grid");
        grid.setSizeFull();
        grid.setColumns("id", "adres");
        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editMarkt(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by id markt...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addMarkt = new Button("Add Markt");
        addMarkt.addClickListener(click -> addMarkt());
        Button deleteMarkt = new Button("Delete Markt");
        Button updateMarkt = new Button("Update Markt");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addMarkt,
                deleteMarkt, updateMarkt);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    public void editMarkt(Markt markt) {
        if (markt == null) {
            closeEditor();
        } else {
            marktForm.setMarkt(markt);
            marktForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        marktForm.setMarkt(null);
        marktForm.setVisible(false);
        removeClassName("editing");
    }

    private void addMarkt() {
        grid.asSingleSelect().clear();
        editMarkt(new Markt());
    }

    private void updateList() {
        grid.setItems(marktService.getAllMarkt(filterText.getValue()));
    }

    private void configureMarktForm() {
        marktForm = new MarktForm();
        marktForm.setWidth("25em");
        marktForm.addListener(MarktForm.SaveEvent.class, this::saveMarkt);
        marktForm.addListener(MarktForm.DeleteEvent.class, this::deleteMarkt);
        marktForm.addListener(MarktForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveMarkt(MarktForm.SaveEvent event) {
        marktService.addMarkt(event.getMarkt());
        updateList();
        closeEditor();
    }

    private void deleteMarkt(MarktForm.DeleteEvent event) {
        marktService.deleteMarkt(event.getMarkt());
        updateList();
        closeEditor();
    }
}
