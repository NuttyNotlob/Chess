package com.company.tests.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;
import com.company.pieces.Pawn;
import com.company.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void horizontalMove() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Test with a piece on the tile
        Pawn tileBlockingPawnOne = new Pawn(0,3,white,board);
        board.getGameboard()[0][3] = tileBlockingPawnOne;
        assertFalse(rook.canMove(0,3));

        Pawn tileBlockingPawnTwo = new Pawn(6,3,white,board);
        board.getGameboard()[6][3] = tileBlockingPawnTwo;
        assertFalse(rook.canMove(6,3));

        // Test with no piece between
        assertTrue(rook.canMove(1,3));

        assertTrue(rook.canMove(5,3));

        // Test with a piece in the way
        Pawn moveBlockingPawnOne = new Pawn(2,3,white,board);
        board.getGameboard()[2][3] = moveBlockingPawnOne;
        assertFalse(rook.canMove(1,3));

        Pawn moveBlockingPawnTwo = new Pawn(4,3,white,board);
        board.getGameboard()[4][3] = moveBlockingPawnTwo;
        assertFalse(rook.canMove(5,3));
    }

    @Test
    void verticalMove() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Test with a piece on the tile
        Pawn tileBlockingPawnOne = new Pawn(3,0,white,board);
        board.getGameboard()[3][0] = tileBlockingPawnOne;
        assertFalse(rook.canMove(3,0));

        Pawn tileBlockingPawnTwo = new Pawn(3,6,white,board);
        board.getGameboard()[3][6] = tileBlockingPawnTwo;
        assertFalse(rook.canMove(3,6));

        // Test with no piece between
        assertTrue(rook.canMove(3,1));

        assertTrue(rook.canMove(3,1));

        // Test with a piece in the way
        Pawn moveBlockingPawnOne = new Pawn(3,2,white,board);
        board.getGameboard()[3][2] = moveBlockingPawnOne;
        assertFalse(rook.canMove(3,1));

        Pawn moveBlockingPawnTwo = new Pawn(3,4,white,board);
        board.getGameboard()[3][4] = moveBlockingPawnTwo;
        assertFalse(rook.canMove(3,5));
    }

    @Test
    void restrictedMovement() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Test for moves which should not be allowed
        assertFalse(rook.canMove(5,5));
        assertFalse(rook.canMove(1,1));
        assertFalse(rook.canMove(4,7));
    }

    @Test
    void horizontalCapture() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Test with no piece on the tile
        assertFalse(rook.canCapture(1,3));

        assertFalse(rook.canCapture(5,3));

        // Test with no piece in way of capture
        Pawn capturePawnOne = new Pawn(1,3,black,board);
        board.getGameboard()[1][3] = capturePawnOne;
        assertTrue(rook.canCapture(1,3));

        Pawn capturePawnTwo = new Pawn(5,3,black,board);
        board.getGameboard()[5][3] = capturePawnTwo;
        assertTrue(rook.canCapture(5,3));

        // Test capture with a piece in the way
        Pawn captureBlockingPawnOne = new Pawn(2,3,white,board);
        board.getGameboard()[2][3] = captureBlockingPawnOne;
        assertFalse(rook.canCapture(1,3));

        Pawn captureBlockingPawnTwo = new Pawn(4,3,white,board);
        board.getGameboard()[4][3] = captureBlockingPawnTwo;
        assertFalse(rook.canCapture(5,3));
    }

    @Test
    void verticalCapture() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Test with no piece on the tile
        assertFalse(rook.canCapture(3,1));

        assertFalse(rook.canCapture(3,5));

        // Test with no piece in way of capture
        Pawn capturePawnOne = new Pawn(3,1,black,board);
        board.getGameboard()[3][1] = capturePawnOne;
        assertTrue(rook.canCapture(3,1));

        Pawn capturePawnTwo = new Pawn(3,5,black,board);
        board.getGameboard()[3][5] = capturePawnTwo;
        assertTrue(rook.canCapture(3,5));

        // Test capture with a piece in the way
        Pawn captureBlockingPawnOne = new Pawn(3,2,white,board);
        board.getGameboard()[3][2] = captureBlockingPawnOne;
        assertFalse(rook.canCapture(3,1));

        Pawn captureBlockingPawnTwo = new Pawn(3,4,white,board);
        board.getGameboard()[3][4] = captureBlockingPawnTwo;
        assertFalse(rook.canCapture(3,5));
    }

    @Test
    void restrictedCapture() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Test for moves which should not be allowed
        Pawn capturedPawnOne = new Pawn(5,5,black,board);
        board.getGameboard()[5][5] = capturedPawnOne;
        assertFalse(rook.canCapture(5,5));

        Pawn capturedPawnTwo = new Pawn(1,1,black,board);
        board.getGameboard()[1][1] = capturedPawnTwo;
        assertFalse(rook.canCapture(1,1));

        Pawn capturedPawnThree = new Pawn(4,7,black,board);
        board.getGameboard()[4][7] = capturedPawnThree;
        assertFalse(rook.canCapture(4,7));
    }

    @Test
    void captureOwnPiece() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Rook rook = new Rook(3,3,white,board);
        board.getGameboard()[3][3] = rook;

        // Set up a piece of same player and check for capture
        Pawn playerPawn = new Pawn(3,4,white,board);
        board.getGameboard()[3][4] = playerPawn;
        assertFalse(rook.canCapture(3,4));
    }

}