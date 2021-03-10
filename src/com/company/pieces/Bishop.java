package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public class Bishop extends Piece {

    public Bishop(int x, int y, Player player, Board gameboard) {
        super(x, y, player, gameboard);
        this.setM_type("Bishop");
    }

    @Override
    public boolean canMove(int x, int y) {

        // Check that the move is actually movement
        if (this.getM_x() == x) { return false;}

        // Bishop can move diagonally, so first is to check the position we are moving to is a diagonal of where we are
        if (Math.abs(this.getM_x() - x) != Math.abs(this.getM_y()- y)) { return false;}

        // After that, we just need to check the spaces between where we're moving are vacant, including the final space
        // For this, we first calculate the distance between the spaces
        int distance = Math.abs(this.getM_x() - x);

        // Now we iterate through the spaces
        for (int i = 1; i <= distance; i++) {
            // Need to cast here, as produced a bug previously without due to checkPositions always being the original
            // position as I wasn't adding anything to them, as addition was always 0
            double distanceMultiplier = ((double)i)/distance;
            int checkXPosition = (int)(this.getM_x() + (x - this.getM_x())*(distanceMultiplier));
            int checkYPosition = (int)(this.getM_y() + (y - this.getM_y())*(distanceMultiplier));

            // If we find a piece on the tile we're checking, then we return false - the move isn't valid
            if (this.getM_gameboard().getGameboard()[checkXPosition][checkYPosition] != null) {
                System.out.println(checkXPosition + " " + checkYPosition);
                return false;
            }
        }

        // By getting to this point, the move is valid, so we return true
        return true;
    }

    @Override
    public boolean canCapture(int x, int y) {
        // This runs similar to the canMove() function, except for the last tile we check that it has an enemy piece,
        // rather than being empty

        // Check that the move is actually movement
        if (this.getM_x() == x) { return false;}

        // Bishop can move diagonally, so first is to check the position we are moving to is a diagonal of where we are
        if (Math.abs(this.getM_x() - x) != Math.abs(this.getM_y()- y)) { return false;}

        // For a capture, check to make sure the final square has an enemy piece on it
        if (this.getM_gameboard().getGameboard()[x][y] == null) { return false;}
        else if (this.getM_gameboard().getGameboard()[x][y].getM_player().getColour().compareTo(this.getM_player().getColour()) == 0) {
            return false;
        }

        // After that, we just need to check the spaces between where we're moving are vacant, including the final space
        // For this, we first calculate the distance between the spaces
        int distance = Math.abs(this.getM_x() - x);

        // Now we iterate through the spaces, not including the final tile of movement
        for (int i = 1; i < distance; i++) {
            double distanceMultiplier = ((double)i)/distance;
            int checkXPosition = (int)(this.getM_x() + (x - this.getM_x())*(distanceMultiplier));
            int checkYPosition = (int)(this.getM_y() + (y - this.getM_y())*(distanceMultiplier));

            // We check there's a piece on the tile we're capturing, and that it's not our team's piece - otherwise
            // the capture isn't valid
            if (this.getM_gameboard().getGameboard()[checkXPosition][checkYPosition] != null) {
                return false;
            }
        }

        // If it has passed all the above checks, then we return true, and the bishop can capture
        return true;
    }
}
