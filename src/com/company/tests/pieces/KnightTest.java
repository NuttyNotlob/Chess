package com.company.tests.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;
import com.company.pieces.Knight;
import com.company.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void canMove() {
        // This tests all available movements to the knight
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;

        // Check it can make all the necessary moves
        assertTrue(knight.canMove(2,5));
        assertTrue(knight.canMove(4,5));
        assertTrue(knight.canMove(5,4));
        assertTrue(knight.canMove(5,2));
        assertTrue(knight.canMove(4,1));
        assertTrue(knight.canMove(2,1));
        assertTrue(knight.canMove(1,2));
        assertTrue(knight.canMove(1,4));
    }

    @Test
    void canMoveOver() {
        // This is the same as canMove(), but with pieces in the way
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;

        // Surround the knight with pawns
        Pawn pawnOne = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = pawnOne;
        Pawn pawnTwo = new Pawn(3,4,white,board);
        board.getGameboard()[3][4] = pawnTwo;
        Pawn pawnThree = new Pawn(4,4,white,board);
        board.getGameboard()[4][4] = pawnThree;
        Pawn pawnFour = new Pawn(4,3,white,board);
        board.getGameboard()[4][3] = pawnFour;
        Pawn pawnFive = new Pawn(4,2,white,board);
        board.getGameboard()[4][2] = pawnFive;
        Pawn pawnSix = new Pawn(3,2,white,board);
        board.getGameboard()[3][2] = pawnSix;
        Pawn pawnSeven = new Pawn(2,2,white,board);
        board.getGameboard()[2][2] = pawnSeven;
        Pawn pawnEight = new Pawn(2,3,white,board);
        board.getGameboard()[2][3] = pawnEight;

        // Check it can make all the necessary moves
        assertTrue(knight.canMove(2,5));
        assertTrue(knight.canMove(4,5));
        assertTrue(knight.canMove(5,4));
        assertTrue(knight.canMove(5,2));
        assertTrue(knight.canMove(4,1));
        assertTrue(knight.canMove(2,1));
        assertTrue(knight.canMove(1,2));
        assertTrue(knight.canMove(1,4));
    }

    @Test
    void restrictedMove() {
        // This tests movement the knight should not be able to make
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;

        // Test movement to squares it shouldn't move to
        assertFalse(knight.canMove(3,6));
        assertFalse(knight.canMove(6,3));
        assertFalse(knight.canMove(0,3));
        assertFalse(knight.canMove(3,0));
        assertFalse(knight.canMove(4,4));
        assertFalse(knight.canMove(0,1));

        // Test movement to a square that's filled
        assertTrue(knight.canMove(2,5));
        Pawn blockingPawn = new Pawn(2,5,white,board);
        board.getGameboard()[2][5] = blockingPawn;
        assertFalse(knight.canMove(2,5));
    }

    @Test
    void canCapture() {
        // This tests all available captures to the knight
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;

        // Set up pawns to be captured
        Pawn pawnCaptureOne = new Pawn(2,5,black,board);
        board.getGameboard()[2][5] = pawnCaptureOne;
        Pawn pawnCaptureTwo = new Pawn(4,5,black,board);
        board.getGameboard()[4][5] = pawnCaptureTwo;
        Pawn pawnCaptureThree = new Pawn(5,4,black,board);
        board.getGameboard()[5][4] = pawnCaptureThree;
        Pawn pawnCaptureFour = new Pawn(5,2,black,board);
        board.getGameboard()[5][2] = pawnCaptureFour;
        Pawn pawnCaptureFive = new Pawn(4,1,black,board);
        board.getGameboard()[4][1] = pawnCaptureFive;
        Pawn pawnCaptureSix = new Pawn(2,1,black,board);
        board.getGameboard()[2][1] = pawnCaptureSix;
        Pawn pawnCaptureSeven = new Pawn(1,2,black,board);
        board.getGameboard()[1][2] = pawnCaptureSeven;
        Pawn pawnCaptureEight = new Pawn(1,4,black,board);
        board.getGameboard()[1][4] = pawnCaptureEight;

        // Check it can make all the necessary captures
        assertTrue(knight.canCapture(2,5));
        assertTrue(knight.canCapture(4,5));
        assertTrue(knight.canCapture(5,4));
        assertTrue(knight.canCapture(5,2));
        assertTrue(knight.canCapture(4,1));
        assertTrue(knight.canCapture(2,1));
        assertTrue(knight.canCapture(1,2));
        assertTrue(knight.canCapture(1,4));
    }

    @Test
    void canCaptureOver() {
        // This tests all available captures to the knight
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;

        // Set up pawns to be captured
        Pawn pawnCaptureOne = new Pawn(2,5,black,board);
        board.getGameboard()[2][5] = pawnCaptureOne;
        Pawn pawnCaptureTwo = new Pawn(4,5,black,board);
        board.getGameboard()[4][5] = pawnCaptureTwo;
        Pawn pawnCaptureThree = new Pawn(5,4,black,board);
        board.getGameboard()[5][4] = pawnCaptureThree;
        Pawn pawnCaptureFour = new Pawn(5,2,black,board);
        board.getGameboard()[5][2] = pawnCaptureFour;
        Pawn pawnCaptureFive = new Pawn(4,1,black,board);
        board.getGameboard()[4][1] = pawnCaptureFive;
        Pawn pawnCaptureSix = new Pawn(2,1,black,board);
        board.getGameboard()[2][1] = pawnCaptureSix;
        Pawn pawnCaptureSeven = new Pawn(1,2,black,board);
        board.getGameboard()[1][2] = pawnCaptureSeven;
        Pawn pawnCaptureEight = new Pawn(1,4,black,board);
        board.getGameboard()[1][4] = pawnCaptureEight;

        // Surround the knight with pawns
        Pawn pawnOne = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = pawnOne;
        Pawn pawnTwo = new Pawn(3,4,white,board);
        board.getGameboard()[3][4] = pawnTwo;
        Pawn pawnThree = new Pawn(4,4,white,board);
        board.getGameboard()[4][4] = pawnThree;
        Pawn pawnFour = new Pawn(4,3,white,board);
        board.getGameboard()[4][3] = pawnFour;
        Pawn pawnFive = new Pawn(4,2,white,board);
        board.getGameboard()[4][2] = pawnFive;
        Pawn pawnSix = new Pawn(3,2,white,board);
        board.getGameboard()[3][2] = pawnSix;
        Pawn pawnSeven = new Pawn(2,2,white,board);
        board.getGameboard()[2][2] = pawnSeven;
        Pawn pawnEight = new Pawn(2,3,white,board);
        board.getGameboard()[2][3] = pawnEight;

        // Check it can still make all the necessary captures
        assertTrue(knight.canCapture(2,5));
        assertTrue(knight.canCapture(4,5));
        assertTrue(knight.canCapture(5,4));
        assertTrue(knight.canCapture(5,2));
        assertTrue(knight.canCapture(4,1));
        assertTrue(knight.canCapture(2,1));
        assertTrue(knight.canCapture(1,2));
        assertTrue(knight.canCapture(1,4));
    }

    @Test
    void restrictedCapture() {
        // Tests captures the knight should not be able to make
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;

        // Set up pawns to be 'captured'
        Pawn pawnCaptureOne = new Pawn(3,6,black,board);
        board.getGameboard()[3][6] = pawnCaptureOne;
        Pawn pawnCaptureTwo = new Pawn(6,3,black,board);
        board.getGameboard()[6][3] = pawnCaptureTwo;
        Pawn pawnCaptureThree = new Pawn(0,3,black,board);
        board.getGameboard()[0][3] = pawnCaptureThree;
        Pawn pawnCaptureFour = new Pawn(3,0,black,board);
        board.getGameboard()[3][0] = pawnCaptureFour;
        Pawn pawnCaptureFive = new Pawn(4,4,black,board);
        board.getGameboard()[4][4] = pawnCaptureFive;
        Pawn pawnCaptureSix = new Pawn(0,1,black,board);
        board.getGameboard()[0][1] = pawnCaptureSix;

        // Test captures to squares it shouldn't be able to capture
        assertFalse(knight.canCapture(3,6));
        assertFalse(knight.canCapture(6,3));
        assertFalse(knight.canCapture(0,3));
        assertFalse(knight.canCapture(3,0));
        assertFalse(knight.canCapture(4,4));
        assertFalse(knight.canCapture(0,1));

        // Test capture on a square that's empty
        assertFalse(knight.canCapture(2,5));
    }

    @Test
    void captureOwnPiece() {
        // Test to ensure piece cannot capture its own pieces
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Knight knight = new Knight(3,3,white,board);
        board.getGameboard()[3][3] = knight;
        Pawn ownPawn = new Pawn(2,5,white,board);
        board.getGameboard()[2][5] = ownPawn;

        // Make sure piece can't capture pawn of it's own colour
        assertFalse(knight.canCapture(2,5));
    }

}