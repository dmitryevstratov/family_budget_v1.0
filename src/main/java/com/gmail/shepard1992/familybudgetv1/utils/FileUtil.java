package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Locale;

import static com.gmail.shepard1992.familybudgetv1.constants.FilesConstants.*;

@Component
public class FileUtil {

    private final Calendar calendar = Calendar.getInstance();

    public File getFile() {
        int directoryYear = createYearDirectory();
        String month = calendar.getDisplayName(Calendar.MONTH,
                Calendar.SHORT, new Locale(ENG));
        File file = getFileByName(MAIN_DIRECTORY + directoryYear, month + XML);
        if (file == null) {
            try {
                file = Files.createFile(Path.of(MAIN_DIRECTORY + directoryYear + DEL + month + XML)).toFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private int createYearDirectory() {
        int year = calendar.get(Calendar.YEAR);
        if (!Files.exists(Path.of(MAIN_DIRECTORY + year))) {
            try {
                Files.createDirectory(Path.of(MAIN_DIRECTORY + year));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return year;
    }

    private File getFileByName(String pathYear, String month) {
        File result = null;
        File[] listFiles = new File(pathYear).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.getName().equals(month)) {
                    result = file;
                }
            }
        }
        return result;
    }

    public boolean checkEmptyFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.contains("<income>")) {
                    return false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void loadIncomeDtoData(IncomeService service, TableView<IncomeDto> tableView) {
        ObservableList<IncomeDto> incomesDtoData = FXCollections.observableArrayList();
        incomesDtoData.addAll(service.getAll(getFile()));
        tableView.setItems(incomesDtoData);
    }

}
