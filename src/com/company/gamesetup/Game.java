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
        // Constructor that sets up board with a normal chess setup
        this.m_gameboard = new Board();
        this.m_black = new Player("Black");
        this.m_white = new Player("White");
        this.m_turnCounter = 0;

        normalSetup();
    }

    public Board getM_gameboard() {
        return m_gameboard;
    }

    public ChessGUI getGameGUI() {
        return gameGUI;
    }

    public void setGameGUI(ChessGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    public void setMovePositions(int movePosition, int value) {
        // Method used by MouseEvents in GUI to set the tiles that the player's move/capture is being made between
        this.movePositions[movePosition] = value;
    }

    public void normalSetup() {
        // Sets up the board in a normal chess layout

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

    public void startTurn() {
        // This method is called at the start of the game, and subsequently at the start of each player's turn

        if (this.m_turnCounter == 0) {
            // This branch is followed only on turn 1, to set our class variables in place now that the game has started
            // First we set the controller of the game
            gameController = gameGUI.getGameController();

            // White plays first
            currentPlayer = this.m_white;

            // We draw the board for the first time. After this, this is handled in the endTurn() method
            // This goes into the endTurn() method as it will need to be drawn on a checkmate, when there would not be
            // a next turn
            gameController.drawBoard(this.getM_gameboard().getGameboard());
        }

        // Reset the movePositions variable to be empty
        this.movePositions = new int[4];

        // State who's turn it is in the informationDisplay
        gameController.setInfoDisplay(currentPlayer.getColour() + " to play");

        // Set the game controller to now take move inputs, after which it will call the playTurn() method
        this.getGameGUI().getGameController().setTurnInputState("OwnPieceSelection");

    }

    public boolean playTurn() {
        // This method is called when the players make their second tile selection for their move. It will return true
        // if it is able to make that move, and subsequently moves to the endTurn() method through the
        // GameSceneController. If the inputs mean it is unable to make a move, it will return false and the
        // GameSceneController returns tot eh startTurn() method without changing player, essentially forcing the player
        // to remake their selection

        // Check to ensure there's a piece in our start position and, if there is, that it's a piece of our own colour
        if ((this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]] == null) ||
                (this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]].getM_player().getColour().compareTo(currentPlayer.getColour()) != 0)) {
            System.out.println("ERROR: Please ensure start position contains a piece of your colour");
            return false;
        }

        // Make the move as a move or capture, depending on whether the target tile is empty. We also use the canMove()
        // or canCapture() of the piece to check the validity of the move
        // First we check for a capture
        if (this.m_gameboard.getGameboard()[movePositions[2]][movePositions[3]] != null) {
                if(this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]].canCapture(movePositions[2], movePositions[3])) {
                    // If it's all valid we make the capture and switch player
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
            // Now the checks for a move
                if(this.m_gameboard.getGameboard()[movePositions[0]][movePositions[1]].canMove(movePositions[2], movePositions[3])) {
                    // If it's all valid we make the move and switch player
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

        // If we manage to make it to this point then the move/capture was not valid, and so we return false. This works
        // with the GameSceneController to essentially restart the turn with the startTurn() function
        System.out.println("Invalid move/capture");
        return false;
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

    public void endTurn() {
        // Re-draw the board into the GUI
        gameController.drawBoard(this.getM_gameboard().getGameboard());

        // Make all necessary checks to see if the player is in check, or if checkmate has occurred
        if (this.m_gameboard.checkTest(currentPlayer)) {
            if (this.m_gameboard.checkmateTest(currentPlayer)) {
                // If they are in checkmate, then the startTurn() function is not called as the game has finished
                System.out.println(currentPlayer.getColour() + " has been checkmated! Game over");
            } else {
                // Otherwise if they are just in check, the next turn is called
                System.out.println(currentPlayer.getColour() + " is in check");
                startTurn();
            }
        } else {
            // If the player is not in check, we call for the next turn to start
            startTurn();
        }
    }

}
