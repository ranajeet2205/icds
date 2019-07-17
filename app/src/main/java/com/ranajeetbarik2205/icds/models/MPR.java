package com.ranajeetbarik2205.icds.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "monthly_progress")
public class MPR  implements Parcelable {

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

    @ColumnInfo(name = "number_adolosense_girl")
    private String number_adolosense_girl;

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
               String number_health_checkup, String number_referal_children, String number_adolosense_girl,
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
        this.number_adolosense_girl = number_adolosense_girl;
        this.uri_photo_children = uri_photo_children;
        this.uri_photo_centre = uri_photo_centre;
        this.uri_signature = uri_signature;
        this.status = status;
    }


    protected MPR(Parcel in) {
        id = in.readInt();
        centre = in.readString();
        reporting_month = in.readString();
        aww_name = in.readString();
        awh_name = in.readString();
        number_pm = in.readString();
        number_nm = in.readString();
        number_babies = in.readString();
        number_preschool_children = in.readString();
        number_immunized_children = in.readString();
        number_health_checkup = in.readString();
        number_referal_children = in.readString();
        number_adolosense_girl = in.readString();
        uri_photo_children = in.readString();
        uri_photo_centre = in.readString();
        uri_signature = in.readString();
        status = in.readInt();
    }

    public static final Creator<MPR> CREATOR = new Creator<MPR>() {
        @Override
        public MPR createFromParcel(Parcel in) {
            return new MPR(in);
        }

        @Override
        public MPR[] newArray(int size) {
            return new MPR[size];
        }
    };

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNumber_adolosense_girl() {
        return number_adolosense_girl;
    }

    public void setNumber_adolosense_girl(String number_adolosense_girl) {
        this.number_adolosense_girl = number_adolosense_girl;
    }

    public static Creator<MPR> getCREATOR() {
        return CREATOR;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(centre);
        dest.writeString(reporting_month);
        dest.writeString(aww_name);
        dest.writeString(awh_name);
        dest.writeString(number_pm);
        dest.writeString(number_nm);
        dest.writeString(number_babies);
        dest.writeString(number_preschool_children);
        dest.writeString(number_immunized_children);
        dest.writeString(number_health_checkup);
        dest.writeString(number_referal_children);
        dest.writeString(number_adolosense_girl);
        dest.writeString(uri_photo_children);
        dest.writeString(uri_photo_centre);
        dest.writeString(uri_signature);
        dest.writeInt(status);
    }
}
