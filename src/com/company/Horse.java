package com.company;

public class Horse {
    final int TARGET =56;
    private int last;
    private int image;
    private Point coord;//????
    private int status;
    private int stepped;
    private int position;
    public boolean Move(int step){
        //do something
        if(stepped<TARGET) {
            //Xu ly di tren ban co
            for (int i = 0; i < step; i++) {
                position = (position + i)%TARGET; // Load ảnh theo từng vị trí
                if((position >=0 && position <= 5)||(position >=12 && position<=13)||(position >=20 && position <= 25))
                {
                    // load ảnh
                }
                else if((position >= 6 && position <= 11)||(position>=26 && position <=27)||(position>=42 && position<=47))
                {
                    // load ảnh
                }
                else if((position >= 14 && position <= 19)||(position>=34 && position <=39)||(position>=54 && position<=55)) {
                    // load ảnh
                }
                else{
                    // load ảnh
                }
                if(stepped >= TARGET)
                {
                    break;
                }
            }
        }
        // Xu ly vo buc
        if(stepped>=TARGET)
        {
            if(position==12){
                    // Load ảnh theo từng vị trí
            }
            else if(position==25){
                // Load ảnh theo từng vị trí
            }
            else if(position==38){
                // Load ảnh theo từng vị trí
            }
            else if(position==51){
                // Load ảnh theo từng vị trí
            }
        }
        stepped+=step;
        return true;
    }


    public Horse(int image, Point coord,int position) {
        this.image = image;
        this.coord = coord;
        this.status = 0;
        this.stepped = 0;
        this.last=6;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Point getCoord() {
        return coord;
    }

    public void setCoord(Point coord) {

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
}
