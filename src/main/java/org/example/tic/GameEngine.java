package org.example.tic;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Player player1;
    private Player player2;
    private Board board;
    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя первого игрока: ");
        player1 = new Player(scanner.next());
        System.out.println("Введите имя вторго игрока: ");
        player2 = new Player(scanner.next());
        chooseSide();
        chooseWhoGoesFirst();

        board = new Board();
        System.out.println("Доска:");
        System.out.println(board);

        while(checkForWin() == '-' && board.haveEmptyCells()){
            makeMove();
        }
        char winner = checkForWin();
        if(winner == '-'){
            System.out.println("<=== Ничья ===>");
        } else if (player1.getSide() == winner) {
            System.out.println("Победил " + player1.getName() +" ("+ player1.getSide()+ ")!");
        }else if (player2.getSide() == winner){
            System.out.println("Победил " + player2.getName() +" ("+ player2.getSide()+ ")!");
        }
    }
    public void makeMove(){
        Player cuurentPlayer;
        Scanner scanner = new Scanner(System.in);
        if(player1.isMoving()){
            cuurentPlayer = player1;
        }else{
            cuurentPlayer = player2;
        }
        System.out.println("Ход " + cuurentPlayer.getName() + " (" + cuurentPlayer.getSide() + "):");
        //System.out.println("Введите куда вы хотите поставить " + cuurentPlayer.getSide() + ", например 00 - самый верх, 11 - центр и т.д.");
        int x;
        int y;
        do {
            String coordinate = scanner.next();
            x= Integer.parseInt(coordinate) / 10;
            y = Integer.parseInt(coordinate) % 10;
        }while (!board.checkCoordinates(x,y));

        board.placePiece(x,y,cuurentPlayer.getSide());
        changePlayer();
        System.out.println(board);
    }
    public void changePlayer(){
        player1.setMoving(!player1.isMoving());
        player2.setMoving(!player2.isMoving());
    }
    public char checkForWin(){
        int height = board.getHeight();
        int width = board.getWidth();
        //Convert array to string
        String str = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                str += board.getCells()[i][j];
            }
        }
        //for xxx/ooo for row
        if(checkWinInRow(str).equals("XXX")){
            return 'X';
        } else if (checkWinInRow(str).equals("OOO")) {
            return 'O';
        }
        // for xxx/ooo but to column
        if(checkWinInColumn(str).equals("XXX")){
            return 'X';
        } else if (checkWinInColumn(str).equals("OOO")) {
            return 'O';
        }
        //for diagonal
        if(checkWinInDiagonal(str).equals("XXX")){
            return 'X';
        } else if (checkWinInDiagonal(str).equals("OOO")) {
            return 'O';
        }
        return '-';
    }
    public String checkWinInDiagonal(String string){
        String str = "" +  string.charAt(0) + string.charAt(4) + string.charAt(8);
        if(str.equals("XXX") | str.equals("OOO")){
            return str;
        }
        String str2 = "" +  string.charAt(2) + string.charAt(4) + string.charAt(6);
        if(str2.equals("XXX") | str2.equals("OOO")){
            return str2;
        }
        return "---";
    }
    public String checkWinInRow(String string){
        int beginIndex = 0;
        while(beginIndex < string.length()){
            String str = string.substring(beginIndex, beginIndex + 3);
            if (str.equals("XXX") | str.equals("OOO")){
                return str;
            }
            beginIndex = beginIndex + 3;
        }
        return "---";
    }
    public String checkWinInColumn(String string){
        int beginIndex = 0;
        while (beginIndex != 3){
            String str = "" + string.charAt(beginIndex) +
                    string.charAt(beginIndex + 3)  +
                    string.charAt(beginIndex + 6);
            if (str.equals("XXX") | str.equals("OOO")){
                return str;
            }
            beginIndex++;
        }
        return "---";
    }
    public boolean tossCoin(){
        Random rnd = new Random();
        return rnd.nextBoolean();
    }
    public void chooseSide(){
        if(tossCoin()){
            this.player1.setSide('X');
            this.player2.setSide('O');
        }else {
            this.player1.setSide('O');
            this.player2.setSide('X');
        }
    }
    public void chooseWhoGoesFirst(){
        if(tossCoin()){
            player1.setMoving(true);
            player2.setMoving(false);
        }else{
            player1.setMoving(false);
            player2.setMoving(true);
        }
    }
}
