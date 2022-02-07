package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import javafx.stage.Stage;

public interface ModalViewService extends Controller {

    void showAddRowIncomeModalView(String view, IncomeDto incomeDto);

    void showDeleteRowIncomeModalView(String view, String index);

    void setPrimaryStage(Stage primaryStage);

}
