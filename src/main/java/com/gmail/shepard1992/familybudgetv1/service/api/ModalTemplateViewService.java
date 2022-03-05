package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;

import java.io.File;

public interface ModalTemplateViewService extends Controller {

    void showModalLoadTemplateView(String view, File file);

}
