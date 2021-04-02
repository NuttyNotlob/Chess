package com.company;

import com.company.GUI.ChessGUI;
import com.company.gamesetup.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main{

    public static void main(String[] args) {
        ChessGUI gameGUI = new ChessGUI();
        gameGUI.getChessGame().setGameGUI(gameGUI);
        gameGUI.playGame();
    }

}
