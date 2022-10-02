package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;


public class ArbeitTagForm extends FormLayout {


    private ArbeitTag arbeitTag;

    //TextField id = new TextField("id");

    ComboBox<Fahrer> fahrer_name =new ComboBox("fahrer_name");
    //TextField fahrer_id = new TextField("fahrer id");


    DatePicker arbeitbegin_date = new DatePicker("arbeitbegin");
    TimePicker arbeitbegin_zeit = new TimePicker();
    TimePicker arbeitende_zeit = new TimePicker();

    //TextField arbeitbegin_zeit = new TextField("arbeitbegin");
    //TextField arbeitende_zeit = new TextField("arbeitende");
    TextField kilometer = new TextField("kilometer");

    TextField kilometer_rewe = new TextField(" kilometer rewe");
    Select<String> fahrerbruch = new Select<>();//("fahrerbruch");
    Select<String> unfall = new Select<>();//("unfall");
    Select<String> pause = new Select<>();//("pause");

    //ComboBox fahrer_name =new ComboBox("fahrer_name");

    //ComboBox<Status> status = new ComboBox<>("Status");
    //ComboBox<Company> company = new ComboBox<>("Company");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Button edit = new Button("Edit");


   Binder<ArbeitTag> binder = new BeanValidationBinder<>(ArbeitTag.class);

    public ArbeitTagForm(List<Fahrer>fahrerList) {
        addClassName("arbeitTag-form");
        binder.bindInstanceFields(this);


        fahrer_name.setItems(fahrerList);
        fahrer_name.setItemLabelGenerator(Fahrer::getName);

        arbeitbegin_zeit.setLabel("arbeitbeginn zeit");
        arbeitbegin_zeit.setStep(Duration.ofMinutes(15));

        arbeitende_zeit.setLabel("arbeitende zeit");
        arbeitende_zeit.setStep(Duration.ofMinutes(15));
        kilometer.setPlaceholder("kilometer");

        kilometer_rewe.setPlaceholder("kilometer rewe");
        fahrerbruch.setLabel("fahrerbruch");
        fahrerbruch.setItems("nein","ja");
        fahrerbruch.setPlaceholder("fahrerbruch");
        unfall.setLabel("unfall");
        unfall.setItems("nein","ja");
        unfall.setPlaceholder("unfall");
        pause.setLabel("pause");
        pause.setItems("0","15","45","60","90");
        pause.setPlaceholder("pause");

        //company.setItemLabelGenerator(Company::getName);
        //status.setItems(statuses);
        //status.setItemLabelGenerator(Status::getName);


        add(fahrer_name,
                arbeitbegin_date,
                arbeitbegin_zeit,
                arbeitende_zeit,
                kilometer,
                kilometer_rewe,
                fahrerbruch,
                unfall,
                pause,
                createButtonsLayout());

    }


    public void setArbeitTag(ArbeitTag arbeitTag) {
        this.arbeitTag = arbeitTag;
       binder.readBean(arbeitTag);
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, arbeitTag)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//edit
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close, edit);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(arbeitTag);
            fireEvent(new SaveEvent(this, arbeitTag));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }



    //==========================================

    public static abstract class ArbeitTagFormEvent extends ComponentEvent<ArbeitTagForm> {
        private ArbeitTag arbeitTag;

        protected ArbeitTagFormEvent(ArbeitTagForm source, ArbeitTag arbeitTag) {
            super(source, false);
            this.arbeitTag=arbeitTag;
        }

        public ArbeitTag getArbeitTag() {
            return arbeitTag;
        }
    }

    public static class SaveEvent extends ArbeitTagFormEvent {
        SaveEvent(ArbeitTagForm source, ArbeitTag arbeitTag) {
            super(source, arbeitTag);
        }
    }

    public static class DeleteEvent extends ArbeitTagFormEvent {
        DeleteEvent(ArbeitTagForm source, ArbeitTag arbeitTag) {
            super(source, arbeitTag);
        }

    }

    public static class CloseEvent extends ArbeitTagFormEvent {
        CloseEvent(ArbeitTagForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener (Class < T > eventType,
                                                                   ComponentEventListener< T > listener){
        return getEventBus().addListener(eventType, listener);
    }
}