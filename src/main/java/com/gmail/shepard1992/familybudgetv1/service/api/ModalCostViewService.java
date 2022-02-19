package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.cost.AddRowCostModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.DeleteRowModalViewDto;
import javafx.stage.Stage;

public interface ModalCostViewService extends Controller {

    void showAddRowCostModalView(AddRowCostModalViewDto dto);

    void showUpdateRowCostModalView(AddRowCostModalViewDto dto);

    void showDeleteRowCostModalView(DeleteRowModalViewDto dto);

    void setPrimaryStage(Stage primaryStage);

}
