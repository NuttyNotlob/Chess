package com.company.GUI;

import com.company.gamesetup.Game;
import com.company.pieces.Piece;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class GameSceneController {

    ImageView [][] pieceImages;
    Game chessGame;
    boolean gameStarted;
    String turnInputState;

    // Following variables all represent UI elements in the GUI scene
    @FXML
    AnchorPane gameAnchorPane;
    @FXML
    Label infoDisplay;
    @FXML
    Rectangle selectionMarker;

    public GameSceneController() {
    }

    @FXML
    void initialize() {
        // Method called when scene has been created. This is used to initialise all the ImageView objects that the
        // piece images will be placed into

        // ImageView array is created, and the TurnInputState (used to set the ImageView's behaviour on a MouseEvent
        // from a click) is set to "NoInput"
        this.pieceImages = new ImageView[8][8];
        this.gameStarted = false;
        this.turnInputState = "NoInput";

        // ImageViews are created on top of each tile on the chessboard background image
        for (int i = 0; i < pieceImages.length; i++) {
            for (int j = 0; j < pieceImages.length; j++) {
                // ImageView location and size parameters are set, and it is added to the scene node
                pieceImages[i][j] = new ImageView();
                pieceImages[i][j].setFitWidth(71.5); pieceImages[i][j].setFitHeight(71.5);
                pieceImages[i][j].setLayoutX(71.5*i + 22);
                pieceImages[i][j].setLayoutY(71.5* (7 - j) + 8);
                gameAnchorPane.getChildren().add(pieceImages[i][j]);

                // ImageView events to be triggered on a MouseEvent are now set
                int finalI = i;
                int finalJ = j;
                pieceImages[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (turnInputState.compareTo("OwnPieceSelection") == 0) {
                            // This path is followed if it is the first click of your turn (i.e. you are selecting which
                            // of your pieces you would like to move)

                            // The movePositions of the chessGame object are set, to feed into the method which plays
                            // the turn out. The input set is then changed to set the game ready for the second click
                            chessGame.setMovePositions(0, finalI);
                            chessGame.setMovePositions(1, finalJ);
                            setTurnInputState("MoveTileSelection");

                            // Move the selectionMarker to this tile and set the stroke width to 1 so it is visible
                            selectionMarker.setLayoutX(pieceImages[finalI][finalJ].getLayoutX()+4);
                            selectionMarker.setLayoutY(pieceImages[finalI][finalJ].getLayoutY()+3);
                            selectionMarker.setStrokeWidth(1);

                        } else if (turnInputState.compareTo("MoveTileSelection") == 0) {
                            // This path is followed if it is the second click of your turn (i.e. you are selecting
                            // where you would like your piece to move/capture)

                            chessGame.setMovePositions(2, finalI);
                            chessGame.setMovePositions(3, finalJ);

                            // Reset the selectionMarker stroke width back to 0 so it is invisible
                            selectionMarker.setStrokeWidth(0);

                            // Now check if the movePosition values supplied by the tiles selected by the player provide
                            // a valid turn. If they do, the turn is ended, and the next player's turn will subsequently
                            // begin. If they don't, the startTurn() function is called without changing the currently
                            // active player, essentially restarting the turn after displaying an error message to the
                            // player
                            if (chessGame.playTurn()) {
                                chessGame.endTurn();
                            } else {
                                chessGame.startTurn();
                            }
                        }
                    }
                });
            }
        }

    }

    public void startGame() {
        this.chessGame.startTurn();
        gameStarted = true;
    }

    public void drawBoard (Piece[][] boardState) {
        // Note that this method is reliant on the Assets folder being used for all images, and the related image to
        // each piece having the correct filename. Any change to class names or additional piece classes must be
        // duplicated with adjustment/addition to Assets folder

        // Iterate through each tile on the board
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState.length; j++) {
                if (boardState[i][j] != null) {
                    // For non-null pieces, we find out the type of piece it is, and set the image asset to use based
                    // on this from a string matching the filename
                    String pieceType = (boardState[i][j].getPieceString());
                    Image image = new Image(getClass().getResourceAsStream("Assets/" + pieceType + ".png"));

                    // Now we insert this image into the associated tile's ImageView
                    pieceImages[i][j].setImage(image);
                    pieceImages[i][j].setOpacity(1);
                } else {
                    // If the tile is empty, it requires an image for it to be clickable (and so able to be selected as
                    // a tile to move into on a player's turn). As such we set the image to be a BlackPawn, but set the
                    // image to be invisible
                    pieceImages[i][j].setImage(new Image(getClass().getResourceAsStream("Assets/BlackPawn.png")));
                    pieceImages[i][j].setOpacity(0);
                }
            }
        }
    }

    public void setChessGame(Game chessGame) {
        this.chessGame = chessGame;
    }

    public void setTurnInputState(String turnInputState) {
        this.turnInputState = turnInputState;
    }

    public void setInfoDisplay(String info) {
        infoDisplay.setText(info);
    }
}
