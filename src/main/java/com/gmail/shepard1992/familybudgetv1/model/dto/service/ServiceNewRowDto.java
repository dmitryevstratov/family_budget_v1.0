package com.gmail.shepard1992.familybudgetv1.model.dto.service;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class ServiceNewRowDto {

    private final TextField index;
    private final TextField category;
    private final TextField type;
    private final TextField sumFact;
    private final TextField sumPlan;
    private final Stage dialogStage;
    private final File file;

    private ServiceNewRowDto(ServiceNewRowDtoBuilder builder) {
        this.index = builder.index;
        this.category = builder.category;
        this.type = builder.type;
        this.sumFact = builder.sumFact;
        this.sumPlan = builder.sumPlan;
        this.dialogStage = builder.dialogStage;
        this.file = builder.file;
    }

    public TextField getCategory() {
        return category;
    }

    public TextField getType() {
        return type;
    }

    public TextField getSumFact() {
        return sumFact;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public File getFile() {
        return file;
    }

    public TextField getIndex() {
        return index;
    }

    public TextField getSumPlan() {
        return sumPlan;
    }

    public static class ServiceNewRowDtoBuilder {
        private TextField index;
        private TextField category;
        private TextField type;
        private TextField sumFact;
        private TextField sumPlan;
        private Stage dialogStage;
        private File file;

        public ServiceNewRowDtoBuilder setIndex(TextField index) {
            this.index = index;
            return this;
        }

        public ServiceNewRowDtoBuilder setCategory(TextField category) {
            this.category = category;
            return this;
        }

        public ServiceNewRowDtoBuilder setType(TextField type) {
            this.type = type;
            return this;
        }

        public ServiceNewRowDtoBuilder setSumFact(TextField sumFact) {
            this.sumFact = sumFact;
            return this;
        }

        public ServiceNewRowDtoBuilder setSumPlan(TextField sumPlan) {
            this.sumPlan = sumPlan;
            return this;
        }

        public ServiceNewRowDtoBuilder setDialogStage(Stage dialogStage) {
            this.dialogStage = dialogStage;
            return this;
        }

        public ServiceNewRowDtoBuilder setFile(File file) {
            this.file = file;
            return this;
        }

        public ServiceNewRowDto build() {
            return new ServiceNewRowDto(this);
        }

    }

}
