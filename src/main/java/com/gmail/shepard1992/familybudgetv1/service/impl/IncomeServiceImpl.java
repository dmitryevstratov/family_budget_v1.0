package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceUpdateRowDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.IncomeRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.constants.ServiceConstants.*;

@Service
public class IncomeServiceImpl implements IncomeService, TotalService {

    private final ValidationUtil validationUtil;
    private final IncomeRepository repository;
    private final MapperUtil mapperUtil;
    private final IndexUtil indexUtil;

    @Autowired
    public IncomeServiceImpl(ValidationUtil validationUtil, IncomeRepository repository, MapperUtil mapperUtil, IndexUtil indexUtil) {
        this.validationUtil = validationUtil;
        this.repository = repository;
        this.mapperUtil = mapperUtil;
        this.indexUtil = indexUtil;
    }

    @Override
    public boolean addRow(ParamsForServiceAddRowDto params) {
        if (validationUtil.isInputAddValid(params)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSum(Double.parseDouble(params.getSum().getText()))
                    .setType(params.getType().getText()).build();
            repository.save(mapperUtil.convertToIncome(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateRow(ParamsForServiceUpdateRowDto params) {
        if (validationUtil.isInputUpdateValid(params)) {
            Income income = new Income.IncomeBuilder()
                    .setIndex(params.getIndex().getText())
                    .setCategory(params.getCategory().getText())
                    .setType(params.getType().getText())
                    .build();
            if (!params.getSum().getText().isEmpty()) {
                income.setIncomeSum(Double.parseDouble(params.getSum().getText()));
            }
            repository.update(income, params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            return true;
        }
        return false;
    }

    private void updateTotal(Stage params, File file) {
        params.close();
        setTotalByCategory(file);
        setTotalAll(file);
    }

    @Override
    public boolean deleteRow(ParamsForServiceDeleteRowDto params) {
        if (validationUtil.isInputDeleteValid(params)) {
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
        List<IncomeDto> allIncomes = getAll(file);
        allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .forEachOrdered(incomeDto -> repository.deleteByCategory(incomeDto.getCategory(), file));
        Map<String, List<IncomeDto>> groupByCategory = allIncomes.stream()
                .filter(incomeDto -> !incomeDto.getCategory().contains(TOTAL_BY))
                .filter(incomeDto -> !incomeDto.getCategory().contains(TOTAL_ALL))
                .collect(Collectors.groupingBy(IncomeDto::getCategory));
        for (Map.Entry<String, List<IncomeDto>> stringListEntry : groupByCategory.entrySet()) {
            double sum = stringListEntry.getValue().stream().mapToDouble(IncomeDto::getSum).sum();
            repository.save(new Income.IncomeBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_BY + stringListEntry.getKey())
                    .setSum(sum)
                    .setType(EMPTY)
                    .build(), file);
        }
    }

    @Override
    public void setTotalAll(File file) {
        List<IncomeDto> allIncomes = getAll(file);
        allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(incomeDto -> repository.deleteByCategory(incomeDto.getCategory(), file));
        double totalAll = allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(IncomeDto::getSum)
                .sum();
        if (totalAll != 0.0) {
            repository.save(new Income.IncomeBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_ALL)
                    .setSum(totalAll)
                    .setType(EMPTY)
                    .build(), file);
        }
    }

}
