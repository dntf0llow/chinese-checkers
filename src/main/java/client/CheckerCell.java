package client;

import client.enums.CheckerCellEnum;
import client.enums.PlayerTagEnum;
import javafx.scene.paint.Color;

public class CheckerCell {
    private final int q;
    private final int r;
    private final int s;

    private CheckerCellEnum type;
    private PlayerTagEnum ownerTag;
    public Color color;

    public CheckerCell(int x, int y, CheckerCellEnum type, PlayerTagEnum ownerTag, Color color) {
        this.q = x;
        this.r = y;
        this.s = -q-r;
        this.type = type;
        this.ownerTag = ownerTag;
        this.color = color;
    }

    public boolean isOnBoard() {
        return type != CheckerCellEnum.CELL_EMPTY;
    }

    public boolean isTaken() {
        return type == CheckerCellEnum.CELL_TAKEN;
    }

    public CheckerCellEnum getType() {
        return type;
    }

    public void setType(CheckerCellEnum type) {
        this.type = type;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getS() {
        return s;
    }
}
