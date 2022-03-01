package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ShowDeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.income.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.income.ModalDeleteIncomeRowController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ModalIncomeViewServiceImpl implements ModalIncomeViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewFacade viewFacade;

    @Autowired
    public ModalIncomeViewServiceImpl(ApplicationContext context, ViewFacade viewFacade) {
        this.context = context;
        this.viewFacade = viewFacade;
    }

    @Override
    public void showAddRowIncomeModalView(AbstractAddRowModalViewDto dto) {
        try {
            ModalViewDto<ModalAddRowIncomeController> params = new ModalViewDto<>(ModalAddRowIncomeController.class, context, mainApp, primaryStage, dto.getView(), dto.getDto(), dto.getFile());
            viewFacade.showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showUpdateRowIncomeModalView(AbstractAddRowModalViewDto dto) {
        try {
            ModalViewDto<ModalUpdateRowIncomeController> params = new ModalViewDto<>(ModalUpdateRowIncomeController.class, context, mainApp, primaryStage, dto.getView(), dto.getDto(), dto.getFile());
            viewFacade.showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDeleteRowIncomeModalView(DeleteRowModalViewDto dto) {
        ShowDeleteRowModalViewDto<ModalDeleteIncomeRowController> deleteRowModalViewDto = new ShowDeleteRowModalViewDto<>(ModalDeleteIncomeRowController.class, context, mainApp, primaryStage, dto, viewFacade);
        viewFacade.showDeleteRowModalView(deleteRowModalViewDto);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
