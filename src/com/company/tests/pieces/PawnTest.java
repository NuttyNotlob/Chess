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
        assertTrue(pawn.canMove(3,5));
        pawn.incrementM_timesMoved();
        assertFalse(pawn.canMove(3,5));
    }

    @Test
    void restrictedMove() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Pawn pawn = new Pawn(3,3,white,board);
        board.getGameboard()[3][3] = pawn;

        // Test different restricted movement
        assertFalse(pawn.canMove(3,2));
        assertFalse(pawn.canMove(4,4));
        assertFalse(pawn.canMove(3,6));
    }

    @Test
    void captureUpLeft() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Pawn pawn = new Pawn(3,3,white,board);
        Pawn enemyPawn = new Pawn(2,4,black,board);
        board.getGameboard()[3][3] = pawn;
        board.getGameboard()[2][4] = enemyPawn;

        // Ensure capture works
        assertTrue(pawn.canCapture(2,4));
    }

    @Test
    void captureUpRight() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Pawn pawn = new Pawn(3,3,white,board);
        Pawn enemyPawn = new Pawn(4,4,black,board);
        board.getGameboard()[3][3] = pawn;
        board.getGameboard()[4][4] = enemyPawn;

        // Ensure capture works
        assertTrue(pawn.canCapture(4,4));
    }

    @Test
    void restrictedCapture() {
        // Set up the board and pieces on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Pawn pawn = new Pawn(3,3,white,board);
        Pawn enemyPawnOne = new Pawn(4,2,black,board);
        Pawn enemyPawnTwo = new Pawn(2,2,black,board);
        Pawn enemyPawnThree = new Pawn(3,4,black,board);
        Pawn enemyPawnFour = new Pawn(5,5,black,board);
        board.getGameboard()[3][3] = pawn;
        board.getGameboard()[4][2] = enemyPawnOne;
        board.getGameboard()[2][2] = enemyPawnTwo;
        board.getGameboard()[3][4] = enemyPawnThree;
        board.getGameboard()[5][5] = enemyPawnFour;

        // Test restricted captures
        assertFalse(pawn.canCapture(4,2));
        assertFalse(pawn.canCapture(2,2));
        assertFalse(pawn.canCapture(3,4));
        assertFalse(pawn.canCapture(5,5));

        // Make sure capture is working still just to check
        Pawn enemyPawnFive = new Pawn(2,4,black,board);
        board.getGameboard()[2][4] = enemyPawnFive;
        assertTrue(pawn.canCapture(2,4));
    }

    @Test
    void captureOwnPiece() {
        Board board = new Board();
        Player white = new Player("White");
        Pawn pawn = new Pawn(3,3,white,board);
        Pawn ownPawn = new Pawn(4,4,white,board);
        board.getGameboard()[3][3] = pawn;
        board.getGameboard()[2][4] = ownPawn;

        // Ensure capture works
        assertFalse(pawn.canCapture(2,4));

    }

}