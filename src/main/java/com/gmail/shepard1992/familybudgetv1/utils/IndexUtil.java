package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.constants.ServiceConstants.*;

@Component
public class IndexUtil {

    public String incrementIndex(List<IncomeDto> incomeDtoList) {
        if (incomeDtoList.isEmpty()) {
            return ZERO;
        } else {
            List<IncomeDto> collect = incomeDtoList.stream()
                    .filter(dto -> !dto.getCategory().contains(TOTAL_BY))
                    .filter(dto -> !dto.getCategory().contains(TOTAL_ALL))
                    .collect(Collectors.toList());
            return String.valueOf((Integer.parseInt(collect.get(collect.size() - 1).getIndex()) + 1));
        }
    }

}
