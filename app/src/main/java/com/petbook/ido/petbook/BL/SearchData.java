package com.petbook.ido.petbook.BL;

/**
 * Created by Ido on 8/13/2016.
 */
public class SearchData {

    private int nGender;
    private int nAnimalType;
    private String strCondition;
    private int nAreaCode;
    private int nMinAge;
    private int nMaxAge;

    public SearchData(int nGender, int nAnimalType, String strCondition, int nAreaCode, int nMinAge, int nMaxAge) {
        this.nGender = nGender;
        this.nAnimalType = nAnimalType;
        this.strCondition = strCondition;
        this.nAreaCode = nAreaCode;
        this.nMinAge = nMinAge;
        this.nMaxAge = nMaxAge;
    }

    public int getnGender() {
        return nGender;
    }

    public void setnGender(int nGender) {
        this.nGender = nGender;
    }

    public int getnAnimalType() {
        return nAnimalType;
    }

    public void setnAnimalType(int nAnimalType) {
        this.nAnimalType = nAnimalType;
    }

    public String getStrCondition() {
        return strCondition;
    }

    public void setStrCondition(String strCondition) {
        this.strCondition = strCondition;
    }

    public int getnAreaCode() {
        return nAreaCode;
    }

    public void setnAreaCode(int nAreaCode) {
        this.nAreaCode = nAreaCode;
    }

    public int getnMinAge() {
        return nMinAge;
    }

    public void setnMinAge(int nMinAge) {
        this.nMinAge = nMinAge;
    }

    public int getnMaxAge() {
        return nMaxAge;
    }

    public void setnMaxAge(int nMaxAge) {
        this.nMaxAge = nMaxAge;
    }




}
