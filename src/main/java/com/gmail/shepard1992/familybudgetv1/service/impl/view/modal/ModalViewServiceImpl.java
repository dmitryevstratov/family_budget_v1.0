package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.model.dto.ShowViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.utils.ViewUtil;
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
    private final ViewUtil viewUtil;

    @Autowired
    public ModalViewServiceImpl(ApplicationContext context, ViewUtil viewUtil) {
        this.context = context;
        this.viewUtil = viewUtil;
    }

    @Override
    public void showCreateReportModalView(String view) {
        try {
            ShowViewDto<ModalCreateReportController> dto = new ShowViewDto<>(view, ModalCreateReportController.class, context, primaryStage);
            FXMLLoader loader = viewUtil.showView(dto);
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
