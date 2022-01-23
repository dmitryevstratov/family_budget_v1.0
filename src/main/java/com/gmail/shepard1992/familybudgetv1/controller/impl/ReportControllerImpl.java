package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class ReportControllerImpl implements ReportController {

    private MainApplication mainApp;

    @FXML
    private Button addRowBtn;

    @FXML
    private Button addGroupBtn;

    @FXML
    private Button deleteRowBtn;

    public ReportControllerImpl() {

    }

    @FXML
    @Override
    public void addRowBtn() {
        addRowBtn.addEventHandler(MOUSE_CLICKED, e -> System.out.println("add row"));
    }

    @FXML
    @Override
    public void addGroupBtn() {
        addGroupBtn.addEventHandler(MOUSE_CLICKED, e -> System.out.println("add group"));
    }

    @FXML
    @Override
    public void deleteRowBtn() {
        deleteRowBtn.addEventHandler(MOUSE_CLICKED, e -> System.out.println("delete row"));
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
