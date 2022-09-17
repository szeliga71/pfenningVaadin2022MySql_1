package com.example.pfenningvaadin2022mysql_1.Vaadin;


import com.example.pfenningvaadin2022mysql_1.model.Lkw;
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

public class LkwForm extends FormLayout {


    private Lkw lkw;

    TextField kenz = new TextField("kenz");
    TextField nr_rewe = new TextField("rewe_nr");
    TextField marke = new TextField("marke");

    //ComboBox<Status> status = new ComboBox<>("Status");
    //ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Button edit = new Button("Edit");


    Binder<Lkw> binder = new BeanValidationBinder<>(Lkw.class);

    public LkwForm() {
        addClassName("lkw-form");
        binder.bindInstanceFields(this);


        //company.setItems(companies);
        //company.setItemLabelGenerator(Company::getName);
        //status.setItems(statuses);
        //status.setItemLabelGenerator(Status::getName);

        add(kenz,
                nr_rewe,
                marke,
                createButtonsLayout());

    }


    public void setLkw(Lkw lkw) {
        this.lkw=lkw;
        binder.readBean(lkw);
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, lkw)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//edit
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close, edit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(lkw);
            fireEvent(new SaveEvent(this, lkw));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }



    //==========================================

    public static abstract class LkwFormEvent extends ComponentEvent<LkwForm> {
        private Lkw lkw;

        protected LkwFormEvent(LkwForm source, Lkw lkw) {
            super(source, false);
            this.lkw=lkw;
        }

        public Lkw getLkw() {
            return lkw;
        }
    }

    public static class SaveEvent extends LkwFormEvent {
        SaveEvent(LkwForm source, Lkw lkw) {
            super(source, lkw);
        }
    }

    public static class DeleteEvent extends LkwFormEvent {
        DeleteEvent(LkwForm source, Lkw lkw) {
            super(source, lkw);
        }

    }

    public static class CloseEvent extends LkwFormEvent {
        CloseEvent(LkwForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class < T > eventType,
                                                                   ComponentEventListener< T > listener){
        return getEventBus().addListener(eventType, listener);
    }
}
