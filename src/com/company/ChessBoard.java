package com.company;

import java.util.ArrayList;

public class ChessBoard {
    final int NUM_USER = 4;
    final int NUM_HORSE=4;
    private ArrayList<User> listUser;
    private ArrayList<Tuple> listIdHorse;// Luu ngua voi status =1 dang duoc chay voi x la idUser va y là idHorse

    public ChessBoard() {
        listUser = new ArrayList<User>(4);
        listIdHorse=new ArrayList<Tuple>();

        for (int i = 0; i < NUM_USER; i++) {
            listUser.add(i, new User(i + 1));
        }
        listUser.get(0).setFlag(1);
    }

    public boolean Turn(int idUser) {
        Dice dice;
        dice = new Dice(1, 1);// Khoi tao load image
        int step = dice.rollDice();
        Horse horse=null;
        User user=listUser.get(idUser);
        boolean isRepeat=false;
        //Đk đi kèm với select xuat chuong cua nguoi dung
        if (dice.getNumDice1() == dice.getNumDice2() || (step == 7 && (dice.getNumDice1() == 1 || dice.getNumDice1() == 6))) {

            //if() nguoi dung chon
            horse = XuatChuong(idUser);
            if (horse != null) {
                listIdHorse.add(new Tuple(idUser,horse.getIdHorse()));
            }

            isRepeat=true;
        }

        //Cần xem xét lại
        Tuple Id=new Tuple();
        int flagConflict=0;
        if (horse == null) {
            //Khởi tạo mảng ngụa có thể chạy
            ArrayList<Horse> horseValid = new ArrayList<>();
            for (int i = 0; i < NUM_HORSE; i++) {
                horse = user.getHorse(i);
                flagConflict=checkConflict(Id,horse, step);
                if (horse.getStatus() == 1 && flagConflict!=-1) {
                    horseValid.add(horse);
                }
            }

            //Xu ly nguoi dung chon ngua trong mang ngua vua nay
            horse.Move(step - 1);

            if(flagConflict==1 && idUser!=Id.x){
                Dangua(Id);
            }
            horse.Move(1);


        }


        return isRepeat;
    }


    public int checkConflict(Tuple Id,Horse horse,int step){
        for(int i=0; i<listIdHorse.size();i++){
            Horse otherHorse= getHorse(listIdHorse.get(i));
            if(horse.getPosition() + step == otherHorse.getPosition()){
                Id=listIdHorse.get(i);
                return 1;
            }
            if(horse.getPosition() < otherHorse.getPosition() && horse.getPosition() + step > otherHorse.getPosition()) {
                Id=listIdHorse.get(i);
                return -1;
            }
        }
        return 0;
    }

    public void Dangua(Tuple Id){
        Horse horse=getHorse(Id);

        horse.Reset();
        for(int i=0;i<listIdHorse.size();i++) {
            if(listIdHorse.get(i).x ==Id.x &&listIdHorse.get(i).y==Id.y ) {
                listIdHorse.remove(i);
                break;
            }
        }
        //listHorse.remove(horse.getPosition() + 1);
    }

    public Horse XuatChuong(int idUser){
        User user=listUser.get(idUser);
        Tuple Id=new Tuple();
        int flag=0;
        for(int i=0;i<NUM_HORSE;i++){
            Horse horse=user.getHorse(i);
            if(horse.getStatus()==0){
                flag=checkConflict(Id,horse,0);

                if(flag!=0){
                    if(Id.x!=idUser) Dangua(Id);
                    else break;
                }
                horse.initialCoord();
                horse.setStatus(1);
                return horse;
            }
        }
        return null;
    }

    public Horse getHorse(Tuple Id){
        User user= listUser.get(Id.x);
        Horse horse= user.getHorse(Id.y);
        return horse;
    }
}
