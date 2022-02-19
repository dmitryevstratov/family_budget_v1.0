package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.service.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.service.ServiceNewRowDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.constants.ServiceConstants.*;

@org.springframework.stereotype.Service
public class CostServiceImpl implements Service<CostDto>, TotalService {

    private final ValidationUtil validationUtil;
    private final Repository<Cost> repository;
    private final MapperUtil mapperUtil;
    private final IndexUtil<CostDto> indexUtil;

    @Autowired
    public CostServiceImpl(ValidationUtil validationUtil, Repository<Cost> repository, MapperUtil mapperUtil, IndexUtil<CostDto> indexUtil) {
        this.validationUtil = validationUtil;
        this.repository = repository;
        this.mapperUtil = mapperUtil;
        this.indexUtil = indexUtil;
    }

    @Override
    public boolean addRow(ServiceNewRowDto params) {
        if (validationUtil.isInputAddValid(params)) {
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSumFact(Double.parseDouble(params.getSumFact().getText()))
                    .setSumPlan(Double.parseDouble(params.getSumPlan().getText()))
                    .setType(params.getType().getText()).build();
            repository.save(mapperUtil.convertToCost(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateRow(ServiceNewRowDto params) {
        if (validationUtil.isInputUpdateValid(params)) {
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setIndex(params.getIndex().getText())
                    .setCategory(params.getCategory().getText())
                    .setType(params.getType().getText())
                    .build();
            if (!params.getSumFact().getText().isEmpty()) {
                dto.setCostSumFact(Double.parseDouble(params.getSumFact().getText()));
            }
            if (!params.getSumPlan().getText().isEmpty()) {
                dto.setCostSumPlan(Double.parseDouble(params.getSumPlan().getText()));
            }
            repository.update(mapperUtil.convertToCost(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRow(ServiceDeleteRowDto params) {
        if (validationUtil.isInputDeleteValid(params)) {
            boolean deleteByIndex = repository.deleteByIndex(Integer.parseInt(params.getIndexField().getText()), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return deleteByIndex;
        } else {
            return false;
        }
    }

    @Override
    public List<CostDto> getAll(File file) {
        return repository.getAll(file)
                .stream()
                .map(mapperUtil::convertToCostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void setTotalByCategory(File file) {
        List<CostDto> allCosts = getAll(file);
        allCosts.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .forEachOrdered(incomeDto -> repository.deleteByCategory(incomeDto.getCategory(), file));
        Map<String, List<CostDto>> groupByCategory = allCosts.stream()
                .filter(dto -> !dto.getCategory().contains(TOTAL_BY))
                .filter(dto -> !dto.getCategory().contains(TOTAL_ALL))
                .collect(Collectors.groupingBy(CostDto::getCategory));
        for (Map.Entry<String, List<CostDto>> stringListEntry : groupByCategory.entrySet()) {
            double sumFact = stringListEntry.getValue().stream().mapToDouble(CostDto::getSumFact).sum();
            double sumPlan = stringListEntry.getValue().stream().mapToDouble(CostDto::getSumPlan).sum();
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_BY + stringListEntry.getKey())
                    .setSumFact(sumFact)
                    .setSumPlan(sumPlan)
                    .setType(EMPTY)
                    .build();
            repository.save(mapperUtil.convertToCost(dto), file);
        }
    }

    @Override
    public void setTotalAll(File file) {
        List<CostDto> allCosts = getAll(file);
        allCosts.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(dto -> repository.deleteByCategory(dto.getCategory(), file));
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
        if (totalPlanAll != 0.0 && totalFactAll != 0.0) {
            CostDto dto = new CostDto.CostDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_ALL)
                    .setSumFact(totalFactAll)
                    .setSumPlan(totalPlanAll)
                    .setType(EMPTY)
                    .build();
            repository.save(mapperUtil.convertToCost(dto), file);
        }
    }

    private void updateTotal(Stage stage, File file) {
        stage.close();
        setTotalByCategory(file);
        setTotalAll(file);
    }

}
