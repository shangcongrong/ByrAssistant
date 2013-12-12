package com.byr.assistant.ui.note;

import android.app.Activity;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Note;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 上午3:00
 * To change this template use File | Settings | File Templates.
 */
public class NoteAdapter extends SingleTypeAdapter<Note> {

    public NoteAdapter(Activity activity, List<Note> list) {
        super(activity, R.layout.note_list_item);
        setItems(list);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_note_headline, R.id.tv_note_content, R.id.tv_note_edit_time};
    }

    @Override
    protected void update(int position, Note note) {
        setText(0, note.getHeadLine());
        setText(1, note.getContent());
        setText(2, note.getEditDate());
    }
}
