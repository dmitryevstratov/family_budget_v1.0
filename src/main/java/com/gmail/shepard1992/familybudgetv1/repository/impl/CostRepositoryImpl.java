package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;

import java.io.File;
import java.util.List;

public class CostRepositoryImpl implements Repository<Cost> {

    private final FileUtil fileUtil;

    public CostRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean save(Cost element, File file) {
        return false;
    }

    @Override
    public void update(Cost element, File file) {

    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        return false;
    }

    @Override
    public List<Cost> getAll(File file) {
        return null;
    }

    @Override
    public void clear(File file) {

    }

    @Override
    public void deleteByCategory(String category, File file) {

    }

}
