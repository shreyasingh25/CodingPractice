package core;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class UserDataSorterTest {

    private static final String FILE_PATH = "./codetest_files/input_files";
    private static final String TEST_RESULT = "./src/test/java/testResult";
    UserDataSorter sorter = new UserDataSorter();

    @Test
    void checkDataTypeFactoryCreation() {
        DataTypeFactory dataFatory = new DataTypeFactory();

        DataProcessor dataProcessor = dataFatory.getDataProcessor("+");
        assertNull(dataProcessor);

        dataProcessor = dataFatory.getDataProcessor(",");
        assertEquals(dataProcessor.getDelimiter(), (","));

        dataProcessor = dataFatory.getDataProcessor("|");
        assertEquals(dataProcessor.getDelimiter(), ("\\|"));

        dataProcessor = dataFatory.getDataProcessor(" ");
        assertEquals(dataProcessor.getDelimiter(), (" "));
    }

    @Test
    void testCanGetData() throws Exception {
        List<UserData> commaData = sorter.getData(",", FILE_PATH + "/comma.txt");
        assertThat(3, equalTo(commaData.size()));
        List<UserData> pipeData = sorter.getData("|", FILE_PATH + "/pipe.txt");
        assertThat(3, equalTo(pipeData.size()));
        List<UserData> spaceData = sorter.getData(" ", FILE_PATH + "/space.txt");
        assertThat(3, equalTo(spaceData.size()));

        //check when file not found, result should be 0
        commaData = sorter.getData(",", FILE_PATH + "/commaT.txt");
        assertThat(0, equalTo(commaData.size()));

        //check when one of the delimeter is wrong, result should be 0
        spaceData = sorter.getData("|", FILE_PATH + "/space.txt");
        assertThat(0, equalTo(spaceData.size()));

        //checkDateParsing
    }

    @Test
    void canFetchUserDataFromFiles() throws Exception {
        List<UserData> userDataList = sorter.fetchUserDataFromFiles();
        assertThat(9, equalTo(userDataList.size()));
    }

    @Test
    void canSortByBirthDate() throws Exception {

        List<UserData> userDataOriginal = sorter.fetchUserDataFromFiles();
        userDataOriginal = sorter.sortModule(2, userDataOriginal);
        String userOriginalJsonData = convertObjectToJson(userDataOriginal);


        List<UserData> SortedByBirthDate = sorter.getData(" ", TEST_RESULT + "/SortedByBirthDate");
        String SortedByBirthDateJson = convertObjectToJson(SortedByBirthDate);

        assertThat(userOriginalJsonData, equalTo(SortedByBirthDateJson));
    }

    @Test
    void canSortByLastName() throws Exception {

        List<UserData> userDataOriginal = sorter.fetchUserDataFromFiles();
        userDataOriginal = sorter.sortModule(3, userDataOriginal);
        String userOriginalJsonData = convertObjectToJson(userDataOriginal);

        List<UserData> SortedByLastName = sorter.getData(" ", TEST_RESULT + "/SortedByLastName");
        String SortedByLastNameJson = convertObjectToJson(SortedByLastName);

        assertThat(userOriginalJsonData, equalTo(SortedByLastNameJson));
    }

    @Test
    void canSortByGenderAndLastName() throws Exception {

        List<UserData> userDataOriginal = sorter.fetchUserDataFromFiles();
        userDataOriginal = sorter.sortModule(1, userDataOriginal);
        String userOriginalJsonData = convertObjectToJson(userDataOriginal);

        List<UserData> sorterData = sorter.getData(" ", TEST_RESULT + "/SortedByGenderLastName");
        String sorterDataJson = convertObjectToJson(sorterData);

        assertThat(userOriginalJsonData, equalTo(sorterDataJson));
    }

    String convertObjectToJson(List<UserData> dataLista) {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        try {
            // get Oraganisation object as a json string
            jsonStr = Obj.writeValueAsString(dataLista);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}