package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import javafx.stage.Stage;

public interface ModalViewService extends Controller {

    void showCreateReportModalView(String view);

    void showOpenReportModalView(String view);

}
