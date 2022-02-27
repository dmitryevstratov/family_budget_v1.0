package com.gmail.shepard1992.familybudgetv1.view.controller.api.modal;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import javafx.stage.Stage;

public interface ModalCreateReportController extends Controller {

    void chooseFile();

    void createReport();

    void setDialogStage(Stage dialogStage);

}
