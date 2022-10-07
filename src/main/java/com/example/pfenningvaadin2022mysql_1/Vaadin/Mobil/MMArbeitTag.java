package com.example.pfenningvaadin2022mysql_1.Vaadin.Mobil;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Markt;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.example.pfenningvaadin2022mysql_1.service.ArbeitTagService;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.lang.Nullable;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Route(value="mmarbeittag",layout = MMainLayout.class)
@PageTitle("MM Arbeit Tag | Vaadin Pfenning")
@RolesAllowed("USER")
public class MMArbeitTag extends VerticalLayout {

FahrerService fahrerService;
ArbeitTagService arbeitTagService;
ArbeitTag arbeitTag;
    Binder<ArbeitTag> binder = new Binder<>(ArbeitTag.class);


    NativeButton toTour = new NativeButton(
            "ADD TOUR");

    Button save = new Button("Save");
    Button update = new Button("Update");
    Button close = new Button("Cancel");



    public MMArbeitTag(FahrerService fahrerService,ArbeitTagService arbeitTagService){
        this.fahrerService=fahrerService;
        this.arbeitTagService=arbeitTagService;

        setSizeFull();
        setMargin(true);
        setSpacing(true);
        FormLayout formLayout=new FormLayout();

        //addClassName("mm-arbeitTag");
        //setWidth("25em");


//arbeit tag id long


        ComboBox<Fahrer> fahrer_name=new ComboBox<>();
        fahrer_name.setItems(fahrerService.getAllFahrer());
        fahrer_name.setSizeFull();
        formLayout.addFormItem(fahrer_name,"fahrer name");
        binder.forField(fahrer_name).bind(ArbeitTag::getFahrer_name,ArbeitTag::setFahrer_name);

        DatePicker arbeitbegin_date = new DatePicker();
        arbeitbegin_date.setSizeFull();
        formLayout.addFormItem(arbeitbegin_date,"arbeitbegin");
        binder.forField(arbeitbegin_date).bind(ArbeitTag::getArbeitbegin_date,ArbeitTag::setArbeitbegin_date);

        TimePicker arbeitbegin_zeit = new TimePicker();
        arbeitbegin_zeit.setStep(Duration.ofMinutes(15));
        arbeitbegin_zeit.setSizeFull();
        formLayout.addFormItem(arbeitbegin_zeit,"begin zeit");
        binder.forField(arbeitbegin_zeit).bind(ArbeitTag::getArbeitbegin_zeit,ArbeitTag::setArbeitbegin_zeit);


        TimePicker arbeitende_zeit = new TimePicker();
        arbeitende_zeit.setStep(Duration.ofMinutes(15));
        arbeitende_zeit.setSizeFull();
        formLayout.addFormItem(arbeitende_zeit,"ende zeit");
        binder.forField(arbeitende_zeit).bind(ArbeitTag::getArbeitende_zeit,ArbeitTag::setArbeitende_zeit);


        IntegerField kilometer = new IntegerField();
        kilometer.setSizeFull();
        formLayout.addFormItem(kilometer,"kilometer");
        binder.forField(kilometer).bind(ArbeitTag::getKilometer,ArbeitTag::setKilometer);


        ComboBox<String> fahrerbruch = new ComboBox<>("","ja","nein");
        fahrerbruch.setSizeFull();
        formLayout.addFormItem(fahrerbruch,"fahrerbruch");
        binder.forField(fahrerbruch).bind(ArbeitTag::getFahrerbruch,ArbeitTag::setFahrerbruch);

        ComboBox<String> unfall = new ComboBox<>("","ja","nein");
        unfall.setSizeFull();
        formLayout.addFormItem(unfall,"unfall");
        binder.forField(unfall).bind(ArbeitTag::getUnfall,ArbeitTag::setUnfall);

        ComboBox<String> pause = new ComboBox<>("","15","45","60","90");
        pause.setSizeFull();
        formLayout.addFormItem(pause,"pause");
        binder.forField(pause).bind(ArbeitTag::getPause,ArbeitTag::setPause);

        //private List<Tour> touren;

        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1));


        add(formLayout,createButtonsLayout());

        //binder.bindInstanceFields(this);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        //addTour.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        toTour.addClickListener(e ->
                toTour.getUI().ifPresent(ui ->
                        ui.navigate("mmtour")));

        //testing
    update.addClickListener(click->{try{
    ArbeitTag arbeitTagBean1=new ArbeitTag();
    binder.writeBean(arbeitTagBean1);

    System.out.println(arbeitTagBean1);
    System.out.println(arbeitTagBean1.getFahrer_name());
    System.out.println(arbeitTagBean1.getArbeitbegin_date());
        System.out.println(arbeitTagBean1.getId());}
    //System.out.println(arbeitTagBean1.getArbeitbegin_zeit());
    //System.out.println(arbeitTagBean1.getArbeitende_zeit());
    //System.out.println(arbeitTagBean1.getKilometer());}
    catch(ValidationException e){}});

        save.addClickListener(click->
        {
            try{
                ArbeitTag arbeitTagBean=new ArbeitTag();
                binder.writeBean(arbeitTagBean);
                arbeitTagService.addArbeitTag(arbeitTagBean);
            }
            catch(ValidationException e){}});

        //addTour.addClickListener(click ->)

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, update, close,toTour);
    }
    public void setArbeitTag(ArbeitTag arbeitTag) {
        this.arbeitTag = arbeitTag;
        binder.readBean(arbeitTag);
    }
}
