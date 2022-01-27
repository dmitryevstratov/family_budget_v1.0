package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.model.Income;

import java.io.File;
import java.util.List;

public interface IncomeRepository {

    void save(Income income, File file);

    Income update(Income income);

    boolean deleteByIndex(Integer index, File file);

    List<Income> getAll(File file);

    void clear(File file);

    void deleteByCategory(String category, File file);

}
