package core;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Stream;

//ASSUMPTION : files will fit in memory
public class UserDataSorter {
    private static final String FILE_PATH = "./codetest_files/input_files";
    private static final String COMMA_SEPARATOR = ",";
    private static final String PIPE_SEPARATOR = "|";
    private static final String SPACE_SEPARATOR = " ";

    public static void main(String[] args) throws Exception {
        UserDataSorter test = new UserDataSorter();
        test.processInformation();
    }

    private void processInformation() throws Exception {
        List<UserData> userDataList = fetchUserDataFromFiles();
        sortModule(1, userDataList);
        sortModule(2, userDataList);
        sortModule(3, userDataList);
    }

    public List<UserData> fetchUserDataFromFiles() throws Exception {
        List<UserData> userDataList = new ArrayList<>();
        List<UserData> commaData = getData(COMMA_SEPARATOR,FILE_PATH + "/comma.txt");
        List<UserData> pipeData = getData(PIPE_SEPARATOR,FILE_PATH + "/pipe.txt");
        List<UserData> spaceData = getData(SPACE_SEPARATOR,FILE_PATH + "/space.txt");

        Stream.of(commaData, pipeData, spaceData).forEach(userDataList::addAll);
        return userDataList;
    }

    public List<UserData> getData(String delimiter,String fileName) throws Exception{
        DataTypeFactory dataFatory = new DataTypeFactory();
        List<UserData> data = new ArrayList<>();
        DataProcessor dataProcessor = dataFatory.getDataProcessor(delimiter);
        if(dataProcessor != null) {
            try{
                data = dataProcessor.getDataFromFile(fileName);
            }catch(Exception ex){
                throw new FileNotFoundException(ex.getMessage());
            }finally{
                return data;
            }
        }
        return data;
    }

    public List<UserData> sortModule(int behaviour, List<UserData> userDataList) {
        System.out.println("Option " + behaviour);
        switch(behaviour) {
            case 1:
                userDataList = sortByGenderAndLastName(userDataList);
                break;
            case 2:
                Collections.sort(userDataList, new BirthDateSorter());
                break;
            case 3:
                Collections.sort(userDataList, new LastNameSorterDesc());
                break;
            default:
                break;
        }
        printUserDataList(userDataList);
        return userDataList;

    }

    public List<UserData> sortByGenderAndLastName(List<UserData> userDataList) {
        List<UserData> newList = new ArrayList<>();
        Collections.sort(userDataList, new LastNameSorter());
        for(UserData user : userDataList) {
            if(user.getGender().equals("Female")) {
                newList.add(user);
            }
        }
        for(UserData user : userDataList) {
            if(user.getGender().equals("Male")) {
                newList.add(user);
            }
        }
        return newList;
    }

    private void printUserDataList(List<UserData> data) {
        for(UserData u : data) {
            System.out.println(u.toString());
        }
    }

    public class LastNameSorter implements Comparator<UserData> {
        public int compare(UserData o1, UserData o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }

    public class LastNameSorterDesc implements Comparator<UserData> {
        public int compare(UserData o1, UserData o2) {
            return o2.getLastName().compareTo(o1.getLastName());
        }
    }

    public class BirthDateSorter implements Comparator<UserData> {
        public int compare(UserData o1, UserData o2) {
            return o1.getBirthDate().compareTo(o2.getBirthDate());
        }
    }
}
