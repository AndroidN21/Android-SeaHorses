package hcmus.nhom21.handle;

import android.database.Cursor;
import android.widget.ImageView;

import java.util.ArrayList;

public class ChessBoard {
    //Các biến liên quan đến widget của activity
    private Tuple LOCATE_BOARD;
    private Tuple SIZE_BOARD;
    private Tuple SIZE_HORSE;
    private Database database;

    //Hằng số
    final int NUM_USER = 4;
    final int NUM_HORSE=4;

    private ArrayList<User> listUser;
    private ArrayList<Tuple> listIdHorse;// Luu ngua voi status =1 dang duoc chay voi x la idUser va y là idHorse

    private int userTurn;
    private int horseTurn;
    private int step;

    public ChessBoard(Tuple LOCATE_BOARD, Tuple SIZE_BOARD, Tuple SIZE_HORSE, Database database){
        this.LOCATE_BOARD=LOCATE_BOARD;
        this.SIZE_BOARD=SIZE_BOARD;
        this.SIZE_HORSE=SIZE_HORSE;
        this.database = database;
    }

    public void initChessBoard(ArrayList<ImageView> imgHorse){
        //int[] horseInitialCoord=new int[2];
        //imgHorse.get(0).getLocationOnScreen(horseInitialCoord);
        //System.out.println("IMGHORSE:   " + horseInitialCoord[0] + "&&&&" +horseInitialCoord[1] + "\n");
        listUser=new ArrayList<User>(NUM_USER);
        listIdHorse = new ArrayList<Tuple>();
        userTurn=0;
        horseTurn=-1;

        //Khởi tạo User và những con ngựa nó quản lý
        for (int idUser = 0; idUser < NUM_USER; idUser++) {
            User user = new User(idUser, LOCATE_BOARD, SIZE_BOARD, SIZE_HORSE);
            listUser.add(idUser, user);

            for (int idHorse = 0; idHorse < NUM_HORSE; idHorse++) {
                user.getListHorse().add(idHorse, new Horse(imgHorse.get(idUser * NUM_HORSE + idHorse), idUser * 14, idUser, idHorse));
                user.setInitialHorseCoord(idHorse);
                //user.setHorseCoord(idHorse);
            }
        }

    }

    public void loadChessBoard(){
        Cursor dataHorse = database.getData("SELECT * FROM Horse");
        Cursor dataUser = database.getData("SELECT * FROM User");

        int idHorse, idUser, position, level, stepped;
        while (dataHorse.moveToNext()) {
            int idLogic = dataHorse.getInt(0);
            idHorse = idLogic % 4;
            idUser = idLogic / 4;
            position = dataHorse.getInt(1);
            level = dataHorse.getInt(2);
            stepped = dataHorse.getInt(3);

            //System.out.println(idHorse+" "+idUser+" "+id+" "+" "+" ");
            listIdHorse.add(new Tuple(idUser, idHorse));
            Horse horse = getHorse(new Tuple(idUser, idHorse));
            horse.setPosition(position);
            horse.setStatus(1);
            horse.setLevel(level);
            horse.setStatus(stepped);
        }

        while (dataUser.moveToNext()) {
            idUser = dataUser.getInt(0);
            int step = dataUser.getInt(1);
            User user = listUser.get(idUser);
            user.setStep(step);
        }

        database.queryData("DROP TABLE Horse");
        database.queryData("DROP TABLE User");
    }

    public void saveChessBoard(){
        //Tạo bảng
        database.queryData("CREATE TABLE IF NOT EXISTS Horse(id INTEGER PRIMARY KEY, position INTEGER, level INTEGER, stepped INTEGER)");
        database.queryData("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY, step INTEGER)");
        //Insert data horse đang di chuyển
        Horse horse;
        int idLogic;
        for (int i = 0; i < listIdHorse.size(); i++) {
            horse = getHorse(listIdHorse.get(i));
            idLogic = horse.getIdUser() * 4 + horse.getIdHorse();
            database.queryData("INSERT INTO Horse VALUES (" + idLogic + "," + horse.getPosition() + "," + horse.getLevel() + "," + horse.getStepped() + ")");
        }

        //Insert data user
        User user;
        for (int idUser = 0; idUser < listUser.size(); idUser++) {
            user = listUser.get(idUser);
            if (user.getFlag() == 1) {
                database.queryData("INSERT INTO User VALUES (" + idUser + "," + user.getStep() + ")");
                break;
            }
        }

    }

    public ArrayList<Integer> Turn() {
        Tuple idPair = new Tuple();
        int flagConflict = 0;
        Dice dice = new Dice(1, 1);
        Horse horse = null;
        step = dice.rollDice();

        System.out.println("Buoc nhay: "+ dice.getNumDice1() +"----"+dice.getNumDice2() +"\n");
        User user = listUser.get(userTurn);
        boolean isRepeat = false;//Mặc định gán false

        if (dice.getNumDice1() == dice.getNumDice2() || (step == 7 && (dice.getNumDice1() == 1 || dice.getNumDice1() == 6)))
            isRepeat = true;

        //Khởi tạo mảng ngụa có thể chạy
        ArrayList<Integer> horseValid = new ArrayList<Integer>();
        for (int idHorse = 0; idHorse < NUM_HORSE; idHorse++) {
            horse = user.getHorse(idHorse);
            flagConflict = checkConflict(idPair, horse, step);
            if (horse.getStatus() == 1 && flagConflict != -1 && idPair.x != userTurn) {
                horseValid.add(idHorse);
            }
            if (isRepeat && horse.getStatus() == 0) {
                horseValid.add(idHorse);
            }
        }
        if(horseValid.size()<=0) {
            userTurn= (userTurn+1)%4;
        }
        return horseValid;
    }
    public void moveHorse(int step){
        User user=listUser.get(userTurn);
        Horse horse=user.getHorse(horseTurn);
        user.MoveHorse(horseTurn,step);
        System.out.println("DI CHUYEN HORSE: " +horse.getPosition()+"---("+horse.getCoord().x + "," +horse.getCoord().y+") \n");
    }
    public void updateChessBoard(){
        User user=listUser.get(userTurn);
        user.setImgHorse(horseTurn);
    }

    public int checkConflict(Tuple idPair, Horse horse, int step) {
        idPair=new Tuple();
        for (int i = 0; i < listIdHorse.size(); i++) {
            Horse otherHorse = getHorse(listIdHorse.get(i));
            if (horse.getPosition() + step == otherHorse.getPosition()) {
                idPair = listIdHorse.get(i);
                return 1;
            }
            if (horse.getPosition() < otherHorse.getPosition() && horse.getPosition() + step > otherHorse.getPosition()) {
                idPair = listIdHorse.get(i);
                return -1;
            }
        }
        return 0;
    }

    public void Dangua(Tuple idPair) {
        Horse horse = getHorse(idPair);

        horse.resetInitial();
        for (int i = 0; i < listIdHorse.size(); i++) {
            if (listIdHorse.get(i).x == idPair.x && listIdHorse.get(i).y == idPair.y) {
                listIdHorse.remove(i);
                break;
            }
        }
        //listHorse.remove(horse.getPosition() + 1);
    }

    public void XuatChuong() {
        User user = listUser.get(userTurn);
        Tuple idPair = new Tuple();
        int flag = 0;

        Horse horse = user.getHorse(horseTurn);
        flag = checkConflict(idPair, horse, 0);

        if (flag != 0 && idPair.x != userTurn) {
            Dangua(idPair);
        }
        user.setHorseCoordByPosition(horseTurn);
        System.out.println("Xuat chuong thanh cong "+ user.getHorse(horseTurn).getStatus());
    }

    public Horse getHorse(Tuple idPair) {
        User user = listUser.get(idPair.x);
        Horse horse = user.getHorse(idPair.y);
        return horse;
    }

    public User getUser(){
        return listUser.get(userTurn);
    }

    public User getUser(int idUser){
        return listUser.get(idUser);
    }

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public ArrayList<Tuple> getListIdHorse() {
        return listIdHorse;
    }

    public void setListIdHorse(ArrayList<Tuple> listIdHorse) {
        this.listIdHorse = listIdHorse;
    }

    public int getUserTurn() {
        return userTurn;
    }

    public void setUserTurn(int userTurn) {
        this.userTurn = userTurn;
    }

    public int getHorseTurn() {
        return horseTurn;
    }

    public void setHorseTurn(int horseTurn) {
        this.horseTurn = horseTurn;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
