package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.viewIncomeService.AddRowIncomeModalView;
import com.gmail.shepard1992.familybudgetv1.model.dto.viewIncomeService.DeleteRowIncomeModalView;
import javafx.stage.Stage;

import java.io.File;

public interface ModalIncomeViewService extends Controller {

    void showAddRowIncomeModalView(AddRowIncomeModalView dto);

    void showUpdateRowIncomeModalView(AddRowIncomeModalView dto);

    void showDeleteRowIncomeModalView(DeleteRowIncomeModalView dto);

    void setPrimaryStage(Stage primaryStage);

}
