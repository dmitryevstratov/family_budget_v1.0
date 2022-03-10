package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.api.TemplateRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadTemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository repository;
    private final RepositoryData<Income> incomeRepositoryData;
    private final RepositoryData<Cost> costRepositoryData;
    private final ValidationUtil validationUtil;


    @Autowired
    public TemplateServiceImpl(TemplateRepository repository, RepositoryData<Income> incomeRepositoryData, RepositoryData<Cost> costRepositoryData, ValidationUtil validationUtil) {
        this.repository = repository;
        this.incomeRepositoryData = incomeRepositoryData;
        this.costRepositoryData = costRepositoryData;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveTemplate(File file) {
        repository.save(file);
    }

    @Override
    public boolean loadTemplate(LoadTemplateDto dto) {
        if (validationUtil.isInputLoadTemplateValid(dto)) {
            incomeRepositoryData.clear(dto.getFile());
            costRepositoryData.clear(dto.getFile());

            incomeRepositoryData.getAll(dto.getTmp()).forEach(income -> incomeRepositoryData.save(income, dto.getFile()));
            costRepositoryData.getAll(dto.getTmp()).forEach(cost -> costRepositoryData.save(cost, dto.getFile()));
            return true;
        }
        return false;
    }

    @Override
    public File chooseTemplate(ChooseFileDto dto) {
        return repository.chooseTemplate(dto.getStage(), dto);
    }

}
