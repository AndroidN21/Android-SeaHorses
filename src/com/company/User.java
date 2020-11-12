package com.company;

import java.util.ArrayList;

public class User {
    private ArrayList<Horse> listHorse;
    private int flag;
    private int id;

    public User(ArrayList<Horse> listHorse, int id) {
        this.listHorse = listHorse;
        this.flag = 0;
        this.id = id;
    }

    public ArrayList<Horse> getListHorse() {
        return listHorse;
    }

    public void setListHorse(ArrayList<Horse> listHorse) {
        this.listHorse = listHorse;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
