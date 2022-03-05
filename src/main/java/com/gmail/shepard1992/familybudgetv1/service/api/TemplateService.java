package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadTemplateDto;

import java.io.File;

public interface TemplateService {

    void saveTemplate(File file);

    boolean loadTemplate(LoadTemplateDto dto);

    File chooseTemplate(ChooseFileDto dto);

}
