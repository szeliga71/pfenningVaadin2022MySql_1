package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.service.ArbeitTagService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Route(layout = MainLayout.class)
@PageTitle("Arbeit Tag List | Vaadin Pfenning")
public class ArbeitTagList extends VerticalLayout {


    Grid<ArbeitTag> grid = new Grid<>(ArbeitTag.class);
    TextField filterText = new TextField();
    //DatePicker dateStart = new DatePicker("Date start", LocalDate.now());
    //DatePicker dateStop = new DatePicker("Date end",LocalDate.now());

    //TextField dateStart=new TextField("arbeitbegin");
    //TextField dateStop=new TextField("arbeitende");

    DatePicker dateStart=new DatePicker("arbeitbegin",LocalDate.now());
    DatePicker dateStop=new DatePicker("arbeitende",LocalDate.now());

    ArbeitTagService arbeitTagService;

    ArbeitTagForm arbeitTagForm;




    public ArbeitTagList(ArbeitTagService arbeitTagService) {
        this.arbeitTagService=arbeitTagService;
        addClassName("Arbeit-Tag-list");
        setSizeFull();
        configureGrid();
        configureArbeitTagForm();

        //add(getToolbar(), grid);
        add(getToolbar(), getContent());
        updateList();
        closeEditor();

    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,arbeitTagForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, arbeitTagForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }
    private void configureGrid() {
        grid.addClassNames("arbeitTag-grid");
        grid.setSizeFull();

        grid.setColumns(/*"id",*/"arbeitbegin_date","arbeitbegin_zeit","arbeitende_zeit","kilometer","kilometer_rewe","fahrerbruch","unfall","pause");
        grid.addColumn(arbeitTag -> arbeitTag.getFahrer_name().getName()).setHeader("fahrer name");


        
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editArbeitTag(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setLabel("Find");
       filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);

        dateStart.setPlaceholder("Filter by arbeitsbegin von...");
        dateStart.setClearButtonVisible(true);
       dateStop.setPlaceholder("Filter by arbeitsbegin bis...");
        dateStop.setClearButtonVisible(true);

        //dateStart.addValueChangeListener(e -> dateStop.setMin(e.getValue()));
        //dateStart.addValueChangeListener((e -> updateList()));
        //dateStop.addValueChangeListener(e -> dateStart.setMax(e.getValue()));

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
                //dateStart.setValueChangeMode(ValueChangeMode.LAZY);
                dateStart.addValueChangeListener(e -> updateList());
        //dateStop.setValueChangeMode(ValueChangeMode.LAZY);
        dateStop.addValueChangeListener(e -> updateList());



        Button addArbeitTagButton = new Button("Add Arbeit Tag");

        addArbeitTagButton.addClickListener(click -> addArbeitTag());

        Button deleteArbeitTag = new Button("Delete Arbeit Tag");
        Button updateArbeitTag = new Button("Update Arbeit Tag");

        HorizontalLayout toolbar = new HorizontalLayout(filterText,dateStart,dateStop, addArbeitTagButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
    public void editArbeitTag(ArbeitTag arbeitTag) {
        if (arbeitTag == null) {
            closeEditor();
        } else {
            arbeitTagForm.setArbeitTag(arbeitTag);
            arbeitTagForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        arbeitTagForm.setArbeitTag(null);
        arbeitTagForm.setVisible(false);
        removeClassName("editing");
    }

    private void addArbeitTag() {
        grid.asSingleSelect().clear();
        editArbeitTag(new ArbeitTag());
    }

    private void updateList() {
       //grid.setItems(arbeitTagService.fFF(filterText.getValue()));//(filterText.getValue()));

        grid.setItems(arbeitTagService.findArbeitTagesByDate(dateStart.getValue(),dateStop.getValue()));
    }

    private void configureArbeitTagForm() {
        arbeitTagForm = new ArbeitTagForm();
        arbeitTagForm.setWidth("25em");
        arbeitTagForm.addListener(ArbeitTagForm.SaveEvent.class, this::saveArbeitTag);
        arbeitTagForm.addListener(ArbeitTagForm.DeleteEvent.class, this::deleteArbeitTag);
        arbeitTagForm.addListener(ArbeitTagForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveArbeitTag(ArbeitTagForm.SaveEvent event) {
        arbeitTagService.addArbeitTag(event.getArbeitTag());
        updateList();
        closeEditor();
    }

    private void deleteArbeitTag(ArbeitTagForm.DeleteEvent event) {
        arbeitTagService.deleteArbeitTag(event.getArbeitTag());
        updateList();
        closeEditor();
    }

}
