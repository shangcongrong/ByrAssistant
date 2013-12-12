package com.byr.assistant.core.sync;

import com.byr.assistant.core.model.Forum;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangxin
 * Date: 13-11-28
 * Time: 下午10:23
 * To change this template use File | Settings | File Templates.
 */
public class ForumUtils {

    public static List<Forum> jsonArrayToList(JsonArray jsonArray) {

        List<Forum> list = new ArrayList<Forum>();

        for (JsonElement element : jsonArray)
            list.add(jsonToCourse(element));

        return list;
    }

    public static Forum jsonToCourse(JsonElement element) {
        Gson gson = new Gson();
        Forum forum = gson.fromJson(element, Forum.class);
        return forum;
    }
}
