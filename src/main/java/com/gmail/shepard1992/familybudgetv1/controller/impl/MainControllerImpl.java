package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

@Component
public class MainControllerImpl implements MainController {

    private MainApplication mainApp;

    @FXML
    private Button buttonCreateReport;

    public MainControllerImpl() {

    }

    @FXML
    public void createReport() {
        buttonCreateReport.addEventHandler(MOUSE_CLICKED, e -> mainApp.showReportView());
    }

    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
