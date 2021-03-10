package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public class Queen extends Piece{
    // The Queen has the combined movement of the Bishop and Rook, and so we will be making use of the code in those to
    // simplify this and prevent repeated code (if you can't tell, I made this piece last)

    public Queen(int x, int y, Player player, Board gameboard) {
        super(x, y, player, gameboard);
        this.setM_type("Queen");
    }

    @Override
    public boolean canMove(int x, int y) {
        // First check a move is actually made
        if (x == this.getM_x() && y == this.getM_y()) { return false;}

        // Next we check that it's going diagonally or horizontally/vertically. This check is just the check for a
        // bishop, and a rook combined. If it meets neither of those checks, it is not a valid move
        if ((Math.abs(this.getM_x() - x) != Math.abs(this.getM_y()- y)) && (x != this.getM_x() && y != this.getM_y())) { return false;}

        // Depending on which it is going, we run different checks, which is be a copy of the bishop/rook checks
        // First the bishop checks
        if (Math.abs(this.getM_x() - x) == Math.abs(this.getM_y()- y)) {
            // For this, we first calculate the distance between the spaces
            int distance = Math.abs(this.getM_x() - x);

            // Now we iterate through the spaces
            for (int i = 1; i <= distance; i++) {
                double distanceMultiplier = ((double)i)/distance;
                int checkXPosition = (int)(this.getM_x() + (x - this.getM_x())*(distanceMultiplier));
                int checkYPosition = (int)(this.getM_y() + (y - this.getM_y())*(distanceMultiplier));

                // If we find a piece on the tile we're checking, then we return false - the move isn't valid
                if (this.getM_gameboard().getGameboard()[checkXPosition][checkYPosition] != null) {
                    return false;
                }
            }
        }

        // Now the rook horizontal move check
        else if (y == this.getM_y()) {
            int distance = Math.abs(x - this.getM_x());

            for (int i = 1; i <= distance; i++) {
                double distanceMultiplier = ((double)i)/distance;
                int checkXPosition = (int)(this.getM_x() + (x - this.getM_x())*(distanceMultiplier));

                // If we find a piece on the tile we're checking, then we return false - the move isn't valid
                if (this.getM_gameboard().getGameboard()[checkXPosition][y] != null) {
                    return false;
                }
            }
        }

        // And finally the vertical rook movement
        else if (x == this.getM_x()) {
            int distance = Math.abs(y - this.getM_y());

            for (int i = 1; i <= distance; i++) {
                double distanceMultiplier = ((double)i)/distance;
                int checkYPosition = (int)(this.getM_y() + (y - this.getM_y())*(distanceMultiplier));

                // If we find a piece on the tile we're checking, then we return false - the move isn't valid
                if (this.getM_gameboard().getGameboard()[x][checkYPosition] != null) {
                    return false;
                }
            }
        }

        // If we reach this point, the move is valid
        return true;
    }

    @Override
    public boolean canCapture(int x, int y) {
        // Similar to the other pieces, this method is the same as the canMove() method, just checking the last tile
        // as well for a piece, rather than being empty

        // First check a move is actually made
        if (x == this.getM_x() && y == this.getM_y()) { return false;}

        // Next we check that it's going diagonally or horizontally/vertically. This check is just the check for a
        // bishop, and a rook combined. If it meets neither of those checks, it is not a valid move
        if ((Math.abs(this.getM_x() - x) != Math.abs(this.getM_y()- y)) && (x != this.getM_x() && y != this.getM_y())) { return false;}

        // Now we make sure there's an enemy piece on the x, y position
        if (this.getM_gameboard().getGameboard()[x][y] == null) { return false;}
        else if (this.getM_gameboard().getGameboard()[x][y].getM_player().getColour().compareTo(this.getM_player().getColour()) == 0) {
            return false;
        }

        // Depending on which it is going, we run different checks, which is be a copy of the bishop/rook checks
        // We can roll this into one using the canMove() method
        int distance = Math.max(Math.abs(y - this.getM_y()), Math.abs(x - this.getM_x()));

        for (int i = 1; i < distance; i++) {
            double distanceMultiplier = ((double)i)/distance;
            int checkXPosition = (int)(this.getM_x() + (x - this.getM_x())*(distanceMultiplier));
            int checkYPosition = (int)(this.getM_y() + (y - this.getM_y())*(distanceMultiplier));

            // We check there's no piece on the tiles between where we are and where we're capturing
            if (this.getM_gameboard().getGameboard()[checkXPosition][checkYPosition] != null) {
                return false;
            }
        }

        // If we reach this point, the capture is valid
        return true;
    }
}
