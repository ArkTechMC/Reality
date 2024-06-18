package com.iafenvoy.reality.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iafenvoy.reality.Reality;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    private static final Gson GSON = new Gson();

    public static <T> T load(Class<T> clazz, String path, T defaultValue) {
        try {
            FileInputStream stream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(stream);
            return GSON.fromJson(reader, clazz);
        } catch (IOException e) {
            Reality.LOGGER.error("Failed to read file {}", path, e);
            try {
                FileUtils.write(new File(path), GSON.toJson(defaultValue), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                Reality.LOGGER.error("Failed to write file {}", path, ex);
            }
            return defaultValue;
        }
    }

    public static Map<String, Double> loadDoubleMap(String path) {
        Map<String, JsonElement> data = loadMap(path);
        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : data.entrySet())
            try {
                result.put(entry.getKey(), entry.getValue().getAsDouble());
            } catch (Exception e) {
                Reality.LOGGER.error("Failed to read json element {}:{}", entry.getKey(), entry.getValue(), e);
            }
        return result;
    }

    public static Map<String, JsonElement> loadMap(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(stream);
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject object = element.getAsJsonObject();
            return object.asMap();
        } catch (IOException | IllegalStateException e) {
            Reality.LOGGER.error("Failed to read file {}", path, e);
            try {
                FileUtils.write(new File(path), "{}", StandardCharsets.UTF_8);
            } catch (IOException ex) {
                Reality.LOGGER.error("Failed to write file {}", path, ex);
            }
            return new HashMap<>();
        }
    }
}
