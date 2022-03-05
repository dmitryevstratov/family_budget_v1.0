package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
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
        ObservableList<D> dtoData = FXCollections.observableArrayList();
        dtoData.addAll(dto.getDtoService().getAll(dto.getFile()));
        dto.getTable().setItems(dtoData);
    }

    public File saveTemplate(File file) {
        if (file != null) {
            String strPath = getStringPath(file);
            Path path = Path.of(getStringPath(file));
            try {
                if (Files.deleteIfExists(path)) {
                    try {
                        return fileRW(file, Files.createFile(path).toFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    return fileRW(file, new File(strPath));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private File fileRW(File fileR, File fileW) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileW, true); FileReader fileReader = new FileReader(fileR)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                fileWriter.write(c);
            }
        }
        return fileW;
    }

    private String getStringPath(File file) {
        List<String> list = new ArrayList<>(Arrays.asList(file.getAbsolutePath()
                .split(DEL_DIR)));
        list.add(list.size() - 2, FILE_TEMPLATES_NAME);
        list.remove(list.size() - 2);
        return list.toString()
                .replace(COMMA, File.separator)
                .replace(XML, "_" + FILE_TEMPLATES_NAME + XML)
                .replace("[", "")
                .replace("]", "");
    }

    public File chooseFile(Stage primaryStage, ChooseFileDto dto) {
        File dir = dto.getFileChooser().showOpenDialog(primaryStage);
        if (dir != null) {
            dto.getText().setText(dir.getAbsolutePath());
            return dir;
        } else {
            dto.getText().setText(null);
        }
        return null;
    }

}
