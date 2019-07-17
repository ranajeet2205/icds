package com.ranajeetbarik2205.icds.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beneficiary")
public class BNF implements Parcelable {

    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "reporting_month")
    private String reporting_month;

    @ColumnInfo(name = "centre")
    private String centre;

    @ColumnInfo(name = "number_pm")
    private String number_pm;

    @ColumnInfo(name = "number_nm")
    private String number_nm;

    @ColumnInfo(name = "number_babies")
    private String number_babies;

    @ColumnInfo(name = "number_preschool")
    private String number_preschool;

    @ColumnInfo(name = "number_total_beneficiary")
    private String number_total_beneficiary;

    @ColumnInfo(name = "status")
    private int status;

    public BNF() {
    }

    public BNF( String reporting_month, String centre, String number_pm, String number_nm,
                String number_babies,String number_preschool,
                String number_total_beneficiary,int status) {


        this.reporting_month = reporting_month;
        this.centre = centre;
        this.number_pm = number_pm;
        this.number_nm = number_nm;
        this.number_babies = number_babies;
        this.number_preschool = number_preschool;
        this.number_total_beneficiary = number_total_beneficiary;
        this.status = status;
    }

    protected BNF(Parcel in) {
        id = in.readInt();
        reporting_month = in.readString();
        centre = in.readString();
        number_pm = in.readString();
        number_nm = in.readString();
        number_babies = in.readString();
        number_preschool = in.readString();
        number_total_beneficiary = in.readString();
        status = in.readInt();
    }

    public static final Creator<BNF> CREATOR = new Creator<BNF>() {
        @Override
        public BNF createFromParcel(Parcel in) {
            return new BNF(in);
        }

        @Override
        public BNF[] newArray(int size) {
            return new BNF[size];
        }
    };

    public String getNumber_preschool() {
        return number_preschool;
    }

    public void setNumber_preschool(String number_preschool) {
        this.number_preschool = number_preschool;
    }

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

    public String getNumber_total_beneficiary() {
        return number_total_beneficiary;
    }

    public void setNumber_total_beneficiary(String number_total_beneficiary) {
        this.number_total_beneficiary = number_total_beneficiary;
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
        dest.writeString(number_pm);
        dest.writeString(number_nm);
        dest.writeString(number_babies);
        dest.writeString(number_preschool);
        dest.writeString(number_total_beneficiary);
        dest.writeInt(status);
    }
}
