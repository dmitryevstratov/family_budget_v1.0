package com.gmail.shepard1992.familybudgetv1.api.mainApplication.income;

import java.io.File;

public interface IncomeActionApi {

    void addIncomeRow(File file);

    void updateIncomeRow(File file);

    void deleteIncomeRow(File file);

}
