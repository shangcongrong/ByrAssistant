package com.byr.assistant.core;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * http://code.google.com/p/android/issues/detail?id=14944.
 * User: orange
 * Date: 13-9-19
 * Time: 下午2:17
 */
public abstract class AsyncLoader<D> extends AsyncTaskLoader<D> {

    private D data;

    public AsyncLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(D data) {
        if (isReset())
            return;

        this.data = data;

        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (data != null)
            deliverResult(data);

        if (takeContentChanged() || data == null)
            forceLoad();
    }


    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();
        data = null;
    }
}
