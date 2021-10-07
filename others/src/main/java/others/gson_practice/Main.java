package others.gson_practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        final Gson gson = new GsonBuilder().create();

        final TestObj testObj = new TestObj("Kai", "123");
//        final TestObj testObj = new TestObj();

        String gsonJsonString = gson.toJson(testObj);
        System.out.println("gsonJsonString:" + gsonJsonString);

        TestObj gsonTestObj = gson.fromJson(gsonJsonString, TestObj.class);
        System.out.println("gsonTestObj:" + gsonTestObj);

        final ObjectMapper objectMapper = new ObjectMapper();
        String jacksonJsonString = objectMapper.writeValueAsString(testObj);
        System.out.println("jacksonJsonString:" + jacksonJsonString);

        TestObj jacksonTestObj = objectMapper.readValue(jacksonJsonString, TestObj.class);
        System.out.println("jacksonTestObj:" + jacksonTestObj);
    }
}
