package com.brianmorales.gymlogger10.models;


import java.util.ArrayList;
import java.util.List;

public class Exercises {
    String exerciseTitle;
    List<String> exerciseList;


    public Exercises(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
        exerciseList = new ArrayList<>();

    }
    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public List<String> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<String> exerciseList) {
        this.exerciseList = exerciseList;
    }
    public boolean isEmpty(){
        return exerciseList.isEmpty();
    }

}
