package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.TotalServiceByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.TotalServiceUpdateDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.ValidationIndexDto;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.TotalServiceUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceNewRowDto;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.*;

@org.springframework.stereotype.Service
public class CostServiceImpl implements Service<CostDto>, TotalService {

    private final ValidationUtil validationUtil;
    private final RepositoryData<Cost> repositoryData;
    private final MapperUtil mapperUtil;
    private final IndexUtil<CostDto> indexUtil;
    private final TotalServiceUtil totalServiceUtil;

    @Autowired
    public CostServiceImpl(ValidationUtil validationUtil, RepositoryData<Cost> repositoryData, MapperUtil mapperUtil, IndexUtil<CostDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        this.validationUtil = validationUtil;
        this.repositoryData = repositoryData;
        this.mapperUtil = mapperUtil;
        this.indexUtil = indexUtil;
        this.totalServiceUtil = totalServiceUtil;
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
            repositoryData.save(mapperUtil.convertToCost(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        } else {
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
        if (validationUtil.isInputUpdateValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
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
            repositoryData.update(mapperUtil.convertToCost(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        }
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
        if (validationUtil.isInputDeleteValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
            boolean deleteByIndex = repositoryData.deleteByIndex(Integer.parseInt(params.getIndexField().getText()), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return deleteByIndex;
        } else {
            return false;
        }
    }

    @Override
    public List<CostDto> getAll(File file) {
        return repositoryData.getAll(file)
                .stream()
                .map(mapperUtil::convertToCostDto)
                .collect(Collectors.toList());
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
                    .build();
            repositoryData.save(mapperUtil.convertToCost(dto), file);
        };
        TotalServiceByCategoryDto<CostDto, Cost> dto = new TotalServiceByCategoryDto<>(file, this, repositoryData, consumer);
        totalServiceUtil.setTotalByCategory(dto);
    }

    @Override
    public void setTotalAll(File file) {
        List<CostDto> allCosts = getAll(file);
        allCosts.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(dto -> repositoryData.deleteByCategory(dto.getCategory(), file));
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

        CostDto dto = new CostDto.CostDtoBuilder()
                .setIndex(EMPTY)
                .setCategory(TOTAL_ALL)
                .setSumFact(totalFactAll)
                .setSumPlan(totalPlanAll)
                .setType(EMPTY)
                .build();
        repositoryData.save(mapperUtil.convertToCost(dto), file);

    }

    @Override
    public void updateTotal(Stage stage, File file) {
        TotalServiceUpdateDto dto = new TotalServiceUpdateDto(this, stage, file);
        totalServiceUtil.updateTotal(dto);
    }

}
