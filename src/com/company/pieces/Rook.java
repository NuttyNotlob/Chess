package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public class Rook extends Piece {

    public Rook(int x, int y, Player player, Board gameboard) {
        super(x, y, player, gameboard);
        this.setM_type("Rook");
    }

    @Override
    public boolean canMove(int x, int y) {
        // First, check a move was actually made
        if (x == this.getM_x() && y == this.getM_y()) { return false;}

        // Next, checking the input is in a straight line
        if (x != this.getM_x() && y != this.getM_y()) { return false;}

        // Checking the spaces between the move are empty including the final space, first in the x direction
        if (y == this.getM_y()) {
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

        // Now we do the same, but in the y direction
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

        // If we get to this point, the move is valid and we return true
        return true;
    }

    @Override
    public boolean canCapture(int x, int y) {
        // This runs similar to the canMove() function, except for the last tile we check that it has an enemy piece,
        // rather than being empty

        // First, check a move was actually made
        if (x == this.getM_x() && y == this.getM_y()) { return false;}

        // Next, checking the input is in a straight line
        if (x != this.getM_x() && y != this.getM_y()) { return false;}

        // For a capture, check to make sure the final square has an enemy piece on it
        if (this.getM_gameboard().getGameboard()[x][y] == null) { return false;}
        else if (this.getM_gameboard().getGameboard()[x][y].getM_player().getColour().compareTo(this.getM_player().getColour()) == 0) {
            return false;
        }

        // Checking the spaces between the move are empty including the final space, first in the x direction
        if (y == this.getM_y()) {
            int distance = Math.abs(x - this.getM_x());

            for (int i = 1; i < distance; i++) {
                double distanceMultiplier = ((double)i)/distance;
                int checkXPosition = (int)(this.getM_x() + (x - this.getM_x())*(distanceMultiplier));

                // If we find a piece on the tile we're checking, then we return false - the move isn't valid
                if (this.getM_gameboard().getGameboard()[checkXPosition][y] != null) {
                    return false;
                }
            }
        }

        // Now we do the same, but in the y direction
        else if (x == this.getM_x()) {
            int distance = Math.abs(y - this.getM_y());

            for (int i = 1; i < distance; i++) {
                double distanceMultiplier = ((double)i)/distance;
                int checkYPosition = (int)(this.getM_y() + (y - this.getM_y())*(distanceMultiplier));

                // If we find a piece on the tile we're checking, then we return false - we can't get to the tile we are
                // capturing on
                if (this.getM_gameboard().getGameboard()[x][checkYPosition] != null) {
                    return false;
                }
            }
        }

        // If we get to this point, the capture is valid and we return true
        return true;
    }
}
