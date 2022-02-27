package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
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
public class IncomeServiceImpl implements Service<IncomeDto>, TotalService {

    private final ValidationUtil validationUtil;
    private final Repository<Income> repository;
    private final MapperUtil mapperUtil;
    private final IndexUtil<IncomeDto> indexUtil;
    private final TotalServiceUtil totalServiceUtil;

    @Autowired
    public IncomeServiceImpl(ValidationUtil validationUtil, Repository<Income> repository, MapperUtil mapperUtil, IndexUtil<IncomeDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        this.validationUtil = validationUtil;
        this.repository = repository;
        this.mapperUtil = mapperUtil;
        this.indexUtil = indexUtil;
        this.totalServiceUtil = totalServiceUtil;
    }

    @Override
    public boolean addRow(ServiceNewRowDto params) {
        if (validationUtil.isInputAddValid(params)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSumFact(Double.parseDouble(params.getSumFact().getText()))
                    .setType(params.getType().getText()).build();
            repository.save(mapperUtil.convertToIncome(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateRow(ServiceNewRowDto params) {
        ValidationIndexDto<Income> validationIndexDto = new ValidationIndexDto<>(
                repository,
                params.getIndex().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        if (validationUtil.isInputUpdateValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setIndex(params.getIndex().getText())
                    .setCategory(params.getCategory().getText())
                    .setType(params.getType().getText())
                    .build();
            if (!params.getSumFact().getText().isEmpty()) {
                dto.setIncomeSum(Double.parseDouble(params.getSumFact().getText()));
            }
            repository.update(mapperUtil.convertToIncome(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRow(ServiceDeleteRowDto params) {
        ValidationIndexDto<Income> validationIndexDto = new ValidationIndexDto<>(
                repository,
                params.getIndexField().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        if (validationUtil.isInputDeleteValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
            boolean deleteByIndex = repository.deleteByIndex(Integer.parseInt(params.getIndexField().getText()), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return deleteByIndex;
        } else {
            return false;
        }
    }

    @Override
    public List<IncomeDto> getAll(File file) {
        return repository.getAll(file)
                .stream()
                .map(mapperUtil::convertToIncomeDto)
                .collect(Collectors.toList());
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
            repository.save(mapperUtil.convertToIncome(dto), file);
        };
        TotalServiceByCategoryDto<IncomeDto, Income> dto = new TotalServiceByCategoryDto<>(file, this, repository, consumer);
        totalServiceUtil.setTotalByCategory(dto);
    }

    @Override
    public void setTotalAll(File file) {
        List<IncomeDto> allIncomes = getAll(file);
        allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(dto -> repository.deleteByCategory(dto.getCategory(), file));
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
            repository.save(mapperUtil.convertToIncome(dto), file);
        }
    }

    @Override
    public void updateTotal(Stage stage, File file) {
        TotalServiceUpdateDto dto = new TotalServiceUpdateDto(this, stage, file);
        totalServiceUtil.updateTotal(dto);
    }

}
