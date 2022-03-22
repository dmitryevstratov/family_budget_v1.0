package com.gmail.shepard1992.familybudgetv1.view.controller.impl;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class MainControllerImpl implements MainController {

    private MainApplication mainApp;
    private Stage stage;

    private final ButtonApi btnCreate = MainApplication::showModalCreateReportView;
    private final ButtonApi btnOpen = MainApplication::showModalOpenReportView;
    private final ButtonApi btnOpenYearReport = MainApplication::showModalOpenYearReportView;
    private final ButtonApi btnShowHelp = MainApplication::showModalHelpView;

    public MainControllerImpl() {

    }

    @FXML
    @Override
    public void showModalCreateReport() {
        btnCreate.click(mainApp);
    }

    @FXML
    @Override
    public void showModalOpenReport() {
        btnOpen.click(mainApp);
    }

    @FXML
    @Override
    public void showModalOpenYearReport() {
        btnOpenYearReport.click(mainApp);
    }

    @FXML
    @Override
    public void showModalHelp() {
        btnShowHelp.click(mainApp);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.stage = dialogStage;
    }

}
