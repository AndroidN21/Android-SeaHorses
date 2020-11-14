package com.company;

import java.util.ArrayList;

public class ChessBoard {
    final int NUM_USER = 4;
    private ArrayList<User> listUser;
    private ArrayList<Horse> listHorse;

    public ChessBoard() {
        listUser = new ArrayList<User>(4);
        for (int i = 0; i < NUM_USER; i++) {
            listUser.add(i, new User(i + 1));
        }
        listUser.get(0).setFlag(1);
    }

    public boolean Turn(int id) {
        Dice dice;
        dice = new Dice(1, 1);// Khoi tao load image
        int step = dice.rollDice();
        Horse horse=null;
        User user=listUser.get(id);
        boolean isRepeat=false;
        //Đk đi kèm với select xuat chuong cua nguoi dung
        if (dice.getNumDice1() == dice.getNumDice2() || (step == 7 && (dice.getNumDice1() == 1 || dice.getNumDice1() == 6))) {

            //if() nguoi dung chon
            horse = listUser.get(id).XuatChuong();
            if (horse != null) {
                listHorse.add(horse);
            }

            isRepeat=true;
        }
        if (horse == null) {
            //Khởi tạo mảng ngụa có thể chạy
            ArrayList<Horse> horseValid = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
               horse = user.getListHorse().get(i);
                if (horse.getStatus() == 1 && checkConflict(horse, step)!=-1) {
                    horseValid.add(horse);
                }
            }

            //Xu ly nguoi dung chon ngua trong mang ngua vua nay
            horse.Move(step - 1);
            int index=checkConflict(horse,1);
            if(index>=0){
                Dangua(index);
            }
            horse.Move(1);


        }


        return isRepeat;
    }


    public int checkConflict(Horse horse,int step){
        for(int i=0; i<listHorse.size();i++){
            if(horse.getPosition() + step == listHorse.get(i).getPosition()){
                return i;
            }
            if(horse.getPosition() < listHorse.get(i).getPosition() && horse.getPosition() + step > listHorse.get(i).getPosition())
                return -1;
        }
        return -2;
    }

    public void Dangua(int index){
        Horse horse=listHorse.get(index);
        int idUser=horse.getIdUser();
        User user =listUser.get(idUser);
        int idHorse=horse.getIdHorse();

        user.getHorse(idHorse).Reset();
        listHorse.remove(index);
        //listHorse.remove(horse.getPosition() + 1);
    }
}
