package com.rushbox.android.theboxapp.model;

import android.support.annotation.Nullable;

import com.rushbox.android.theboxapp.connections.ResponseOperation;
import com.rushbox.android.theboxapp.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Ronner on 06-04-2016.
 */
public class User {
    private long ID_User;
    private String TX_Name;
    private String TX_LastName;
    private String TX_Email;
    private String TX_UserName;
    private String TX_Password;
    private String TX_PhoneNumber;
    @Nullable
    private Boolean BO_Provider;
    @Nullable
    private Boolean BO_Active;
    @Nullable
    private Date DT_Register;
    private byte[] IM_Image;

    public User() {
    }

    public User(String TX_Name, String TX_LastName, String TX_Email, String TX_UserName, String TX_Password, String TX_PhoneNumber) {
        this.TX_Name = TX_Name;
        this.TX_LastName = TX_LastName;
        this.TX_Email = TX_Email;
        this.TX_UserName = TX_UserName;
        this.TX_Password = TX_Password;
        this.TX_PhoneNumber = TX_PhoneNumber;
    }

    public User(long ID_User, String TX_Name, String TX_LastName, String TX_Email, String TX_UserName, String TX_Password, String TX_PhoneNumber) {
        this.ID_User = ID_User;
        this.TX_Name = TX_Name;
        this.TX_LastName = TX_LastName;
        this.TX_Email = TX_Email;
        this.TX_UserName = TX_UserName;
        this.TX_Password = TX_Password;
        this.TX_PhoneNumber = TX_PhoneNumber;
    }

    public User(long ID_User, String TX_Name, String TX_LastName, String TX_Email, String TX_UserName, String TX_Password,
                String TX_PhoneNumber, Boolean BO_Provider, Boolean BO_Active, Date DT_Register, byte[] IM_Image) {
        this.ID_User = ID_User;
        this.TX_Name = TX_Name;
        this.TX_LastName = TX_LastName;
        this.TX_Email = TX_Email;
        this.TX_UserName = TX_UserName;
        this.TX_Password = TX_Password;

        this.TX_PhoneNumber = TX_PhoneNumber;
        this.BO_Provider = BO_Provider;
        this.BO_Active = BO_Active;
        this.DT_Register = DT_Register;
        this.IM_Image = IM_Image;
    }

    public long getID_User() {

        return ID_User;
    }

    public void setID_User(long ID_User) {
        this.ID_User = ID_User;
    }

    public String getTX_Name() {
        return TX_Name;
    }

    public void setTX_Name(String TX_Name) {
        this.TX_Name = TX_Name;
    }

    public String getTX_LastName() {
        return TX_LastName;
    }

    public void setTX_LastName(String TX_LastName) {
        this.TX_LastName = TX_LastName;
    }

    public String getTX_Email() {
        return TX_Email;
    }

    public void setTX_Email(String TX_Email) {
        this.TX_Email = TX_Email;
    }

    public String getTX_UserName() {
        return TX_UserName;
    }

    public void setTX_UserName(String TX_UserName) {
        this.TX_UserName = TX_UserName;
    }

    public String getTX_Password() {
        return TX_Password;
    }

    public void setTX_Password(String TX_Password) {
        this.TX_Password = TX_Password;
    }

    public String getTX_PhoneNumber() {
        return TX_PhoneNumber;
    }

    public void setTX_PhoneNumber(String TX_PhoneNumber) {
        this.TX_PhoneNumber = TX_PhoneNumber;
    }

    @Nullable
    public Boolean getBO_Provider() {
        return BO_Provider;
    }

    public void setBO_Provider(@Nullable Boolean BO_Provider) {
        this.BO_Provider = BO_Provider;
    }

    @Nullable
    public Boolean getBO_Active() {
        return BO_Active;
    }

    public void setBO_Active(@Nullable Boolean BO_Active) {
        this.BO_Active = BO_Active;
    }

    @Nullable
    public Date getDT_Register() {
        return DT_Register;
    }

    public void setDT_Register(@Nullable Date DT_Register) {
        this.DT_Register = DT_Register;
    }

    public byte[] getIM_Image() {
        return IM_Image;
    }

    public void setIM_Image(byte[] IM_Image) {
        this.IM_Image = IM_Image;
    }

    public static User parseUser(String object) {
        return (User) Utility.fromJson(object, User.class);
    }
}
