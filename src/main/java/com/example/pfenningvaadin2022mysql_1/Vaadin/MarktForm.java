package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Markt;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;



public class MarktForm extends FormLayout {


    private Markt markt;

    TextField id = new TextField("id");
    TextField adres = new TextField("adres");


    //ComboBox<Status> status = new ComboBox<>("Status");
    //ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Button edit = new Button("Edit");


    Binder<Markt> binder = new BeanValidationBinder<>(Markt.class);

    public MarktForm() {
        addClassName("markt-form");
        binder.bindInstanceFields(this);


        //company.setItems(companies);
        //company.setItemLabelGenerator(Company::getName);
        //status.setItems(statuses);
        //status.setItemLabelGenerator(Status::getName);

        add(id,
                adres,
                createButtonsLayout());
        //configureFahrerForm();
    }


    public void setMarkt(Markt markt) {
        this.markt = markt;
        binder.readBean(markt);
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, markt)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//edit
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close, edit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(markt);
            fireEvent(new SaveEvent(this, markt));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


        /*private void configureFahrerForm () {
            fahrerForm = new FahrerForm();
            setWidth("25em");

        }*/

    //==========================================

    public static abstract class MarktFormEvent extends ComponentEvent<MarktForm> {
        private Markt markt;

        protected MarktFormEvent(MarktForm source, Markt markt) {
            super(source, false);
            this.markt = markt;
        }

        public Markt getMarkt() {
            return markt;
        }
    }

    public static class SaveEvent extends MarktFormEvent {
        SaveEvent(MarktForm source, Markt markt) {
            super(source, markt);
        }
    }

    public static class DeleteEvent extends MarktFormEvent {
        DeleteEvent(MarktForm source, Markt markt) {
            super(source, markt);
        }

    }

    public static class CloseEvent extends MarktFormEvent {
        CloseEvent(MarktForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class < T > eventType,
                                                                   ComponentEventListener< T > listener){
        return getEventBus().addListener(eventType, listener);
    }
}