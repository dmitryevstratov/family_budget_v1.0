package com.gmail.shepard1992.familybudgetv1.view.mainApp.api.income;

import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;

import java.io.File;

public interface ShowModalIncomeViewApi {

    void showAddRowIncomeModalView(IncomeDto incomeDto, File file);

    void showUpdateRowIncomeModalView(IncomeDto incomeDto, File file);

    void showDeleteRowIncomeModalView(String index, File file);

}
