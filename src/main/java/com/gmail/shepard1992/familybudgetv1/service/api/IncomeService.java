package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceDeleteRowDto;

import java.io.File;
import java.util.List;

public interface IncomeService {

    boolean addRow(ParamsForServiceAddRowDto params);

    boolean updateRow();

    boolean deleteRow(ParamsForServiceDeleteRowDto params);

    List<IncomeDto> getAll(File file);

}
