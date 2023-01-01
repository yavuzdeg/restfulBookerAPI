package utils;

import com.fasterxml.jackson.annotation.PropertyAccessor;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtil {

    // method to convert Json data to Java object (de-serialization)

    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();

    }

    public static <T> T convertJsonToJava(String json, Class<T> cls){

        T javaResult = null;

        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("Json could not be converted into Java object" + e.getMessage());
        }

        return javaResult;

    }

    // method to convert Java object to Json data (serialization)

    public static String convertJavaToJson(Object obj){

        String jsonResult = null;


        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java Object could not be converted into Json data" + e.getMessage());
        }

        return jsonResult;
    }

}
