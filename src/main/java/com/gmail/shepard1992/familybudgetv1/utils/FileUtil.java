package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.LoadMonthReportDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ValueUtil valueUtil;
    private final MapperUtil mapperUtil;

    @Autowired
    public FileUtil(ValueUtil valueUtil, MapperUtil mapperUtil) {
        this.valueUtil = valueUtil;
        this.mapperUtil = mapperUtil;
    }

    public File getFile(CreateDirectoryDto dto, File dir) throws IOException {
        if (dir != null) {
            String pathYear = Objects.requireNonNull(dir).getAbsolutePath() + DEL + dto.getYear().getValue();
            String pathMonth = dto.getMonth().getValue().toString() + XML;
            File file = getFileByName(pathYear, pathMonth);
            if (file == null && !Files.exists(Path.of(pathYear + DEL + pathMonth))) {
                createYearDirectory(Objects.requireNonNull(dir).getAbsolutePath() + DEL, dto.getYear().getValue());
                file = Files.createFile(Path.of(pathYear + DEL + pathMonth)).toFile();
            }
            return file;
        } else {
            return null;
        }
    }

    private void createYearDirectory(String dir, Integer dto) throws IOException {
        if (!Files.exists(Path.of(dir + dto))) {
            Files.createDirectory(Path.of(dir + dto));
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

    public boolean checkEmptyFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.contains("<reportWrapper>")) {
                    return false;
                }
            }
        } catch (IOException ex) {
            throw ex;
        }
        return true;
    }

    public <D> void loadDtoData(LoadDto<D> dto) {
        ObservableList<D> dtoData = FXCollections.observableArrayList();
        dtoData.addAll(dto.getDtoService().getAll(dto.getFile()));
        dto.getTable().setItems(dtoData);
    }

    public File saveTemplate(File file) throws IOException {
        if (file != null) {
            String strPath = getStringPath(file);
            Path path = Path.of(getStringPath(file));
            if (Files.deleteIfExists(path)) {
                return fileRW(file, Files.createFile(path).toFile());
            } else {
                return fileRW(file, new File(strPath));
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

    public File chooseDirectory(Stage primaryStage, CreateDirectoryDto dto) {
        File dir = dto.getDirectoryChooser().showDialog(primaryStage);
        if (dir != null) {
            dto.getText().setText(dir.getAbsolutePath());
            return dir;
        } else {
            dto.getText().setText(null);
        }
        return null;
    }

    public File[] chooseFiles(Stage primaryStage, ChooseFilesDto dto) {
        File dir = dto.getDirectoryChooser().showDialog(primaryStage);
        if (dir != null) {
            dto.getText().setText(dir.getAbsolutePath());
            return dir.listFiles();
        } else {
            dto.getText().setText(null);
        }
        return null;
    }

    public void loadDtoData(LoadMonthReportDto loadDto) throws RepositoryException {
        ObservableList<MonthReportDto> dtoData = FXCollections.observableArrayList();
        for (File file : loadDto.getFiles()) {
            Report report = loadDto.getRepository().get(file);
            Double incomeSum = valueUtil.getSumByModelList(report.getIncomeList().getIncome());
            Double costSum = valueUtil.getSumByModelList(report.getCostList().getCost());
            double totalSum = incomeSum - costSum;
            String totalPercent = valueUtil.getTotalPercent(totalSum, incomeSum);
            MonthReportDto dto = new MonthReportDto.MonthReportDtoBuilder()
                    .setMonth(mapperUtil.getNameMonthByNumber(report.getMonth()))
                    .setIncome(incomeSum)
                    .setCost(costSum)
                    .setTotal(totalSum)
                    .setTotalPercent(totalPercent)
                    .setMajorPurchases("")
                    .build();
            dtoData.add(dto);
        }
        dtoData.add(valueUtil.getTotal(dtoData));
        loadDto.getTableView().setItems(dtoData);
    }

}
