package com.example.pfenningvaadin2022mysql_1.Vaadin.Mobil;

import com.example.pfenningvaadin2022mysql_1.Vaadin.*;
import com.example.pfenningvaadin2022mysql_1.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;

import javax.annotation.security.RolesAllowed;

@PageTitle("MMain")
@Route(value ="mobil")
@RolesAllowed("USER")
public class MMainLayout extends AppLayout {

    private final SecurityService securityService;


    MMainLayout(SecurityService securityService)
    {
this.securityService=securityService;

    createHeader();




    }
private void createHeader() {

        H4 logo = new H4("pfenning");
        logo.addClassNames("text-l", "m-m");


        Button logout = new Button("Log out", e -> securityService.logout());

    NativeButton button = new NativeButton(
            "Navigate to arbeittag");
    button.addClickListener(e ->
                    button.getUI().ifPresent(ui ->
                            ui.navigate("mmarbeittag")));

        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                logo,logout
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        header.expand(logo);

        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {

        RouterLink listLink1 = new RouterLink("Fahrer List", FahrerList.class);
        listLink1.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink0 = new RouterLink("Arbeit Tag List", ArbeitTagList.class);
        listLink0.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink4 = new RouterLink("Markt List", MarktList.class);
        listLink4.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink3 = new RouterLink("Lkw List ", LkwList.class);
        listLink3.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink7 = new RouterLink("Tour List", TourList.class);
        listLink7.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLinkT = new RouterLink("Tlo", Tlo.class);
        listLinkT.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink listLink6 = new RouterLink("Stopp List", StoppList.class);
        listLink6.setHighlightCondition(HighlightConditions.sameLocation());


        //addToDrawer(new VerticalLayout(listLink0,listLink1,listLink4,listLink3,listLink7,listLink6,
          //      listLinkT));


    }

}