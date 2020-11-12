package com.company;

import java.util.ArrayList;

public class ChessBoard {
    final int NUM_USER=4;
    private ArrayList<User> listUser;
    private ArrayList<Horse> listHorse; // Khoi phuc du lieu khi bi gian doan

    public ChessBoard(){
        listUser=new ArrayList<User>(4);
        for(int i=0;i<NUM_USER;i++) {
            listUser.add(i, new User(i+1));
        }
        listUser.get(0).setFlag(1);
    }


    public void Turn(int id){
        Dice dice;
        dice = new Dice(1,1);
        if(dice.getNumDice1() == dice.getNumDice2()){
            //Xử lý xuất chuồng
        }

    }

    public boolean handleConflict(int position, int step){
        for(int i =0;i<listHorse.size();i++)
        {
            if(position + step ==  listHorse.get(i).getPosition()){
                // Xu ly da ngua
                return true;
            }
            if(position < listHorse.get(i).getPosition() && position + step > listHorse.get(i).getPosition()){
                return false;
            }
        }
        return true;
    }

}
