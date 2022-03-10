package com.brianmorales.gymlogger10.database;

import com.brianmorales.gymlogger10.models.GymLog;

import java.util.ArrayList;

public class GymLogDatabase {


    private static final ArrayList<GymLog> loggerList = new ArrayList<>();

    public static ArrayList<GymLog> getLoggerList() {
        return loggerList;
    }


    public static void addGymLog(GymLog logger) {
        loggerList.add(logger);
    }

    public static void removeGymLog(GymLog logger) {
        if(loggerList.isEmpty()){
            return;
        }
        for(GymLog l : loggerList){
            if(l.getWorkoutTitle() == logger.getWorkoutTitle()){
                loggerList.remove(l);
            }
        }
    }

    public static boolean containsGymLog(GymLog logger) {
        if(loggerList.isEmpty()){
            return false;
        }
        for(GymLog l : loggerList) {
            if(logger.getWorkoutTitle() ==  l.getWorkoutTitle()){
                return true;
            }
        }
        return false;
    }

    public static void updateLoggerDB(GymLog logger) {
        for(GymLog log : loggerList){
            if(logger.getWorkoutTitle() ==  log.getWorkoutTitle()){
                log.updateLogger(logger);
            }
        }
    }
    public static int getListSize(){
        return loggerList.size();
    }



}
