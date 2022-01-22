package com.gmail.shepard1992.familybudgetv1;

import com.gmail.shepard1992.familybudgetv1.api.ShowViewsApi;
import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.gmail.shepard1992.familybudgetv1.constants.ViewsPath.REPORT_VIEW;
import static com.gmail.shepard1992.familybudgetv1.constants.ViewsPath.START_VIEW;

public class MainApplication extends Application implements ShowViewsApi {

    private Stage primaryStage;

    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initRootView();
    }

    public void initRootView() {
        try {
            FXMLLoader loader = showView(START_VIEW);
            MainController mainController = loader.getController();
            mainController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReportView() {
        try {
            showView(REPORT_VIEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader showView(String startView) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(startView));
        BorderPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        return loader;
    }

}