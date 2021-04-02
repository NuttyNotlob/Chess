package com.company.GUI;

import com.company.gamesetup.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessGUI extends Application {

    private Game chessGame;


    @Override
    public void start(Stage gameStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/GameScene.fxml"));
        gameStage.setTitle("Chess");
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();
    }
}
