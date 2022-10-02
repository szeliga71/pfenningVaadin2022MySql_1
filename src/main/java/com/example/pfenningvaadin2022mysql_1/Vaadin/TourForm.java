package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Lkw;
import com.example.pfenningvaadin2022mysql_1.model.Stopp;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;


public class TourForm extends FormLayout {


    private Tour tour;

    ComboBox<Lkw> lkw_kenz =new ComboBox("lkw");
    TextField id = new TextField("id");
    TimePicker abfahrtlager = new TimePicker();//("abfahrtlager");
    TimePicker ankunftlager = new TimePicker();//("ankunftlager");
    //TextField lkw_kenz = new TextField("lkw_kenz");
    TextField tour_kilometer = new TextField("tour_kilometer");
    TextField tour_nr = new TextField("tour_nr");

    //ComboBox<Status> status = new ComboBox<>("Status");
    //ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Button edit = new Button("Edit");


    Binder<Tour> binder = new BeanValidationBinder<>(Tour.class);

    public TourForm(List<Lkw>lkwList) {
        addClassName("tour-form");
        binder.bindInstanceFields(this);

        abfahrtlager.setLabel("abfahrtlager");
        abfahrtlager.setStep(Duration.ofMinutes(15));
        ankunftlager.setLabel("abfahrtlager");
        ankunftlager.setStep(Duration.ofMinutes(15));
        lkw_kenz.setItems(lkwList);
        lkw_kenz.setItemLabelGenerator(Lkw::getKenz);


        //company.setItems(companies);
        //company.setItemLabelGenerator(Company::getName);
        //status.setItems(statuses);
        //status.setItemLabelGenerator(Status::getName);

        add(id,
        abfahrtlager,
        ankunftlager,
        lkw_kenz,
        tour_kilometer,
        tour_nr,
                createButtonsLayout());
        //configureFahrerForm();
    }


    public void setTour(Tour tour) {
        this.tour = tour;
        binder.readBean(tour);
    }

    // private HorizontalLayout createButtonsLayout()
    private Component createButtonsLayout()
    {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, tour)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//edit
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close, edit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(tour);
            fireEvent(new SaveEvent(this, tour));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


       /* private void configureTourForm () {
            tourForm = new TourForm();
            setWidth("25em");

        }*/

    //==========================================

    public static abstract class TourFormEvent extends ComponentEvent<TourForm> {
        private Tour tour;

        protected TourFormEvent(TourForm source, Tour tour) {
            super(source, false);
            this.tour = tour;
        }

        public Tour getTour() {
            return tour;
        }
    }

    public static class SaveEvent extends TourFormEvent {
        SaveEvent(TourForm source, Tour tour) {
            super(source, tour);
        }
    }

    public static class DeleteEvent extends TourFormEvent {
        DeleteEvent(TourForm source, Tour tour) {
            super(source, tour);
        }

    }

    public static class CloseEvent extends TourFormEvent {
        CloseEvent(TourForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class < T > eventType,
                                                                   ComponentEventListener< T > listener){
        return getEventBus().addListener(eventType, listener);
    }
}