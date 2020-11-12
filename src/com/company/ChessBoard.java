package com.company;

import java.util.ArrayList;

public class ChessBoard {
    final int NUM_USER=4;
    private ArrayList<User> listUser;

    public ChessBoard(){
        listUser=new ArrayList<User>(4);
        for(int i=0;i<NUM_USER;i++) {
            listUser.add(i, new User(i+1));
        }
        listUser.get(0).setFlag(1);
    }

}
