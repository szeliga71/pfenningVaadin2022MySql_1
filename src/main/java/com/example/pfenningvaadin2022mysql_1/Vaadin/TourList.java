package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Stopp;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
import com.example.pfenningvaadin2022mysql_1.service.LkwService;
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

import javax.annotation.security.RolesAllowed;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;


@Route(layout = MainLayout.class)
@PageTitle("Tour | Vaadin Pfenning")
@RolesAllowed("ADMIN")
public class TourList extends VerticalLayout {
    Grid<Tour> grid = new Grid<>(Tour.class);
    TextField filterText = new TextField();
    TourService tourService;
    LkwService lkwService;
    TourForm tourForm;

    public TourList(TourService tourService,LkwService lkwService) {
        this.tourService = tourService;
        this.lkwService=lkwService;
        addClassName("Tour-list");
        setSizeFull();
        configureGrid();
        configureTourForm();

        //add(getToolbar(), grid);
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, tourForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, tourForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("tour-grid");
        grid.setSizeFull();
        grid.setColumns("id", "abfahrtlager", "ankunftlager", "lkw_kenz", "rewe_kilometer","tour_kilometer", "tour_nr");

        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editTour(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by tour nr...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addTour = new Button("Add Tour");
        addTour.addClickListener(click -> addTour());
        Button deleteTour = new Button("Delete Tour");
        Button updateTour = new Button("Update Tour");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addTour,
                deleteTour, updateTour);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editTour(Tour tour) {
        if (tour == null) {
            closeEditor();
        } else {
            tourForm.setTour(tour);
            tourForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        tourForm.setTour(null);
        tourForm.setVisible(false);
        removeClassName("editing");
    }

    private void addTour() {
        grid.asSingleSelect().clear();
        editTour(new Tour());
    }

    private void updateList() {
        grid.setItems(tourService.findAllToursByTourNr(filterText.getValue()));
    }

    private void configureTourForm() {

        tourForm = new TourForm(lkwService.findAllLkw());
        tourForm.setWidth("25em");
        tourForm.addListener(TourForm.SaveEvent.class, this::saveTour);
        tourForm.addListener(TourForm.DeleteEvent.class, this::deleteTour);
        tourForm.addListener(TourForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveTour(TourForm.SaveEvent event) {
        tourService.addTour(event.getTour());
        updateList();
        closeEditor();
    }

    private void deleteTour(TourForm.DeleteEvent event) {
        tourService.deleteTour(event.getTour());
        updateList();
        closeEditor();
    }
}