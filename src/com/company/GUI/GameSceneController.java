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

public class GameSceneController {

    ImageView [][] pieceImages;
    Game chessGame;
    boolean gameStarted;
    String turnInputState;

    @FXML
    AnchorPane gameAnchorPane;
    @FXML
    Label infoDisplay;

    public GameSceneController() {
    }

    @FXML
    void initialize() {
        this.pieceImages = new ImageView[8][8];
        this.gameStarted = false;
        this.turnInputState = "NoInput";

        for (int i = 0; i < pieceImages.length; i++) {
            for (int j = 0; j < pieceImages.length; j++) {
                pieceImages[i][j] = new ImageView();
                pieceImages[i][j].setFitWidth(71.5); pieceImages[i][j].setFitHeight(71.5);
                pieceImages[i][j].setLayoutX(71.5*i + 22);
                pieceImages[i][j].setLayoutY(71.5* (7 - j) + 8);
                gameAnchorPane.getChildren().add(pieceImages[i][j]);

                int finalI = i;
                int finalJ = j;
                pieceImages[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (turnInputState.compareTo("OwnPieceSelection") == 0) {
                            chessGame.setMovePositions(0, finalI);
                            chessGame.setMovePositions(1, finalJ);
                            System.out.println("First selected");
                            setTurnInputState("MoveTileSelection");
                        } else if (turnInputState.compareTo("MoveTileSelection") == 0) {
                            chessGame.setMovePositions(2, finalI);
                            chessGame.setMovePositions(3, finalJ);
                            System.out.println("Second selected");
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
        // each piece having the correct filename

        // Iterate through each tile on the board
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState.length; j++) {
                if (boardState[i][j] != null) {
                    // For non-null pieces, we find out the type of piece it is, and set the image asset to use based
                    // on this from a String matching the filename
                    String pieceType = (boardState[i][j].getPieceString());
                    Image image = new Image(getClass().getResourceAsStream("Assets/" + pieceType + ".png"));

                    // Now we insert this image into the associated tile's ImageView
                    pieceImages[i][j].setImage(image);
                    pieceImages[i][j].setOpacity(1);
                } else {
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
