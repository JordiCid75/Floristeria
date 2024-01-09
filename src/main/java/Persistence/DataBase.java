package Persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;


public class DataBase {

   private ObjectMapper objectMapper;
   private  File file;

   private final String filePath = "src/main/resources/FlowerShop.json";

    public DataBase()
    {
        objectMapper = new ObjectMapper();
        file = new File(filePath);
    }


}
