package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import javafx.stage.Stage;

public interface ViewService extends Controller {

    void showReportView(String view);

    void showRootView(String view);

    void setPrimaryStage(Stage primaryStage);

}
