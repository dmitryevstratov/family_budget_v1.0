package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.model.api.Model;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.DeleteRowDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;

@Component
public class DeleteRowUtil<M extends Model> {

    private final ValidationUtil validationUtil;
    private final RepositoryData<M> repositoryData;
    private static final Logger log = Logger.getLogger(DeleteRowUtil.class.getName());

    @Autowired
    public DeleteRowUtil(ValidationUtil validationUtil, RepositoryData<M> repositoryData) {
        this.validationUtil = validationUtil;
        this.repositoryData = repositoryData;
    }

    public boolean deleteRow(DeleteRowDto<M> deleteRowDto) {
        try {
            if (validationUtil.isInputDeleteValid(deleteRowDto.getParams()) && validationUtil.isIndexValid(deleteRowDto.getValidationIndexDto())) {
                boolean deleteByIndex = repositoryData.deleteByIndex(Integer.parseInt(deleteRowDto.getParams().getIndexField().getText()), deleteRowDto.getParams().getFile());
                deleteRowDto.getConsumer().accept(deleteRowDto.getTotalService());
                log.debug(SERVICE_LOGS + "удалить запись " + deleteRowDto.getParams().getIndexField().getText());
                return deleteByIndex;
            } else {
                log.debug(SERVICE_LOGS + "не удалена запись " + deleteRowDto.getParams().getIndexField().getText());
                return false;
            }
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

}
