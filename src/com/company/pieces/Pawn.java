package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public class Pawn extends Piece {

    public Pawn(int x, int y, Player player, Board gameboard) {
        super(x, y, player, gameboard);
        this.setM_type("Pawn");
    }

    @Override
    public boolean canMove(int x, int y) {

        // Check a move was actually made
        if (y == this.getM_y()) { return false;}

        // For a pawn the movement is only straight, so we can instantly rule out any moves that aren't straight
        if (x != this.getM_x()) {return false;}

        // It can also only move a maximum of 2, so any time where the absolute difference (as it's for both black and
        // white) is more than 2 in the y, we can also discount it
        if (Math.abs(y - this.getM_y()) > 2) {return false;}

        // Then we make a check for whether they're trying to move more than one, when the piece has already moved
        if ((Math.abs(y - this.getM_y()) > 1) && this.getM_timesMoved() > 0) {return false;}

        // Set the board that we check the piece against
        Piece[][] checkingBoard = this.getM_gameboard().getGameboard();

        // A pawn is only able to move forward, so for the pawn we need to check differently based on its colour
        // (Could do this with different game board but as pawn is the only one where this is applicable, it made more
        // sense to just have separate checks)
        if (this.getM_player().getColour().compareTo("White") == 0) {
            // Check that it's moving forward
            if (y <= this.getM_y()) {return false;}

            // All that's left now is checking that where it's going is empty
            if (checkingBoard[x][y] == null) {
                return true;
            }

        }

        // Now we do the same for black, with the moving forward check checking the opposite way
        if (this.getM_player().getColour().compareTo("Black") == 0) {
            // Check that it's moving forward
            if (y >= this.getM_y()) {return false;}

            // All that's left now is checking that where it's going is empty
            if (checkingBoard[x][y] == null) {
                return true;
            }

        }

        // Any other outcome whatever that may be, should simply return false
        return false;
    }

    @Override
    public boolean canCapture(int x, int y) {

        // Check a move was actually made
        if (y == this.getM_y()) { return false;}

        // For a pawn, it must capture diagonally, so we can cancel out any input where the x difference is not 1
        if (Math.abs(x - this.getM_x()) != 1) {return false;}

        // Set the board that we check the piece against
        Piece[][] checkingBoard = this.getM_gameboard().getGameboard();

        // Now we check for the white pieces
        if (this.getM_player().getColour().compareTo("White") == 0) {

            // Check it's exactly 1 further up in the y direction
            if (y - this.getM_y() != 1) {return false;}

            // Now we make sure where we're trying to capture is actually an enemy piece
            if (checkingBoard[x][y] != null) {
                if (checkingBoard[x][y].getM_player().getColour().compareTo("Black") == 0) {return true;}
            }
        }

        // And now we do the same for the black pieces
        if (this.getM_player().getColour().compareTo("Black") == 0) {

            // Check it's exactly 1 further down in the y direction
            if (this.getM_y() - y != 1) {return false;}

            // Now we make sure where we're trying to capture is actually an enemy piece
            if (checkingBoard[x][y] != null) {
                if (checkingBoard[x][y].getM_player().getColour().compareTo("White") == 0) {return true;}
            }
        }

        // Any other outcome whatever that may be, should simply return false. Shouldn't be getting to this point though
        return false;
    }
}
