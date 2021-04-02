package com.company.GUI;

import com.company.gamesetup.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessGUI extends Application {

    private Game chessGame;

    public ChessGUI() {
        this.chessGame = new Game();
    }

    @Override
    public void start(Stage gameStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();
        gameStage.setTitle("Chess");
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();
        GameSceneController gameController = loader.getController();
        gameController.setChessGame(this.chessGame);
    }

    public void playGame() {
        launch();
    }

    public Game getChessGame() {
        return chessGame;
    }
}
