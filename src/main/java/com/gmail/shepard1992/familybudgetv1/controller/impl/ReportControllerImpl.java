package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.controller.buttons.api.ButtonApi;

public class ReportControllerImpl implements ReportController {

    private MainApplication mainApp;

    private final ButtonApi addIncomeBtn = MainApplication::addRow;
    private final ButtonApi updateIncomeBtn = MainApplication::updateRow;
    private final ButtonApi deleteIncomeBtn = MainApplication::deleteRow;

    public ReportControllerImpl() {

    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void addRow() {
        addIncomeBtn.click(mainApp);
    }

    @Override
    public void updateRow() {
        updateIncomeBtn.click(mainApp);
    }

    @Override
    public void deleteRow() {
        deleteIncomeBtn.click(mainApp);
    }
}
