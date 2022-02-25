package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceNewRowDto;

import java.io.File;
import java.util.List;

public interface Service<D> {

    boolean addRow(ServiceNewRowDto params);

    boolean updateRow(ServiceNewRowDto params);

    boolean deleteRow(ServiceDeleteRowDto params);

    List<D> getAll(File file);

}
