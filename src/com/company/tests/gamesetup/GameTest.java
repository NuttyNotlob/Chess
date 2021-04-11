package com.company.tests.gamesetup;

import com.company.gamesetup.Game;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void makeMove() {

    }

    @Test
    void makeCapture() {

    }

    @Test
    void getMoveInput() {

    }

    @Test
    void gameInCheckWhite() {

    }

    @Test
    void gameInCheckBlack() {
        // Set the output to go to a string to check later for the expected output of telling Black is in check
        ByteArrayOutputStream OutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(OutputStream));

        // Board setup
        Game game = new Game();

        // Turn user input simulation to produce checkmate for black, using a simple checkmate
        String input = "e2" + System.getProperty("line.separator") + "e4" + System.getProperty("line.separator") +
                "a7" + System.getProperty("line.separator") + "a6" + System.getProperty("line.separator") +
                "d1" + System.getProperty("line.separator") + "h5" + System.getProperty("line.separator") +
                "a6" + System.getProperty("line.separator") + "a5" + System.getProperty("line.separator") +
                "h5" + System.getProperty("line.separator") + "f7" + System.getProperty("line.separator") +
                "END" + System.getProperty("line.separator");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Play game with this input
        game.startTurn();

        // Check that Black was stated to be in check
        assertTrue(OutputStream.toString().contains("Black is in check"));

    }

    @Test
    void fullGameCheckmateWhite() {
        // Board setup
        Game game = new Game();

        // Turn user input simulation to produce checkmate for white, using a fool's mate
        String input = "f2" + System.getProperty("line.separator") + "f3" + System.getProperty("line.separator") +
                "e7" + System.getProperty("line.separator") + "e5" + System.getProperty("line.separator") +
                "g2" + System.getProperty("line.separator") + "g4" + System.getProperty("line.separator") +
                "d8" + System.getProperty("line.separator") + "h4" + System.getProperty("line.separator");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Check for checkmate (returns 1 in the playGame() method)
        // todo assertTrue(game.startTurn() == 1);
    }

    @Test
    void fullGameCheckmateBlack() {
        // Board setup
        Game game = new Game();

        // Turn user input simulation to produce checkmate for black, using a simple checkmate
        String input = "e2" + System.getProperty("line.separator") + "e4" + System.getProperty("line.separator") +
                "a7" + System.getProperty("line.separator") + "a6" + System.getProperty("line.separator") +
                "d1" + System.getProperty("line.separator") + "h5" + System.getProperty("line.separator") +
                "a6" + System.getProperty("line.separator") + "a5" + System.getProperty("line.separator") +
                "f1" + System.getProperty("line.separator") + "c4" + System.getProperty("line.separator") +
                "b7" + System.getProperty("line.separator") + "b6" + System.getProperty("line.separator") +
                "h5" + System.getProperty("line.separator") + "f7" + System.getProperty("line.separator");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Check for checkmate (returns 1 in the playGame() method)
        // todo assertTrue(game.startTurn() == 1);
    }
}