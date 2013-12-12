package com.byr.assistant.core.persistance;

/**
 * User: orange
 * Date: 13-11-25
 * Time: 下午6:39
 */
public class DbConstants {

    public static class CourseConstants {

        public static String TABLE_USER_COURSES = "userCourses";

        public static String TABLE_COUSERS = "courses";

        public static String COURSE_ID = "courseId";

        public static String COLLEGE = "college";

        public static String COURSE_TYPE = "courseType";

        public static String INTERVAL = "interval";

        public static String COURSE_NAME = "courseName";

        public static String CREDIT = "credit";

        public static String START_TIME = "startTime";

        public static String END_TIME = "endTime";

        public static String MAJOR = "major";

        public static String LOCATION = "location";

        public static String STUDENT_COUNT = "studentCount";

        public static String TEACHER = "teacher";

        public static String START_DATE = "startDate";

        public static String END_DATE = "endDate";

        public static String WORKDAY = "workday";

        public static String ADDED = "added";

    }


    public static class EventConstants {

        public static String TABLE_EVENT = "events";

        public static String EVENT_ID = "eventId";

        public static String EVENT_NAME = "eventName";

        public static String TIME = "time";

        public static String LOCATION = "location";

        public static String DEPARTMENT = "department";

        public static String CONTENT = "content";


    }

    public static class ForumConstants {

        public static String TABLE_FORUM = "forums";

        public static String FORUM_ID = "forumId";

        public static String TITLE = "title";

        public static String ARTICLE_URL = "articleUrl";

        public static String AUTHOR = "author";

        public static String CONTENT = "content";

        public static String PUBLISH_DATE = "publishDate";
    }

    public static class NoteConstants {

        public static String TABLE_NOTE = "notes";

        public static String NOTE_ID = "_id";

        public static String HEADLINE = "headLine";

        public static String CONTENT = "content";

        public static String EDIT_TIME = "editTime";
    }

}
