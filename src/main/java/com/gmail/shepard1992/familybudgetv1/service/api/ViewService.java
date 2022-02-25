package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import javafx.stage.Stage;

import java.io.File;

public interface ViewService extends Controller {

    void showReportView(String view, File file);

    void showRootView(String view);

    void setPrimaryStage(Stage primaryStage);

}
