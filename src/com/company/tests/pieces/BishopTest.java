package com.company.tests.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;
import com.company.pieces.Bishop;
import com.company.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void moveUpLeft() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with a piece on the tile
        Pawn tileBlockingPawn = new Pawn(0,6,white,board);
        board.getGameboard()[0][6] = tileBlockingPawn;
        assertFalse(bishop.canMove(0,6));

        // Test with no piece between
        assertTrue(bishop.canMove(1,5));

        // Test with a piece in the way
        Pawn moveBlockingPawn = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = moveBlockingPawn;
        assertFalse(bishop.canMove(1,5));
    }

    @Test
    void moveUpRight() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece between
        assertTrue(bishop.canMove(5,5));

        // Test with a piece on the tile
        Pawn tileBlockingPawn = new Pawn(6,6,white,board);
        board.getGameboard()[6][6] = tileBlockingPawn;
        assertFalse(bishop.canMove(6,6));

        // Test with a piece in the way
        Pawn moveBlockingPawn = new Pawn(4,4,white,board);
        board.getGameboard()[4][4] = moveBlockingPawn;
        assertFalse(bishop.canMove(5,5));
    }

    @Test
    void moveDownLeft() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece between
        assertTrue(bishop.canMove(1,1));

        // Test with a piece on the tile
        Pawn tileBlockingPawn = new Pawn(0,0,white,board);
        board.getGameboard()[0][0] = tileBlockingPawn;
        assertFalse(bishop.canMove(0,0));

        // Test with a piece in the way
        Pawn moveBlockingPawn = new Pawn(2,2,white,board);
        board.getGameboard()[2][2] = moveBlockingPawn;
        assertFalse(bishop.canMove(1,1));
    }

    @Test
    void moveDownRight() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece between
        assertTrue(bishop.canMove(5,1));

        // Test with a piece on the tile
        Pawn tileBlockingPawn = new Pawn(6,0,white,board);
        board.getGameboard()[6][0] = tileBlockingPawn;
        assertFalse(bishop.canMove(6,0));

        // Test with a piece in the way
        Pawn moveBlockingPawn = new Pawn(4,2,white,board);
        board.getGameboard()[4][2] = moveBlockingPawn;
        assertFalse(bishop.canMove(5,1));
    }

    @Test
    void restrictedMovement() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Various movement tests which should not be allowed
        assertFalse(bishop.canMove(4,3));
        assertFalse(bishop.canMove(3,4));
        assertFalse(bishop.canMove(0,1));
    }

    @Test
    void captureUpLeft() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece on tile
        assertFalse(bishop.canCapture(1,5));

        // Test with no piece between
        Pawn capturedPawn = new Pawn(1,5,black,board);
        board.getGameboard()[1][5] = capturedPawn;
        assertTrue(bishop.canCapture(1,5));

        // Test with a piece in the way
        Pawn captureBlockingPawn = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = captureBlockingPawn;
        assertFalse(bishop.canCapture(1,5));
    }

    @Test
    void captureUpRight() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece on tile
        assertFalse(bishop.canCapture(5,5));

        // Test with no piece between
        Pawn capturedPawn = new Pawn(5,5,black,board);
        board.getGameboard()[5][5] = capturedPawn;
        assertTrue(bishop.canCapture(5,5));

        // Test with a piece in the way
        Pawn captureBlockingPawn = new Pawn(4,4,white,board);
        board.getGameboard()[4][4] = captureBlockingPawn;
        assertFalse(bishop.canCapture(5,5));
    }

    @Test
    void captureDownLeft() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece on tile
        assertFalse(bishop.canCapture(1,1));

        // Test with no piece between
        Pawn capturedPawn = new Pawn(1,1,black,board);
        board.getGameboard()[1][1] = capturedPawn;
        assertTrue(bishop.canCapture(1,1));

        // Test with a piece in the way
        Pawn captureBlockingPawn = new Pawn(2,2,white,board);
        board.getGameboard()[2][2] = captureBlockingPawn;
        assertFalse(bishop.canCapture(1,1));
    }

    @Test
    void captureDownRight() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Test with no piece on tile
        assertFalse(bishop.canCapture(5,1));

        // Test with no piece between
        Pawn capturedPawn = new Pawn(5,1,black,board);
        board.getGameboard()[5][1] = capturedPawn;
        assertTrue(bishop.canCapture(5,1));

        // Test with a piece in the way
        Pawn captureBlockingPawn = new Pawn(4,2,white,board);
        board.getGameboard()[4][2] = captureBlockingPawn;
        assertFalse(bishop.canCapture(5,1));
    }

    @Test
    void restrictedCapture() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Various capture tests which should not be allowed
        Pawn capturedPawnOne = new Pawn(4,3,black,board);
        board.getGameboard()[4][3] = capturedPawnOne;
        assertFalse(bishop.canCapture(4,3));

        Pawn capturedPawnTwo = new Pawn(3,4,black,board);
        board.getGameboard()[3][4] = capturedPawnTwo;
        assertFalse(bishop.canCapture(3,4));

        Pawn capturedPawnThree = new Pawn(0,1,black,board);
        board.getGameboard()[0][1] = capturedPawnThree;
        assertFalse(bishop.canCapture(0,1));
    }

    @Test
    void captureOwnPiece() {
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Bishop bishop = new Bishop(3,3,white,board);
        board.getGameboard()[3][3] = bishop;

        // Set up a piece of same player and check for capture
        Pawn playerPawn = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = playerPawn;
        assertFalse(bishop.canCapture(2,4));
    }

}