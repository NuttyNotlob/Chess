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

    // Constructor for this class creates a new chess game to be associated with the controller
    public ChessGUI() {
        this.chessGame = new Game();
    }

    public void playGame() {
        // Simple function to start the GUI application
        // Called in the main function as the main function cannot directly call chessGUI.launch()
        launch();
    }

    @Override
    public void start(Stage gameStage) throws Exception {
        // start() function called by GUI upon application launch
        // First we set the chess game's GUI to be our current instance of this class (note the chess game is that
        // created as part of this class's constructor)
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

    public GameSceneController getGameController() {
        return gameController;
    }
}
