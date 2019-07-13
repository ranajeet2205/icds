package com.ranajeetbarik2205.icds.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="immunization")
public class Immunization {

    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "reporting_month")
    private String reporting_month;

    @ColumnInfo(name = "centre")
    private String centre;

    @ColumnInfo(name = "number_total_due")
    private String number_total_due;

    @ColumnInfo(name = "total_number_received")
    private String total_number_received;

    @ColumnInfo(name = "uri_photo_immunization")
    private String uri_photo_immunization;

    @ColumnInfo(name = "status")
    private int status;

    public Immunization() {
    }

    public Immunization( String reporting_month, String centre, String number_total_due,
                         String total_number_received,String uri_photo_immunization, int status) {

        this.reporting_month = reporting_month;
        this.centre = centre;
        this.number_total_due = number_total_due;
        this.total_number_received = total_number_received;
        this.uri_photo_immunization = uri_photo_immunization;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri_photo_immunization() {
        return uri_photo_immunization;
    }

    public void setUri_photo_immunization(String uri_photo_immunization) {
        this.uri_photo_immunization = uri_photo_immunization;
    }

    public String getReporting_month() {
        return reporting_month;
    }

    public void setReporting_month(String reporting_month) {
        this.reporting_month = reporting_month;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getNumber_total_due() {
        return number_total_due;
    }

    public void setNumber_total_due(String number_total_due) {
        this.number_total_due = number_total_due;
    }

    public String getTotal_number_received() {
        return total_number_received;
    }

    public void setTotal_number_received(String total_number_received) {
        this.total_number_received = total_number_received;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
