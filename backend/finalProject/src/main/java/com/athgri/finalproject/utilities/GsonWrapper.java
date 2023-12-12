package com.athgri.finalproject.utilities;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Utility class for converting between JSON strings and lists using Gson.
 */
public class GsonWrapper {

    private static final Gson gson = new Gson();

    /**
     * Converts a List to a JSON string.
     *
     * @param list The List to be converted.
     * @param <T>  The type of elements in the List.
     * @return A JSON string representing the List.
     */
    public static <T> String listToJson(List<T> list) {

        return gson.toJson(list);
    }

    /**
     * Converts a JSON string to a List.
     *
     * @param json        The JSON string to be converted.
     * @param elementType The class representing the type of elements in the List.
     * @param <T>         The type of elements in the List.
     * @return A List containing elements parsed from the JSON string.
     */
    public static <T> List<T> jsonToList(String json, Class<T> elementType) {

        Type listType = TypeToken.getParameterized(List.class, elementType).getType();

        return gson.fromJson(json, listType);
    }
}
