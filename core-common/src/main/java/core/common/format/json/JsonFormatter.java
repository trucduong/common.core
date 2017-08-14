package core.common.format.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonFormatter {

    public static String toJson(Object src) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter())
                // .setDateFormat("dd/MM/YYYY HH:mm:ss")
                .create();
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter())
                // .setDateFormat("dd/MM/YYYY HH:mm:ss")
                .create();
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter())
                // .setDateFormat("dd/MM/YYYY HH:mm:ss")
                .create();
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T fromJsonFile(String path, Class<T> classOfT) {
        try {
            Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter()).create();
            JsonReader reader = new JsonReader(new FileReader(path));
            return gson.fromJson(reader, classOfT);
        } catch (FileNotFoundException e) {
        }

        return null;
    }

    public static <T> T fromJsonFile(String path, Type typeOfT) {
        try {
            Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter()).create();
            JsonReader reader = new JsonReader(new FileReader(path));
            T result = gson.fromJson(reader, typeOfT);
            reader.close();
            return result;
        } catch (Exception e) {
        }

        return null;
    }

    public static boolean toJsonFile(String path, String fileName, Object src, Type typeOfT) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            Gson gson = new GsonBuilder().setExclusionStrategies(new JsonIgnoreFilter()).create();
            JsonWriter writer = new JsonWriter(new FileWriter(path + File.separatorChar + fileName, false));
            gson.toJson(src, typeOfT, writer);
            writer.close();
            return true;
        } catch (Exception e) {
        }

        return false;
    }
}
