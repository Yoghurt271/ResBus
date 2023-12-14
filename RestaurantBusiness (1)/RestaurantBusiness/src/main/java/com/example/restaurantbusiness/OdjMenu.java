package com.example.restaurantbusiness;

public class OdjMenu {

    int id;
    String name;
    int size;
    int prise;
    String type;
    String AdditInfo;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setPrise(int prise) {
        this.prise = prise;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAdditInfo(String AdditInfo) {
        this.AdditInfo = AdditInfo;
    }

    public int getId() { return id; }
    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public int getPrise() {
        return prise;
    }
    public String getType() {
        return type;
    }
    public String getAdditInfo() {
        return AdditInfo;
    }


    public OdjMenu(int id, String name, int size, int prise,String type, String AdditInfo){
        this.id = id;
        this.name = name;
        this.size = size;
        this.prise = prise;
        this.type = type;
        this.AdditInfo = AdditInfo;
    }

}
