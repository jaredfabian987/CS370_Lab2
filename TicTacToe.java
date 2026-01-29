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

    public boolean validRow;
    public boolean validCol;

    public boolean isInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

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
        // first we will check each row for a win
        // checking for three consecutive characters of the same kind
        for (int row = 0; row < BOARDSIZE; row ++){
            // you need to check that the first cell is empty if not the test case:
            // ' ' ' ' ' ' of three consecutive blanks would count as a win
            if (board[row][0] != ' ' && board[row][0] == board [row][1] && board [row][1] == board [row][2]){
                return Status.WIN;
            }
        }

        // checking through all the columns for a win
        for (int col = 0; col < BOARDSIZE; col ++){
            if (board[0][col] != ' ' && board [0][col] == board[1][col] && board[1][col]== board[2][col]){
                return Status.WIN;
            }
        }

        // now need to check the diagonals
        // first check top left to bottom right
        if (board[0][0]!= ' ' && board [0][0] == board [1][1] && board [1][1] == board[2][2]){
            return Status.WIN;
        }

        // next check top right to bottom left
        if (board[0][2]!= ' ' && board [0][2] == board [1][1] && board [1][1] == board[2][0]){
            return Status.WIN;
        }

        // next check if there is an empty space for the game to continue
        for (int r = 0; r < BOARDSIZE; r++){
            for (int c = 0; c < BOARDSIZE; c++){
                if (board[r][c] == ' '){
                    return Status.CONTINUE;
                }
            }
        }

        // after doing all these checks, the only option is that the game result in a draw;
        return Status.DRAW;
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
    public void placeSymbol (int row, int col, char val){
        if (validMove(row,col)){
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
        return true;
    }

    // function to be called in main will be used to play the game
    public void play(){
        // create a new scanner for user input
        Scanner cin = new Scanner (System.in);

        // loop continues as long as the game is not over
        while (!gameOver){
            // print the board
            int col, row;
            printBoard();

            // display whos turn it is
            // if the firstPlayer is true then they are x or player 1
            // otherwise they are 0 or players 2
            printStatus (firstPlayer ? 1 : 2);
            while (true) {
                // ask the user for the row
                System.out.print("Enter row: ");
                // checks if the next value enterd is an ent
                while (!cin.hasNextInt()){
                    System.out.println("Please enter an int value...");
                    cin.next(); // kind of like cin.ignore
                    System.out.println("Enter row: ");
                }
                row = cin.nextInt();

                // ask the user for the col
                System.out.print("Enter col: ");
                // checks if the next value enterd is an ent
                while (!cin.hasNextInt()){
                    System.out.println("Please enter an int value...");
                    cin.next(); // kind of like cin.ignore
                    System.out.println("Enter col: ");
                }
                col = cin.nextInt();

                if (validMove(row,col)){
                    break;
                }
            }

                // ask the user for the column
                System.out.print("Enter column: ");
                col = cin.nextInt();

            char symbol = firstPlayer ? 'X' : 'O';
            // check if the move is valid before placing
            //   if (validMove (row, col)){
            // place the symbol calling the place Symbol method
            placeSymbol(row,col, symbol);

            // check the status of the game
            Status result = gameStatus();

            // if someone won
            if (result == Status.WIN){
                printBoard();
                System.out.println("Player " + symbol + " won!");
                gameOver = true; // to terminate the loop
            }
            else if (result == Status.DRAW){
                printBoard();
                System.out.println("It's a draw, gg's.");
                gameOver = true;
            }
            // the game will continue and we need to change turns
            else {
                // switch the player
                firstPlayer = !firstPlayer;
            }


        }



            // if the move is invalid the loop repeats and player tries again
     //   }

        // close the scanner
        cin.close();
    }



}

