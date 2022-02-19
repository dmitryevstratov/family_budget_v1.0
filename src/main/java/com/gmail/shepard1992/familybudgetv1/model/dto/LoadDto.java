package com.gmail.shepard1992.familybudgetv1.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import javafx.scene.control.TableView;

import java.io.File;

public class LoadDto<D> {

    private final Service<D> service;
    private final TableView<D> tableView;
    private final File file;

    public LoadDto(Service<D> service, TableView<D> tableView, File file) {
        this.service = service;
        this.tableView = tableView;
        this.file = file;
    }

    public Service<D> getDtoService() {
        return service;
    }

    public TableView<D> getTable() {
        return tableView;
    }

    public File getFile() {
        return file;
    }
}
