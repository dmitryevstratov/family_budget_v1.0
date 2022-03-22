package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.repository.impl.CostRepositoryDataImpl;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalCostViewService;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ShowDeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalAddRowCostController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalDeleteCostRowController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalUpdateRowCostController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ModalCostViewServiceImpl implements ModalCostViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewFacade viewFacade;
    private static final Logger log = Logger.getLogger(ModalCostViewServiceImpl.class.getName());

    @Autowired
    public ModalCostViewServiceImpl(ApplicationContext context, ViewFacade viewFacade) {
        this.context = context;
        this.viewFacade = viewFacade;
    }

    @Override
    public void showAddRowCostModalView(AbstractAddRowModalViewDto dto) {
        try {
            ModalViewDto<ModalAddRowCostController> params = new ModalViewDto<>(ModalAddRowCostController.class, context, mainApp, primaryStage, dto.getView(), dto.getDto(), dto.getFile());
            viewFacade.showModalView(params);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void showUpdateRowCostModalView(AbstractAddRowModalViewDto dto) {
        try {
            ModalViewDto<ModalUpdateRowCostController> params = new ModalViewDto<>(ModalUpdateRowCostController.class, context, mainApp, primaryStage, dto.getView(), dto.getDto(), dto.getFile());
            viewFacade.showModalView(params);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void showDeleteRowCostModalView(DeleteRowModalViewDto dto) {
        ShowDeleteRowModalViewDto<ModalDeleteCostRowController> deleteRowModalViewDto = new ShowDeleteRowModalViewDto<>(ModalDeleteCostRowController.class, context, mainApp, primaryStage, dto, viewFacade);
        try {
            viewFacade.showDeleteRowModalView(deleteRowModalViewDto);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
