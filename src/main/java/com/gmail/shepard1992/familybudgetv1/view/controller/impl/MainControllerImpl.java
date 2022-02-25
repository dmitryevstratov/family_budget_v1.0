package com.gmail.shepard1992.familybudgetv1.view.controller.impl;

import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonApi;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class MainControllerImpl implements MainController {

    private MainApplication mainApp;

    private final ButtonApi btn = MainApplication::showModalCreateReportView;

    public MainControllerImpl() {

    }

    @FXML
    @Override
    public void showModalCreateReport() {
        btn.click(mainApp);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
