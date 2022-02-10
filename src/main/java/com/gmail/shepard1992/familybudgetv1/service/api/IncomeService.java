package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.incomeService.ServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.incomeService.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.incomeService.ServiceUpdateRowDto;

import java.io.File;
import java.util.List;

public interface IncomeService {

    boolean addRow(ServiceAddRowDto params);

    boolean updateRow(ServiceUpdateRowDto params);

    boolean deleteRow(ServiceDeleteRowDto params);

    List<IncomeDto> getAll(File file);

}
