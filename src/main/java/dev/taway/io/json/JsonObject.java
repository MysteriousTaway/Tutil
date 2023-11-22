package dev.taway.io.json;

import dev.taway.exception.io.JsonObjectException;
import dev.taway.io.file.File;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Object used for storing, serialising and deserializing JSON data. <br>
 * NOTE: Serialization   = Program -> File<br>
 * Deserialization = File -> Program<br>
 * Wrapper for {@link org.json.simple}
 *
 * @since 0.1.4
 */
public class JsonObject {
    private JSONObject jsonObject;

    public JsonObject() {
    }

    public JsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * Converts HashMap into a JsonObject
     *
     * @param data        Data to be converted.
     * @param clearObject If true then the JsonObject will be cleared before writing into it.
     * @see #getJsonObject()
     * @since 0.1.4
     */
    @SneakyThrows
    public void hashMapToJsonObject(HashMap<String, Object> data, boolean clearObject) {
        if (clearObject) jsonObject = null;
        if (jsonObject == null) {
            jsonObject = new JSONObject();
            jsonObject.putAll(data);
        } else {
            throw new JsonObjectException("Could not convert HashMap to JsonObject as it's not empty and currently contains data.");
        }
    }

    /**
     * Converts string version of JSON and saves it into a JsonObject.
     *
     * @param jsonString  Json string to be converted into a JsonObject
     * @param clearObject If true then the JsonObject will be cleared before writing into it.
     * @see #getJsonObject()
     * @since 0.1.4
     */
    @SneakyThrows
    public void stringToJsonObject(String jsonString, boolean clearObject) throws ParseException {
        if (clearObject) jsonObject = null;
        if (jsonObject == null) {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } else {
            throw new JsonObjectException("Could not convert String to JsonObject as it's not empty and currently contains data.");
        }
    }

    /**
     * Deserializes the file into a JsonObject;
     *
     * @param path        Path to JSON file.
     * @param clearObject If the object should be cleared before deserializing into it.
     * @see #getJsonObject()
     * @since 0.1.4
     */
    @SneakyThrows
    public void deserializeJsonFromFile(String path, boolean clearObject) throws IOException, ParseException {
        if (clearObject) jsonObject = null;
        if (jsonObject == null) {
            File file = new File(path);
            String jsonString = file.readAllAsString();
            stringToJsonObject(jsonString, true);
        } else {
            throw new JsonObjectException("Could not convert file to JsonObject as it's not empty and currently contains data.");
        }
    }

    /**
     * Serializes the JSON object into a file.
     *
     * @param path        Path to where the json should be saved to.
     * @param clearObject Determines if the JsonObject should be cleared after writing it into a file.
     * @since 0.1.4
     */
    @SneakyThrows
    public void serializeJsonToFile(String path, boolean clearObject) throws IOException {
        if (jsonObject != null) {
            File file = new File(path);
            file.create();
            file.overwrite(jsonObject.toJSONString());
        } else {
            throw new JsonObjectException("Could not convert JsonObject to file as it's empty and currently contains no data.");
        }
        if (clearObject) jsonObject = null;
    }

    /**
     * If the JsonObject exists it returns it otherwise it logs a warn message and returns a new (empty) JsonObject.
     *
     * @return Returns the JsonObject.
     * @since 0.1.4
     */
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    /**
     * @param jsonObject Sets the JsonObject.
     * @since 0.1.4
     */
    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
