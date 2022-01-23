package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class MainControllerImpl implements MainController {

    private MainApplication mainApp;

    @FXML
    private Button createReportBtn;

    public MainControllerImpl() {

    }

    @FXML
    @Override
    public void createReportBtn() {
        createReportBtn.addEventHandler(MOUSE_CLICKED, e -> mainApp.showReportView());
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
