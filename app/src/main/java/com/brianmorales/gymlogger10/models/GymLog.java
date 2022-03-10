package com.brianmorales.gymlogger10.models;

import java.io.Serializable;
import java.util.ArrayList;

public class GymLog implements Serializable {


    private String workoutTitle;
    private int id;
    private String setsDescription;


    private class Sets implements Serializable{

        private final double weight;
        private final int reps;

        public Sets(double weight, int reps) {
            this.weight = weight;
            this.reps = reps;
        }

        public double getWeight() {
            return weight;
        }

        public int getReps() {
            return reps;
        }
    }

    private ArrayList<Sets> sets = new ArrayList<>();

    public GymLog() {
    }

    public GymLog(String workoutTitle){
        this.workoutTitle = workoutTitle;
    }

    public GymLog(String workoutTitle, String setsDescription, int id) {
        this.workoutTitle = workoutTitle;
        this.setsDescription = setsDescription;
        this.id = id;
    }

    public GymLog(String workoutTitle, double weight, int reps) {
        this.workoutTitle = workoutTitle;
        sets.add(new Sets(weight, reps));

    }

    public void addSets(double weight, int reps){
        sets.add(new Sets(weight, reps));
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public int getSetSize() {
        return sets.size();
    }

    public String getSetsToString(){
        StringBuilder output = new StringBuilder();
        for(Sets s: this.sets){
            output.append("                                    ")
                    .append(s.getWeight())
                    .append(" lbs")
                    .append("                ")
                    .append(s.getReps())
                    .append(" reps")
                    .append("\n");
        }
        return output.toString();
    }


    public String getWeightAt(int index) {
        return Double.toString(sets.get(index).getWeight());
    }

    public String getRepsAt(int index) {
        return Integer.toString(sets.get(index).getReps());
    }

    public ArrayList<Sets> getSets(){
        return sets;
    }

    public void updateLogger(GymLog logger) {
        this.sets = logger.getSets();
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getSetsDescription() {
        return setsDescription;
    }

}
