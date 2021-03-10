package com.company.gamesetup;

import com.company.pieces.Pawn;
import com.company.pieces.Piece;

public class Board {
    // Currently I've set apart the board and game as they're different objects. However, with the fact that I need
    // to instantiate the pieces in the Game object, I'm wondering why I might even need this object in future.
    // Will keep for now and will see if required

    private Piece[][] m_gameboard;

    public Piece[][] getGameboard() {
        return m_gameboard;
    }

    public Board() {
        this.m_gameboard = new Piece[8][8];
    }
}
