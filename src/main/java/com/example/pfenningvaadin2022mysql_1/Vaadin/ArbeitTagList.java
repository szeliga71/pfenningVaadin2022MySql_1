package com.example.pfenningvaadin2022mysql_1.Vaadin;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.service.ArbeitTagService;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
import com.example.pfenningvaadin2022mysql_1.service.TourService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.stream.Collectors;


@Route(layout = MainLayout.class)
@PageTitle("Arbeit Tag List | Vaadin Pfenning")
public class ArbeitTagList extends VerticalLayout {


    Grid<ArbeitTag> grid = new Grid<>(ArbeitTag.class);

    TextField filterText = new TextField();


    //DatePicker dateStart = new DatePicker("Date start", LocalDate.now());
    //DatePicker dateStop = new DatePicker("Date end",LocalDate.now());

    //TextField dateStart=new TextField("arbeitbegin");
    //TextField dateStop=new TextField("arbeitende");

    DatePicker dateStart=new DatePicker("");//LocalDate.now());


    DatePicker dateStop=new DatePicker("",LocalDate.now());

    ArbeitTagService arbeitTagService;
    FahrerService fahrerService;

    ArbeitTagForm arbeitTagForm;

    TourList tourList;

    TourForm tourForm;




    public ArbeitTagList(ArbeitTagService arbeitTagService, FahrerService fahrerService) {
        this.arbeitTagService=arbeitTagService;
        this.fahrerService=fahrerService;


        addClassName("Arbeit-Tag-list");
        setSizeFull();
        configureGrid();
        configureArbeitTagForm();

        //add(getToolbar(), grid);
        add(getToolbar(), getContent());
        updateList();
        closeEditor();

    }
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,arbeitTagForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, arbeitTagForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }
    private void configureGrid() {
        grid.addClassNames("arbeitTag-grid");
        grid.setSizeFull();

        grid.setColumns("fahrer_name","arbeitbegin_date","arbeitbegin_zeit","arbeitende_zeit","kilometer","kilometer_rewe","fahrerbruch","unfall","pause");



        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
               editArbeitTag(event.getValue()));

        //grid.asSingleSelect().addValueChangeListener(event -> showTours(event.getValue()));

    }

    private HorizontalLayout getToolbar() {
        Select<ArbeitTag>select =new Select<>();
        //select.setLabel("fahrer name");
        //select.setItemLabelGenerator(ArbeitTag::getFahrer_name);


       filterText.setPlaceholder("Filter by name...");

        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        dateStart.setPlaceholder("Filter by arbeitsbegin von...");
        dateStart.setClearButtonVisible(true);
       dateStop.setPlaceholder("Filter by arbeitsbegin bis...");
        dateStop.setClearButtonVisible(true);

        //dateStart.addValueChangeListener(e -> dateStop.setMin(e.getValue()));
        //dateStart.addValueChangeListener((e -> updateList()));
        //dateStop.addValueChangeListener(e -> dateStart.setMax(e.getValue()));


                //dateStart.setValueChangeMode(ValueChangeMode.LAZY);
                dateStart.addValueChangeListener(e -> updateList());
        //dateStop.setValueChangeMode(ValueChangeMode.LAZY);
        dateStop.addValueChangeListener(e -> updateList());



        Button addArbeitTagButton = new Button("Add Arbeit Tag");

        addArbeitTagButton.addClickListener(click -> addArbeitTag());


        Button exportExcell = new Button("Export Excell");

        exportExcell.addClickListener(click -> exportExcell());

        Button deleteArbeitTag = new Button("Delete Arbeit Tag");
        Button updateArbeitTag = new Button("Update Arbeit Tag");

        HorizontalLayout toolbar = new HorizontalLayout(filterText,dateStart,dateStop, addArbeitTagButton,exportExcell);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void exportExcell() {
        //( ClickEvent < Button > clickEvent ) -> {
          /*  String fileName = "ArbeitTag" + Instant.now().toString() + ".csv";
            final String fileNamePath = "/Users/szeli/" + fileName;
            try (
                    BufferedWriter writer = Files.newBufferedWriter( Paths.get( fileNamePath ) );
                    CSVPrinter csvPrinter = new CSVPrinter( writer , CSVFormat.RFC4180.withHeader( "Name" , "Phone" ) );
            )
            {
                Collection < Person > persons = ( ( ListDataProvider < Person > ) grid.getDataProvider() ).getItems();
                for ( Person person : persons )
                {
                    csvPrinter.printRecord( person.getName() , person.getPhone() );
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }

            // Tell user.
            Notification.show( "Exported to file in your home folder: " + fileName );
        }
        );
        grid.getDataProvider().addDataProviderListener( dataChangeEvent -> {
            exportButton.setEnabled( true );
        } );*/
    }

    public void editArbeitTag(ArbeitTag arbeitTag) {
        if (arbeitTag == null) {
            closeEditor();
        } else {
            arbeitTagForm.setArbeitTag(arbeitTag);
            arbeitTagForm.setVisible(true);
            addClassName("editing");
        }
    }

    /*public void showTours(ArbeitTag arbeitTag) {
        if (arbeitTag == null) {
            closeEditor();
        } else {
            tourList.tourService.findAllTourr();
            //tourList.tourService.showTourByAT(arbeitTag.getId());
            tourList.setVisible(true);
            addClassName("show");
        }
    }*/

    private void closeEditor() {
        arbeitTagForm.setArbeitTag(null);
        arbeitTagForm.setVisible(false);
        removeClassName("editing");
    }

    private void addArbeitTag() {
        grid.asSingleSelect().clear();
        editArbeitTag(new ArbeitTag());
    }

    private void updateList() {
       grid.setItems(arbeitTagService.findArbeitTagesByName(dateStart.getValue(),dateStop.getValue(),filterText.getValue()));//(filterText.getValue()));

        //grid.setItems(arbeitTagService.findArbeitTagesByDate(dateStart.getValue(),dateStop.getValue(),filterText.getValue()));
    }

    private void configureArbeitTagForm() {
        arbeitTagForm = new ArbeitTagForm(fahrerService.getAllFahrer());
        arbeitTagForm.setWidth("25em");
        arbeitTagForm.addListener(ArbeitTagForm.SaveEvent.class, this::saveArbeitTag);
        arbeitTagForm.addListener(ArbeitTagForm.DeleteEvent.class, this::deleteArbeitTag);
        arbeitTagForm.addListener(ArbeitTagForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveArbeitTag(ArbeitTagForm.SaveEvent event) {
        arbeitTagService.addArbeitTag(event.getArbeitTag());
        updateList();
        closeEditor();
    }

    private void deleteArbeitTag(ArbeitTagForm.DeleteEvent event) {
        arbeitTagService.deleteArbeitTag(event.getArbeitTag());
        updateList();
        closeEditor();
    }

}
