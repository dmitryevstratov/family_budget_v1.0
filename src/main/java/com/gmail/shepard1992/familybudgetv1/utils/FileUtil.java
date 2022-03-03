package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.gmail.shepard1992.familybudgetv1.utils.FileConstants.*;

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

    public <D> void loadDtoData(LoadDto<D> dto) {
        ObservableList<D> incomesDtoData = FXCollections.observableArrayList();
        incomesDtoData.addAll(dto.getDtoService().getAll(dto.getFile()));
        dto.getTable().setItems(incomesDtoData);
    }

    public File saveTemplate(File file) {
        if (file != null) {
            List<String> list = new ArrayList<>(Arrays.asList(file.getAbsolutePath()
                    .split(DEL_DIR)));
            list.add(list.size() - 2, FILE_TEMPLATES_NAME);
            list.remove(list.size() - 2);
            String strPath = list.toString()
                    .replace(COMMA, File.separator)
                    .replace(XML, "_" + FILE_TEMPLATES_NAME + XML)
                    .replace("[", "")
                    .replace("]", "");
            Path path = Path.of(strPath);
            if (!Files.exists(path)) {
                try {
                    File tmp = Files.createFile(path).toFile();

                    try (FileWriter fileWriter = new FileWriter(tmp, true); FileReader fileReader = new FileReader(file)) {
                        int c;
                        while ((c = fileReader.read()) != -1) {
                            fileWriter.write(c);
                        }
                    }
                    return tmp;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return new File(strPath);
            }
        }
        return null;
    }

}
