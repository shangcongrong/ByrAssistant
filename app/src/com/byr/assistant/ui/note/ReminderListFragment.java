package com.byr.assistant.ui.note;

import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Reminder;
import com.byr.assistant.ui.ItemListFragment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-28
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class ReminderListFragment extends ItemListFragment<Reminder>{
    @Override
    protected SingleTypeAdapter<Reminder> createAdapter(List<Reminder> items) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
