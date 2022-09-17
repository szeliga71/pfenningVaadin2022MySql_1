package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
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


public class TourForm extends FormLayout {


   /* private Tour tour;

    TextField id_pf = new TextField("Personalnummer");
    TextField id_rewe = new TextField("Rewe Nummer");
    TextField name = new TextField("Name");
    TextField vorname = new TextField("Vorname");
    TextField sprache = new TextField("Sprache");

    //ComboBox<Status> status = new ComboBox<>("Status");
    //ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Button edit = new Button("Edit");


    Binder<Fahrer> binder = new BeanValidationBinder<>(Fahrer.class);

    public FahrerForm() {
        addClassName("fahrer-form");
        binder.bindInstanceFields(this);


        //company.setItems(companies);
        //company.setItemLabelGenerator(Company::getName);
        //status.setItems(statuses);
        //status.setItemLabelGenerator(Status::getName);

        add(id_pf,
                id_rewe,
                name,
                vorname,
                sprache,
                createButtonsLayout());
        //configureFahrerForm();
    }


    public void setFahrer(Fahrer fahrer) {
        this.fahrer = fahrer;
        binder.readBean(fahrer);
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, fahrer)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//edit
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close, edit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(fahrer);
            fireEvent(new SaveEvent(this, fahrer));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


        /*private void configureFahrerForm () {
            fahrerForm = new FahrerForm();
            setWidth("25em");

        }

    //==========================================

    public static abstract class FahrerFormEvent extends ComponentEvent<FahrerForm> {
        private Fahrer fahrer;

        protected FahrerFormEvent(FahrerForm source, Fahrer fahrer) {
            super(source, false);
            this.fahrer = fahrer;
        }

        public Fahrer getFahrer() {
            return fahrer;
        }
    }

    public static class SaveEvent extends FahrerFormEvent {
        SaveEvent(FahrerForm source, Fahrer fahrer) {
            super(source, fahrer);
        }
    }

    public static class DeleteEvent extends FahrerFormEvent {
        DeleteEvent(FahrerForm source, Fahrer fahrer) {
            super(source, fahrer);
        }

    }

    public static class CloseEvent extends FahrerFormEvent {
        CloseEvent(FahrerForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class < T > eventType,
                                                                   ComponentEventListener< T > listener){
        return getEventBus().addListener(eventType, listener);
    }*/
}