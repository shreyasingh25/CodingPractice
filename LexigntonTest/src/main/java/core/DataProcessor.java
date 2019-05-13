package core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public interface DataProcessor {

    SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat DATE_FORMATTER_1 = new SimpleDateFormat("MM-dd-yyyy");

    default List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return lines;
    }

    default String getGender(Character c) {
        return (c == 'F') ? "Female" : "Male";
    }

    public List<UserData> getDataFromFile(String fileName);
    public String getDelimiter();
}
