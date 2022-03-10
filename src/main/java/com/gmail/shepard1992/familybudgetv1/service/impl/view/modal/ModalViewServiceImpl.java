package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ShowReportModalViewDto;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenYearReportController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ModalViewServiceImpl implements ModalViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewFacade viewFacade;

    @Autowired
    public ModalViewServiceImpl(ApplicationContext context, ViewFacade viewFacade) {
        this.context = context;
        this.viewFacade = viewFacade;
    }

    @Override
    public void showCreateReportModalView(String view) {
        ShowReportModalViewDto<ModalCreateReportController> dto = new ShowReportModalViewDto<>(ModalCreateReportController.class, context, mainApp, primaryStage, view);
        viewFacade.showReportModalView(dto);
    }

    @Override
    public void showOpenReportModalView(String view) {
        ShowReportModalViewDto<ModalOpenReportController> dto = new ShowReportModalViewDto<>(ModalOpenReportController.class, context, mainApp, primaryStage, view);
        viewFacade.showReportModalView(dto);
    }

    @Override
    public void showModalOpenYearReportView(String view) {
        ShowReportModalViewDto<ModalOpenYearReportController> dto = new ShowReportModalViewDto<>(ModalOpenYearReportController.class, context, mainApp, primaryStage, view);
        viewFacade.showReportModalView(dto);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.primaryStage = dialogStage;
    }


}
