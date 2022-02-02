package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ViewServiceImpl implements ViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;

    public ViewServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void showRootView(String view) {
        try {
            FXMLLoader loader = showView(view, MainController.class);
            MainController mainController = loader.getController();
            mainController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showReportView(String view) {
        try {
            FXMLLoader loader = showView(view, ReportController.class);
            ReportController reportController = loader.getController();
            reportController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <C> FXMLLoader showView(String view, Class<C> classController) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(view));
        loader.setControllerFactory(cls -> context.getBean(classController));
        BorderPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        return loader;
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
