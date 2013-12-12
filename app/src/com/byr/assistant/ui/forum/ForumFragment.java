package com.byr.assistant.ui.forum;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Forum;
import com.byr.assistant.core.persistance.DbCache;
import com.byr.assistant.core.persistance.DbManager;
import com.byr.assistant.core.persistance.PersistableForum;
import com.byr.assistant.core.sync.ForumUtils;
import com.byr.assistant.core.sync.RestClient;
import com.byr.assistant.ui.ItemListFragment;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午12:22
 */
public class ForumFragment extends ItemListFragment<Forum> {

    private String TAG = "com.byr.assistant.ui.forum.ForumFragment";

    private List<Forum> list;

    protected DbCache cache;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cache = DbManager.getInstance(getActivity()).getDatabaseCache();
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
    protected SingleTypeAdapter<Forum> createAdapter(List<Forum> items) {
        return new ForumAdapter(getActivity(), items);
    }


    @Override
    public void onLoadFinished(List items) {
        super.onLoadFinished(items);
    }

    private void request() throws JSONException {

        AjaxParams params = new AjaxParams();
        params.put("message", "gettopten");

        RestClient.get("TopTenServlet", params, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String result) {

                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(result).getAsJsonObject();
                String status = object.get("status").getAsString();

                if (status.equals("OK"))
                    list = ForumUtils.jsonArrayToList(object.get("strResponse").getAsJsonArray());

                try {
                    cache.store(new PersistableForum(), list);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                onLoadFinished(list);

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
