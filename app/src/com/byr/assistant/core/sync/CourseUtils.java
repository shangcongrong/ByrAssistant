package com.byr.assistant.core.sync;

import com.byr.assistant.core.model.Course;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orange on 13-11-27.
 */
public class CourseUtils {

    public static List<Course> jsonArrayToList(JsonArray jsonArray) {

        List<Course> list = new ArrayList<Course>();

        for (JsonElement element : jsonArray)
            list.add(jsonToCourse(element));

        return list;
    }

    public static Course jsonToCourse(JsonElement element) {
        Gson gson = new Gson();
        Course course = gson.fromJson(element, Course.class);
        return course;
    }

}
