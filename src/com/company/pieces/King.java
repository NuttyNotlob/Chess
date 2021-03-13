package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public class King extends Piece{

    public King(int x, int y, Player player, Board gameboard) {
        super(x, y, player, gameboard);
        this.setM_type("King");
    }

    @Override
    public boolean canMove(int x, int y) {
        // First we check the move is actually a move
        if (x == this.getM_x() && y == this.getM_y()) { return false;}

        // Next we make sure the king is only moving by one space
        if (Math.abs(x - this.getM_x()) + Math.abs(y - this.getM_y()) != 1) { return false;}

        // Now make sure there's no piece on the space we are moving to
        if (this.getM_gameboard().getGameboard()[x][y] != null) { return false;}

        // Next we set up a new imaginary board and place a piece on the tile that we are looking to move to.
        // We then check if any of the enemy pieces would be able to capture on this tile, as the king can't move
        // into check

        // We need a new board to place imaginary pieces on it for checking
        Board checkingBoard = new Board();

        // Then make a copy of the original board layout through a loop (as it's a 2D array)
        for (int i = 0; i < this.getM_gameboard().getGameboard().length; i++) {
            for (int j = 0; j < this.getM_gameboard().getGameboard()[i].length; j++) {
                checkingBoard.getGameboard()[i][j] = this.getM_gameboard().getGameboard()[i][j];
            }
        }

        // Need to know the opposition's colour, based on what our pieces colour is
        String oppositionColour;

        switch (this.getM_player().getColour()){
            case "White":
                oppositionColour = "Black";
                break;
            default:
                oppositionColour = "White";
                break;
        }

        // Set a pretend pawn to be present on this new tile
        checkingBoard.getGameboard()[x][y] = new Pawn(x, y, new Player(this.getM_player().getColour()), checkingBoard);

        // Loop through all the pieces on the board for the opposition, and check if they can capture on the new tile
        for (int a = 0; a < this.getM_gameboard().getGameboard().length; a++) {
            for (int b = 0; b < this.getM_gameboard().getGameboard()[a].length; b++) {

                // If no piece, we continue
                if (checkingBoard.getGameboard()[a][b] == null) { continue;}

                // If it's an opposition piece, we check if it can capture on our square we're moving to, and if so
                // we return false
                else if (checkingBoard.getGameboard()[a][b].getM_player().getColour().compareTo(oppositionColour) == 0) {
                    if (checkingBoard.getGameboard()[a][b].canCapture(x, y)) {
                        return false;
                    }
                }
            }
        }

        // By getting to this point we've passed all checks and we return true, as the move is valid
        return true;
    }

    @Override
    public boolean canCapture(int x, int y) {
        // This is the same as canMove(), except checking the square we are moving to has an enemy piece in it

        // First we check the move is actually a move
        if (x == this.getM_x() && y == this.getM_y()) { return false;}

        // Next we make sure the king is only moving by one space
        if ((Math.abs(x - this.getM_x()) > 1) || (Math.abs(y - this.getM_y()) > 1)) { return false;}

        // Now make sure there is an enemy piece on the space we are moving to
        if (this.getM_gameboard().getGameboard()[x][y] == null) { return false;}
        else if (this.getM_gameboard().getGameboard()[x][y].getM_player().getColour().compareTo(this.getM_player().getColour()) == 0) {
            return false;
        }

        // Next we set up a new imaginary board and place a piece on the tile that we are looking to move to.
        // We then check if any of the enemy pieces would be able to capture on this tile, as the king can't move
        // into check

        // We need a new board to place imaginary pieces on it for checking
        Board checkingBoard = new Board();

        // Then make a copy of the original board layout through a loop (as it's a 2D array)
        for (int i = 0; i < this.getM_gameboard().getGameboard().length; i++) {
            for (int j = 0; j < this.getM_gameboard().getGameboard()[i].length; j++) {
                checkingBoard.getGameboard()[i][j] = this.getM_gameboard().getGameboard()[i][j];
                if (checkingBoard.getGameboard()[i][j] != null) {
                    checkingBoard.getGameboard()[i][j].setM_gameboard(checkingBoard);
                }
            }
        }

        // Need to know the opposition's colour, based on what our pieces colour is
        String oppositionColour;

        switch (this.getM_player().getColour()){
            case "White":
                oppositionColour = "Black";
                break;
            default:
                oppositionColour = "White";
                break;
        }

        // Set a pretend pawn to be present on this new tile
        checkingBoard.getGameboard()[x][y] = new Pawn(x, y, new Player(this.getM_player().getColour()), checkingBoard);

        // Loop through all the pieces on the board for the opposition, and check if they can capture on the new tile
        for (int a = 0; a < this.getM_gameboard().getGameboard().length; a++) {
            for (int b = 0; b < this.getM_gameboard().getGameboard()[a].length; b++) {

                // If no piece, we continue
                if (checkingBoard.getGameboard()[a][b] == null) { continue;}

                // If it's an opposition piece, we check if it can capture on our square we're moving to, and if so
                // we return false
                else if (checkingBoard.getGameboard()[a][b].getM_player().getColour().compareTo(oppositionColour) == 0) {
                    if (checkingBoard.getGameboard()[a][b].canCapture(x, y)) {
                        return false;
                    }
                }
            }
        }

        // By getting to this point we've passed all checks and we return true, as the move is valid
        return true;
    }
}
