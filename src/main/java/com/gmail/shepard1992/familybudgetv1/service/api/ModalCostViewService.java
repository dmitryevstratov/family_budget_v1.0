package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.view.cost.AddRowCostModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import javafx.stage.Stage;

public interface ModalCostViewService extends Controller {

    void showAddRowCostModalView(AddRowCostModalViewDto dto);

    void showUpdateRowCostModalView(AddRowCostModalViewDto dto);

    void showDeleteRowCostModalView(DeleteRowModalViewDto dto);

    void setPrimaryStage(Stage primaryStage);

}
