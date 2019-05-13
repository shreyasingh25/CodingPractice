package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpaceDataProcessor implements DataProcessor {

    @Override
    public List<UserData> getDataFromFile(String fileName) {
        DataProcessor instance = new CommaDataProcessor();
        List<String> data = instance.readFileInList(fileName);
        List<UserData> userDataList = new ArrayList<>();
        for (String l : data) {
            String[] splitS = Arrays.stream(l.split(getDelimiter()))
                    .map(String::trim)
                    .toArray(String[]::new);
            UserData userData = new UserData();
            int i = 0;
            userData.setLastName((splitS[i]));
            userData.setFirstName((splitS[++i]));
            i++;
            String gender = instance.getGender(splitS[++i].charAt(0));
            userData.setGender(gender);
            try {
                userData.setBirthDate(DATE_FORMATTER_1.parse(splitS[++i]));
            } catch (Exception ex) {
                System.out.println("unable to parse date");
            }
            userData.setColor(splitS[++i]);
            userDataList.add(userData);
        }
        return userDataList;
    }

    @Override
    public String getDelimiter() {
        return " ";
    }
}
