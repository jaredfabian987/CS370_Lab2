import java.util.Scanner;
public class TicTacToe {

    // constant for the boardsize
    public static final int BOARDSIZE = 3;

    // declare the 3 possible statuses for the game
    public enum Status {WIN, DRAW, CONTINUE}

    // declare a matrix for the board
    public char [][] board;

    // declare boolean for the first player
    public boolean firstPlayer;

    // declare a boolean for whether the game is over
    public boolean gameOver;

    // the constructor for the board class
    public TicTacToe (){
        board = new char [BOARDSIZE][BOARDSIZE];

        firstPlayer = true;
        gameOver = false;

        for (int row = 0; row < BOARDSIZE; row++){
            for (int col = 0; col < BOARDSIZE; col++){
                board [row][col] = ' ';
            }
        }
    }

    public boolean validMove (int row, int col){
        if ((row <= 1 || row >= BOARDSIZE) &&  (col <=1 || col >= BOARDSIZE) && (board[row][col] == ' ')){
            return true;
        }

        system.out.println("Invalid move, out of bounds or full...pls try again");
        return false;
    }




}

