package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MapperUtil {

    public Income convertToIncome(IncomeDto incomeDto) {
        return new Income.IncomeBuilder()
                .setIndex(incomeDto.getIndex())
                .setCategory(incomeDto.getCategory())
                .setType(incomeDto.getType())
                .setSum(incomeDto.getSum())
                .build();
    }

    public IncomeDto convertToIncomeDto(Income income) {
        return new IncomeDto.IncomeDtoBuilder()
                .setIndex(income.getIndex())
                .setCategory(income.getCategory())
                .setType(income.getType())
                .setSumFact(income.getSumFact())
                .build();
    }

    public Cost convertToCost(CostDto costDto) {
        return new Cost.CostBuilder()
                .setIndex(costDto.getIndex())
                .setCategory(costDto.getCategory())
                .setType(costDto.getType())
                .setSumPlan(costDto.getSumPlan())
                .setSumFact(costDto.getSumFact())
                .build();
    }

    public CostDto convertToCostDto(Cost cost) {
        return new CostDto.CostDtoBuilder()
                .setIndex(cost.getIndex())
                .setCategory(cost.getCategory())
                .setType(cost.getType())
                .setSumPlan(cost.getSumPlan())
                .setSumFact(cost.getSumFact())
                .build();
    }

    public ObservableList<Integer> getObservableListRange(Integer start, Integer end) {
        return FXCollections.observableArrayList(IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList()));
    }

}
