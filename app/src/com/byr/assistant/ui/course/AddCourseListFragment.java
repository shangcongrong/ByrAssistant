package com.byr.assistant.ui.course;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Course;
import com.byr.assistant.core.persistance.DbCache;
import com.byr.assistant.core.persistance.DbManager;
import com.byr.assistant.core.persistance.PersistableCourse;
import com.byr.assistant.core.persistance.PersistableUserCourse;
import com.byr.assistant.core.sync.CourseUtils;
import com.byr.assistant.core.sync.RestClient;
import com.byr.assistant.ui.ItemListFragment;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by orange on 13-11-26.
 */
public class AddCourseListFragment extends ItemListFragment<Course> {

    private String TAG = "com.byr.assistant.ui.user.AddCourseListFragment";

    protected DbCache cache;

    private List<Course> listShown = Collections.emptyList();

    private PersistableCourse persistableCourse;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cache = DbManager.getInstance(activity).getDatabaseCache();
        persistableCourse = new PersistableCourse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            request();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        storeUserCourses();
    }

    private void storeUserCourses() {
        Course course;
        List<Course> userList = new ArrayList<Course>();

        for (int i = 0; i < listShown.size(); i++) {
            course = listShown.get(i);
            if (course.isAdded())
                userList.add(course);
        }

        try {
            cache.store(new PersistableUserCourse(), userList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            userList.clear();
        }
    }

    @Override
    public SingleTypeAdapter<Course> createAdapter(List<Course> items) {
        return new AddCourseAdapter(getActivity(), items);
    }

    @Override
    public void onLoadFinished(List items) {
        super.onLoadFinished(items);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        AddCourseAdapter adapter = (AddCourseAdapter) getListAdapter().getWrappedAdapter();
        adapter.updateCheckBoxState(position);
        notifyDataSetChanged();
    }

    private List<Course> storeCourses(List<Course> list) {

        try {
            cache.store(persistableCourse, list);
            List<Course> userCourses = cache.loadFromDB(new PersistableUserCourse());

            if (userCourses == null)
                return cache.loadFromDB(persistableCourse);

            for (Course course : userCourses)
                cache.update(persistableCourse, course);

            return cache.loadFromDB(persistableCourse);

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void request() throws JSONException {

        AjaxParams params = new AjaxParams();
        params.put("message", "allcourse");
        params.put("college", "计算机学院");

        RestClient.get("CourseServlet", params, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String result) {

                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(result).getAsJsonObject();
                String status = object.get("status").getAsString();

                if (status.equals("OK")) {
                    List<Course> listTemp = CourseUtils.jsonArrayToList(object.get("strResponse").getAsJsonArray());
                    listShown = storeCourses(listTemp);
                } else
                    listShown = Collections.emptyList();

                onLoadFinished(listShown);

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.e(TAG, t.getMessage());
            }
        });
    }


}
