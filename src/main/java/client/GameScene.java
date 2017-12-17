package client;

import client.controllers.GameController;
import client.managers.GameManager;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {

    private GameController controller;

    public GameScene(Parent root, double width, double height, GameManager manager) {
        super(root, width, height);
        init(manager);
    }
    public GameScene(Parent root, GameManager manager) {
        super(root);
        init(manager);
    }


    private void init(GameManager manager) {
        controller= new GameController();
        controller.setGameManager(manager);
    }
}
