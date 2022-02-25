package com.gmail.shepard1992.familybudgetv1.view.controller.api;

import java.io.File;

public interface ReportController extends Controller {

    void addIncomeRow();

    void updateIncomeRow();

    void deleteIncomeRow();

    void addCostRow();

    void updateCostRow();

    void deleteCostRow();

    void setFile(File file);

}
