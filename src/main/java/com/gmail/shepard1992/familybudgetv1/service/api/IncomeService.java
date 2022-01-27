package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;

import java.io.File;
import java.util.List;

public interface IncomeService {

    boolean addRow(ParamsForServiceAddRowDto params);

    boolean updateRow();

    boolean deleteRow(Integer index, File file);

    List<IncomeDto> getAll(File file);

}
