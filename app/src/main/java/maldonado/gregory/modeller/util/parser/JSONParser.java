package maldonado.gregory.modeller.util.parser;

import org.json.JSONArray;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gregory Maldonado
 * @since 2021-05-06
 *
 * Parsing methods for parsing JSON into HashMap Data Type
 */

public class JSONParser {

    /**
     * Parses JSON file into HashMap for easy use.
     * HashMap is in the format of [' Atomic Symbol '] = {
     *                                                      ['Name']          = $NAME$
     *                                                      ['Atomic Weight'] = $ATOMIC WEIGHT$
     *                                                      .
     *                                                      .
     *                                                      .
     *                                                           }
     *
     * @return HashMap that represents the JSON data
     */
    protected static HashMap<String, HashMap<String, String>> parse() {
        String jsonString = getJSONData("data/Elements.json");
        List<Object> jsonObjects = parseJSON(jsonString);
        return jsonToHashMap(jsonObjects);
    }

    /**
     *
     * @param objects The return type of the JSON library used which are parsed JSON
     * @return HashMap that represents the JSON data
     */
    private static HashMap<String, HashMap<String, String>> jsonToHashMap(List<Object> objects) {
        HashMap<String, HashMap<String, String>> json = new HashMap<>();

        for (Object jsonObject : objects) {
            HashMap<String, String> data = buildJSON(jsonObject);
            json.put(data.get("symbol"), data);
        }
        return json;
    }

    /**
     *
     * @param filePath String that contains the file path for the JSON data
     * @return JSON data String
     */
    private static String getJSONData(String filePath) {
        String jsonData = "";
        try {
            File jsonFile = new File(filePath);
            Scanner scanner = new Scanner(jsonFile);
            while (scanner.hasNextLine()) {
                jsonData = jsonData + scanner.nextLine();
            }
        } catch (Exception e) {System.out.println(e.toString());}
        return jsonData;
    }

    /**
     * Converts the JSON String into a list of type Object
     * @param jsonString String that contains JSON data
     * @return List of Objects that contains the JSON data
     */
    private static List<Object> parseJSON(String jsonString) {
        try {
            JSONArray jsonData = new JSONArray(jsonString);
            return jsonData.toList();
        } catch (Exception e) {System.out.println(e.toString()); System.out.println("Working Directory = " + System.getProperty("user.dir"));}
        return null;
    }

    /**
     *
     * @param jsonObject Object that contains JSON data
     * @return HashMap of JSON data for a specific Atom
     */
    private static HashMap<String, String> buildJSON(Object jsonObject) {

        HashMap<String, String> json = new HashMap<>();

        String jsonString = jsonObject.toString();
        jsonString = jsonString.substring(1, jsonString.length() - 2);
        String[] properties = jsonString.split(", ");
        for (String property : properties) {
            String[] data = property.split("=");
            if (data.length > 1) {
                json.put(data[0], data[1]);
                continue;
            }
            json.put(data[0], "");
        }
        return json;
    }

}
