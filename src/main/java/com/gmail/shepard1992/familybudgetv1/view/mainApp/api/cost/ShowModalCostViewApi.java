package com.gmail.shepard1992.familybudgetv1.view.mainApp.api.cost;

import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;

import java.io.File;

public interface ShowModalCostViewApi {

    void showAddRowIncomeModalView(CostDto costDto, File file);

    void showUpdateRowCostModalView(CostDto costDto, File file);

    void showDeleteRowCostModalView(String index, File file);

}
