package com.example.restaurantbusiness;

public class Obj {
    String name;
    int size;
    int prise;
    String AdditInfo;

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public void setAdditInfo(String AdditInfo) {
        this.AdditInfo = AdditInfo;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getPrise() {
        return prise;
    }

    public String getAdditInfo() {
        return AdditInfo;
    }


    public Obj(String name, int size, int prise, String AdditInfo){
        this.name = name;
        this.size = size;
        this.prise = prise;
        this.AdditInfo = AdditInfo;
    }
}
