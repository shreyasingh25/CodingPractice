package core;

public class DataTypeFactory {

    public DataProcessor getDataProcessor(String delimiter){
        if(",".equals(delimiter)){
            return new CommaDataProcessor();
        }
        else if("|".equals(delimiter)){
            return new PipeDataProcessor();
        }
        else if(" ".equals(delimiter)){
            return new SpaceDataProcessor();
        }
        return null;
    }
}
