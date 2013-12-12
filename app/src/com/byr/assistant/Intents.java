package com.byr.assistant;

import android.content.Intent;
import com.byr.assistant.core.model.Course;
import com.byr.assistant.core.model.Event;
import com.byr.assistant.core.model.Forum;
import com.byr.assistant.core.model.Note;

import java.io.Serializable;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 下午7:37
 */
public class Intents {

    public static final String INTENT_PREFIX = "com.byr.assistant.";

    public static final String INTENT_EXTRA_PREFIX = INTENT_PREFIX + "extra.";

    public static final String EXTRA_COURSE = INTENT_EXTRA_PREFIX
            + "COURSE";

    public static final String EXTRA_EVENT = INTENT_EXTRA_PREFIX
            + "EVENT";

    public static final String EXTRA_FORUM = INTENT_EXTRA_PREFIX
            + "FORUM";

    public static final String EXTRA_NOTE = INTENT_EXTRA_PREFIX + "NOTE";


    public static class Builder {

        private final Intent intent;

        /**
         * Create builder with suffix
         *
         * @param actionSuffix
         */
        public Builder(String actionSuffix) {
            // actionSuffix = e.g. "repos.VIEW"
            intent = new Intent(INTENT_PREFIX + actionSuffix);
        }

        public Builder course(Course course) {
            return add(EXTRA_COURSE, course);
        }

        public Builder event(Event event) {
            return add(EXTRA_EVENT, event);
        }

        public Builder forum(Forum forum) {
            return add(EXTRA_FORUM, forum);
        }

        public Builder note(Note note) {
            return add(EXTRA_NOTE, note);
        }

        public Builder add(String fieldName, String value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        public Builder add(String fieldName, CharSequence[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        public Builder add(String fieldName, int value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        public Builder add(String fieldName, int[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        public Builder add(String fieldName, boolean[] values) {
            intent.putExtra(fieldName, values);
            return this;
        }

        public Builder add(String fieldName, Serializable value) {
            intent.putExtra(fieldName, value);
            return this;
        }

        public Intent toIntent() {
            return intent;
        }
    }
}
