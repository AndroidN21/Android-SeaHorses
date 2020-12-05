package hcmus.nhom21.handle;

import android.widget.ImageView;

import java.util.ArrayList;

public class User {

    final int NUM_HORSE = 4;
    public static int MODE_NONE=1;
    public static int MODE_USER=2;
    public static int MODE_ROBOT=3;

    final float scaleStableInBoardSize = (float) (140) / 390;
    final float scaleSmallJumpInBoardSize = (float) (24) / 390;
    final float scaleBigJumpInBoardSize = (float) (37) / 390;

    private ArrayList<Horse> listHorse;
    private int flag;//Cờ này báo User đến lượt chơi
    private int idUser;
    private int mode;
    private int horseWinNum;

    private Tuple chessBoardCoord;//Toa đo cua bàn cờ với x,y
    private Tuple userCoord;//Tọa độ của khu vực user
    private Tuple chessBoardSize;//Kích thược bàn cờ x là chiều rộng y là chiều dài
    private Tuple stableSize;//Kích thước mỗi ô user
    private Tuple horseSize;

    public User(int idUser, Tuple chessBoardCoord, Tuple chessBoardSize, Tuple horseSize) {
        this.listHorse = new ArrayList<Horse>(4);
        this.mode = MODE_ROBOT;
        this.horseWinNum=0;
        this.flag = 0;
        this.idUser = idUser;
        this.chessBoardCoord=chessBoardCoord;
        this.chessBoardCoord=new Tuple(chessBoardCoord.x,chessBoardCoord.y-36);

        this.chessBoardSize = chessBoardSize;
        this.horseSize = horseSize;

        this.stableSize = new Tuple();
        this.stableSize.x = (int) (this.chessBoardSize.x * scaleStableInBoardSize);
        this.stableSize.y = (int) (this.chessBoardSize.y * scaleStableInBoardSize);

        //Xác định vị trí của mỗi user (nếu các có thêm -60 là do bị lệch sẽ tìm khắc phục sau)
        this.userCoord = new Tuple();

        if (idUser == 0) {
            this.userCoord.x = this.chessBoardCoord.x;
            this.userCoord.y = this.chessBoardCoord.y;
        } else if (idUser == 1) {
            this.userCoord.x = this.chessBoardCoord.x;
            this.userCoord.y = this.chessBoardCoord.y + this.chessBoardSize.y - this.stableSize.y -10;
        } else if (idUser == 2) {
            this.userCoord.x = this.chessBoardCoord.x + this.chessBoardSize.x - this.stableSize.x -16;
            this.userCoord.y = this.chessBoardCoord.y + this.chessBoardSize.y - this.stableSize.y -10;
        } else {
            this.userCoord.x = this.chessBoardCoord.x + this.chessBoardSize.x - this.stableSize.x -16;
            this.userCoord.y = this.chessBoardCoord.y;
        }
//        System.out.println("UserStable " + idUser + ":  " + stableSize.x + "&&&" + stableSize.y + "/n");
//        System.out.println("UserChessboard " + idUser + ":  " + chessBoardSize.x + "&&&" + chessBoardSize.y + "/n");
//        System.out.println("User " + idUser + ":  " + userCoord.x + "&&&" + userCoord.y + "/n");
    }

    public void MoveHorse(int idHorse, int step) {
        Horse horse =listHorse.get(idHorse);
        Tuple smallJump =new Tuple();
        smallJump.x =(int) (chessBoardSize.x * scaleSmallJumpInBoardSize-1);
        smallJump.y =(int) (chessBoardSize.y * scaleSmallJumpInBoardSize+1);
        Tuple bigJump =new Tuple();
        bigJump.x =(int) (chessBoardSize.x * scaleBigJumpInBoardSize);
        bigJump.y =(int) (chessBoardSize.y * scaleBigJumpInBoardSize);
        horse.Move(step, smallJump, bigJump);
        if(horse.getLevel()==Horse.NUM_LEVEL-this.horseWinNum){
            this.horseWinNum+=1;
        }
    }

    public boolean checkWin(){return horseWinNum==NUM_HORSE;}


    public Horse getHorse(int idHorse) {
        return listHorse.get(idHorse);
    }

    public void setImgHorse(int idHorse) {
        listHorse.get(idHorse).resetImgHorse();
    }
    //Set tọa độ của Horse dựa vào position đc sử dụng khi chỉ có position
    public void setHorseCoordByPosition(int idHorse) {

        Horse horse = listHorse.get(idHorse);
        int position = horse.getPosition();
        int level = horse.getLevel();
        int stepped = horse.getStepped();

        Tuple smallJump =new Tuple();
        smallJump.x =(int) (chessBoardSize.x * scaleSmallJumpInBoardSize);
        smallJump.y =(int) (chessBoardSize.y * scaleSmallJumpInBoardSize+1);
        Tuple bigJump =new Tuple();
        bigJump.x =(int) (chessBoardSize.x * scaleBigJumpInBoardSize);
        bigJump.y =(int) (chessBoardSize.y * scaleBigJumpInBoardSize);
        //Tọa độ tại vị trí position=0
        Tuple coord = new Tuple(chessBoardCoord.x + stableSize.x +9, chessBoardCoord.y-horseSize.y +12);

        //System.out.println("User jump " + idUser + ":  " + smallJump + "&&&" + bigJump + "/n");
        for (int i = 0; i < position; i++) {
            if (i >= 12 && i <= 13) {
                coord.y += bigJump.y;
            }else if ((i >= 0 && i <= 5) || (i >= 20 && i <= 25)) {
                coord.y += smallJump.y;
            } else if (i >= 54 && i <= 55) {
                coord.x -= bigJump.x;
            } else if ((i >= 6 && i <= 11) || (i >= 42 && i <= 47)) {
                coord.x -= smallJump.x;
            } else if (i >= 26 && i <= 27) {
                coord.x += bigJump.x;
            } else if ((i >= 14 && i <= 19) || (i >= 34 && i <= 39)) {
                coord.x += smallJump.x;
            } else if (i >= 40 && i <= 41) {
                coord.y -= bigJump.y;
            } else coord.y -= smallJump.y;
        }

        if (stepped >= Horse.TARGET) {
            for (int i = 0; i < level; i++) {
                if (position == 13) {
                    coord.x += smallJump.x;
                } else if (position == 27) {
                    coord.y -= smallJump.y;
                } else if (position == 41) {
                    coord.x -= smallJump.x;
                } else if (position == 55) {
                    coord.y += smallJump.y;
                }
                if (level >= Horse.NUM_LEVEL) {
                    break;
                }
                System.out.println("User jump " + idUser + ":  " + coord.x+ "&&&" + coord.y + "\n");
            }
        }

        horse.setCoord(coord);
        horse.setPosition(idUser*14);
        horse.resetImgHorse();
        horse.setStatus(1);
    }

    public void setInitialHorseCoord(int idHorse) {//Reset toa do ngua trong chuong
        Horse horse =listHorse.get(idHorse);
        int deviation = 5;
        //Tính điểm giữa của mỗi ô(nếu các có thêm hằng số bất kỳ là do bị lệch sẽ tìm khắc phục sau)
        Tuple middle = new Tuple(); //Đây là điểm tọa độ ở giữa ô user
        middle.x = (2 * userCoord.x + this.stableSize.x) / 2;
        middle.y = (2 * userCoord.y + this.stableSize.y) / 2;

        //System.out.println("Middle " + idUser + ":  " + middle.x + "&&&" + middle.y + "/n");
        Tuple initialCoord = new Tuple(middle.x, middle.y);

//        //Tính vị trí của mỗi chú ngựa cách điểm giữa 1 khoang sai so
        switch (idHorse) {
            case 0:
                initialCoord = new Tuple(middle.x - (horseSize.x + deviation), middle.y - (horseSize.y + deviation));
                break;
            case 1:
                initialCoord = new Tuple(middle.x - (horseSize.x + deviation), middle.y + deviation);
                break;
            case 2:
                initialCoord = new Tuple(middle.x + deviation, middle.y + deviation);
                break;
            case 3:
                initialCoord = new Tuple(middle.x + deviation, middle.y - (horseSize.y + deviation));
                break;
            default:
                break;
        }

        horse.setCoord(initialCoord);
        horse.setPosition(idUser*14);
        horse.resetImgHorse();
        horse.setStatus(0);
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

    public int getHorseWinNum() {
        return horseWinNum;
    }

    public void setHorseWinNum(int horseWinNum) {
        this.horseWinNum = horseWinNum;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
