package com.ranajeetbarik2205.icds.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "children_weight")
public class Weight {

    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "centre")
    private String centre;

    @ColumnInfo(name = "reporting_month")
    private String reporting_month;

    @ColumnInfo(name = "number_babies")
    private String number_babies;

    @ColumnInfo(name = "number_normal_babies")
    private String number_normal_babies;

    @ColumnInfo(name = "number_moderate_babies")
    private String number_moderate_babies;

    @ColumnInfo(name = "number_smn_babies")
    private String number_smn_babies;

    @ColumnInfo(name = "number__normal_preschool_children")
    private String number_preschool_children;

    @ColumnInfo(name = "number_moderate_preschool_children")
    private String number_moderate_children;

    @ColumnInfo(name = "number_snm_preschool_children")
    private String number_snm_children;

    @ColumnInfo(name = "status")
    private int status;

    public Weight() {
    }

    public Weight( String centre, String reporting_month, String number_babies, String number_normal_babies, String number_moderate_babies, String number_smn_babies, String number_preschool_children, String number_moderate_children, String number_snm_children, int status) {
        this.centre = centre;
        this.reporting_month = reporting_month;
        this.number_babies = number_babies;
        this.number_normal_babies = number_normal_babies;
        this.number_moderate_babies = number_moderate_babies;
        this.number_smn_babies = number_smn_babies;
        this.number_preschool_children = number_preschool_children;
        this.number_moderate_children = number_moderate_children;
        this.number_snm_children = number_snm_children;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNumber_babies() {
        return number_babies;
    }

    public void setNumber_babies(String number_babies) {
        this.number_babies = number_babies;
    }

    public String getNumber_normal_babies() {
        return number_normal_babies;
    }

    public void setNumber_normal_babies(String number_normal_babies) {
        this.number_normal_babies = number_normal_babies;
    }

    public String getNumber_moderate_babies() {
        return number_moderate_babies;
    }

    public void setNumber_moderate_babies(String number_moderate_babies) {
        this.number_moderate_babies = number_moderate_babies;
    }

    public String getNumber_smn_babies() {
        return number_smn_babies;
    }

    public void setNumber_smn_babies(String number_smn_babies) {
        this.number_smn_babies = number_smn_babies;
    }

    public String getNumber_preschool_children() {
        return number_preschool_children;
    }

    public void setNumber_preschool_children(String number_preschool_children) {
        this.number_preschool_children = number_preschool_children;
    }

    public String getNumber_moderate_children() {
        return number_moderate_children;
    }

    public void setNumber_moderate_children(String number_moderate_children) {
        this.number_moderate_children = number_moderate_children;
    }

    public String getNumber_snm_children() {
        return number_snm_children;
    }

    public void setNumber_snm_children(String number_snm_children) {
        this.number_snm_children = number_snm_children;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
