package com.example.pfenningvaadin2022mysql_1.Vaadin.Mobil;

import com.example.pfenningvaadin2022mysql_1.model.*;
import com.example.pfenningvaadin2022mysql_1.service.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.time.Duration;


@Route(layout = MMainLayout.class)
@PageTitle("MM Stopp | Vaadin Pfenning")
@RolesAllowed("USER")
public class MMStopp extends VerticalLayout {

    MarktService marktService;
    StoppService stoppService;

    TourService tourService;
    Stopp stopp;
    Binder<Stopp> binder = new Binder<>(Stopp.class);

    Button save = new Button("Save");
    //Button update = new Button("Update");
    Button close = new Button("Cancel");
    Button addTour = new Button("Add Tour");


    public MMStopp(StoppService stoppService,MarktService marktService,TourService tourService){
        this.stoppService=stoppService;
        this.marktService=marktService;
        this.tourService=tourService;

        setSizeFull();
        setMargin(true);
        setSpacing(true);
        FormLayout formLayout=new FormLayout();

        addClassName("mm-stopp");
        //setWidth("25em");



        TimePicker ankunftstopp = new TimePicker();
        ankunftstopp.setStep(Duration.ofMinutes(15));
        ankunftstopp.setSizeFull();
        formLayout.addFormItem(ankunftstopp,"begin stopp");
        binder.forField(ankunftstopp).bind(Stopp::getAnkunftstopp,Stopp::setAnkunftstopp);


        TimePicker abfahrtstopp = new TimePicker();
        abfahrtstopp.setStep(Duration.ofMinutes(15));
        abfahrtstopp.setSizeFull();
        formLayout.addFormItem(abfahrtstopp,"ende zeit");
        binder.forField(abfahrtstopp).bind(Stopp::getAbfahrtstopp,Stopp::setAbfahrtstopp);


        ComboBox<Markt> marktId=new ComboBox<>();
        marktId.setItems(marktService.getAllMarkts());
        marktId.setSizeFull();
        formLayout.addFormItem(marktId,"markt id");
        binder.forField(marktId).bind(Stopp::getMarktId,Stopp::setMarktId);

        ComboBox<Tour> tourId=new ComboBox<>();
        tourId.setItems(tourService.getAllTours());
        tourId.setSizeFull();
        formLayout.addFormItem(tourId,"tour id");
        binder.forField(tourId).bind(Stopp::getTourId,Stopp::setTourId);

        ComboBox<String> leergut = new ComboBox<>("","ja","nein");
        leergut.setSizeFull();
        formLayout.addFormItem(leergut,"leergut");
        binder.forField(leergut).bind(Stopp::getLeergut,Stopp::setLeergut);

        IntegerField rollcontainer = new IntegerField();
        rollcontainer.setValue(0);
        rollcontainer.setSizeFull();
        formLayout.addFormItem(rollcontainer ,"rollcontainer ");
        binder.forField(rollcontainer ).bind(Stopp::getRollcontainer,Stopp::setRollcontainer);

        IntegerField dd = new IntegerField();
        dd.setValue(0);
        dd.setSizeFull();
        formLayout.addFormItem(dd ,"dd");
        binder.forField(dd).bind(Stopp::getDd,Stopp::setDd);

        IntegerField tkBox = new IntegerField();
        tkBox.setValue(0);
        tkBox.setSizeFull();
        formLayout.addFormItem(tkBox ,"tkBox");
        binder.forField(tkBox).bind(Stopp::getTk_box,Stopp::setTk_box);

        IntegerField tkSchmall = new IntegerField();
        tkSchmall.setValue(0);
        tkSchmall.setSizeFull();
        formLayout.addFormItem(tkSchmall ,"tkSchmall");
        binder.forField(tkSchmall).bind(Stopp::getTk_schmall,Stopp::setTk_schmall);

        IntegerField m1 = new IntegerField();
        m1.setValue(0);
        m1.setSizeFull();
        formLayout.addFormItem(m1 ,"m1");
        binder.forField(m1).bind(Stopp::getM1,Stopp::setM1);

        IntegerField m2 = new IntegerField();
        m2.setValue(0);
        m2.setSizeFull();
        formLayout.addFormItem(m2 ,"m2");
        binder.forField(m2).bind(Stopp::getM2,Stopp::setM2);

        IntegerField m4 = new IntegerField();
        m4.setValue(0);
        m4.setSizeFull();
        formLayout.addFormItem(m4 ,"m4");
        binder.forField(m4).bind(Stopp::getM4,Stopp::setM4);

        IntegerField blumengross = new IntegerField();
        blumengross.setValue(0);
        blumengross.setSizeFull();
        formLayout.addFormItem(blumengross ,"blumengross");
        binder.forField(blumengross).bind(Stopp::getBlumengross,Stopp::setBlumengross);




        //private List<Tour> touren;

        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1));


        add(formLayout,createButtonsLayout());


    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //update.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);


        //update.addClickListener(click->{try{
            //Stopp stoppBean1=new Stopp();
            //binder.writeBean(stoppBean1);

            //System.out.println(stoppBean1);
            //System.out.println(stoppBean1.getAnkunftstopp());
            //System.out.println(stoppBean1.getAbfahrtstopp());
            //System.out.println(stoppBean1.getLeergut());
            //System.out.println(stoppBean1.getMarktId());
            //System.out.println(stoppBean1.getTourId());
            //System.out.println(stoppBean1.getRollcontainer());
        //}
        //System.out.println(arbeitTagBean1.getArbeitbegin_zeit());
        //System.out.println(arbeitTagBean1.getArbeitende_zeit());
        //System.out.println(arbeitTagBean1.getKilometer());}
        //catch(ValidationException e){}});

        save.addClickListener(click->
        {
            try{
                Stopp stoppBean=new Stopp();
                binder.writeBean(stoppBean);
                stoppService.addStopp(stoppBean);
            }
            catch(ValidationException e){}
                save.getUI().ifPresent(ui ->
                        ui.navigate("mmtour"));
        }
        );

        //addTour.addClickListener(click ->)

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, close);
    }
    public void setStopp(Stopp stopp) {
        this.stopp = stopp;
        binder.readBean(stopp);
    }
}