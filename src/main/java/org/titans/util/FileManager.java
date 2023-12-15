package org.titans.util;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.titans.entities.Category;
import org.titans.entities.Task;
import org.titans.entities.TaskHistoryAction;
import org.titans.entities.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void saveToFileJson(List<Task> taskList) {

        String jsonString = gson.toJson(taskList);
        String filePath = "src/main/resources/Tasks.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data saved to " + filePath);
    }
    public static void saveToFileJsonCategory(List<Category> categories) {

        String jsonString = gson.toJson(categories);
        String filePath = "src/main/resources/Tasks.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data saved to " + filePath);
    }
    public static void saveToFileJsonUser(List<User> users) {

        String jsonString = gson.toJson(users);
        String filePath = "src/main/resources/Tasks.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data saved to " + filePath);
    }
    public static void saveToFileJsonHistory(List<TaskHistoryAction> historyActions) {

        String jsonString = gson.toJson(historyActions);
        String filePath = "src/main/resources/Tasks.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data saved to " + filePath);
    }
    public static List<Task> loadFromJson() {
        List<Task> list = new ArrayList<>();
        try {


            InputStream inputStream = Resources.getResource("tasks.json").openStream();
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            Type listType = new TypeToken<ArrayList<Task>>() {
            }.getType();
            list = new Gson().fromJson(json, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
