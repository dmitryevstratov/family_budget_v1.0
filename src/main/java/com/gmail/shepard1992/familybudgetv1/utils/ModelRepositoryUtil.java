package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.repository.impl.CostRepositoryDataImpl;
import com.gmail.shepard1992.familybudgetv1.service.model.api.Model;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryDeleteByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryDeleteByIndexDto;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryUpdateDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.repository.constants.Logs.REPOSITORY_LOGS;
import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.EMPTY;

@Component
public class ModelRepositoryUtil {

    private static final Logger log = Logger.getLogger(ModelRepositoryUtil.class.getName());

    public <E extends Model> void update(RepositoryUpdateDto<E> dto) throws RepositoryException {
        List<E> list = dto.getRepositoryData().getAll(dto.getFile());
        if (list.stream().anyMatch(inc -> inc.getIndex().equals(dto.getElement().getIndex()))) {
            for (E inc : list) {
                if (inc.getIndex().equals(dto.getElement().getIndex())) {
                    dto.getConsumer().accept(inc);
                }
            }
            dto.getRepositoryData().clear(dto.getFile());
            list.forEach(inc -> {
                try {
                    dto.getRepositoryData().save(inc, dto.getFile());
                } catch (RepositoryException e) {
                    log.error(e.getMessage());
                    log.error(e.getStackTrace());
                }
            });
            log.debug(REPOSITORY_LOGS + "редактирование модели " + dto.getElement().toString());
        }
        log.debug(REPOSITORY_LOGS + "модель не редактировалась " + dto.getElement().toString());
    }

    public <E extends Model> boolean deleteByIndex(RepositoryDeleteByIndexDto<E> dto) throws RepositoryException {
        List<E> list = dto.getRepositoryData().getAll(dto.getFile());
        List<E> listToSave = list.stream()
                .filter(inc -> !inc.getIndex().equals(dto.getIndex().toString()))
                .collect(Collectors.toList());
        if (list.size() > listToSave.size()) {
            dto.getRepositoryData().clear(dto.getFile());
            for (int j = 0; j < listToSave.size(); j++) {
                E element = listToSave.get(j);
                if (!element.getIndex().equals(j + EMPTY)) {
                    element.setModelIndex(j + EMPTY);
                }
                dto.getRepositoryData().save(element, dto.getFile());
            }
            log.debug(REPOSITORY_LOGS + "удаление модели по индексу " + dto.getIndex());
            return true;
        }
        log.debug(REPOSITORY_LOGS + "модель по индексу не удалена " + dto.getIndex());
        return false;
    }

    public <E extends Model> void deleteByCategory(RepositoryDeleteByCategoryDto<E> dto) throws RepositoryException {
        List<E> list = dto.getRepositoryData().getAll(dto.getFile());
        if (list.stream().anyMatch(inc -> inc.getCategory().equals(dto.getCategory()))) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCategory().equals(dto.getCategory())) {
                    list.remove(i);
                    dto.getRepositoryData().clear(dto.getFile());
                    list.forEach((cost -> {
                        try {
                            dto.getRepositoryData().save(cost, dto.getFile());
                        } catch (RepositoryException e) {
                            log.error(e.getMessage());
                            log.error(e.getStackTrace());
                        }
                    }));
                }
            }
        }
    }

}
