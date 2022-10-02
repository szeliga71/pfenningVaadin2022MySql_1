package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Markt;
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

import javax.persistence.JoinTable;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;


public class StoppForm extends FormLayout {


    private Stopp stopp;

    TextField id = new TextField("id");
    TimePicker ankunftstopp = new TimePicker("ankunftstopp");
    TimePicker abfahrtstopp = new TimePicker("abfahrtstopp");
    TextField leergut = new TextField("leergut");
    //TextField marktId = new TextField("marktId");
    ComboBox<Markt> marktId =new ComboBox("markt id");

    ComboBox<Tour> tourId =new ComboBox("tour id");
    TextField rollcontainer = new TextField("rollcontainer");
    TextField pallette = new TextField("pallette");
    TextField dd = new TextField("dd");
    TextField tk_box = new TextField("tk box");
    TextField tk_schmall = new TextField("tk schmall");
    TextField m1 = new TextField("m1");
    TextField m2 = new TextField("m2");
    TextField m4 = new TextField("m4");
    TextField blumengross = new TextField("blumengross");

    //ComboBox<Status> status = new ComboBox<>("Status");
    //ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Button edit = new Button("Edit");


    Binder<Stopp> binder = new BeanValidationBinder<>(Stopp.class);

    public StoppForm(List<Markt> marktList,List<Tour>tourList) {
        addClassName("stopp-form");
        binder.bindInstanceFields(this);

       // addClassName("arbeitTag-form");
        //binder.bindInstanceFields(this);


        marktId.setItems(marktList);
        marktId.setItemLabelGenerator(Markt::getId);


        tourId.setItems(tourList);
        tourId.setItemLabelGenerator(Tour::getTour_nr);

        ankunftstopp.setLabel("ankunft zeit");
        ankunftstopp.setStep(Duration.ofMinutes(15));

        abfahrtstopp.setLabel("abfahrt zeit");
        abfahrtstopp.setStep(Duration.ofMinutes(15));


        //company.setItems(companies);
        //company.setItemLabelGenerator(Company::getName);
        //status.setItems(statuses);
        //status.setItemLabelGenerator(Status::getName);

        add(id,
                ankunftstopp,
                abfahrtstopp,
                leergut,
                marktId,
                tourId,
                rollcontainer,
                pallette,
                dd,
                tk_box,
                tk_schmall,
                m1,
                m2,
                m4,
                blumengross,
                createButtonsLayout());
        //configureFahrerForm();
    }


    public void setStopp(Stopp stopp) {
        this.stopp = stopp;
        binder.readBean(stopp);
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, stopp)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//edit
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close, edit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(stopp);
            fireEvent(new SaveEvent(this, stopp));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


        /*private void configureFahrerForm () {
            fahrerForm = new FahrerForm();
            setWidth("25em");

        }*/

    //==========================================

    public static abstract class StoppFormEvent extends ComponentEvent<StoppForm> {
        private Stopp stopp;

        protected StoppFormEvent(StoppForm source, Stopp stopp) {
            super(source, false);
            this.stopp = stopp;
        }

        public Stopp getStopp() {
            return stopp;
        }
    }

    public static class SaveEvent extends StoppFormEvent {
        SaveEvent(StoppForm source, Stopp stopp) {
            super(source, stopp);
        }
    }

    public static class DeleteEvent extends StoppFormEvent {
        DeleteEvent(StoppForm source, Stopp stopp) {
            super(source, stopp);
        }

    }

    public static class CloseEvent extends StoppFormEvent {
        CloseEvent(StoppForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class < T > eventType,
                                                                   ComponentEventListener< T > listener){
        return getEventBus().addListener(eventType, listener);
    }
}