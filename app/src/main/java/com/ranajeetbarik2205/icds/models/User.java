package com.ranajeetbarik2205.icds.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "full_name")
    private String full_name;

    @ColumnInfo(name = "designation")
    private String designation;

    @ColumnInfo(name = "phone_number")
    private String phone_number;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "aadhar")
    private String aadhar;

    @ColumnInfo(name = "votor_id")
    private String votor_id;

    @ColumnInfo(name = "date_of_joining")
    private String date_of_joining;

    @ColumnInfo(name = "joining_district")
    private String joining_district;

    @ColumnInfo(name = "joining_project")
    private String joining_project;

    @ColumnInfo(name = "address")
    private String address;


    public User() {
    }

    public User(String full_name, String designation, String phone_number, String email,
                String aadhar, String votor_id, String date_of_joining, String joining_district,
                String joining_project, String address) {
        this.full_name = full_name;
        this.designation = designation;
        this.phone_number = phone_number;
        this.email = email;
        this.aadhar = aadhar;
        this.votor_id = votor_id;
        this.date_of_joining = date_of_joining;
        this.joining_district = joining_district;
        this.joining_project = joining_project;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getVotor_id() {
        return votor_id;
    }

    public void setVotor_id(String votor_id) {
        this.votor_id = votor_id;
    }

    public String getDate_of_joining() {
        return date_of_joining;
    }

    public void setDate_of_joining(String date_of_joining) {
        this.date_of_joining = date_of_joining;
    }

    public String getJoining_district() {
        return joining_district;
    }

    public void setJoining_district(String joining_district) {
        this.joining_district = joining_district;
    }

    public String getJoining_project() {
        return joining_project;
    }

    public void setJoining_project(String joining_project) {
        this.joining_project = joining_project;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
