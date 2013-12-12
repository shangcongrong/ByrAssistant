package com.byr.assistant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byr.assistant.R;
import com.byr.assistant.core.model.Note;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class HomeCellNote extends HomeCellBase<Note> {

    private TextView tvHeadline;

    private TextView tvContent;

    private TextView tvEditDate;

    private TextView tvEmptyText;

    private LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout_note, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHeadline = (TextView) view.findViewById(R.id.tv_note_headline);
        tvContent = (TextView) view.findViewById(R.id.tv_note_content);
        tvEditDate = (TextView) view.findViewById(R.id.tv_note_edit_time);
        tvEmptyText = (TextView) view.findViewById(R.id.tv_empty_text);
        layout = (LinearLayout) view.findViewById(R.id.layout_home_note);
    }

    @Override
    HomeCellBase<Note> loadData(Note note) {
        if (note != null) {
            tvHeadline.setText(note.getHeadLine());
            tvContent.setText(note.getContent());
            tvEditDate.setText(note.getEditDate());
            showData();
        } else {
            showEmptyText();
        }

        return this;
    }

    @Override
    void showEmptyText() {

        tvEmptyText.setVisibility(View.VISIBLE);
        tvEmptyText.setText("你暂时没有笔记哦～");
        layout.setVisibility(View.GONE);
    }

    @Override
    void showData() {
        tvEmptyText.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }
}
