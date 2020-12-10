package hcmus.nhom21.handle;

import android.database.Cursor;
import android.view.View;
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
    final int TARGET = 56;

    private ArrayList<User> listUser;
    private ArrayList<Tuple> listIdHorse;// Luu ngua voi status =1 dang duoc chay voi x la idUser va y là idHorse
    int[] arrSetupPlayer;

    private int userTurn;
    private int horseTurn;
    private int step;
    private boolean isRepeat;

    public ChessBoard(int[] arrSetupPlayer, Tuple LOCATE_BOARD, Tuple SIZE_BOARD, Tuple SIZE_HORSE, Database database){
        this.arrSetupPlayer=arrSetupPlayer;
        this.LOCATE_BOARD=LOCATE_BOARD;
        this.SIZE_BOARD=SIZE_BOARD;
        this.SIZE_HORSE=SIZE_HORSE;
        this.database = database;

        listUser=new ArrayList<User>(NUM_USER);
        listIdHorse = new ArrayList<Tuple>();
        userTurn=0;
        horseTurn=-1;
    }

    public void initChessBoard(ArrayList<ImageView> imgHorse){
        //Khởi tạo User và những con ngựa nó quản lý
        for (int idUser = 0; idUser < NUM_USER; idUser++) {
            User user = new User(idUser,arrSetupPlayer[idUser], LOCATE_BOARD, SIZE_BOARD, SIZE_HORSE);
            listUser.add(idUser, user);
            if(arrSetupPlayer[idUser]!=User.MODE_NONE) {
                for (int idHorse = 0; idHorse < NUM_HORSE; idHorse++) {
                    user.getListHorse().add(idHorse, new Horse(imgHorse.get(idUser * NUM_HORSE + idHorse), idUser * 14, idUser, idHorse));
                    user.setInitialHorseCoord(idHorse);
                }
            }
            else{
                for (int idHorse = 0; idHorse < NUM_HORSE; idHorse++) {
                    imgHorse.get(idUser * NUM_HORSE + idHorse).setVisibility(View.VISIBLE);
                    imgHorse.get(idUser * NUM_HORSE + idHorse).setSelected(true);
                }
            }
        }

    }

    public void loadChessBoard(){
        Cursor dataHorse = database.getData("SELECT * FROM Horse");
        Cursor dataUser = database.getData("SELECT * FROM User");
        Cursor dataChessBoard = database.getData("SELECT * FROM ChessBoard");

        int idHorse, idUser, position, level, stepped;
        dataHorse.moveToFirst();
        do{
            int idLogic = dataHorse.getInt(0);
            idHorse = idLogic % 4;
            idUser = idLogic / 4;
            position = dataHorse.getInt(1);
            level = dataHorse.getInt(2);
            System.out.println("TABLE HORSE" +idHorse +" "+ idUser+" "+position+" "+level+"\n");

            listIdHorse.add(new Tuple(idUser, idHorse));
            Horse horse = getHorse(new Tuple(idUser, idHorse));
            horse.setPosition(position);
            horse.setStatus(1);
            horse.setLevel(level);
            User user=listUser.get(idUser);
            user.setHorseCoordByPosition(idHorse);

        }
        while (dataHorse.moveToNext());

        int mode, horseWinNum;
        dataUser.moveToFirst();
        do{
            idUser=dataUser.getInt(0);
            mode=dataUser.getInt(1);
            horseWinNum=dataUser.getInt(2);

            getUser(idUser).setMode(mode);
            getUser(idUser).setHorseWinNum(horseWinNum);
        }
        while(dataUser.moveToNext());

        dataChessBoard.moveToFirst();

        userTurn = dataChessBoard.getInt(0);
        horseTurn = dataChessBoard.getInt(1);
        step = dataChessBoard.getInt(2);
        isRepeat= (dataChessBoard.getInt(3)==1);
        System.out.println("CHESSBOAR" + userTurn + " " + horseTurn + " "+ step +" " + isRepeat);
        database.queryData("DROP TABLE Horse");
        database.queryData("DROP TABLE User");
        database.queryData("DROP TABLE ChessBoard");
    }

    public void saveChessBoard(){
        database.queryData("DROP TABLE IF EXISTS Horse");
        database.queryData("DROP TABLE IF EXISTS User");
        database.queryData("DROP TABLE IF EXISTS ChessBoard");

        //Tạo bảng
        database.queryData("CREATE TABLE IF NOT EXISTS Horse(id INTEGER PRIMARY KEY, position INTEGER, level INTEGER)");
        database.queryData("CREATE TABLE IF NOT EXISTS User(id INTEGER PRIMARY KEY, mode INTEGER, horseWinNum INTEGER)");
        database.queryData("CREATE TABLE IF NOT EXISTS ChessBoard(userTurn INTEGER, horseTURN Integer, step Integer, isRepeat INTEGER)");
        //Insert data horse đang di chuyển
        Horse horse;
        int idLogic;
        for (int i = 0; i < listIdHorse.size(); i++) {
            horse = getHorse(listIdHorse.get(i));
            idLogic = horse.getIdUser() * 4 + horse.getIdHorse();
            database.queryData("INSERT INTO Horse VALUES (" + idLogic + "," + horse.getPosition() + "," + horse.getLevel() + ")");
        }

        User user;
        for (int i = 0; i < listUser.size(); i++) {
            user=getUser(i);
            database.queryData("INSERT INTO User VALUES (" + user.getIdUser() + "," + user.getMode() + "," +  user.getHorseWinNum() +")");
        }

        //Insert turn sleeped
        database.queryData("INSERT INTO ChessBoard VALUES (" + userTurn + "," + horseTurn + "," + step + "," + (isRepeat?1:0) + ")");

    }

    public Tuple rollDice(){
        User user= listUser.get(userTurn);
        Dice dice = new Dice(1, 1);
        boolean isLucky=false;

        if(isRepeat){
            do {
                step = dice.rollDice();
            }
            while(dice.getNumDice1() == dice.getNumDice2());
        }
        else {
            for (int idHorse = 0; idHorse < NUM_HORSE; idHorse++) {
                if (user.getHorse(idHorse).getStatus() == 1)
                    break;
                if (idHorse == NUM_HORSE - 1)
                    isLucky = true;
            }
            do {
                step = dice.rollDice();
            }
            while (isLucky && dice.getNumDice1() != dice.getNumDice2());
        }
        isRepeat = false;//Mặc định gán false
        if (dice.getNumDice1() == dice.getNumDice2() || (step == 7 && (dice.getNumDice1() == 1 || dice.getNumDice1() == 6)))
            isRepeat = true;
        //System.out.println("Buoc nhay: "+ dice.getNumDice1() +"----"+dice.getNumDice2() +"\n");
        return new Tuple(dice.getNumDice1(),dice.getNumDice2());
    }

    public ArrayList<Integer> generateHorseValid() {
        Tuple errorConflict = new Tuple(0,0);
        Horse horse = null;
        User user = listUser.get(userTurn);

        //Khởi tạo mảng ngụa có thể chạy
        ArrayList<Integer> horseValid = new ArrayList<Integer>();
        for (int idHorse = 0; idHorse < NUM_HORSE; idHorse++) {
            horse = user.getHorse(idHorse);
            int tmp=step;
            if(horse.getStepped()+step >Horse.TARGET){
                tmp=horse.getStepped()+ step-Horse.TARGET;
            }
            else if(horse.getStatus()==0 && isRepeat==true)
            {
                tmp=0;
            }

            errorConflict = checkConflict(horse, tmp);

            Tuple idPair = new Tuple();
            try {
                idPair = listIdHorse.get(errorConflict.x);
            }catch (Exception e){};

            int conflictCode=errorConflict.y;
            if (horse.getStatus() == 1 && horse.getStepped()+step <= Horse.TARGET + Horse.NUM_LEVEL - user.getHorseWinNum()) {
                if(conflictCode==0 || (conflictCode==1 && idPair.x != userTurn)){
                    horseValid.add(idHorse);
                }
            }
            else if (isRepeat && horse.getStatus() == 0) {
                if(errorConflict.y==0 || (errorConflict.y == 1 && idPair.x != userTurn))
                    horseValid.add(idHorse);
            }
        }
//        if(horseValid.size()<=0 && isRepeat==false) {
//            userTurn= (userTurn+1)%4;
//        }
        return horseValid;
    }

    public void moveHorse(int step){
        User user=listUser.get(userTurn);
        //Horse horse=user.getHorse(horseTurn);
        user.MoveHorse(horseTurn,step);
    }
    public void updateChessBoard(){
        User user=listUser.get(userTurn);
        user.setImgHorse(horseTurn);
    }

    public Tuple checkConflict(Horse horse, int step) { //Trả về 1 cặp biến lỗi vs biến đầu là vị trí ngựa trong listIdHorse và biến sau là mã lỗi
        Tuple errorConflict=new Tuple(-1,0);
        for (int i = 0; i < listIdHorse.size(); i++) {

            Horse otherHorse = getHorse(listIdHorse.get(i));
            int newPosition = (horse.getPosition() + step)%TARGET;
            int newRound = (horse.getPosition() + step)/TARGET;

            if ( newPosition == otherHorse.getPosition()) {
                errorConflict = new Tuple(i,1);
            }

            if(horse.getPosition() < otherHorse.getPosition() && newPosition > otherHorse.getPosition()) {
                errorConflict = new Tuple(i, -1);
                break;
            }
            if(newRound>=1) {
                if (horse.getPosition() < otherHorse.getPosition()){
                    errorConflict = new Tuple(i, -1);
                break;
                }
                else if(otherHorse.getPosition()<newPosition) {
                    errorConflict = new Tuple(i, -1);
                    break;
                }
            }
        }
        return errorConflict;
    }

    public void Dangua(int id) {
        Tuple idPair = listIdHorse.get(id);
        User user = listUser.get(idPair.x);
        user.setInitialHorseCoord(idPair.y);
        listIdHorse.remove(id);
    }

    public void XuatChuong() {
        User user = listUser.get(userTurn);

        Tuple errorConflict = new Tuple(-1,0);

        Horse horse = user.getHorse(horseTurn);
        errorConflict = checkConflict(horse, 0);
        int conflictCode = errorConflict.y;
        Tuple idPair =new Tuple();
        if (conflictCode != 0) {
            idPair=listIdHorse.get(errorConflict.x);
            if(idPair.x != userTurn)
                Dangua(errorConflict.x);
        }
        listIdHorse.add(new Tuple(userTurn,horseTurn));
        user.setHorseCoordByPosition(horseTurn);
    }

    public Horse getHorse(Tuple idPair) {
        User user = listUser.get(idPair.x);
        Horse horse = user.getHorse(idPair.y);
        return horse;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
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
