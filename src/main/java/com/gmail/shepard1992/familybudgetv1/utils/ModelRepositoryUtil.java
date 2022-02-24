package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.api.Model;
import com.gmail.shepard1992.familybudgetv1.model.dto.repository.RepositoryDeleteByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.repository.RepositoryDeleteByIndexDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.repository.RepositoryUpdateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.constants.ServiceConstants.EMPTY;

@Component
public class ModelRepositoryUtil {

    public <E extends Model> void update(RepositoryUpdateDto<E> dto) {
        List<E> list = dto.getRepository().getAll(dto.getFile());
        if (list.stream().anyMatch(inc -> inc.getIndex().equals(dto.getElement().getIndex()))) {
            for (E inc : list) {
                if (inc.getIndex().equals(dto.getElement().getIndex())) {
                    dto.getConsumer().accept(inc);
                }
            }
            dto.getRepository().clear(dto.getFile());
            list.forEach(inc -> dto.getRepository().save(inc, dto.getFile()));
        }
    }

    public <E extends Model> boolean deleteByIndex(RepositoryDeleteByIndexDto<E> dto) {
        List<E> list = dto.getRepository().getAll(dto.getFile());
        List<E> listToSave = list.stream()
                .filter(inc -> !inc.getIndex().equals(dto.getIndex().toString()))
                .collect(Collectors.toList());
        if (list.size() > listToSave.size()) {
            dto.getRepository().clear(dto.getFile());
            for (int j = 0; j < listToSave.size(); j++) {
                E element = listToSave.get(j);
                if (!element.getIndex().equals(j + EMPTY)) {
                    element.setModelIndex(j + EMPTY);
                }
                dto.getRepository().save(element, dto.getFile());
            }
            return true;
        }
        return false;
    }

    public <E extends Model> void deleteByCategory(RepositoryDeleteByCategoryDto<E> dto) {
        List<E> list = dto.getRepository().getAll(dto.getFile());
        if (list.stream().anyMatch(inc -> inc.getCategory().equals(dto.getCategory()))) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCategory().equals(dto.getCategory())) {
                    list.remove(i);
                    dto.getRepository().clear(dto.getFile());
                    list.forEach((cost -> dto.getRepository().save(cost, dto.getFile())));
                }
            }
        }
    }

}
