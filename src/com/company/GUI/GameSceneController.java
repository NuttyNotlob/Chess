package com.company.GUI;

import com.company.gamesetup.Board;
import com.company.pieces.Piece;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameSceneController {

    @FXML
    ImageView [][] pieceImages;

    public GameSceneController() {
        // When the Game Scene is set up, we initialise all the ImageViews
        generateImageViews();
    }

    private void generateImageViews() {
        // This method sets up the array of ImageViews required to hold the piece images on the board

        this.pieceImages = new ImageView[8][8];

        for (int i = 0; i < pieceImages.length; i++) {
            for (int j = 0; j < pieceImages[i].length; j++) {
                pieceImages[i][j] = new ImageView();
                pieceImages[i][j].setFitWidth(71); pieceImages[i][j].setFitHeight(71);
                pieceImages[i][j].setLayoutX(71*i + 22);
                pieceImages[i][j].setLayoutY(71*i + 8);
            }
        }
    }

    public void drawBoard (Piece[][] boardState) {
        // Note that this method is reliant on the Assets folder being used for all images, and the related image to
        // each piece having the correct filename

        // Iterate through each tile on the board
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] != null) {
                    // For non-null pieces, we find out the type of piece it is, and set the image asset to use based
                    // on this from a String matching the filename
                    String pieceType = (boardState[i][j].getPieceString());
                    Image image = new Image("Assets/" + pieceType + ".png");

                    // Now we insert this image into the associated tile's ImageView
                    pieceImages[i][j].setImage(image);
                }
            }
        }
    }
}
