package com.company.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;

public class Knight extends Piece{

    public Knight(int x, int y, Player player, Board gameboard) {
        super(x, y, player, gameboard);
        this.setM_type("Knight");
    }

    @Override
    public boolean canMove(int x, int y) {
        // For all knight moves, we are moving a total of 3 squares, so we do an initial check
        if (Math.abs(x - this.getM_x()) + Math.abs(y - this.getM_y()) != 3) { return false;}

        // However, it can't be 3 squares in the same direction, so we also check for
        if ((Math.abs(x - this.getM_x()) == 3) || (Math.abs(y - this.getM_y()) == 3)) { return false;}

        // We then make sure the tile we are moving to is empty
        if (this.getM_gameboard().getGameboard()[x][y] != null) { return false;}

        // By making it past the checks, the move is valid, and so we return true
        return true;
    }

    @Override
    public boolean canCapture(int x, int y) {
        // This is similar to capture, except we check the space we are going to has a piece, and is not empty
        // Same initial checks as the canMove() method
        if (Math.abs(x - this.getM_x()) + Math.abs(y - this.getM_y()) != 3) { return false;}
        if ((Math.abs(x - this.getM_x()) == 3) || (Math.abs(y - this.getM_y()) == 3)) { return false;}

        // We then make sure the tile we are moving to has a piece, and that it's not our own
        if (this.getM_gameboard().getGameboard()[x][y] == null) { return false;}
        else if (this.getM_gameboard().getGameboard()[x][y].getM_player().getColour().compareTo(this.getM_player().getColour()) == 0) { return false;}

        // By making it past the checks, the move is valid, and so we return true
        return true;
    }
}
