package com.ranajeetbarik2205.icds.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "monthly_progress")
public class MPR {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "centre")
    private String centre;

    @ColumnInfo(name = "reporting_month")
    private String reporting_month;

    @ColumnInfo(name = "aww_name")
    private String aww_name;

    @ColumnInfo(name = "awh_name")
    private String awh_name;

    @ColumnInfo(name = "number_pm")
    private String number_pm;

    @ColumnInfo(name = "number_nm")
    private String number_nm;

    @ColumnInfo(name = "number_babies")
    private String number_babies;

    @ColumnInfo(name = "number_preschool_children")
    private String number_preschool_children;

    @ColumnInfo(name = "number_immunized_children")
    private String number_immunized_children;

    @ColumnInfo(name = "number_health_checkup")
    private String number_health_checkup;

    @ColumnInfo(name = "number_referal_children")
    private String number_referal_children;

    @ColumnInfo(name = "uri_photo_children")
    private String uri_photo_children;

    @ColumnInfo(name = "uri_photo_centre")
    private String uri_photo_centre;

    @ColumnInfo(name = "uri_signature")
    private String uri_signature;

    @ColumnInfo(name = "status")
    private int status;

    public MPR() {
    }

    public MPR(String reporting_month, String centre, String aww_name, String awh_name,
               String number_pm, String number_nm, String number_babies,
               String number_preschool_children, String number_immunized_children,
               String number_health_checkup, String number_referal_children,
               String uri_photo_children, String uri_photo_centre,String uri_signature,int status) {

        this.reporting_month = reporting_month;
        this.centre = centre;
        this.aww_name = aww_name;
        this.awh_name = awh_name;
        this.number_pm = number_pm;
        this.number_nm = number_nm;
        this.number_babies = number_babies;
        this.number_preschool_children = number_preschool_children;
        this.number_immunized_children = number_immunized_children;
        this.number_health_checkup = number_health_checkup;
        this.number_referal_children = number_referal_children;
        this.uri_photo_children = uri_photo_children;
        this.uri_photo_centre = uri_photo_centre;
        this.uri_signature = uri_signature;
        this.status = status;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUri_photo_children() {
        return uri_photo_children;
    }

    public void setUri_photo_children(String uri_photo_children) {
        this.uri_photo_children = uri_photo_children;
    }

    public String getUri_photo_centre() {
        return uri_photo_centre;
    }

    public void setUri_photo_centre(String uri_photo_centre) {
        this.uri_photo_centre = uri_photo_centre;
    }

    public String getUri_signature() {
        return uri_signature;
    }

    public void setUri_signature(String uri_signature) {
        this.uri_signature = uri_signature;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getReporting_month() {
        return reporting_month;
    }

    public void setReporting_month(String reporting_month) {
        this.reporting_month = reporting_month;
    }

    public String getAww_name() {
        return aww_name;
    }

    public void setAww_name(String aww_name) {
        this.aww_name = aww_name;
    }

    public String getAwh_name() {
        return awh_name;
    }

    public void setAwh_name(String awh_name) {
        this.awh_name = awh_name;
    }

    public String getNumber_pm() {
        return number_pm;
    }

    public void setNumber_pm(String number_pm) {
        this.number_pm = number_pm;
    }

    public String getNumber_nm() {
        return number_nm;
    }

    public void setNumber_nm(String number_nm) {
        this.number_nm = number_nm;
    }

    public String getNumber_babies() {
        return number_babies;
    }

    public void setNumber_babies(String number_babies) {
        this.number_babies = number_babies;
    }

    public String getNumber_preschool_children() {
        return number_preschool_children;
    }

    public void setNumber_preschool_children(String number_preschool_children) {
        this.number_preschool_children = number_preschool_children;
    }

    public String getNumber_immunized_children() {
        return number_immunized_children;
    }

    public void setNumber_immunized_children(String number_immunized_children) {
        this.number_immunized_children = number_immunized_children;
    }

    public String getNumber_health_checkup() {
        return number_health_checkup;
    }

    public void setNumber_health_checkup(String number_health_checkup) {
        this.number_health_checkup = number_health_checkup;
    }

    public String getNumber_referal_children() {
        return number_referal_children;
    }

    public void setNumber_referal_children(String number_referal_children) {
        this.number_referal_children = number_referal_children;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
