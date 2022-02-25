package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.view.income.AddRowIncomeModalViewDto;
import javafx.stage.Stage;

public interface ModalIncomeViewService extends Controller {

    void showAddRowIncomeModalView(AddRowIncomeModalViewDto dto);

    void showUpdateRowIncomeModalView(AddRowIncomeModalViewDto dto);

    void showDeleteRowIncomeModalView(DeleteRowModalViewDto dto);

    void setPrimaryStage(Stage primaryStage);

}
