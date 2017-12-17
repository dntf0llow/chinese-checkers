package client;

import client.managers.GameManager;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Window extends Application {

    private static final String TITLE = "Chinese Checkers";


    private GameManager gameManager;
    private Stage gameStage;
    private GameScene gameScene;
    private GameScene menuScene;

    @Override
    public void start(Stage stage) {
        gameStage = stage;
        gameManager = new GameManager();
        Group menu = new Group();


        stage.setTitle(TITLE);
        stage.setResizable(false);

        setUpMenu(menu);


        stage.setScene(menuScene);
        stage.show();
    }

    private void setUpMenu(Group parent) {

       //TODO: MENU
        setUpGame(2);

    }

    private void setUpGame(int players) {
        Group root = new Group();
        gameScene = new GameScene(root, gameManager);


    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
