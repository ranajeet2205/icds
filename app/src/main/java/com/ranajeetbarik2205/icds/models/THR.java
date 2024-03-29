package com.ranajeetbarik2205.icds.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "take_home_ration")
public class THR implements Parcelable {

    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "reporting_month")
    private String reporting_month;

    @ColumnInfo(name = "centre")
    private String centre;

    @ColumnInfo(name = "number_total_beneficiary")
    private String number_total_beneficiary;

    @ColumnInfo(name = "number_total_packets")
    private String number_total_packets;

    @ColumnInfo(name = "uri_thr_photo")
    private String uri_thr_photo;

    @ColumnInfo(name = "total_cost")
    private String total_cost;

    @ColumnInfo(name = "status")
    private int status;

    public THR() {
    }

    public THR( String reporting_month, String centre, String number_total_beneficiary,
                String number_total_packets,String uri_thr_photo, String total_cost,int status) {

        this.reporting_month = reporting_month;
        this.centre = centre;
        this.number_total_beneficiary = number_total_beneficiary;
        this.number_total_packets = number_total_packets;
        this.uri_thr_photo = uri_thr_photo;
        this.total_cost = total_cost;
        this.status = status;
    }

    protected THR(Parcel in) {
        id = in.readInt();
        reporting_month = in.readString();
        centre = in.readString();
        number_total_beneficiary = in.readString();
        number_total_packets = in.readString();
        uri_thr_photo = in.readString();
        total_cost = in.readString();
        status = in.readInt();
    }

    public static final Creator<THR> CREATOR = new Creator<THR>() {
        @Override
        public THR createFromParcel(Parcel in) {
            return new THR(in);
        }

        @Override
        public THR[] newArray(int size) {
            return new THR[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUri_thr_photo() {
        return uri_thr_photo;
    }

    public void setUri_thr_photo(String uri_thr_photo) {
        this.uri_thr_photo = uri_thr_photo;
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

    public String getNumber_total_beneficiary() {
        return number_total_beneficiary;
    }

    public void setNumber_total_beneficiary(String number_total_beneficiary) {
        this.number_total_beneficiary = number_total_beneficiary;
    }

    public String getNumber_total_packets() {
        return number_total_packets;
    }

    public void setNumber_total_packets(String number_total_packets) {
        this.number_total_packets = number_total_packets;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(reporting_month);
        dest.writeString(centre);
        dest.writeString(number_total_beneficiary);
        dest.writeString(number_total_packets);
        dest.writeString(uri_thr_photo);
        dest.writeString(total_cost);
        dest.writeInt(status);
    }
}
