/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animalshelter1;
import  java.time.LocalDateTime;

/**
 *
 * @author ma1898
 */
public class Log {
    
    String Time;
    int LogID;
    String SQL;
    
      Log(int LogID, String Time, String SQL) {

        this.Time = Time;
        this.LogID = LogID;
        this.SQL=SQL;

    }
     public int getLogID() {
        return LogID;
    }

   
    public void setLogID(int LogID) {
        this.LogID = LogID;
    }

   
    public String getTime() {
        return Time;
    }


    public void setTime(String Time) {
        this.Time = Time;
    }


    public String getSQL() {
        return SQL;
    }

   
    public void setSql(String SQL) {
        this.SQL = SQL;
    }


  
}
