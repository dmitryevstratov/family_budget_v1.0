package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.*;
import com.gmail.shepard1992.familybudgetv1.utils.*;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceNewRowDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;
import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.*;

@org.springframework.stereotype.Service
public class CostServiceImpl implements Service<CostDto>, TotalService {

    private final ValidationUtil validationUtil;
    private final RepositoryData<Cost> repositoryData;
    private final MapperUtil mapperUtil;
    private final IndexUtil<CostDto> indexUtil;
    private final TotalServiceUtil totalServiceUtil;
    private final DeleteRowUtil<Cost> deleteRowUtil;
    private static final Logger log = Logger.getLogger(CostServiceImpl.class.getName());

    @Autowired
    public CostServiceImpl(ValidationUtil validationUtil, RepositoryData<Cost> repositoryData, MapperUtil mapperUtil, IndexUtil<CostDto> indexUtil, TotalServiceUtil totalServiceUtil, DeleteRowUtil<Cost> deleteRowUtil) {
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
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSumFact(Double.parseDouble(params.getSumFact().getText()))
                    .setSumPlan(Double.parseDouble(params.getSumPlan().getText()))
                    .setType(params.getType().getText())
                    .setIsBigPurchase(String.valueOf(params.getIsBigPurchase().isSelected()))
                    .build();
            try {
                repositoryData.save(mapperUtil.convertToCost(dto), params.getFile());
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(Arrays.toString(e.getStackTrace()));
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
        ValidationIndexDto<Cost> validationIndexDto = new ValidationIndexDto<>(
                repositoryData,
                params.getIndex().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        try {
            if (validationUtil.isInputUpdateValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
                CostDto dto = new CostDto.CostDtoBuilder()
                        .setIndex(params.getIndex().getText())
                        .setCategory(params.getCategory().getText())
                        .setType(params.getType().getText())
                        .setIsBigPurchase(String.valueOf(params.getIsBigPurchase().isSelected()))
                        .build();
                if (!params.getSumFact().getText().isEmpty()) {
                    dto.setCostSumFact(Double.parseDouble(params.getSumFact().getText()));
                }
                if (!params.getSumPlan().getText().isEmpty()) {
                    dto.setCostSumPlan(Double.parseDouble(params.getSumPlan().getText()));
                }
                repositoryData.update(mapperUtil.convertToCost(dto), params.getFile());
                updateTotal(params.getDialogStage(), params.getFile());
                log.debug(SERVICE_LOGS + "редактировать запись " + dto.toString());
                return true;
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
        log.debug(SERVICE_LOGS + "запись не редактирована");
        return false;
    }

    @Override
    public boolean deleteRow(ServiceDeleteRowDto params) {
        ValidationIndexDto<Cost> validationIndexDto = new ValidationIndexDto<>(
                repositoryData,
                params.getIndexField().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        DeleteRowDto<Cost> deleteRowDto = new DeleteRowDto<>(params,
                validationIndexDto,
                c -> c.updateTotal(params.getDialogStage(), params.getFile()),
                this);
        return deleteRowUtil.deleteRow(deleteRowDto);
    }

    @Override
    public List<CostDto> getAll(File file) {
        try {
            return repositoryData.getAll(file)
                    .stream()
                    .map(mapperUtil::convertToCostDto)
                    .collect(Collectors.toList());
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    public void setTotalByCategory(File file) {
        Consumer<Map.Entry<String, List<CostDto>>> consumer = o -> {
            double sumFact = o.getValue().stream().mapToDouble(CostDto::getSumFact).sum();
            double sumPlan = o.getValue().stream().mapToDouble(CostDto::getSumPlan).sum();
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_BY + o.getKey())
                    .setSumFact(sumFact)
                    .setSumPlan(sumPlan)
                    .setType(EMPTY)
                    .setIsBigPurchase(EMPTY)
                    .build();
            try {
                repositoryData.save(mapperUtil.convertToCost(dto), file);
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(Arrays.toString(e.getStackTrace()));
            }
        };
        TotalServiceByCategoryDto<CostDto, Cost> dto = new TotalServiceByCategoryDto<>(file, this, repositoryData, consumer);
        totalServiceUtil.setTotalByCategory(dto);
    }

    @Override
    public void setTotalAll(File file) {
        List<CostDto> allCosts = getAll(file);
        allCosts.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(dto -> {
                    try {
                        repositoryData.deleteByCategory(dto.getCategory(), file);
                    } catch (RepositoryException e) {
                        log.error(e.getMessage());
                        log.error(Arrays.toString(e.getStackTrace()));
                    }
                });
        double totalFactAll = allCosts.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(CostDto::getSumFact)
                .sum();
        double totalPlanAll = allCosts.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(CostDto::getSumPlan)
                .sum();
        if (getAll(file).size() > 0) {
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_ALL)
                    .setSumFact(totalFactAll)
                    .setSumPlan(totalPlanAll)
                    .setType(EMPTY)
                    .setIsBigPurchase(EMPTY)
                    .build();
            try {
                repositoryData.save(mapperUtil.convertToCost(dto), file);
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    @Override
    public void updateTotal(Stage stage, File file) {
        TotalServiceUpdateDto dto = new TotalServiceUpdateDto(this, stage, file);
        totalServiceUtil.updateTotal(dto);
    }

}
