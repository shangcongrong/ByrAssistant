package com.byr.assistant.ui.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.byr.assistant.R;
import com.byr.assistant.core.Toaster;
import com.byr.assistant.core.model.Note;
import com.byr.assistant.core.persistance.DbCache;
import com.byr.assistant.core.persistance.DbManager;
import com.byr.assistant.core.persistance.PersistableNote;
import com.byr.assistant.ui.DialogFragment;
import com.byr.assistant.utils.TimeUtils;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-29
 * Time: 下午10:38
 * To change this template use File | Settings | File Templates.
 */
public class AddNoteFragment extends DialogFragment implements View.OnClickListener {

    private EditText etHeadline;

    private EditText etContent;

    private Button btnSave;

    private Button btnCancel;

    private DbCache cache;

    private Note note;

    private boolean isAddNote;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cache = DbManager.getInstance(activity).getDatabaseCache();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = getSerializableExtra("note");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_add_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etHeadline = (EditText) view.findViewById(R.id.et_note_headline);
        etContent = (EditText) view.findViewById(R.id.et_note_content);
        btnSave = (Button) view.findViewById(R.id.btn_save);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        initViews();
    }

    private void initViews() {

        if (note != null) {
            isAddNote = false;
            loadNote();
        } else {
            isAddNote = true;
            note = new Note();
        }
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void loadNote() {
        String headline = note.getHeadLine();
        String content = note.getContent();
        etHeadline.setText(headline);
        etContent.setText(content);
    }

    private void saveNote() {

        String headline = etHeadline.getText().toString();
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(headline) && TextUtils.isEmpty(content)) {
            Toaster.showShort(getActivity(), "请输入内容再进行保存~");
            return;
        }

        note.setHeadLine(headline);
        note.setContent(content);
        note.setEditDate(TimeUtils.getCurrentTime());

        if (isAddNote) {
            cache.insert(new PersistableNote(), note);
        } else
            cache.update(new PersistableNote(), note);

        startActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveNote();
                break;
            case R.id.btn_cancel:
                startActivity();
                break;
            default:
                break;
        }
    }

    private void startActivity() {
        Intent intent = new Intent(getActivity(), NoteActivity.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
