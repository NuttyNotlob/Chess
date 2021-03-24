package com.company.tests.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;
import com.company.pieces.King;
import com.company.pieces.Pawn;
import com.company.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void canMove() {
        // This tests all available movements to the king
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Check it can move in each direction
        assertTrue(king.canMove(2,4));
        assertTrue(king.canMove(3,4));
        assertTrue(king.canMove(4,4));
        assertTrue(king.canMove(4,3));
        assertTrue(king.canMove(4,2));
        assertTrue(king.canMove(3,2));
        assertTrue(king.canMove(2,2));
        assertTrue(king.canMove(2,3));
    }

    @Test
    void restrictedMove() {
        // This tests movements the king should be unable to make
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Check various tiles it shouldn't move to
        assertFalse(king.canMove(1,3));
        assertFalse(king.canMove(3,5));
        assertFalse(king.canMove(1,4));
        assertFalse(king.canMove(4,7));

        // Check it can't move to a tile if it is full
        assertTrue(king.canMove(2,4));
        Pawn blockingPawn = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = blockingPawn;
        assertFalse(king.canMove(2,4));
    }

    @Test
    void canCapture() {
        // This tests all available captures to the king
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Each pawn needs to be set up individually and the capture checked, with the piece then deleted. This is to
        // avoid the king capturing into check

        // Up-left
        Pawn pawnUpLeft = new Pawn(2,4, black,board);
        board.getGameboard()[2][4] = pawnUpLeft;
        assertTrue(king.canCapture(2,4));
        board.getGameboard()[2][4] = null;

        // Up-middle
        Pawn pawnUpMiddle = new Pawn(3,4, black,board);
        board.getGameboard()[3][4] = pawnUpMiddle;
        assertTrue(king.canCapture(3,4));
        board.getGameboard()[3][4] = null;

        // Up-right
        Pawn pawnUpRight = new Pawn(4,4, black,board);
        board.getGameboard()[4][4] = pawnUpRight;
        assertTrue(king.canCapture(4,4));
        board.getGameboard()[4][4] = null;

        // Middle-right
        Pawn pawnMiddleRight = new Pawn(4,3, black,board);
        board.getGameboard()[4][3] = pawnMiddleRight;
        assertTrue(king.canCapture(4,3));
        board.getGameboard()[4][3] = null;

        // Lower-right
        Pawn pawnLowerRight = new Pawn(4,2, black,board);
        board.getGameboard()[4][2] = pawnLowerRight;
        assertTrue(king.canCapture(4,2));
        board.getGameboard()[4][2] = null;

        // Lower-middle
        Pawn pawnLowerMiddle = new Pawn(3,2, black,board);
        board.getGameboard()[3][2] = pawnLowerMiddle;
        assertTrue(king.canCapture(3,2));
        board.getGameboard()[3][2] = null;

        // Lower-left
        Pawn pawnLowerLeft = new Pawn(2,2, black,board);
        board.getGameboard()[2][2] = pawnLowerLeft;
        assertTrue(king.canCapture(2,2));
        board.getGameboard()[2][2] = null;

        // Middle-left
        Pawn pawnMiddleLeft = new Pawn(2,3, black,board);
        board.getGameboard()[2][3] = pawnMiddleLeft;
        assertTrue(king.canCapture(2,3));
        board.getGameboard()[2][3] = null;
    }

    @Test
    void restrictedCapture() {
        // This tests captures the king should be unable to make
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Check various tiles it shouldn't be able to capture
        Pawn pawnOne = new Pawn(3,1, black,board);
        board.getGameboard()[3][1] = pawnOne;
        assertFalse(king.canCapture(3,1));

        Pawn pawnTwo = new Pawn(5,3, black,board);
        board.getGameboard()[5][3] = pawnTwo;
        assertFalse(king.canCapture(5,3));

        Pawn pawnThree = new Pawn(4,1, black,board);
        board.getGameboard()[4][1] = pawnThree;
        assertFalse(king.canCapture(4,1));

        Pawn pawnFour = new Pawn(7,4, black,board);
        board.getGameboard()[7][4] = pawnFour;
        assertFalse(king.canCapture(7,4));

        // Check it can't capture an empty tile
        assertFalse(king.canCapture(2,4));
    }

    @Test
    void captureOwnPiece() {
        // Test to ensure king cannot capture its own pieces
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Set own pawn and check for capture
        Pawn ownPawn = new Pawn(2,4, white,board);
        board.getGameboard()[2][4] = ownPawn;
        assertFalse(king.canCapture(2,4));
    }

    @Test
    void moveToCheck() {
        // This tests whether the king can make a move into check
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Check the king can move to a square on its own
        assertTrue(king.canMove(2,4));

        // Now set up a piece that puts that tile into check, and see if it can move there
        Rook enemyRook = new Rook(2,0,black,board);
        board.getGameboard()[2][0] = enemyRook;
        assertFalse(king.canMove(2,4));

        // Now check the opposite direction, with our own piece
        // Check the king can move to a square on its own
        assertTrue(king.canMove(4,4));

        // Now set up a piece of king's colour that puts that tile into check, and see if it can move there
        Rook ownRook = new Rook(4,0,white,board);
        board.getGameboard()[4][0] = ownRook;
        assertTrue(king.canMove(4,4));
    }

    @Test
    void captureToCheck() {
        // This tests whether the king can make a move into check
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        King king = new King(3,3,white,board);
        board.getGameboard()[3][3] = king;

        // Check the king can capture to a square on its own
        Pawn enemyPawnOne = new Pawn(2,4, black,board);
        board.getGameboard()[2][4] = enemyPawnOne;
        assertTrue(king.canCapture(2,4));

        // Now set up a piece that puts that tile into check, and see if it can move there
        Rook enemyRook = new Rook(2,0,black,board);
        board.getGameboard()[2][0] = enemyRook;
        assertFalse(king.canCapture(2,4));

        // Now check the opposite direction, with our own piece
        // Check the king can move to a square on its own
        Pawn enemyPawnTwo = new Pawn(4,4, black,board);
        board.getGameboard()[4][4] = enemyPawnTwo;
        assertTrue(king.canCapture(4,4));

        // Now set up a piece of king's colour that puts that tile into check, and see if it can move there
        Rook ownRook = new Rook(4,0,white,board);
        board.getGameboard()[4][0] = ownRook;
        assertTrue(king.canCapture(4,4));
    }
}