package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.api.AbstractServiceRowDto;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class ServiceNewRowDto extends AbstractServiceRowDto {

    private final TextField category;
    private final TextField type;
    private final TextField sumFact;
    private final TextField sumPlan;

    private ServiceNewRowDto(ServiceNewRowDtoBuilder builder) {
        super(builder.indexField, builder.dialogStage, builder.file);
        this.category = builder.category;
        this.type = builder.type;
        this.sumFact = builder.sumFact;
        this.sumPlan = builder.sumPlan;
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
        return super.getDialogStage();
    }

    public File getFile() {
        return super.getFile();
    }

    public TextField getIndex() {
        return super.getIndexField();
    }

    public TextField getSumPlan() {
        return sumPlan;
    }

    public static class ServiceNewRowDtoBuilder {
        private TextField indexField;
        private TextField category;
        private TextField type;
        private TextField sumFact;
        private TextField sumPlan;
        private Stage dialogStage;
        private File file;

        public ServiceNewRowDtoBuilder setIndex(TextField indexField) {
            this.indexField = indexField;
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
