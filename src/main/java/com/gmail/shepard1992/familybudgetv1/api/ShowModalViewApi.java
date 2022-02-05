package com.gmail.shepard1992.familybudgetv1.api;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;

public interface ShowModalViewApi {

    void showAddRowIncomeModalView(IncomeDto incomeDto);

    void showDeleteRowIncomeModalView(String index);

}
