package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.dto.service.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.service.ServiceNewRowDto;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public boolean isInputDeleteValid(ServiceDeleteRowDto params) {
        String errorMessage = "";
        if (params.getIndexField().getText() == null || params.getIndexField().getText().length() == 0) {
            errorMessage += "Не заполнено поле Номер строки!\n";
        } else {
            try {
                Integer.parseInt(params.getIndexField().getText());
            } catch (Exception e) {
                errorMessage += "Поле Номер строки должна содержать только числа!\n";
            }
        }
        if (errorMessage.equals("")) {
            return true;
        } else {
            showAlertModal(params.getDialogStage(), errorMessage);
            return false;
        }
    }

    public boolean isInputAddValid(ServiceNewRowDto params) {
        String errorMessage = "";
        if (params.getCategory() == null || params.getCategory().getText().length() == 0) {
            errorMessage += "Не заполнено поле Категория!\n";
        }
        if (params.getType() == null || params.getType().getText().length() == 0) {
            errorMessage += "Не заполнено поле Тип!\n";
        }
        if (params.getSumFact() == null || params.getSumFact().getText().length() == 0) {
            errorMessage += "Не заполнено поле Сумма (фактическая)!\n";
        } else {
            try {
                Double.parseDouble(params.getSumFact().getText());
            } catch (Exception e) {
                errorMessage += "Поле Сумма должна содержать только числа!\n";
            }
        }
        if (params.getSumPlan() == null || params.getSumPlan().getText().length() == 0) {
            errorMessage += "Не заполнено поле Сумма (плановая)!\n";
        } else {
            try {
                Double.parseDouble(params.getSumPlan().getText());
            } catch (Exception e) {
                errorMessage += "Поле Сумма должна содержать только числа!\n";
            }
        }
        if (errorMessage.equals("")) {
            return true;
        } else {
            showAlertModal(params.getDialogStage(), errorMessage);
            return false;
        }
    }

    private void showAlertModal(Stage stage, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(stage);
        alert.setTitle("Некорректно заполнены поля");
        alert.setHeaderText("Пожалуйста, исправьте неверные поля");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public boolean isInputUpdateValid(ServiceNewRowDto params) {
        String errorMessage = "";
        if (params.getIndex() == null || params.getIndex().getText().length() == 0) {
            errorMessage += "Не заполнено поле Номер строки!\n";
        }
        if (params.getSumFact().getText().length() != 0) {
            try {
                Double.parseDouble(params.getSumFact().getText());
            } catch (Exception e) {
                errorMessage += "Поле Сумма (фактическая) должна содержать только числа!\n";
            }
        } else if (params.getSumPlan().getText().length() != 0) {
            try {
                Double.parseDouble(params.getSumPlan().getText());
            } catch (Exception e) {
                errorMessage += "Поле Сумма (плановая) должна содержать только числа!\n";
            }
        }else {
            try {
                Integer.parseInt(params.getIndex().getText());
            } catch (Exception e) {
                errorMessage += "Поле Номер строки должно содержать только числа!\n";
            }
        }
        if (errorMessage.equals("")) {
            return true;
        } else {
            showAlertModal(params.getDialogStage(), errorMessage);
            return false;
        }
    }

}
