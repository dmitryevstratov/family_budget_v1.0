package com.gmail.shepard1992.familybudgetv1.controller.api.modal;

import com.gmail.shepard1992.familybudgetv1.controller.api.Controller;
import javafx.stage.Stage;

import java.io.File;

public interface ModalController extends Controller {

    void setDialogStage(Stage dialogStage);

    void setFile(File file);

}
