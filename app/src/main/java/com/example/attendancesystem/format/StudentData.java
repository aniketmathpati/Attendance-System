package com.example.attendancesystem.format;

public class StudentData {
    private String fname,lname,rollno,Class,div,branch;
    private int id,status,totalAttendance;

    public int getStudentId(){
        return id;
    }

    public void setStudentId(int id){
        this.id=id;
    }

    public String getFname(){
        return fname;
    }

    public void setFname(String fname){
        this.fname=fname;
    }

    public String getLname(){
        return lname;
    }

    public void setLname(String lname){
        this.lname=lname;
    }

    public String getRollno(){
        return rollno;
    }

    public void setRollno(String rollno){
        this.rollno=rollno;
    }

    public String getDiv(){
        return div;
    }

    public void setDiv(String div){
        this.div=div;
    }

    public String getclass(){
        return Class;
    }

    public void setClass(String Class){
        this.Class=Class;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status=status;
    }

    public String getBranch(){
        return branch;
    }

    public void setBranch(String branch){
        this.branch=branch;
    }


    public int getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(int totalAttendance) {
        this.totalAttendance = totalAttendance;
    }
}
