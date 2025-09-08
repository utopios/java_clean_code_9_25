package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TicTacToe Game Class
 */
public class TicTacToeGame {

    /**
     * Game state maintainer variable
     */
    private int[][] g = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    /**
     * This is the main method
     *
     * @param args
     */
    public static void main(String[] args) {
        // create game object
        TicTacToeGame ticTacToeGame = new TicTacToeGame();

        // start the game
        ticTacToeGame.start();
    }

    /**
     * Starts the game
     */
    private void start() {
        do {
            // prints the game state
            printGame();
            System.out.println();

            // ask user for his move
            System.out.print("\nEnter move r,c (0-2,0-2): ");

            String input = "";

            try {
                // read from the console
                //InputStreamReader inputStr = new InputStreamReader(System.in);
                //BufferedReader reader = new BufferedReader(inputStr);
                //input = reader.readLine();

                input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException e) {
                // probably the game should end here, some better alternative can be
                // thought for this part of the catch block.  The error handling
                // can also be done better.
            }

            // play the next move in the game
            next(input, true);

            // print the game state again after move
            printGame();

            if (gameStatus()) {
                // if the game is over show winner as player
                System.out.println("\n\nYou are the winner");
            } else {
                int r; // row
                int c; // row

                // Added by Topchand developer
                int index = 100; // number of computer tries
                input = "";
                // generate random move for computer generate a move
                while (index > 0) {
                    r = (int) (Math.random() * 3);
                    c = (int) (Math.random() * 3);
                    if (g[r][c] == 0) {
                        input = "" + r + "," + c;
                        break;
                    }
                    index--;
                } // while

                // play computer move
                next(input, false);

                /*
                printGame();
                System.out.println();
                */

                if (gameStatus()) {
                    // if the game if over declare computer winner.
                    System.out.println("\n\nBetter Luck next time");
                }
            }

        } while (!gameStatus()); // end while, run the game till it is over
    }

    // check if game is over
    private boolean gameStatus() {
        // row 0
        if (g[0][0] == g[0][1] && g[0][1] == g[0][2] && g[0][2] != 0)
            return true;

        // row 1
        if (g[1][0] == g[1][1] && g[1][1] == g[1][2] && g[1][2] != 0)
            return true;

        // row 1
        if (g[2][0] == g[2][1] && g[2][1] == g[1][2] && g[1][2] != 0)
            return true;

        // column 0
        if (g[0][0] == g[1][0] && g[1][0] == g[2][0] && g[2][0] != 0)
            return true;

        // column 1
        if (g[0][1] == g[1][1] && g[1][1] == g[2][1] && g[2][1] != 0)
            return true;

        // column 2
        if (g[0][2] == g[1][2] && g[1][2] == g[2][2] && g[2][2] != 0)
            return true;

        // diagonal left to right
        if (g[0][0] == g[1][1] && g[1][1] == g[2][2] && g[2][2] != 0)
            return true;

        // diagonal right to left
        if (g[2][0] == g[1][1] && g[1][1] == g[0][2] && g[0][2] != 0)
            return true;

        return false;
    }

    /**
     * print the state of the game to the console
     */
    private void printGame() {
        System.out.println("\n\nGame after move:");
        for (int r = 0; r < 3; r++) {
            System.out.println();
            for (int c = 0; c < 3; c++) {
                if (g[r][c] == 0) {
                    System.out.print(" .");
                } else if (g[r][c] == 1) {
                    System.out.print(" x");
                } else {
                    System.out.print(" o");
                } // for
            } // for
        }
    }

    /**
     * Mark the next move in the game status.
     *
     * @param move chosen place to mark the move
     * @param flag true if player turn, false if computer turn
     */
    private void next(String move, boolean flag) {
        // split move into two parts row and column
        String[] strings = move.split(",");

        // convert the char into number as int row
        int r = Integer.parseInt(strings[0]);
        // convert the second char into number as column
        int c = Integer.parseInt(strings[1]);

        //if( flag == true ) {
        //    g[r][c] = 1;
        //} else {
        //    g[r][c] = 2;
        //}

        // better way of handling condition
        // suggested by rockstar boss
        int v = (flag ? 1 : 2);
        g[r][c] = v;
    }
}
