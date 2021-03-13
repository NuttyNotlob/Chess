package com.company.gamesetup;

import com.company.pieces.*;

public class Board {

    private final Piece[][] m_gameboard;

    public Piece[][] getGameboard() {
        return m_gameboard;
    }

    public Board() {
        this.m_gameboard = new Piece[8][8];
    }

    public void printBoard() {
        // This method goes through the board and prints the pieces as text. It's intended for initial testing,
        // not as a final product

        // At a higher level, we are iterating through the rows, i.e. the y value of our board (line by line)
        // This needs to start from the top row, and here j = y
        for (int j = this.m_gameboard.length - 1; j >= 0; j--) {
            // Start by printing to a new line
            System.out.println();
            System.out.println("    ----------------------------------------- ");

            System.out.print(" " + (j+1) + " ");

            // Now we iterate through the positions and print out if there's a piece
            for (int i = 0; i < this.m_gameboard[0].length; i++) {
                // Print a space between pieces
                System.out.print(" | ");

                // Check for nulls
                if (this.m_gameboard[i][j] == null) {
                    if ((i+j)%2 == 0) {
                        System.out.print("  ");
                    } else { System.out.print("__"); }

                } else {
                    // If there's a piece, we print it out
                    printPiece(this.m_gameboard[i][j]);
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

    public boolean checkTest(Player testPlayer) {
        // This goes after the player's had their turn, and the player colour has subsequently changed

        int kingPositionX = 10, kingPositionY = 10;

        // Find the position of the player's king
        for (int i = 0; i < this.m_gameboard.length; i++) {
            for (int j = 0; j < this.m_gameboard.length; j++) {
                if (this.m_gameboard[i][j] != null) {
                    if ((this.m_gameboard[i][j].getM_player().getColour().compareTo(testPlayer.getColour()) == 0)
                            && (this.m_gameboard[i][j].getM_type().compareTo("King")) == 0) {

                        kingPositionX = i;
                        kingPositionY = j;
                    }
                }
            }
        }

        // Fails if we can't find king
        if (kingPositionX == 10) { return false;}

        // Check through each piece and see if it can capture the king. If so, we're in check and we return true
        for (int i = 0; i < this.m_gameboard.length; i++) {
            for (int j = 0; j < this.m_gameboard.length; j++) {
                if (this.m_gameboard[i][j] != null) {
                    if (this.m_gameboard[i][j].canCapture(kingPositionX, kingPositionY)) {
                        return true;
                    }
                }

            }
        }

        // If we make it to this point, the king is not under threat of capture, and therefore we're not in check
        return false;
    }

    public boolean checkmateTest(Player checkedPlayer) {
        // This method checks for checkmate. It essentially creates a mimic board and simulates each potential move. If
        // none of them result in being out of check, then it is checkmate

        // We go through each piece on the board. If it belongs to the player in check, we try every potential move and
        // change our board to this move. If the player is still in check we move on. If the move would put them out of
        // check, we return false

        // For sake of loops and reading, note that the loop variables are for the following
        //      i,j - Looping through our current board tiles to find pieces of our player in check
        //      a,b - Checking the tiles the piece can move/capture to
        //      c,d - Looping variables used in recreating our board into an imaginary board

        for (int i = 0; i < this.m_gameboard.length; i++) {
            for (int j = 0; j < this.m_gameboard.length; j++) {

                // Only checking moves for player's pieces
                if (this.m_gameboard[i][j] != null) {
                    if (this.m_gameboard[i][j].getM_player().getColour().compareTo(checkedPlayer.getColour()) == 0) {

                        // We go through each of the board tiles. If we can make a move/capture there, we recreate the
                        // board with this move, and see if the player is still in check
                        for (int a = 0; a < this.m_gameboard.length; a++) {
                            for (int b = 0; b < this.m_gameboard.length; b++) {
                                if ((this.m_gameboard[a][b] != null && this.m_gameboard[i][j].canCapture(a,b)) ||
                                        (this.m_gameboard[a][b] == null && this.m_gameboard[i][j].canMove(a,b))) {

                                    // We need a new board to place imaginary pieces on it for checking
                                    Board checkingBoard = new Board();

                                    // Then make a copy of the original board layout through a loop (as it's a 2D array)
                                    for (int c = 0; c < this.m_gameboard.length; c++) {
                                        for (int d = 0; d < this.m_gameboard[i].length; d++) {
                                            if (this.m_gameboard[c][d] != null) {
                                                switch (this.m_gameboard[c][d].getM_type()) {
                                                    case "King":
                                                        checkingBoard.getGameboard()[c][d] = new King(c,d,this.m_gameboard[c][d].getM_player(),checkingBoard);
                                                        break;
                                                    case "Queen":
                                                        checkingBoard.getGameboard()[c][d] = new Queen(c,d,this.m_gameboard[c][d].getM_player(),checkingBoard);
                                                        break;
                                                    case "Knight":
                                                        checkingBoard.getGameboard()[c][d] = new Knight(c,d,this.m_gameboard[c][d].getM_player(),checkingBoard);
                                                        break;
                                                    case "Bishop":
                                                        checkingBoard.getGameboard()[c][d] = new Bishop(c,d,this.m_gameboard[c][d].getM_player(),checkingBoard);
                                                        break;
                                                    case "Rook":
                                                        checkingBoard.getGameboard()[c][d] = new Rook(c,d,this.m_gameboard[c][d].getM_player(),checkingBoard);
                                                        break;
                                                    case "Pawn":
                                                        checkingBoard.getGameboard()[c][d] = new Pawn(c,d,this.m_gameboard[c][d].getM_player(),checkingBoard);
                                                        break;
                                                }
                                                checkingBoard.getGameboard()[c][d].setM_timesMoved(this.m_gameboard[c][d].getM_timesMoved());
                                            }
                                        }
                                    }

                                    // Make the potential move
                                    checkingBoard.m_gameboard[a][b] = checkingBoard.m_gameboard[i][j];
                                    checkingBoard.m_gameboard[a][b].setPosition(a,b);
                                    checkingBoard.m_gameboard[i][j] = null;

                                    // And check if we're still in check after this
                                    if (!checkingBoard.checkTest(checkedPlayer)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // If we get to this point there is no valid move for the player in check
        return true;
    }
}
