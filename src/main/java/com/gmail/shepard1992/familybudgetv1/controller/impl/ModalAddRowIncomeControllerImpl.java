package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.buttons.api.ButtonApi;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalAddRowIncomeControllerImpl implements ModalAddRowIncomeController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private boolean okClicked = false;

    @FXML
    private TextField category;

    @FXML
    private TextField type;

    @FXML
    private TextField sum;

    private final ButtonApi btn = MainApplication::addRowHandleOk;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        btn.click(mainApp);
        okClicked = true;
        dialogStage.close();
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
