package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ShowViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        try {
            ShowViewDto<ModalCreateReportController> dto = new ShowViewDto<>(view, ModalCreateReportController.class, context, primaryStage);
            FXMLLoader loader = viewFacade.showView(dto);
            ModalCreateReportController controller = loader.getController();
            controller.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
