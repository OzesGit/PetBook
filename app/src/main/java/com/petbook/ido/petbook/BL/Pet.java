package com.petbook.ido.petbook.BL;

import android.graphics.Bitmap;
import android.graphics.Picture;

/**
 * Created by Omri on 12/08/2016.
 */
public class Pet {

    private String Name;
    private int Id;
    private String AndroidId;
    private int Gender;
    private int Type;
    private int Condition;
    private String PhoneNumber;
    private int Location;
    private String Email;
    private String Notes;
    private byte[] Picture;
    private int Age;
    private int Areacode;
    private boolean isVirgin;
    private String dealsWith;

    public String getDealsWith() {
        return dealsWith;
    }

    public void setDealsWith(String dealsWith) {
        this.dealsWith = dealsWith;
    }
    public int getAreacode() {
        return Areacode;
    }

    public void setAreacode(int areacode) {
        Areacode = areacode;
    }

    public boolean getIsVirgin() {
        return isVirgin;
    }

    public void setIsVirgin(boolean virgin) {
        isVirgin = virgin;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
    public byte[] getPicture() {
        return Picture;
    }

    public void setPicture(byte[] picture) {
        Picture = picture;
    }


    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getCondition() {
        return Condition;
    }

    public void setCondition(int condition) {
        Condition = condition;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getAndroidId() {
        return AndroidId;
    }

    public void setAndroidId(String androidId) {
        AndroidId = androidId;
    }



    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
