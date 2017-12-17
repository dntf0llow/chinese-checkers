package client.logic;

import client.enums.PlayerTagEnum;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final PlayerTagEnum tag;
    private boolean isActive = false;
    private int numberOfMoves = 0;

    private final List<Point> marbles = new ArrayList<>();

    public Player(String name, PlayerTagEnum tag, boolean isActive) {
        this.name = name;
        this.tag = tag;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean didMove() {
        return numberOfMoves != 0;
    }
}
