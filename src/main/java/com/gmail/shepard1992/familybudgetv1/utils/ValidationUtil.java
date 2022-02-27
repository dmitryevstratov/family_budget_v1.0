package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceNewRowDto;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public boolean isInputDeleteValid(ServiceDeleteRowDto params) {
        StringBuilder errorMessage = new StringBuilder()
                .append(checkAddNumField(params.getIndexField(), "Номер строки"));

        return checkErrorMessage(errorMessage, params.getDialogStage());
    }

    public boolean isInputAddValid(ServiceNewRowDto params) {
        StringBuilder errorMessage = new StringBuilder()
                .append(checkAddTextField(params.getCategory(), "Категория"))
                .append(checkAddTextField(params.getType(), "Тип"))
                .append(checkAddNumField(params.getSumFact(), "Сумма (фактическая)"))
                .append(checkAddNumField(params.getSumPlan(), "Сумма (плановая)"));

        return checkErrorMessage(errorMessage, params.getDialogStage());
    }

    public boolean isInputUpdateValid(ServiceNewRowDto params) {
        StringBuilder errorMessage = new StringBuilder()
                .append(checkAddNumField(params.getIndex(), "Номер"))
                .append(checkUpdateNumField(params.getSumFact(), "Сумма (фактическая)"))
                .append(checkUpdateNumField(params.getSumPlan(), "Сумма (плановая)"));

        return checkErrorMessage(errorMessage, params.getDialogStage());
    }

    public boolean isInputFilePathValid(CreateDirectoryDto dto) {
        if (dto.getText().getText() == null || dto.getText().getText().isEmpty()) {
            showAlertModal(dto.getStage(), new StringBuilder("Не заполнено поле Выбрать директорию"));
            return false;
        } else {
            return true;
        }
    }

    private String checkAddTextField(TextField field, String nameField) {
        return (field.getText() == null || field.getText().length() == 0) ? "Не заполнено поле " + nameField + " !\n" : "";
    }

    private String checkAddNumField(TextField field, String nameField) {
        if (field.getText() == null || field.getText().length() == 0) {
            return "Не заполнено поле " + nameField + " !\n";
        } else {
            return checkUpdateNumField(field, nameField);
        }
    }

    private String checkUpdateNumField(TextField field, String nameField) {
        if (field.getText().length() != 0) {
            try {
                Double.parseDouble(field.getText());
            } catch (Exception e) {
                return "Поле " + nameField + " должна содержать только числа!\n";
            }
        }
        return "";
    }

    private void showAlertModal(Stage stage, StringBuilder errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Некорректно заполнены поля");
        alert.setHeaderText("Пожалуйста, исправьте неверные поля");
        alert.setContentText(errorMessage.toString());
        alert.showAndWait();
    }

    private boolean checkErrorMessage(StringBuilder errorMessage, Stage dialogStage) {
        if (errorMessage.toString().equals("")) {
            return true;
        } else {
            showAlertModal(dialogStage, errorMessage);
            return false;
        }
    }

}
