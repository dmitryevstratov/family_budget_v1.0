package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.impl.CreateReportServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.TotalServiceByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.TotalServiceUpdateDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.TOTAL_ALL;
import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.TOTAL_BY;

@Component
public class TotalServiceUtil {

    private static final Logger log = Logger.getLogger(TotalServiceUtil.class.getName());

    public <D extends AbstractDto, E> void setTotalByCategory(TotalServiceByCategoryDto<D, E> totalDto) {
        List<D> all = totalDto.getService().getAll(totalDto.getFile());
        all.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .forEachOrdered(dto -> {
                    try {
                        totalDto.getRepositoryData().deleteByCategory(dto.getCategory(), totalDto.getFile());
                    } catch (RepositoryException e) {
                        log.error(e.getMessage());
                        log.error(e.getStackTrace());
                    }
                });
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
