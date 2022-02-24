package com.gmail.shepard1992.familybudgetv1.service.api;

import javafx.stage.Stage;

import java.io.File;

public interface TotalService {

    void setTotalByCategory(File file);

    void setTotalAll(File file);

    void updateTotal(Stage stage, File file);

}
