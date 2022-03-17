package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.api.TemplateRepository;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadTemplateDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository repository;
    private final RepositoryData<Income> incomeRepositoryData;
    private final RepositoryData<Cost> costRepositoryData;
    private final ValidationUtil validationUtil;
    private static final Logger log = Logger.getLogger(TemplateServiceImpl.class.getName());


    @Autowired
    public TemplateServiceImpl(TemplateRepository repository, RepositoryData<Income> incomeRepositoryData, RepositoryData<Cost> costRepositoryData, ValidationUtil validationUtil) {
        this.repository = repository;
        this.incomeRepositoryData = incomeRepositoryData;
        this.costRepositoryData = costRepositoryData;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveTemplate(File file) {
        log.debug(SERVICE_LOGS + "сохранить шаблон " + file.getName());
        try {
            repository.save(file);
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }
    }

    @Override
    public boolean loadTemplate(LoadTemplateDto dto) {
        if (validationUtil.isInputLoadTemplateValid(dto)) {
            try {
                incomeRepositoryData.clear(dto.getFile());
                costRepositoryData.clear(dto.getFile());
                incomeRepositoryData.getAll(dto.getTmp()).forEach(income -> {
                    try {
                        incomeRepositoryData.save(income, dto.getFile());
                    } catch (RepositoryException e) {
                        log.error(e.getMessage());
                        log.error(e.getStackTrace());
                    }
                });
                costRepositoryData.getAll(dto.getTmp()).forEach(cost -> {
                    try {
                        costRepositoryData.save(cost, dto.getFile());
                    } catch (RepositoryException e) {
                        log.error(e.getMessage());
                        log.error(e.getStackTrace());
                    }
                });
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(e.getStackTrace());
            }

            log.debug(SERVICE_LOGS + "загрузить шаблон " + dto.getFile().getName());
            return true;
        }
        log.debug(SERVICE_LOGS + "шаблон не загружен " + dto.getFile().getName());
        return false;
    }

    @Override
    public File chooseTemplate(ChooseFileDto dto) {
        return repository.chooseTemplate(dto.getStage(), dto);
    }

}
