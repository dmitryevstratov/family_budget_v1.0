package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalDeleteIncomeRowController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.ShowDeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.income.AddRowIncomeModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.utils.ViewUtil;
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
    private final ViewUtil viewUtil;

    @Autowired
    public ModalIncomeViewServiceImpl(ApplicationContext context, ViewUtil viewUtil) {
        this.context = context;
        this.viewUtil = viewUtil;
    }

    @Override
    public void showAddRowIncomeModalView(AddRowIncomeModalViewDto dto) {
        try {
            ModalViewDto<ModalAddRowIncomeController, IncomeDto> params = new ModalViewDto<>(context, mainApp, primaryStage, dto.getView(), ModalAddRowIncomeController.class, dto.getIncomeDto(), dto.getFile());
            viewUtil.showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showUpdateRowIncomeModalView(AddRowIncomeModalViewDto dto) {
        try {
            ModalViewDto<ModalUpdateRowIncomeController, IncomeDto> params = new ModalViewDto<>(context, mainApp, primaryStage, dto.getView(), ModalUpdateRowIncomeController.class, dto.getIncomeDto(), dto.getFile());
            viewUtil.showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDeleteRowIncomeModalView(DeleteRowModalViewDto dto) {
        ShowDeleteRowModalViewDto<ModalDeleteIncomeRowController> deleteRowModalViewDto = new ShowDeleteRowModalViewDto<>(dto, context, mainApp, ModalDeleteIncomeRowController.class, primaryStage, viewUtil);
        viewUtil.showDeleteRowModalView(deleteRowModalViewDto);
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
