package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.IncomeRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
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
        if (validationUtil.isInputValid(params)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSum(Double.parseDouble(params.getSum().getText()))
                    .setType(params.getType().getText()).build();
            repository.save(mapperUtil.convertToIncome(dto), params.getFile());
            params.getDialogStage().close();
            setTotalByCategory(params.getFile());
            setTotalAll(params.getFile());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateRow() {
        return false;
    }

    @Override
    public boolean deleteRow(Integer index, File file) {
        //ToDo переращет индексов
        return repository.deleteByIndex(index, file);
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
        repository.save(new Income.IncomeBuilder()
                .setIndex(EMPTY)
                .setCategory(TOTAL_ALL)
                .setSum(totalAll)
                .setType(EMPTY)
                .build(), file);
    }

}
