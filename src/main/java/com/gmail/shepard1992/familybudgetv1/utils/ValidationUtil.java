package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceUpdateRowDto;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

    public boolean isInputDeleteValid(ParamsForServiceDeleteRowDto params) {
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

    public boolean isInputAddValid(ParamsForServiceAddRowDto params) {
        String errorMessage = "";
        if (params.getCategory() == null || params.getCategory().getText().length() == 0) {
            errorMessage += "Не заполнено поле Категория!\n";
        }
        if (params.getType() == null || params.getType().getText().length() == 0) {
            errorMessage += "Не заполнено поле Тип!\n";
        }
        if (params.getSum() == null || params.getSum().getText().length() == 0) {
            errorMessage += "Не заполнено поле Сумма!\n";
        } else {
            try {
                Double.parseDouble(params.getSum().getText());
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

    public boolean isInputUpdateValid(ParamsForServiceUpdateRowDto params) {
        String errorMessage = "";
        if (params.getIndex() == null || params.getIndex().getText().length() == 0) {
            errorMessage += "Не заполнено поле Номер строки!\n";
        }
        if (params.getSum().getText().length() != 0) {
            try {
                Double.parseDouble(params.getSum().getText());
            } catch (Exception e) {
                errorMessage += "Поле Сумма должна содержать только числа!\n";
            }
        } else {
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
