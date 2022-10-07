package com.example.pfenningvaadin2022mysql_1.Vaadin.Mobil;

import com.example.pfenningvaadin2022mysql_1.Vaadin.MarktForm;
import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Markt;
import com.example.pfenningvaadin2022mysql_1.service.MarktService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@Route("mmarkt")
@PageTitle("M Markt | Vaadin Pfenning")

public class MMarkt extends FormLayout {


    MarktService marktService;

    private Markt markt;

    Binder<Markt> binder = new Binder<>(Markt.class);

    TextField id = new TextField();



   TextField adres = new TextField("adres");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");



    public MMarkt(MarktService marktService) {
       this.marktService=marktService;
        addClassName("mm-markt");
        setWidth("25em");




        add(id
                ,adres,
                createButtonsLayout());

        binder.bindInstanceFields(this);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click->
        {

            try{
                Markt marktBean=new Markt();
        binder.writeBean(marktBean);
        marktService.addMarkt(marktBean);
        }
        catch(ValidationException e){}});


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    /*public void setMarkt(Markt markt) {
        this.markt = markt;
        binder.readBean(markt);
    }*/

}



