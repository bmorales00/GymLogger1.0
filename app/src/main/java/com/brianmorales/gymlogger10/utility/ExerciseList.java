package com.brianmorales.gymlogger10.utility;

import com.brianmorales.gymlogger10.models.Exercises;

import java.util.Arrays;
import java.util.List;

public class ExerciseList {

    public static Exercises abs = new Exercises("Abs");
    public static Exercises back = new Exercises("Back");
    public static Exercises biceps = new Exercises("Biceps");
    public static Exercises chest = new Exercises("Chest");
    public static Exercises legs = new Exercises("Legs");
    public static Exercises shoulders = new Exercises("Shoulders");
    public static Exercises triceps = new Exercises("Triceps");



      public static List<String> absList = Arrays.asList("Crunch", "Dumbbell Side Bend", "Plank");
      public static List<String> backList = Arrays.asList("Barbell Row", "Lateral PullDown", "DeadLift");
      public static List<String> bicepsList = Arrays.asList("Barbell Curl", "Cable Rope Curls", "Hammer Curls");
      public static List<String> chestList = Arrays.asList("Cable Chest Fly", "Incline Dumbbell Fly", "Low Cable Fly");
      public static List<String> legsList = Arrays.asList("Barbell Squats", "Calf Raise", "Front Squats");
      public static List<String> shouldersList = Arrays.asList("Arnold Dumbbell Press", "Front Raises", "Barbell Military Press");
      public static List<String> tricepsList = Arrays.asList("Cable Overhead Triceps Extensions", "V Bar Push Down", "Dips");




}
