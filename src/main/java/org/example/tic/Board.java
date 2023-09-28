package org.example.tic;
public class Board {
    private int width = 3;
    private int height = 3;
    private char[][] cells = new char[height][width];
    public Board(){
        createStartPosition();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                str += cells[i][j];
            }
            str += "\n";
        }
        return str;
    }
    private void createStartPosition(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = '-';
            }
        }
    }
    public boolean checkCoordinates(int x, int y){
        if(x >= width || y >= height){
            System.out.println("Неверный адрес!");
            return false;
        }else if(cells[x][y] != '-'){
            System.out.println("Клетка занята!");
            return false;
        }
        return true;
    }
    public void placePiece(int x, int y, char piece){
        cells[x][y] = piece;
    }
    public boolean haveEmptyCells(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(cells[i][j] == '-'){
                    return true;
                }
            }
        }
        return false;
    }

    // Getters and setters
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public char[][] getCells() {
        return cells;
    }
    public void setCells(char[][] cells) {
        this.cells = cells;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}