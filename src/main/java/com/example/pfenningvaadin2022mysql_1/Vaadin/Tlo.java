package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

//@RolesAllowed({"ADMIN"})
@PageTitle("Tlo")

@Route(value ="",layout = MainLayout.class)

@PermitAll
public class Tlo extends VerticalLayout {

    public Tlo(){

    setSpacing(false);

    Image img = new Image("/images/pfenning111.png", "placeholder plant");
        img.setWidth("100%");
    add(img);

    //add(new H2("This place intentionally left empty"));
    // add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

    setSizeFull();
    setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    getStyle().set("text-align", "center");


}



}

