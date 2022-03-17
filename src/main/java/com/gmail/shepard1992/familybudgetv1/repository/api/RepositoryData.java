package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;

import java.io.File;
import java.util.List;

public interface RepositoryData<E> {

    boolean save(E element, File file) throws RepositoryException;

    void update(E element, File file) throws RepositoryException;

    boolean deleteByIndex(Integer index, File file) throws RepositoryException;

    List<E> getAll(File file) throws RepositoryException;

    void clear(File file) throws RepositoryException;

    void deleteByCategory(String category, File file) throws RepositoryException;

}
