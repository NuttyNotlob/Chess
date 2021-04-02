package com.company.GUI;

import com.company.gamesetup.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessGUI extends Application {

    private Game chessGame;
    private GameSceneController gameController;

    public ChessGUI() {
        this.chessGame = new Game();
    }

    @Override
    public void start(Stage gameStage) throws Exception {
        // First we set the chess game's GUI to be our current instance of this class
        this.chessGame.setGameGUI(this);

        // Next we set up the application window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();
        gameStage.setTitle("Chess");
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();

        // Finally we set the chess game of the controller
        gameController = loader.getController();
        gameController.setChessGame(this.chessGame);
    }

    public void playGame() {
        launch();
    }

    public GameSceneController getGameController() {
        return gameController;
    }
}
