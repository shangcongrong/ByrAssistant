package com.byr.assistant.ui.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Note;
import com.byr.assistant.core.persistance.DbCache;
import com.byr.assistant.core.persistance.DbManager;
import com.byr.assistant.core.persistance.PersistableNote;
import com.byr.assistant.ui.ItemListFragment;
import com.byr.assistant.ui.home.HomeActivity;
import com.byr.assistant.utils.ActivityUtils;


import java.util.List;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午12:23
 */
public class NoteListFragment extends ItemListFragment<Note> {


    private String TAG = "com.byr.assistant.ui.note.NoteListFragment";

    private DbCache cache;

    private List<Note> list;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cache = DbManager.getInstance(activity).getDatabaseCache();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    protected SingleTypeAdapter<Note> createAdapter(List<Note> items) {
        return new NoteAdapter(getActivity(), items);
    }

    private void loadNotes() {
        list = cache.loadFromDB(new PersistableNote());
        if (list != null)
            onLoadFinished(list);
        else
            setEmptyText("请添加笔记");
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Note note = list.get(position);
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    @Override
    public boolean onListItemLongClick(ListView l, View v, int position, long id) {
        return super.onListItemLongClick(l, v, position, id);
    }
}
