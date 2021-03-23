package com.company.tests.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void canMove() {
        // Test to see if it can move in each direction
    }

    @Test
    void restrictedMove() {
        // Test movement it should not be able to make
    }

    @Test
    void canCapture() {
        // Test to see if it can capture in each direction
    }

    @Test
    void restrictedCapture() {
        // Test captures it should not be able to make
    }

    @Test
    void moveToCheck() {
        // Test to ensure King cannot move into check
    }

    @Test
    void captureToCheck() {
        // Test to ensure King cannot capture into check
    }

    @Test
    void captureOwnPiece() {
        // Test to ensure piece cannot capture its own pieces
    }
}