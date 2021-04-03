package com.company.gamesetup;

import com.company.GUI.ChessGUI;
import com.company.GUI.GameSceneController;
import com.company.pieces.*;

import java.util.concurrent.TimeUnit;

public class Game {

    // This method plays in main and continues until the game is finished (checkmate or resignation)
    // todo Implement Stalemate
    // todo Implement resignation
    // todo Implement draw
    // todo Implement end game

    private final Board m_gameboard;
    private final Player m_black;
    private final Player m_white;
    private Player currentPlayer;
    private int m_turnCounter;
    private ChessGUI gameGUI;
    private int[] movePositions;
    private GameSceneController gameController;


    public Game() {
        this.m_gameboard = new Board();
        this.m_black = new Player("Black");
        this.m_white = new Player("White");
        this.m_turnCounter = 0;

        normalSetup();
    }

    public Game(ChessGUI gameGUI) {
        this();
        this.gameGUI = gameGUI;
    }

    public Board getM_gameboard() {
        return m_gameboard;
    }

    public Player getM_black() {
        return m_black;
    }

    public Player getM_white() {
        return m_white;
    }

    public int getM_turnCounter() {
        return m_turnCounter;
    }

    public void normalSetup() {
        // Black pieces
        this.m_gameboard.getGameboard()[0][6] = new Pawn(0, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[1][6] = new Pawn(1, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[2][6] = new Pawn(2, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[3][6] = new Pawn(3, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[4][6] = new Pawn(4, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[5][6] = new Pawn(5, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[6][6] = new Pawn(6, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[7][6] = new Pawn(7, 6, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[2][7] = new Bishop(2, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[5][7] = new Bishop(5, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[0][7] = new Rook(0, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[7][7] = new Rook(7, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[1][7] = new Knight(1, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[6][7] = new Knight(6, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[4][7] = new King(4, 7, m_black, m_gameboard);
        this.m_gameboard.getGameboard()[3][7] = new Queen(3, 7, m_black, m_gameboard);

        // White pieces
        this.m_gameboard.getGameboard()[0][1] = new Pawn(0, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[1][1] = new Pawn(1, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[2][1] = new Pawn(2, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[3][1] = new Pawn(3, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[4][1] = new Pawn(4, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[5][1] = new Pawn(5, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[6][1] = new Pawn(6, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[7][1] = new Pawn(7, 1, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[5][0] = new Bishop(5, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[2][0] = new Bishop(2, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[0][0] = new Rook(0, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[7][0] = new Rook(7, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[1][0] = new Knight(1, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[6][0] = new Knight(6, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[4][0] = new King(4, 0, m_white, m_gameboard);
        this.m_gameboard.getGameboard()[3][0] = new Queen(3, 0, m_white, m_gameboard);
    }

    public void makeMove(int startX, int startY, int endX, int endY) {
        // Method to make the required steps to move in a turn
        if (this.m_gameboard.getGameboard()[startX][startY].canMove(endX, endY)) {
            this.m_gameboard.getGameboard()[endX][endY] = this.m_gameboard.getGameboard()[startX][startY];
            this.m_gameboard.getGameboard()[endX][endY].setPosition(endX, endY);
            this.m_gameboard.getGameboard()[endX][endY].incrementM_timesMoved();
            this.m_gameboard.getGameboard()[startX][startY] = null;
        }
    }

    public void makeCapture(int startX, int startY, int endX, int endY) {
        // Method to make the required steps to capture in a turn
        if (this.m_gameboard.getGameboard()[startX][startY].canCapture(endX, endY)) {
            this.m_gameboard.getGameboard()[endX][endY] = this.m_gameboard.getGameboard()[startX][startY];
            this.m_gameboard.getGameboard()[endX][endY].setPosition(endX, endY);
            this.m_gameboard.getGameboard()[endX][endY].incrementM_timesMoved();
            this.m_gameboard.getGameboard()[startX][startY] = null;
        }
    }

    public void startTurn() {
        if (this.m_turnCounter == 0) {
            // First we set the controller of the game, now that the game has started
            gameController = gameGUI.getGameController();

            // White plays first
            currentPlayer = this.m_white;

            // Draw the board for the first time. After this, this is handled in the endTurn() method
            gameController.drawBoard(this.getM_gameboard().getGameboard());
        }

        // Now we set up the variables required each turn to store the current player, and the moves they make
        this.movePositions = new int[4];


        // State who's turn it is
        gameController.setInfoDisplay(currentPlayer.getColour() + " to play");

        // Set the game controller to now take move inputs, after which it will call the playTurn() method
        this.getGameGUI().getGameController().setTurnInputState("OwnPieceSelection");

    }

    public boolean playTurn() {
        // Returns false and forces user to retake move if inputs are not sufficient to make a move (e.g. incorrect
        // inputs)

        // Make sure there's a piece in our start position
        if (this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]] == null) {
            System.out.println("ERROR: Please ensure start position contains a piece of your colour");
            return false;
        }
        // And that it's a piece of the current player (not an OR with above check due to length of the check, neater
        // this way)
        else if (this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]].getM_player().getColour().compareTo(currentPlayer.getColour()) != 0) {
            System.out.println("ERROR: Please ensure start position contains a piece of your colour");
            return false;
        }

        if (this.m_gameboard.getGameboard()[movePositions[2]][movePositions[3]] != null) {
                if(this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]].canCapture(movePositions[2], movePositions[3])) {

                    makeCapture(movePositions[0], movePositions[1], movePositions[2], movePositions[3]);

                    if (currentPlayer.getColour().compareTo("White") == 0) {
                        currentPlayer = this.m_black;
                    } else {
                        currentPlayer = this.m_white;
                    }

                    this.m_turnCounter++;

                    return true;
                }

            } else {
                if(this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]].canMove(movePositions[2], movePositions[3])) {
                    makeMove(movePositions[0], movePositions[1], movePositions[2], movePositions[3]);

                    if (currentPlayer.getColour().compareTo("White") == 0) {
                        currentPlayer = this.m_black;
                    } else {
                        currentPlayer = this.m_white;
                    }

                    this.m_turnCounter++;

                    return true;
                }
            }

        System.out.println("Invalid move/capture");
        return false;
    }

    public void endTurn() {
        // Re-draw the board into the GUI
        gameController.drawBoard(this.getM_gameboard().getGameboard());

        if (this.m_gameboard.checkTest(currentPlayer)) {
            if (this.m_gameboard.checkmateTest(currentPlayer)) {
                System.out.println(currentPlayer.getColour() + " has been checkmated! Game over");
            } else {
                System.out.println(currentPlayer.getColour() + " is in check");
            }
        } else {
            startTurn();
        }
    }

    public ChessGUI getGameGUI() {
        return gameGUI;
    }

    public void setGameGUI(ChessGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    public void setMovePositions(int movePosition, int value) {
        this.movePositions[movePosition] = value;
    }
}
