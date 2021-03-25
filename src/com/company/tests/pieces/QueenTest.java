package com.company.tests.pieces;

import com.company.gamesetup.Board;
import com.company.gamesetup.Player;
import com.company.pieces.Pawn;
import com.company.pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void canMove() {
        // This tests a range of available movements to the queen
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;

        // Check it can move in each direction, and at different distances
        assertTrue(queen.canMove(2,4));
        assertTrue(queen.canMove(3,5));
        assertTrue(queen.canMove(6,6));
        assertTrue(queen.canMove(7,3));
        assertTrue(queen.canMove(4,2));
        assertTrue(queen.canMove(3,1));
        assertTrue(queen.canMove(0,0));
        assertTrue(queen.canMove(2,3));
    }

    @Test
    void restrictedMove() {
        // This tests moves the queen should not be able to make
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;

        // Check different moves the queen should not be able to make
        assertFalse(queen.canMove(2,5));
        assertFalse(queen.canMove(2,0));
        assertFalse(queen.canMove(0,2));
        assertFalse(queen.canMove(3,3));

        // Check move to a filled tile
        assertTrue(queen.canMove(7,3));
        Pawn blockingPawnOne = new Pawn(7,3,white,board);
        board.getGameboard()[7][3] = blockingPawnOne;
        assertFalse(queen.canMove(7,3));
    }

    @Test
    void blockedMove() {
        // This test is to ensure the queen cannot move to tiles blocked by other pieces
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;

        // Test in diagonal direction
        assertTrue(queen.canMove(1,5));
        Pawn blockingPawnOne = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = blockingPawnOne;
        assertFalse(queen.canMove(1,5));

        // Test in horizontal direction
        assertTrue(queen.canMove(5,3));
        Pawn blockingPawnTwo = new Pawn(4,3,white,board);
        board.getGameboard()[4][3] = blockingPawnTwo;
        assertFalse(queen.canMove(5,3));

        // Test in vertical direction
        assertTrue(queen.canMove(3,0));
        Pawn blockingPawnThree = new Pawn(3,1,white,board);
        board.getGameboard()[3][1] = blockingPawnThree;
        assertFalse(queen.canMove(3,0));
    }

    @Test
    void canCapture() {
        // This tests a range of available captures to the queen
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;
        
        // Set up pawns to be taken
        Pawn pawnOne = new Pawn(2,4,black,board);
        board.getGameboard()[2][4] = pawnOne;
        Pawn pawnTwo = new Pawn(3,5,black,board);
        board.getGameboard()[3][5] = pawnTwo;
        Pawn pawnThree = new Pawn(6,6,black,board);
        board.getGameboard()[6][6] = pawnThree;
        Pawn pawnFour = new Pawn(7,3,black,board);
        board.getGameboard()[7][3] = pawnFour;
        Pawn pawnFive = new Pawn(4,2,black,board);
        board.getGameboard()[4][2] = pawnFive;
        Pawn pawnSix = new Pawn(3,1,black,board);
        board.getGameboard()[3][1] = pawnSix;
        Pawn pawnSeven = new Pawn(0,0,black,board);
        board.getGameboard()[0][0] = pawnSeven;
        Pawn pawnEight = new Pawn(2,3,black,board);
        board.getGameboard()[2][3] = pawnEight;


        // Check queen can capture each at different directions and distances
        assertTrue(queen.canCapture(2,4));
        assertTrue(queen.canCapture(3,5));
        assertTrue(queen.canCapture(6,6));
        assertTrue(queen.canCapture(7,3));
        assertTrue(queen.canCapture(4,2));
        assertTrue(queen.canCapture(3,1));
        assertTrue(queen.canCapture(0,0));
        assertTrue(queen.canCapture(2,3));
    }

    @Test
    void restrictedCapture() {
        // This tests captures the queen should not be able to make
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;

        // Check different captures the queen should not be able to make
        Pawn pawnOne = new Pawn(2,5,black,board);
        board.getGameboard()[2][5] = pawnOne;
        assertFalse(queen.canCapture(2,5));

        Pawn pawnTwo = new Pawn(2,0,black,board);
        board.getGameboard()[2][0] = pawnTwo;
        assertFalse(queen.canCapture(2,0));

        Pawn pawnThree = new Pawn(0,2,black,board);
        board.getGameboard()[0][2] = pawnThree;
        assertFalse(queen.canCapture(0,2));

        assertFalse(queen.canCapture(3,3));

        // Check capture to an empty tile
        assertTrue(queen.canMove(7,3));
        assertFalse(queen.canCapture(7,3));

    }

    @Test
    void blockedCapture() {
        // This test is to ensure the queen cannot capture tiles blocked by other pieces
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Player black = new Player("Black");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;

        // Test in diagonal direction
        Pawn pawnOne = new Pawn(1,5,black,board);
        board.getGameboard()[1][5] = pawnOne;
        assertTrue(queen.canCapture(1,5));

        Pawn blockingPawnOne = new Pawn(2,4,white,board);
        board.getGameboard()[2][4] = blockingPawnOne;
        assertFalse(queen.canCapture(1,5));

        // Test in horizontal direction
        Pawn pawnTwo = new Pawn(5,3,black,board);
        board.getGameboard()[5][3] = pawnTwo;
        assertTrue(queen.canCapture(5,3));

        Pawn blockingPawnTwo = new Pawn(4,3,white,board);
        board.getGameboard()[4][3] = blockingPawnTwo;
        assertFalse(queen.canCapture(5,3));

        // Test in vertical direction
        Pawn pawnThree = new Pawn(3,0,black,board);
        board.getGameboard()[3][0] = pawnThree;
        assertTrue(queen.canCapture(3,0));

        Pawn blockingPawnThree = new Pawn(3,1,white,board);
        board.getGameboard()[3][1] = blockingPawnThree;
        assertFalse(queen.canCapture(3,0));

    }

    @Test
    void captureOwnPiece() {
        // This test checks the piece cannot capture its own pieces
        // Set up the board and piece on the board
        Board board = new Board();
        Player white = new Player("White");
        Queen queen = new Queen(3,3,white,board);
        board.getGameboard()[3][3] = queen;

        // Check queen cannot capture pawn of same colour
        Pawn pawnOne = new Pawn(3,5,white,board);
        board.getGameboard()[3][5] = pawnOne;
        assertFalse(queen.canCapture(3,5));

    }
}