package com.company.tests.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;
import com.company.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void moveOne() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Pawn pawn = new Pawn(3,3,white,board);
        board.getGameboard()[3][3] = pawn;

        // Test movement
        assertTrue(pawn.canMove(3,4));

        // Test with a piece on the tile
        Pawn moveBlockingPawn = new Pawn(3,4,white,board);
        board.getGameboard()[3][4] = moveBlockingPawn;
        assertFalse(pawn.canMove(3,4));
    }

    @Test
    void moveTwo() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Pawn pawn = new Pawn(3,3,white,board);
        board.getGameboard()[3][3] = pawn;

        // Test movement
        assertTrue(pawn.canMove(3,5));

        // Test with a piece on the tile
        Pawn moveBlockingPawn = new Pawn(3,5,white,board);
        board.getGameboard()[3][5] = moveBlockingPawn;
        assertFalse(pawn.canMove(3,5));

        // Test with a piece in the way
        moveBlockingPawn.setPosition(3, 4);
        board.getGameboard()[3][5] = null;
        board.getGameboard()[3][4] = moveBlockingPawn;
        assertFalse(pawn.canMove(3,5));

        // Test on second move
        board.getGameboard()[3][4] = null;
        board.getGameboard()[3][5] = null;
        pawn.incrementM_timesMoved();
        assertFalse(pawn.canMove(3,5));

    }

    @Test
    void restrictedMove() {

    }

    @Test
    void captureUpLeft() {

    }

    @Test
    void captureUpRight() {

    }

    @Test
    void restrictedCapture() {

    }

    @Test
    void captureOwnPiece() {

    }

}