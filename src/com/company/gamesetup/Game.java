package com.company.gamesetup;

import com.company.pieces.*;

import java.util.Scanner;

public class Game {
    private final Board m_gameboard;
    private final Player m_black;
    private final Player m_white;
    private int m_turnCounter;

    public Game() {
        this.m_gameboard = new Board();
        this.m_black = new Player("Black");
        this.m_white = new Player("White");
        this.m_turnCounter = 0;

        normalSetup();
    }

    public void normalSetup() {
        // Black pieces
        this.m_gameboard.getGameboard()[0][6] = new Pawn(0, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[1][6] = new Pawn(1, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[2][6] = new Pawn(2, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[3][6] = new Pawn(3, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[4][6] = new Pawn(4, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[5][6] = new Pawn(5, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[6][6] = new Pawn(6, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[7][6] = new Pawn(7, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[2][7] = new Bishop(2, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[5][7] = new Bishop(5, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[0][7] = new Rook(0, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[7][7] = new Rook(7, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[1][7] = new Knight(1, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[6][7] = new Knight(6, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[4][7] = new King(4, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[3][7] = new Queen(3, 7, m_black, m_gameboard);

        // White pieces
        this.m_gameboard.getGameboard()[0][1] = new Pawn(0, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[1][1] = new Pawn(1, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[2][1] = new Pawn(2, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[3][1] = new Pawn(3, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[4][1] = new Pawn(4, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[5][1] = new Pawn(5, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[6][1] = new Pawn(6, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[7][1] = new Pawn(7, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[5][0] = new Bishop(5, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[2][0] = new Bishop(2, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[0][0] = new Rook(0, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[7][0] = new Rook(7, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[1][0] = new Knight(1, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[6][0] = new Knight(6, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[4][0] = new King(4, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[3][0] = new Queen(3, 0, m_white, m_gameboard);
    }

    public void printBoard() {
        // This method goes through the board and prints the pieces as text. It's intended for initial testing,
        // not as a final product

        // At a higher level, we are iterating through the rows, i.e. the y value of our board (line by line)
        // This needs to start from the top row, and here j = y
        for (int j = this.m_gameboard.getGameboard().length - 1; j >= 0; j--) {
            // Start by printing to a new line
            System.out.println();
            System.out.println("    ----------------------------------------- ");

            System.out.print(" " + (j+1) + " ");

            // Now we iterate through the positions and print out if there's a piece
            for (int i = 0; i < this.m_gameboard.getGameboard()[0].length; i++) {
                // Print a space between pieces
                System.out.print(" | ");

                // Check for nulls
                if (this.m_gameboard.getGameboard()[i][j] == null) {
                    if ((i+j)%2 == 0) {
                        System.out.print("  ");
                    } else { System.out.print("__"); }

                } else {
                    // If there's a piece, we print it out
                    printPiece(this.m_gameboard.getGameboard()[i][j]);
                }

                // If there's a piece, we print it out
            }
            // Final print of the end line
            System.out.print(" | ");
        }
        // Final barrier line

        System.out.println();
        System.out.println("    ----------------------------------------- ");
        System.out.println("      a    b    c    d    e    f    g    h    ");

    }

    public void printPiece(Piece piece) {
        // Prints an output for the piece, used in the printBoard() method

        // First we print the colour
        switch (piece.getM_player().getColour()) {
            case "White":
                System.out.print("W");
                break;
            case "Black":
                System.out.print("B");
                break;
            default:
                break;
        }

        // Now we print the piece symbol
        switch (piece.getM_type()) {
            case "Pawn":
                System.out.print("p");
                break;
            case "Bishop":
                System.out.print("b");
                break;
            case "Rook":
                System.out.print("r");
                break;
            case "Knight":
                System.out.print("n");
                break;
            case "King":
                System.out.print("K");
                break;
            case "Queen":
                System.out.print("q");
                break;
            default:
                break;
        }
    }

    public void makeMove(int startX, int startY, int endX, int endY) {
        // Method to make the required steps to move in a turn
        if (this.m_gameboard.getGameboard()[startX][startY].canMove(endX, endY)) {
            this.m_gameboard.getGameboard()[endX][endY] = this.m_gameboard.getGameboard()[startX][startY];
            this.m_gameboard.getGameboard()[endX][endY].setPosition(endX, endY);
            this.m_gameboard.getGameboard()[endX][endY].incrementM_timesMoved();
            this.m_gameboard.getGameboard()[startX][startY] = null;
        }
    }

    public void makeCapture(int startX, int startY, int endX, int endY) {
        // Method to make the required steps to capture in a turn
        if (this.m_gameboard.getGameboard()[startX][startY].canCapture(endX, endY)) {
            this.m_gameboard.getGameboard()[endX][endY] = this.m_gameboard.getGameboard()[startX][startY];
            this.m_gameboard.getGameboard()[endX][endY].setPosition(endX, endY);
            this.m_gameboard.getGameboard()[endX][endY].incrementM_timesMoved();
            this.m_gameboard.getGameboard()[startX][startY] = null;
        }
    }

    public void playGame() {
        int [] positions = new int[4];
        Player currentPlayer = this.m_white;

        // This method plays in main and continues until the game is finished (checkmate or resignation)
        // todo Implement Checkmate
        // todo Implement Stalemate
        // todo Implement resignation
        // todo Implement draw
        while(true) {
            if (!getMoveInput(positions, currentPlayer)) { continue;}

            if (this.m_gameboard.getGameboard()[positions[2]][positions[3]] != null) {
                if(this.m_gameboard.getGameboard()[positions[0]][positions[1]].canCapture(positions[2], positions[3])) {

                    makeCapture(positions[0], positions[1], positions[2], positions[3]);

                    if (currentPlayer.getColour().compareTo("White") == 0) {
                        currentPlayer = this.m_black;
                    } else {
                        currentPlayer = this.m_white;
                    }

                    if (this.m_gameboard.getGameboard()[positions[2]][positions[3]].getM_type().compareTo("King") == 0) {
                        return;
                    }

                }
            } else {
                if(this.m_gameboard.getGameboard()[positions[0]][positions[1]].canMove(positions[2], positions[3])) {
                    makeMove(positions[0], positions[1], positions[2], positions[3]);

                    if (currentPlayer.getColour().compareTo("White") == 0) {
                        currentPlayer = this.m_black;
                    } else {
                        currentPlayer = this.m_white;
                    }
                }
            }

            printBoard();
            this.m_turnCounter++;

            if (checkTest(currentPlayer)) {


                System.out.println(currentPlayer.getColour() + " is in check");
            }
        }
    }

    private boolean getMoveInput(int[] positions, Player currentPlayer) {
        // Returns false and forces user to retake move if inputs are not sufficient to make a move (e.g. incorrect
        // inputs)
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the position of the piece to move, in the format 'a1'");

        // Take the characters from the input, and use ASCII numbering to get our board positions from this
        String startInput = scanner.next();
        positions[0] = startInput.charAt(0) - 97;
        positions[1] = startInput.charAt(1) - 49;

        // Checks to ensure input is correct
        if ((positions[0] < 0 || positions[1] < 0) || (positions[0] > 7 || positions[1] > 7)) {
            System.out.println("Please ensure input is in the format 'a1', 'b2', etc.");
            return false;
        }

        // Make sure there's a piece in our start position
        if (this.m_gameboard.getGameboard()[positions[0]][positions[1]] == null) {
            System.out.println("Please ensure start position contains a piece of your colour");
            return false;
        }
        // And that it's a piece of the current player (not an OR with above check due to length of the check, neater
        // this way)
        else if (this.m_gameboard.getGameboard()[positions[0]][positions[1]].getM_player().getColour().compareTo(currentPlayer.getColour()) != 0) {
            System.out.println("Please ensure start position contains a piece of your colour");
            return false;
        }

        System.out.println("Please enter where you would like the piece to move/capture, in the format 'a1'");

        String endInput = scanner.next();
        positions[2] = endInput.charAt(0) - 97;
        positions[3] = endInput.charAt(1) - 49;

        // Check to ensure input is correct
        if ((positions[2] < 0 || positions[3] < 0) || (positions[2] > 7 || positions[3] > 7)) {
            System.out.println("Please ensure input is in the format 'a1', 'b2', etc.");
            return false;
        }

        return true;


    }

    private boolean checkTest(Player nextPlayer) {
        // This goes after the player's had their turn, and the player colour has subsequently changed

        int kingPositionX = 10, kingPositionY = 10;

        // Find the position of the player's king
        for (int i = 0; i < this.m_gameboard.getGameboard().length; i++) {
            for (int j = 0; j < this.m_gameboard.getGameboard().length; j++) {
                if (this.m_gameboard.getGameboard()[i][j] != null) {
                    if ((this.m_gameboard.getGameboard()[i][j].getM_player().getColour().compareTo(nextPlayer.getColour()) == 0)
                            && (this.m_gameboard.getGameboard()[i][j].getM_type().compareTo("King")) == 0) {

                        kingPositionX = i;
                        kingPositionY = j;
                    }
                }
            }
        }

        // Fails if we can't find king
        if (kingPositionX == 10) { return false;}

        // Check through each piece and see if it can capture the king. If so, we're in check and we return true
        for (int i = 0; i < this.m_gameboard.getGameboard().length; i++) {
            for (int j = 0; j < this.m_gameboard.getGameboard().length; j++) {
                if (this.m_gameboard.getGameboard()[i][j] != null) {
                    if (this.m_gameboard.getGameboard()[i][j].canCapture(kingPositionX, kingPositionY)) {
                        return true;
                    }
                }

            }
        }

        // If we make it to this point, the king is not under threat of capture, and therefore we're not in check
        return false;
    }

    private boolean checkmateTest(Player nextPlayer) {
        // This method checks for checkmate. It essentially creates a mimic board and simulates each potential move. If
        // none of them result in being out of check, then it is checkmate




        return false;
    }
}
