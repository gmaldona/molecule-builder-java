package maldonado.gregory.modeller.parser;

import org.json.JSONArray;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class JSONParser {

    public static HashMap<String, HashMap<String, String>> parse() {
        String jsonString = getJSONData("data/Elements.json");
        List<Object> jsonObjects = parseJSON(jsonString);
        return jsonToHashMap(jsonObjects);
    }

    private static HashMap<String, HashMap<String, String>> jsonToHashMap(List<Object> objects) {
        HashMap<String, HashMap<String, String>> json = new HashMap<>();

        for (Object jsonObject : objects) {
            HashMap<String, String> data = buildJSON(jsonObject);
            json.put(data.get("symbol"), data);
        }
        return json;
    }

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

    private static List<Object> parseJSON(String jsonString) {
        try {
            JSONArray jsonData = new JSONArray(jsonString);
            return jsonData.toList();
        } catch (Exception e) {System.out.println(e.toString()); System.out.println("Working Directory = " + System.getProperty("user.dir"));}
        return null;
    }

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
