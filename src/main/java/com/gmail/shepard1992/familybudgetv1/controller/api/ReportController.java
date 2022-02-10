package com.gmail.shepard1992.familybudgetv1.controller.api;

import java.io.File;

public interface ReportController extends Controller {

    void addIncomeRow();

    void updateIncomeRow();

    void deleteIncomeRow();

    void setFile(File file);

}
