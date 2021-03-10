package com.company.gamesetup;

import com.company.pieces.*;

import java.util.Scanner;

public class Game {
    private Board m_gameboard;
    private Player m_black;
    private Player m_white;
    private int m_turnCounter;

    public Game() {
        this.m_gameboard = new Board();
        this.m_black = new Player("Black");
        this.m_white = new Player("White");
        this.m_turnCounter = 0;

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

    public void normalSetup() {
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
                System.out.printf("W");
                break;
            case "Black":
                System.out.printf("B");
                break;
            default:
                break;
        }

        // Now we print the piece symbol

        switch (piece.getM_type()) {
            case "Pawn":
                System.out.printf("p");
                break;
            case "Bishop":
                System.out.printf("b");
                break;
            case "Rook":
                System.out.printf("r");
                break;
            case "Knight":
                System.out.printf("n");
                break;
            case "King":
                System.out.printf("K");
                break;
            case "Queen":
                System.out.printf("q");
                break;
            default:
                break;
        }
    }

    public boolean makeMove(int startX, int startY, int endX, int endY) {
        // Check to make sure the move is within the board
        if ((startX > 7 || startY > 7 || endX > 7 || endY > 7) || (startX < 0 || startY < 0 || endX < 0 || endY < 0)) {
            return false;
        }

        if (this.m_gameboard.getGameboard()[startX][startY].canMove(endX, endY)) {
            this.m_gameboard.getGameboard()[endX][endY] = this.m_gameboard.getGameboard()[startX][startY];
            this.m_gameboard.getGameboard()[endX][endY].setPosition(endX, endY);
            this.m_gameboard.getGameboard()[endX][endY].incrementM_timesMoved();
            this.m_gameboard.getGameboard()[startX][startY] = null;
            return true;
        } else {
            return false;
        }

    }

    public boolean makeCapture(int startX, int startY, int endX, int endY) {
        // Check to make sure the capture is within the board
        if ((startX > 7 || startY > 7 || endX > 7 || endY > 7) || (startX < 0 || startY < 0 || endX < 0 || endY < 0)) {
            return false;
        }

        if (this.m_gameboard.getGameboard()[startX][startY].canCapture(endX, endY)) {
            this.m_gameboard.getGameboard()[endX][endY] = this.m_gameboard.getGameboard()[startX][startY];
            this.m_gameboard.getGameboard()[endX][endY].setPosition(endX, endY);
            this.m_gameboard.getGameboard()[endX][endY].incrementM_timesMoved();
            this.m_gameboard.getGameboard()[startX][startY] = null;
            return true;
        } else {
            return false;
        }
    }

    public void playGame() {
        int startX = 0, startY = 0;
        int endX = 0, endY = 0;
        Scanner scanner = new Scanner(System.in);

        Player currentPlayer = this.m_white;

        // This method plays in main and continues until
        while(true) {
            System.out.println("Please enter the position of the piece to move, in the format ");

            boolean isAnInt = scanner.hasNextInt();
            if(isAnInt) {
                startX = scanner.nextInt();
            }

            isAnInt = scanner.hasNextInt();
            if(isAnInt) {
                startY = scanner.nextInt();
            }

            System.out.println("Please enter where you would like the piece to move/capture");

            isAnInt = scanner.hasNextInt();
            if(isAnInt) {
                endX = scanner.nextInt();
            }

            isAnInt = scanner.hasNextInt();
            if(isAnInt) {
                endY = scanner.nextInt();
            }

            // Make sure there's a piece in our start position
            if (this.m_gameboard.getGameboard()[startX][startY] == null) {
                continue;
            }
            // And that it's a piece of the current player
            else if (this.m_gameboard.getGameboard()[startX][startY].getM_player().getColour().compareTo(currentPlayer.getColour()) != 0) {
                continue;
            }

            if (this.m_gameboard.getGameboard()[endX][endY] != null) {
                if(this.m_gameboard.getGameboard()[startX][startY].canCapture(endX, endY)) {

                    makeCapture(startX, startY, endX, endY);

                    if (currentPlayer.getColour().compareTo("White") == 0) {
                        currentPlayer = this.m_black;
                    } else {
                        currentPlayer = this.m_white;
                    }

                    if (this.m_gameboard.getGameboard()[endX][endY].getM_type().compareTo("King") == 0) {
                        return;
                    }

                }
            } else {
                if(this.m_gameboard.getGameboard()[startX][startY].canMove(endX, endY)) {
                    makeMove(startX, startY, endX, endY);

                    if (currentPlayer.getColour().compareTo("White") == 0) {
                        currentPlayer = this.m_black;
                    } else {
                        currentPlayer = this.m_white;
                    }
                }
            }

            printBoard();
        }
    }
}
