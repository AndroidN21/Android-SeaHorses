package hcmus.nhom21.handle;


import android.widget.ImageView;


public class Horse {
    final int TARGET =56;
    final int DISTANCE=5;
    private Tuple coord;//????
    private int status;
    private int stepped;
    private int position;
    private int idUser;
    private int idHorse;
    ImageView imgHorse;
    public boolean Move(int step){
        //do something
        if(stepped<TARGET) {
            //Xu ly di tren ban co
            for (int i = 0; i < step; i++) {
                stepped+=1;
                position=(position+i)%TARGET;

                if((position>=0&&position<=5)||(position>=12&&position<=13)||(position>=20&&position<=25)) {
                    //Load lai coord va image
                    coord.y-=DISTANCE;
                }
                else if((position>=6 && position<=11)||(position>=26 && position<=27)||(position>=42 && position<=47))
                {
                    //Load lai coord va image
                    coord.x+=DISTANCE;
                }
                else if((position>=14 && position<=19)||(position>=34 && position<=39)||(position>=54 && position<=55)) {
                    //Load lai coord va image
                    coord.x-=DISTANCE;
                }
                else {
                    //Load lai coord va image
                    coord.y+=DISTANCE;
                }
                if(stepped>= TARGET){
                    break;
                }
            }
        }

        //Xu ly di tren buc
        if(stepped>=TARGET) {
            if(position==13) {
                //Load lai coord va image
            }
            else if(position==27) {
                //Load lai coord va image
            }
            else if(position==41) {
                //Load lai coord va image
            }
            else if(position==55) {
                //Load lai coord va image
            }
        }
        return true;
    }

    public Tuple initialCoord(){
        return new Tuple(0,0);
    }

    public void resetInitial(){
        //this.position=position;
        this.coord = new Tuple(0,0);
        this.imgHorse.setX(coord.x);
        this.imgHorse.setY(coord.y);
        this.status = 0;
        this.stepped = 0;
    }

    public Horse(ImageView view, int position, int idUser, int idHorse) {
        imgHorse=view;
        this.position=position;
        this.idUser=idUser;
        this.idHorse=idHorse;
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
