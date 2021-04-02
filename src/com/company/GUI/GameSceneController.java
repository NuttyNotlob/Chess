package com.company.GUI;

import com.company.gamesetup.Board;
import com.company.gamesetup.Game;
import com.company.pieces.Piece;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameSceneController {

    ImageView [][] pieceImages;
    Game chessGame;

    @FXML
    AnchorPane gameAnchorPane;

    public GameSceneController() {
    }

    @FXML
    void initialize() {
        this.pieceImages = new ImageView[8][8];

        for (int i = 0; i < pieceImages.length; i++) {
            for (int j = 0; j < pieceImages[i].length; j++) {
                pieceImages[i][j] = new ImageView();
                pieceImages[i][j].setFitWidth(71); pieceImages[i][j].setFitHeight(71);
                pieceImages[i][j].setLayoutX(71*i + 22);
                pieceImages[i][j].setLayoutY(71* (7 - j) + 8);
                gameAnchorPane.getChildren().add(pieceImages[i][j]);
            }
        }

    }

    public void startGame() {
        drawBoard(this.chessGame.getM_gameboard().getGameboard());
        // this.chessGame.playGame();
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
                } else {
                    pieceImages[i][j].setImage(null);
                }
            }
        }
    }

    public void setChessGame(Game chessGame) {
        this.chessGame = chessGame;
    }
}
