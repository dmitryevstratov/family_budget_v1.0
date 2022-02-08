package com.gmail.shepard1992.familybudgetv1.api.income;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;

public interface ShowModalIncomeViewApi {

    void showAddRowIncomeModalView(IncomeDto incomeDto);

    void showUpdateRowIncomeModalView(IncomeDto incomeDto);

    void showDeleteRowIncomeModalView(String index);

}
