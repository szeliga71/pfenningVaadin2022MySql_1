package com.example.pfenningvaadin2022mysql_1.Vaadin.Mobil;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Lkw;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.example.pfenningvaadin2022mysql_1.service.ArbeitTagService;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
import com.example.pfenningvaadin2022mysql_1.service.LkwService;
import com.example.pfenningvaadin2022mysql_1.service.TourService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.time.Duration;


@Route(layout = MMainLayout.class)
@PageTitle("MMTour | Vaadin Pfenning")
@RolesAllowed("USER")
public class MMTour extends VerticalLayout {

    LkwService lkwService;
    TourService tourService;

    ArbeitTagService arbeitTagService;
    Tour tour;
    Binder<Tour> binder = new Binder<>(Tour.class);

    NativeButton toStopp = new NativeButton(
            "ADD STOPP");
    Button save = new Button("Save");
    Button update = new Button("Update");
    Button close = new Button("Cancel");
    Button addTour = new Button("Edit");


    public MMTour(LkwService lkwService,TourService tourService,ArbeitTagService arbeitTagService){
        this.lkwService=lkwService;
        this.tourService=tourService;
        this.arbeitTagService=arbeitTagService;
addClassName("MM-Tour");
setSizeFull();
        setMargin(true);
        setSpacing(true);

        FormLayout formLayout=new FormLayout();



        TimePicker abfahrtlager = new TimePicker();
        abfahrtlager.setStep(Duration.ofMinutes(15));
        abfahrtlager.setSizeFull();
        formLayout.addFormItem(abfahrtlager,"abfahrtlager");
        binder.forField(abfahrtlager).bind(Tour::getAbfahrtlager,Tour::setAbfahrtlager);

        TimePicker ankunftlager = new TimePicker();
        ankunftlager.setStep(Duration.ofMinutes(15));
        ankunftlager.setSizeFull();
        formLayout.addFormItem(ankunftlager,"ankunftlager");
        binder.forField(ankunftlager).bind(Tour::getAnkunftlager,Tour::setAnkunftlager);

        //===================================================
        ComboBox<Long>arbeitTagIds=new ComboBox<>();
        arbeitTagIds.setItems(arbeitTagService.getAllIds());
        arbeitTagIds.setSizeFull();
        formLayout.addFormItem(arbeitTagIds,"arbeitTagId");
        binder.forField(arbeitTagIds).bind(Tour::getArbeit_tag_id,Tour::setArbeit_tag_id);
                //withNullRepresentation("null")
          //      .withConverter(new StringToLongConverter("wpisz numer "))
          //binder.forField(arbeitTagIds)
            //      .bind(Tour::getArbeit_tag_id,Tour::setArbeit_tag_id);
//=======================================================

        ComboBox<Lkw> lkwKenz=new ComboBox<>();
        lkwKenz.setItems(lkwService.findAllLkw());
        lkwKenz.setSizeFull();
        formLayout.addFormItem(lkwKenz,"kenz");
        binder.forField(lkwKenz).bind(Tour::getLkw_kenz,Tour::setLkw_kenz);


        IntegerField kilometer_rewe = new IntegerField();
        kilometer_rewe.setSizeFull();
        formLayout.addFormItem(kilometer_rewe,"kilometer rewe");
        binder.forField(kilometer_rewe).bind(Tour::getRewe_kilometer,Tour::setRewe_kilometer);

        IntegerField tourKilometer = new IntegerField();
        tourKilometer.setSizeFull();
        formLayout.addFormItem(tourKilometer,"kilometer");
        binder.forField(tourKilometer).bind(Tour::getTour_kilometer,Tour::setTour_kilometer);


        TextField tourNr = new TextField();
        tourNr.setSizeFull();
        formLayout.addFormItem(tourNr,"tour nr");
        binder.forField(tourNr).bind(Tour::getTour_nr,Tour::setTour_nr);


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
        addTour.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);


        toStopp.addClickListener(e ->
                toStopp.getUI().ifPresent(ui ->

                        ui.navigate("mmstopp")));

        //testing

           //System.out.println(arbeitTagBean1);
            //System.out.println(arbeitTagBean1.getFahrer_name());
            //System.out.println(arbeitTagBean1.getArbeitbegin_date());}
        //System.out.println(arbeitTagBean1.getArbeitbegin_zeit());
        //System.out.println(arbeitTagBean1.getArbeitende_zeit());
        //System.out.println(arbeitTagBean1.getKilometer());}
        //catch(ValidationException e){}});

        save.addClickListener(click->
        {
            try{
                Tour tour=new Tour();
                binder.writeBean(tour);
                tourService.addTour(tour);
            }
            catch(ValidationException e){}
        }
        );
        update.addClickListener(click->{try{
            Tour tour1=new Tour();
            binder.writeBean(tour1);

            System.out.println(tour1);
            System.out.println(tour1.getTour_nr());
            System.out.println(tour1.getTour_kilometer());
            System.out.println(tour1.getAbfahrtlager());
            System.out.println(tour1.getAnkunftlager());
            System.out.println(tour1.getArbeit_tag_id());
            System.out.println(tour1.getId());
            System.out.println(tour1.getLkw_kenz());}
        //System.out.println(arbeitTagBean1.getArbeitbegin_zeit());
        //System.out.println(arbeitTagBean1.getArbeitende_zeit());
        //System.out.println(arbeitTagBean1.getKilometer());}
        catch(ValidationException e){}});



        //addTour.addClickListener(click ->)

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, update, close,toStopp);
    }
    public void setArbeitTag(Tour tour) {
        this.tour = tour;
        binder.readBean(tour);
    }
}