package dev.taway.io.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonObjectTest {
    JsonObject jsonObject;

    @Test
    void hashMapToJsonObject() {
        HashMap<String, Object> jsonHashMap = new HashMap<>();
        jsonHashMap.put("one", "one");
        jsonHashMap.put("two", 2);

        jsonObject = new JsonObject();
        jsonObject.hashMapToJsonObject(jsonHashMap, true);
        assertEquals("{\"two\":2,\"one\":\"one\"}", jsonObject.getJsonObject().toJSONString());
    }

    @Test
    void stringToJsonObject() throws ParseException {
        jsonObject = new JsonObject();
        jsonObject.stringToJsonObject("{\"one\":\"one\",\"two\":2}", true);

        assertEquals("{\"one\":\"one\",\"two\":2}", jsonObject.getJsonObject().toJSONString());
    }

    @Test
    void deserializeJsonFromFile() throws IOException, ParseException {
        jsonObject = new JsonObject();
        jsonObject.deserializeJsonFromFile("./src/test/java/dev/taway/io/json/testRead.json", true);

        assertEquals("{\"one\":\"one\",\"two\":2}", jsonObject.getJsonObject().toJSONString());
    }

    @Test
    void serializeJsonToFile() throws IOException, ParseException {
//        File file = new File("./src/test/java/dev/taway/io/json/testWrite.json");
//        file.create();
//        file.overwrite("{\n\"one\":\"one\",\n\"two\":2\n}");

        JSONObject jO = new JSONObject();
        jO.put("one", "one");
        jO.put("two", 2);

        jsonObject = new JsonObject();
        jsonObject.setJsonObject(jO);
        jsonObject.serializeJsonToFile("./src/test/java/dev/taway/io/json/testWrite.json", true);

        jsonObject.deserializeJsonFromFile("./src/test/java/dev/taway/io/json/testWrite.json", true);

        assertEquals("{\"one\":\"one\",\"two\":2}", jsonObject.getJsonObject().toJSONString());
    }
}