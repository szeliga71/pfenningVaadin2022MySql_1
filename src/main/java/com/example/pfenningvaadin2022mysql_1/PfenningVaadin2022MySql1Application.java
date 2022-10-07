package com.example.pfenningvaadin2022mysql_1;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PfenningVaadin2022MySql1Application  {

    public static void main(String[] args) {
        SpringApplication.run(PfenningVaadin2022MySql1Application.class, args);
    }}



/*@SpringBootApplication
@Theme(value = "flowcrmtutorial")
//@PWA(name = "pfenning CRM Tutorial", shortName = "pf CRM Tutorial", offlineResources = {})
//@NpmPackage(value = "line-awesome", version = "1.3.0")
public class PfenningVaadin2022MySql1Application  extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(PfenningVaadin2022MySql1Application.class, args);
    }
}*/