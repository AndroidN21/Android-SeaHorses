package hcmus.nhom21.handle;


import android.widget.ImageView;

import static java.lang.Thread.sleep;


public class Horse {
    final int TARGET = 56;
    final int NUM_LEVEL = 6;
    private Tuple coord;//????
    private int status;
    private int stepped;
    private int position;
    private int level;
    private int idUser;
    private int idHorse;
    ImageView imgHorse;

    public boolean Move(int step, int smallJump, int bigJump) {
        //do something

        int i = 0;
        if (stepped < TARGET) {
            //Xu ly di tren ban co
            while (i < step) {
                stepped += 1;

                if (position >= 12 && position <= 13) {
                    coord.y += bigJump;
                }else if ((position >= 0 && position <= 5) || (position >= 20 && position <= 25)) {
                    coord.y += smallJump;
                } else if (position >= 54 && position <= 55) {
                    coord.x -= bigJump;
                } else if ((position >= 6 && position <= 11) || (position >= 42 && position <= 47)) {
                    coord.x -= smallJump;
                } else if (position >= 26 && position <= 27) {
                    coord.x += bigJump;
                } else if ((position >= 14 && position <= 19) || (position >= 34 && position <= 39)) {
                    coord.x += smallJump;
                } else if (position >= 40 && position <= 41) {
                    coord.y -= bigJump;
                } else coord.y -= smallJump;


                this.resetImgHorse();

                if (stepped >= TARGET)
                    break;
                i++;
                position = (position + 1) % TARGET;
            }
        }

        //Xu ly di tren buc
        if (stepped >= TARGET) {
            while (i < step) {
                if (position == 13) {
                    coord.x += smallJump;
                } else if (position == 27) {
                    coord.y -= smallJump;
                } else if (position == 41) {
                    coord.x -= smallJump;
                } else if (position == 55) {
                    coord.y += smallJump;
                }
                if (level >= NUM_LEVEL) {
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.resetImgHorse();
                level++;
                stepped++;
                i++;
            }

        }
        return true;
    }


    public void resetInitial() {
        //this.position=position;
        this.coord = new Tuple(0, 0);
        this.imgHorse.setX(coord.x);
        this.imgHorse.setY(coord.y);
        this.position = idUser * 14;
        this.level = 0;
        this.status = 0;
        this.stepped = 0;
    }

    public Horse(ImageView view, int position, int idUser, int idHorse) {
        imgHorse = view;
        this.position = position;
        this.idUser = idUser;
        this.idHorse = idHorse;
        this.level = 0;
        this.status = 0;
        this.stepped = 0;
    }

    public void resetImgHorse() {
        //System.out.println("Horse "+idHorse+": "+coord.x +"&&&"+coord.y +"/n");
        this.imgHorse.setX(coord.x);
        this.imgHorse.setY(coord.y);

    }

    public Tuple getCoord() {
        return coord;
    }

    public void setCoord(Tuple coord) {
        this.coord = coord;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStepped() {
        return stepped;
    }

    public void setStepped(int stepped) {
        this.stepped = stepped;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdHorse() {
        return idHorse;
    }

    public void setIdHorse(int idHorse) {
        this.idHorse = idHorse;
    }

}
