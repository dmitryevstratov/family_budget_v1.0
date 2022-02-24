package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.api.Dto;
import com.gmail.shepard1992.familybudgetv1.model.dto.util.TotalServiceByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.util.TotalServiceUpdateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.constants.ServiceConstants.TOTAL_ALL;
import static com.gmail.shepard1992.familybudgetv1.constants.ServiceConstants.TOTAL_BY;

@Component
public class TotalServiceUtil {

    public <D extends Dto, E> void setTotalByCategory(TotalServiceByCategoryDto<D, E> totalDto) {
        List<D> all = totalDto.getService().getAll(totalDto.getFile());
        all.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .forEachOrdered(dto -> totalDto.getRepository().deleteByCategory(dto.getCategory(), totalDto.getFile()));
        Map<String, List<D>> groupByCategory = all.stream()
                .filter(dto -> !dto.getCategory().contains(TOTAL_BY))
                .filter(dto -> !dto.getCategory().contains(TOTAL_ALL))
                .collect(Collectors.groupingBy(D::getCategory));
        for (Map.Entry<String, List<D>> stringListEntry : groupByCategory.entrySet()) {
            totalDto.getConsumer().accept(stringListEntry);
        }
    }

    public void updateTotal(TotalServiceUpdateDto dto) {
        dto.getStage().close();
        dto.getService().setTotalByCategory(dto.getFile());
        dto.getService().setTotalAll(dto.getFile());
    }
}
