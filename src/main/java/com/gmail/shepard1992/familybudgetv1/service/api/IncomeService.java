package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceUpdateRowDto;

import java.io.File;
import java.util.List;

public interface IncomeService {

    boolean addRow(ParamsForServiceAddRowDto params);

    boolean updateRow(ParamsForServiceUpdateRowDto params);

    boolean deleteRow(ParamsForServiceDeleteRowDto params);

    List<IncomeDto> getAll(File file);

}
