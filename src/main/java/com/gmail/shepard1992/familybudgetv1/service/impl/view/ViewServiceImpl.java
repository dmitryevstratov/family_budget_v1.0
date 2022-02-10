package com.gmail.shepard1992.familybudgetv1.service.impl.view;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.model.dto.ShowViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import com.gmail.shepard1992.familybudgetv1.utils.ViewUtil;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ViewServiceImpl implements ViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewUtil viewUtil;

    @Autowired
    public ViewServiceImpl(ApplicationContext context, ViewUtil viewUtil) {
        this.context = context;
        this.viewUtil = viewUtil;
    }

    @Override
    public void showRootView(String view) {
        try {
            ShowViewDto<MainController> dto = new ShowViewDto<>(view, MainController.class, context, primaryStage);
            FXMLLoader loader = viewUtil.showView(dto);
            MainController mainController = loader.getController();
            mainController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showReportView(String view, File file) {
        try {
            ShowViewDto<ReportController> dto = new ShowViewDto<>(view, ReportController.class, context, primaryStage);
            FXMLLoader loader = viewUtil.showView(dto);
            ReportController reportController = loader.getController();
            reportController.setFile(file);
            reportController.setMainApp(mainApp);
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
