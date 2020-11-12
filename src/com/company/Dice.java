package com.company;

public class Dice {
    //private  int image;
    final int MAX=6;
    final int MIN=6;
    private int imgDice1;
    private int imgDice2;
    private int numDice1;
    private int numDice2;

    public int rollDice(){
        numDice1 = (int) (Math.random() * (MAX - MIN)+1);
        numDice2 = (int) (Math.random() * (MAX - MIN)+1);
        //Set image Dice1 va Dice2
        return numDice1+numDice2;
    }

    public Dice(int imgDice1, int imgDice2) {
        this.imgDice1 = imgDice1;
        this.imgDice2 = imgDice2;
    }


    public int getImgDice1() {
        return imgDice1;
    }

    public void setImgDice1(int imgDice1) {
        this.imgDice1 = imgDice1;
    }

    public int getImgDice2() {
        return imgDice2;
    }

    public void setImgDice2(int imgDice2) {
        this.imgDice2 = imgDice2;
    }

    public int getNumDice1() {
        return numDice1;
    }

    public void setNumDice1(int numDice1) {
        this.numDice1 = numDice1;
    }

    public int getNumDice2() {
        return numDice2;
    }

    public void setNumDice2(int numDice2) {
        this.numDice2 = numDice2;
    }
}
