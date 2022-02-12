package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.LoadIncomeDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.gmail.shepard1992.familybudgetv1.constants.FilesConstants.DEL;
import static com.gmail.shepard1992.familybudgetv1.constants.FilesConstants.XML;

@Component
public class FileUtil {

    public File getFile(CreateDirectoryDto dto, File dir) {
        if (dir != null) {
            String pathYear = Objects.requireNonNull(dir).getAbsolutePath() + DEL + dto.getYear().getValue();
            String pathMonth = dto.getMonth().getValue().toString() + XML;
            File file = getFileByName(pathYear, pathMonth);
            if (file == null && !Files.exists(Path.of(pathYear + DEL + pathMonth))) {
                try {
                    createYearDirectory(Objects.requireNonNull(dir).getAbsolutePath() + DEL, dto.getYear().getValue());
                    file = Files.createFile(Path.of(pathYear + DEL + pathMonth)).toFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        } else {
            return null;
        }
    }

    private void createYearDirectory(String dir, Integer dto) {
        if (!Files.exists(Path.of(dir + dto))) {
            try {
                Files.createDirectory(Path.of(dir + dto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                if (s.contains("<reportWrapper>")) {
                    return false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void loadIncomeDtoData(LoadIncomeDto dto) {
        ObservableList<IncomeDto> incomesDtoData = FXCollections.observableArrayList();
        incomesDtoData.addAll(dto.getIncomeService().getAll(dto.getFile()));
        dto.getTableIncome().setItems(incomesDtoData);
    }

}
