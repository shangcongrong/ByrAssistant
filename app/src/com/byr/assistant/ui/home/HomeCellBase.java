package com.byr.assistant.ui.home;

import android.support.v4.app.Fragment;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class HomeCellBase<E> extends Fragment {

    abstract HomeCellBase<E> loadData(E item);

    abstract void showEmptyText();

    abstract void showData();

}
