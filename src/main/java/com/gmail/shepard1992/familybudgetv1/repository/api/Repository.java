package com.gmail.shepard1992.familybudgetv1.repository.api;

import java.io.File;
import java.util.List;

public interface Repository<E> {

    boolean save(E element, File file);

    void update(E element, File file);

    boolean deleteByIndex(Integer index, File file);

    List<E> getAll(File file);

    void clear(File file);

    void deleteByCategory(String category, File file);

}
