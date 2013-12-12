package com.byr.assistant.core.sync;

import com.byr.assistant.core.model.Course;
import com.byr.assistant.core.model.Event;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-29
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class EventUtils {

    public static List<Event> jsonArrayToList(JsonArray jsonArray) {

        List<Event> list = new ArrayList<Event>();

        for (JsonElement element : jsonArray)
            list.add(jsonToEvent(element));

        return list;
    }

    public static Event jsonToEvent(JsonElement element) {
        Gson gson = new Gson();
        Event event = gson.fromJson(element, Event.class);
        return event;
    }

}
