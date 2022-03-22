package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.ModalTemplateViewService;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ShowModalLoadTemplateDto;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalLoadTemplateController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;

public class ModalTemplateViewViewServiceImpl implements ModalTemplateViewService {

    //ToDo обработка ошибок

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewFacade viewFacade;

    public ModalTemplateViewViewServiceImpl(ApplicationContext context, ViewFacade viewFacade) {
        this.context = context;
        this.viewFacade = viewFacade;
    }

    @Override
    public void showModalLoadTemplateView(String view, File file) {
        ShowModalLoadTemplateDto<ModalLoadTemplateController> dto = new ShowModalLoadTemplateDto<>(ModalLoadTemplateController.class, context, mainApp, primaryStage, view, file);
        try {
            viewFacade.showModalView(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
