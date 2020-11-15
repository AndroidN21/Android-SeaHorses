package com.company;

import java.util.ArrayList;

public class User {
    final int NUM_HORSE = 4;
    private ArrayList<Horse> listHorse;
    private int flag;//Cờ này báo User đến lượt chơi
    private int idUser;

    public User(int idUser) {
        this.listHorse = new ArrayList<Horse>(4);
        for (int i = 0; i < NUM_HORSE; i++) {
            this.listHorse.add(i, new Horse(1, new Tuple(0, 0), i * 14, idUser, i));
        }
        this.flag = 0;
        this.idUser = idUser;
    }

    public Horse getHorse(int idHorse) {
        return listHorse.get(idHorse);
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
