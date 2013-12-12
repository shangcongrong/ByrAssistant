package com.byr.assistant.ui.forum;

import android.app.Activity;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Forum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yangxin
 * Date: 13-11-29
 * Time: 上午12:27
 * To change this template use File | Settings | File Templates.
 */
public class ForumAdapter extends SingleTypeAdapter<Forum> {


    public ForumAdapter(Activity activity, List<Forum> list) {
        super(activity, R.layout.forum_list_item);
        setItems(list);
    }


    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_forum_title, R.id.tv_forum_author, R.id.tv_forum_content, R.id.tv_forum_publish_date};
    }

    @Override
    protected void update(int position, Forum forum) {
        setText(0, forum.getTitle());
        setText(1, forum.getAuthor());
        setText(2, forum.getContent());
        setText(3, forum.getPublishDate());
    }

}
