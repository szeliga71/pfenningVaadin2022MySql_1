package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Stopp;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
import com.example.pfenningvaadin2022mysql_1.service.MarktService;
import com.example.pfenningvaadin2022mysql_1.service.StoppService;
import com.example.pfenningvaadin2022mysql_1.service.TourService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.persistence.JoinTable;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;



    @Route(layout = MainLayout.class)
    @PageTitle("Stopp | Vaadin Pfenning")
    public class StoppList extends VerticalLayout {
        Grid<Stopp> grid = new Grid<>(Stopp.class);
        TextField filterText = new TextField();
        StoppService stoppService;

        MarktService marktService;

        TourService tourService;
        StoppForm stoppForm;

        public StoppList(StoppService stoppService,MarktService marktService,TourService tourService) {
            this.stoppService = stoppService;
            this.marktService=marktService;
            this.tourService=tourService;
            addClassName("Stopp-list");
            setSizeFull();
            configureGrid();
            configureStoppForm();

            //add(getToolbar(), grid);
            add(getToolbar(), getContent());
            updateList();
            closeEditor();
        }

        private Component getContent() {
            HorizontalLayout content = new HorizontalLayout(grid,stoppForm);
            content.setFlexGrow(2, grid);
            content.setFlexGrow(1, stoppForm);
            content.addClassNames("content");
            content.setSizeFull();
            return content;
        }

        private void configureGrid() {
            grid.addClassNames("stopp-grid");
            grid.setSizeFull();
            grid.setColumns("id","ankunftstopp","abfahrtstopp", "leergut","marktId","tourId","rollcontainer","pallette","dd","tk_box","tk_schmall","m1","m2","m4","blumengross");


            //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
            //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
            grid.getColumns().forEach(col -> col.setAutoWidth(true));

            grid.asSingleSelect().addValueChangeListener(event ->
                    editStopp(event.getValue()));
        }

        private HorizontalLayout getToolbar() {
            filterText.setPlaceholder("Filter by id markt...");
            filterText.setClearButtonVisible(true);
            filterText.setValueChangeMode(ValueChangeMode.LAZY);
            filterText.addValueChangeListener(e -> updateList());

            Button addStopp = new Button("Add Stopp");
            addStopp.addClickListener(click -> addStopp());
            Button deleteStopp = new Button("Delete Stopp");
            Button updateStopp = new Button("Update Stopp");

            HorizontalLayout toolbar = new HorizontalLayout(filterText, addStopp,
                    deleteStopp, updateStopp);
            toolbar.addClassName("toolbar");
            return toolbar;
        }
        public void editStopp(Stopp stopp) {
            if (stopp == null) {
                closeEditor();
            } else {
                stoppForm.setStopp(stopp);
                stoppForm.setVisible(true);
                addClassName("editing");
            }
        }

        private void closeEditor() {
            stoppForm.setStopp(null);
            stoppForm.setVisible(false);
            removeClassName("editing");
        }

        private void addStopp() {
            grid.asSingleSelect().clear();
            editStopp(new Stopp());
        }

        private void updateList() {
            grid.setItems(stoppService.getAllStopps(filterText.getValue()));
        }

        private void configureStoppForm() {
            stoppForm = new StoppForm(marktService.getAllMarkts(),tourService.getAllTours());
            stoppForm.setWidth("25em");
            stoppForm.addListener(StoppForm.SaveEvent.class, this::saveStopp);
            stoppForm.addListener(StoppForm.DeleteEvent.class, this::deleteStopp);
            stoppForm.addListener(StoppForm.CloseEvent.class, e -> closeEditor());
        }

        private void saveStopp(StoppForm.SaveEvent event) {
            stoppService.addStopp(event.getStopp());
            updateList();
            closeEditor();
        }

        private void deleteStopp(StoppForm.DeleteEvent event) {
            stoppService.deleteStopp(event.getStopp());
            updateList();
            closeEditor();
        }
    }

