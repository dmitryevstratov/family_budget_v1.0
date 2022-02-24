package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.cost.ModalAddRowCostController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.cost.ModalDeleteCostRowController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.cost.ModalUpdateRowCostController;
import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.show.ShowDeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.cost.AddRowCostModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalCostViewService;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ModalCostViewServiceImpl implements ModalCostViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewFacade viewFacade;

    @Autowired
    public ModalCostViewServiceImpl(ApplicationContext context, ViewFacade viewFacade) {
        this.context = context;
        this.viewFacade = viewFacade;
    }

    @Override
    public void showAddRowCostModalView(AddRowCostModalViewDto dto) {
        try {
            ModalViewDto<ModalAddRowCostController, CostDto> params = new ModalViewDto<>(context, mainApp, primaryStage, dto.getView(), ModalAddRowCostController.class, dto.getCostDto(), dto.getFile());
            viewFacade.showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showUpdateRowCostModalView(AddRowCostModalViewDto dto) {
        try {
            ModalViewDto<ModalUpdateRowCostController, CostDto> params = new ModalViewDto<>(context, mainApp, primaryStage, dto.getView(), ModalUpdateRowCostController.class, dto.getCostDto(), dto.getFile());
            viewFacade.showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDeleteRowCostModalView(DeleteRowModalViewDto dto) {
        ShowDeleteRowModalViewDto<ModalDeleteCostRowController> deleteRowModalViewDto = new ShowDeleteRowModalViewDto<>(dto, context, mainApp, ModalDeleteCostRowController.class, primaryStage, viewFacade);
        viewFacade.showDeleteRowModalView(deleteRowModalViewDto);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
