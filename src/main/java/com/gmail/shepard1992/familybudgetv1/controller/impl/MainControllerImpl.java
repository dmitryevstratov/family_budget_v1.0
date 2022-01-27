package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.controller.buttons.api.ButtonApi;
import javafx.fxml.FXML;

public class MainControllerImpl implements MainController {

    private MainApplication mainApp;

    private final ButtonApi btn = MainApplication::showReportView;

    public MainControllerImpl() {

    }

    @FXML
    @Override
    public void createReport() {
        btn.click(mainApp);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
