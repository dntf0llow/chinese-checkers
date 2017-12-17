package client.controllers;

import client.managers.GameManager;

public class GameController implements IController {

    private GameManager manager;


    @Override
    public void setGameManager(GameManager manager) {
        this.manager = manager;
    }
}
