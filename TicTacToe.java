import java.util.Scanner;
public class TicTacToe {

    // constant for the BOARDSIZE
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
        // intialize new board
        board = new char [BOARDSIZE][BOARDSIZE];
        // set first player to true and gameOVer to false
        firstPlayer = true;
        gameOver = false;

        // iteratively fill a board with empty slots
        for (int row = 0; row < BOARDSIZE; row++){
            for (int col = 0; col < BOARDSIZE; col++){
                board [row][col] = ' ';
            }
        }
    }

    // this function will return a value of the type Status
    // which will either be a Win, draw, continue
    // will of course have to check horizontal wins
    // but will also have to check for diagonals
    // a win in this case is classified by three consecutive characters
    public Status gameStatus () {

    }

    // this function will print the board and it's contents
    public void printBoard(){
        // outer loop for the number of rows
        for (int row = 0; row < BOARDSIZE; row ++){
            // I am making this a 25 * 25 board
            // to print the top of the row
            System.out.println("-------------------------");
            // prints the left border of the board
            System.out.print("|");
            // inner loop for the number of columns
            for (int col = 0; col < BOARDSIZE; col++){
                // print the value in the board and some tabs
                System.out.print("\t" +  board[row][col] + "\t|");
            }
            // print a newline after each row
            if (row < BOARDSIZE - 1){
                System.out.println();
            }
        }
        // print the bottom border of the board
        System.out.println("\n-------------------------");
    }

    // void function will place the symbol into the respective position
    // but will first call the validMove function to make sure the position is valid
    // then will place accordingly with respect to the bounds of the matrix
    public void placeSymbol (int col, int row, char val){
        if (validMove(col,row)){
            board [row-1][col-1] = val;
            /* Note, index starts at 0 so if the user enters row 1 col 1 we really want row 0 col 0
            having this makes it easier for the user to enter the row they want
             */
        }
    }

    // void function printStatus takes an interger player
    // this will print who's turn it is, assuming players 1 is always the one with the X
    // and vice versa
    public void printStatus (int player){
        // notes -> condition ? value_if_true : value_if_false
        System.out.println(player == 1 ? "Player X's Turn" : "PLayer O's turn");
    }


    // boolean function return true if the row and column entered are within the bounds
    // of the matrix and if the space is empty
    // otherwise will print an error msg and return false
    public boolean validMove (int row, int col){
        if (row < 1 || row > BOARDSIZE || col < 1 || col > BOARDSIZE){
            System.out.println("Out of bounds, pls try again");
            return false;
        }
        if (board[row-1][col-1] != ' '){
            System.out.println ("That spot is already taken.");
            return false;
        }
        return false;
    }




}

