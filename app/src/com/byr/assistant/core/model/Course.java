package com.byr.assistant.core.model;

import java.io.Serializable;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午12:26
 */
public class Course implements Serializable {


//    {"college":"计算机学院","courseId":1001,"courseInterval":"1","courseName":"数据结构","courseType":"必修","credit":"10","durationTime":"120"
//            ,"endTime":"2013-11-14","major":"计算机科学与技术","place":"学10","startTime":"2013-09-01","stuCount":"20","teacher":"孔融"

    private String courseId;

    private String college;


    //表示上课间隔
    private int interval;

    private String courseName;

    private String courseType;

    //学分
    private int credit;

    private String startTime;

    private String endTime;

    //专业
    private String major;

    private String location;

    //学生人数
    private int studentCount;

    private String teacher;

    private String startDate;

    private String endDate;

    private int workday;

    private boolean added = false;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getWorkday() {
        return workday;
    }

    public void setWorkday(int workday) {
        this.workday = workday;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}
