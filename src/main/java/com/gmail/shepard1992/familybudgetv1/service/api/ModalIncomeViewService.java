package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.income.AddRowIncomeModalViewDto;
import javafx.stage.Stage;

public interface ModalIncomeViewService extends Controller {

    void showAddRowIncomeModalView(AddRowIncomeModalViewDto dto);

    void showUpdateRowIncomeModalView(AddRowIncomeModalViewDto dto);

    void showDeleteRowIncomeModalView(DeleteRowModalViewDto dto);

    void setPrimaryStage(Stage primaryStage);

}
