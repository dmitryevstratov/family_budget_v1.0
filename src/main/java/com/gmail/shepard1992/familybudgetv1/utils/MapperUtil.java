package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import org.springframework.stereotype.Component;

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
                .setSum(income.getSum())
                .build();
    }

}
