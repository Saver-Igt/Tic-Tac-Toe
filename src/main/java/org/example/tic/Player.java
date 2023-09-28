package org.example.tic;
public class Player {
    private String name;
    private char side;
    private boolean isMoving;
    public Player(String name){
        this.name = name;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public char getSide() {
        return side;
    }
    public void setSide(char side) {
        this.side = side;
    }
    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean beginner) {
        isMoving = beginner;
    }
}
