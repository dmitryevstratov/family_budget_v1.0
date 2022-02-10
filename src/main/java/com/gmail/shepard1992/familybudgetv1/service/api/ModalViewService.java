package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import javafx.stage.Stage;

public interface ModalViewService extends Controller {

    void showCreateReportModalView(String view);

    void setPrimaryStage(Stage primaryStage);

}
