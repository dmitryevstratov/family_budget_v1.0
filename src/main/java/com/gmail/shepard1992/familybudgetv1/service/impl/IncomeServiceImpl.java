package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.*;
import com.gmail.shepard1992.familybudgetv1.utils.*;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceNewRowDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;
import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.*;

@org.springframework.stereotype.Service
public class IncomeServiceImpl implements Service<IncomeDto>, TotalService {

    private final ValidationUtil validationUtil;
    private final RepositoryData<Income> repositoryData;
    private final MapperUtil mapperUtil;
    private final IndexUtil<IncomeDto> indexUtil;
    private final TotalServiceUtil totalServiceUtil;
    private final DeleteRowUtil<Income> deleteRowUtil;
    private static final Logger log = Logger.getLogger(IncomeServiceImpl.class.getName());

    @Autowired
    public IncomeServiceImpl(ValidationUtil validationUtil, RepositoryData<Income> repositoryData, MapperUtil mapperUtil, IndexUtil<IncomeDto> indexUtil, TotalServiceUtil totalServiceUtil, DeleteRowUtil<Income> deleteRowUtil) {
        this.validationUtil = validationUtil;
        this.repositoryData = repositoryData;
        this.mapperUtil = mapperUtil;
        this.indexUtil = indexUtil;
        this.totalServiceUtil = totalServiceUtil;
        this.deleteRowUtil = deleteRowUtil;
    }

    @Override
    public boolean addRow(ServiceNewRowDto params) {
        if (validationUtil.isInputAddValid(params)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSumFact(Double.parseDouble(params.getSumFact().getText()))
                    .setType(params.getType().getText()).build();
            try {
                repositoryData.save(mapperUtil.convertToIncome(dto), params.getFile());
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(e.getStackTrace());
            }
            updateTotal(params.getDialogStage(), params.getFile());
            log.debug(SERVICE_LOGS + "добавить запись " + dto.toString());
            return true;
        } else {
            log.debug(SERVICE_LOGS + "запись не добавлена");
            return false;
        }
    }

    @Override
    public boolean updateRow(ServiceNewRowDto params) {
        ValidationIndexDto<Income> validationIndexDto = new ValidationIndexDto<>(
                repositoryData,
                params.getIndex().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        try {
            if (validationUtil.isInputUpdateValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
                IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                        .setIndex(params.getIndex().getText())
                        .setCategory(params.getCategory().getText())
                        .setType(params.getType().getText())
                        .build();
                if (!params.getSumFact().getText().isEmpty()) {
                    dto.setIncomeSum(Double.parseDouble(params.getSumFact().getText()));
                }
                repositoryData.update(mapperUtil.convertToIncome(dto), params.getFile());
                updateTotal(params.getDialogStage(), params.getFile());
                log.debug(SERVICE_LOGS + "редактировать запись " + dto.toString());
                return true;
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }
        log.debug(SERVICE_LOGS + "запись не редактирована");
        return false;
    }

    @Override
    public boolean deleteRow(ServiceDeleteRowDto params) {
        ValidationIndexDto<Income> validationIndexDto = new ValidationIndexDto<>(
                repositoryData,
                params.getIndexField().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        DeleteRowDto<Income> dto = new DeleteRowDto<>(params,
                validationIndexDto,
                c -> c.updateTotal(params.getDialogStage(), params.getFile()),
                this);
        return deleteRowUtil.deleteRow(dto);
    }

    @Override
    public List<IncomeDto> getAll(File file) {
        try {
            return repositoryData.getAll(file)
                    .stream()
                    .map(mapperUtil::convertToIncomeDto)
                    .collect(Collectors.toList());
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            return null;
        }
    }

    @Override
    public void setTotalByCategory(File file) {
        Consumer<Map.Entry<String, List<IncomeDto>>> consumer = o -> {
            double sum = o.getValue().stream().mapToDouble(IncomeDto::getSum).sum();
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_BY + o.getKey())
                    .setSumFact(sum)
                    .setType(EMPTY)
                    .build();
            try {
                repositoryData.save(mapperUtil.convertToIncome(dto), file);
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(e.getStackTrace());
            }
        };
        TotalServiceByCategoryDto<IncomeDto, Income> dto = new TotalServiceByCategoryDto<>(file, this, repositoryData, consumer);
        totalServiceUtil.setTotalByCategory(dto);
    }

    @Override
    public void setTotalAll(File file) {
        List<IncomeDto> allIncomes = getAll(file);
        allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(dto -> {
                    try {
                        repositoryData.deleteByCategory(dto.getCategory(), file);
                    } catch (RepositoryException e) {
                        log.error(e.getMessage());
                        log.error(e.getStackTrace());
                    }
                });
        double totalAll = allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(IncomeDto::getSum)
                .sum();
        if (totalAll != 0.0) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_ALL)
                    .setSumFact(totalAll)
                    .setType(EMPTY)
                    .build();
            try {
                repositoryData.save(mapperUtil.convertToIncome(dto), file);
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(e.getStackTrace());
            }
        }
    }

    @Override
    public void updateTotal(Stage stage, File file) {
        TotalServiceUpdateDto dto = new TotalServiceUpdateDto(this, stage, file);
        totalServiceUtil.updateTotal(dto);
    }

}
