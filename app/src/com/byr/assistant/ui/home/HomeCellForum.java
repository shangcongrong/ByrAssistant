package com.byr.assistant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byr.assistant.R;
import com.byr.assistant.core.model.Forum;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class HomeCellForum extends HomeCellBase<Forum> {


    private TextView tvTitle;

    private TextView tvAuthor;

    private TextView tvContent;

    private TextView tvPushlishDate;

    private TextView tvEmptyText;

    private LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout_forum, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = (TextView) view.findViewById(R.id.tv_forum_title);
        tvContent = (TextView) view.findViewById(R.id.tv_forum_content);
        tvAuthor = (TextView) view.findViewById(R.id.tv_forum_author);
        tvPushlishDate = (TextView) view.findViewById(R.id.tv_forum_publish_date);
        tvEmptyText = (TextView) view.findViewById(R.id.tv_empty_text);
        layout = (LinearLayout) view.findViewById(R.id.layout_home_forum);



    }

    @Override
    HomeCellBase<Forum> loadData(Forum forum) {

        if (forum != null) {
            tvTitle.setText(forum.getTitle());
            tvAuthor.setText(forum.getAuthor());
            tvContent.setText(forum.getContent());
            tvPushlishDate.setText(forum.getPublishDate());


            showData();
        } else {
            showEmptyText();
        }

        return this;
    }

    @Override
    void showEmptyText() {
        tvEmptyText.setText("今日无十大！过一会再来看哦！");
        tvEmptyText.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
    }

    @Override
    void showData() {
        tvEmptyText.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }
}
