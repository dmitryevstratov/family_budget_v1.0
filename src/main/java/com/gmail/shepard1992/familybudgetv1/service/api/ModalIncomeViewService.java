package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import javafx.stage.Stage;

public interface ModalIncomeViewService extends Controller {

    void showAddRowIncomeModalView(AbstractAddRowModalViewDto dto);

    void showUpdateRowIncomeModalView(AbstractAddRowModalViewDto dto);

    void showDeleteRowIncomeModalView(DeleteRowModalViewDto dto);

    void setPrimaryStage(Stage primaryStage);

}
