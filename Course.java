package com.company;
import jdk.nashorn.api.tree.ArrayLiteralTree;

import java.util.ArrayList;

import java.awt.*;

public class Course {
    private String courseCode;
    private String courseDay;
    private ArrayList<Integer> courseSection;

    private Color color;

    public Course(){}
    public Course(String n){courseCode = n;}
    public Course(String n, String d, ArrayList<Integer> sec){courseCode = n; courseDay = d; courseSection = sec;}

    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseDay(){
        return courseDay;
    }
    public ArrayList<Integer> getCourseSection() {
        return courseSection;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }

    public void setCourseSection(ArrayList<Integer> courseSection) {
        this.courseSection = courseSection;
    }



}
