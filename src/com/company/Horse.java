package com.company;

public class Horse {
    final int TARGET =55;
    private int last;
    private int image;
    private Point coord;
    private int status;
    private int stepped;
    private int position;
    public boolean Move(int step){
        //do something
        if(stepped<=TARGET) {
            //Xu ly di tren ban co
            for (int i = 0; i < step; i++) {
                //do st
            }
        }
        else{
            //Xu ly di tren buc
        }
        stepped+=step;
        return true;
    }


    public Horse(int image, Point coord,int position, int status) {
        this.image = image;
        this.coord = coord;
        this.status = status;
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
}
