package hcmus.nhom21.handle;

import android.widget.ImageView;

import java.util.ArrayList;

public class User {
    final int NUM_HORSE = 4;
    final float scaleStableBoardSize = (float) (140) / 390;

    private ArrayList<Horse> listHorse;
    private int flag;//Cờ này báo User đến lượt chơi
    private int idUser;

    private Tuple chessBoardCoord;//Toa đo cua bàn cờ với x,y
    private Tuple userCoord;//Tọa độ của khu vực user
    private Tuple chessBoardSize;//Kích thược bàn cờ x là chiều rộng y là chiều dài
    private Tuple stableSize;//Kích thước mỗi ô user

    public User(int idUser, Tuple chessBoardCoord, Tuple chessBoardSize) {
        this.listHorse = new ArrayList<Horse>(4);
        this.flag = 0;
        this.idUser = idUser;
        this.chessBoardCoord = chessBoardCoord;
        this.chessBoardSize = chessBoardSize;

        this.stableSize = new Tuple();
        this.stableSize.x = (int) (this.chessBoardSize.x * scaleStableBoardSize);
        this.stableSize.y = (int) (this.chessBoardSize.y * scaleStableBoardSize);

        //Xác định vị trí của mỗi user (nếu các có thêm -60 là do bị lệch sẽ tìm khắc phục sau)
        this.userCoord = new Tuple();

        if (idUser == 0) {
            this.userCoord.x = this.chessBoardCoord.x;
            this.userCoord.y = this.chessBoardCoord.y;
        } else if (idUser == 1) {
            this.userCoord.x = this.chessBoardCoord.x;
            this.userCoord.y = this.chessBoardCoord.y + this.chessBoardSize.y - this.stableSize.y - 60;
        } else if (idUser == 2) {
            this.userCoord.x = this.chessBoardCoord.x + this.chessBoardSize.x - this.stableSize.x - 60;
            this.userCoord.y = this.chessBoardCoord.y + this.chessBoardSize.y - this.stableSize.y - 60;
        } else {
            this.userCoord.x = this.chessBoardCoord.x + this.chessBoardSize.x - this.stableSize.x - 60;
            this.userCoord.y = this.chessBoardCoord.y;
        }
        System.out.println("User " + idUser + ":  " + stableSize.x + "&&&" + stableSize.y + "/n");
        System.out.println("User " + idUser + ":  " + userCoord.x + "&&&" + userCoord.y + "/n");
        //Khởi tạo vị trí ban đầu cho ngựa (nằm trong chuồng)
    }

    public Horse getHorse(int idHorse) {
        return listHorse.get(idHorse);
    }

//    public void setHorseCoord(int idHorse,int position){
//        Tuple coord=new Tuple();
//        switch (idUser){
//            case 0:
//                //coord.x=userCoord.x
//                break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            default:
//                break;
//        }
//    }

    public void resetHorseCoord(int idHorse) {//Reset toa do ngua trong chuong
        //Tính điểm giữa của mỗi ô(nếu các có thêm hằng số bất kỳ là do bị lệch sẽ tìm khắc phục sau)
        Tuple middle = new Tuple(); //Đây là điểm tọa độ ở giữa ô user
        middle.x = (2 * userCoord.x + this.stableSize.x) / 2-20;
        middle.y = (2 * userCoord.y + this.stableSize.y) / 2-30;

        System.out.println("Middle " + idUser + ":  " + middle.x + "&&&" + middle.y + "/n");
        Tuple intialCoord = new Tuple();
        //Tính vị trí của mỗi chú ngựa cách điểm giữa 20px
        switch (idHorse) {
            case 0:
                intialCoord = new Tuple(middle.x - 50, middle.y - 50);
                break;
            case 1:
                intialCoord = new Tuple(middle.x - 50, middle.y + 20);
                break;
            case 2:
                intialCoord = new Tuple(middle.x + 20, middle.y + 20);
                break;
            case 3:
                intialCoord = new Tuple(middle.x + 20, middle.y - 50);
                break;
            default:
                break;
        }

        listHorse.get(idHorse).setCoord(intialCoord);
        listHorse.get(idHorse).resetImgHorse();
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
