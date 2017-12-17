package client;

import client.enums.CheckerCellEnum;
import client.enums.PlayerTagEnum;
import client.logic.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CheckerBoard {
    public static final int ROWS = 17;
    public static final int COLUMNS = 25;
    public static final int SIZE = 20;
    public static final double HEIGHT = SIZE * 2;
    public static final double WIDTH = Math.sqrt(3)/2 * HEIGHT;

    final CheckerCell[][] board = new CheckerCell[COLUMNS][ROWS];

    public CheckerBoard(int amountOfPlayers) {
        createBoard();
    }

    public CheckerBoard(String[] lines) {
        createBoardFromStrings(lines);
    }

    private void createBoard() {

    }

    private void createBoardFromStrings(String[] lines) {
        for(int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                char currentChar = lines[y].charAt(x);
                PlayerTagEnum[] possibilities = PlayerTagEnum.values();
                PlayerTagEnum owner;
                CheckerCellEnum type;
                Color[] colors = new Color[]{Color.TRANSPARENT, Color.LIGHTBLUE, Color.LIGHTGREEN, Color.LIGHTPINK, Color.LIGHTCORAL, Color.LIGHTYELLOW, Color.STEELBLUE};
                if (currentChar == '0') {
                    owner = PlayerTagEnum.NONE;
                    type = CheckerCellEnum.CELL_BOARD;
                } else if (currentChar == ' ') {
                    //owner = PlayerTagEnum.NONE;
                    //type = CheckerCellEnum.CELL_EMPTY;
                    continue;
                } else {
                    owner = possibilities[Integer.parseInt(String.valueOf(currentChar))];
                    type = CheckerCellEnum.CELL_TAKEN;
                }

                board[x][y] = new CheckerCell(x, y, type, owner, colors[Integer.parseInt(String.valueOf(currentChar))]);
            }
        }
    }

    public double cellsDistance(CheckerCell a, CheckerCell b) {
        return (Math.abs(a.getQ() - b.getQ()) + Math.abs(a.getR() - b.getR()) + Math.abs(a.getS() - b.getS()));
    }

    public void moveTo(CheckerCell a, CheckerCell b) {
        board[b.getQ()][b.getR()] = a;
        board[a.getQ()][a.getR()] = b;
        System.out.println("moving");

    }

    public Polygon getDrawableShape(int x, int y) {
        Polygon poly = new Polygon();
        for (int i = 0; i < 6; i ++) {
            int angleDeg = 60 * i + 30;
            double angleRad = Math.PI / 180 * angleDeg;
            double centerX = x * WIDTH + WIDTH / 2 * y - 5.5 * WIDTH;
            double centerY = SIZE + y * HEIGHT * 3/4 ;
            Point p = new Point(centerX + SIZE * Math.cos(angleRad), centerY + SIZE * Math.sin(angleRad));
            poly.getPoints().addAll(p.x, p.y);
        }

        return poly;
    }

    public Point screenToBoard(double x, double y) {
        double q = (x * Math.sqrt(3) / 3 - y / 3) / SIZE;
        double r = y * 2/3 / SIZE;
        System.out.println(q + ", " + r);
        return cellRound(q, r);
    }

    public Point cellRound(double cellQ, double cellR)
    {
        double cellS = -cellQ-cellR;
        int q = (int)(Math.round(cellQ));
        int r = (int)(Math.round(cellR));
        int s = (int)(Math.round(cellS));
        double q_diff = Math.abs(q - cellQ);
        double r_diff = Math.abs(r - cellR);
        double s_diff = Math.abs(s - cellS);
        if (q_diff > r_diff && q_diff > s_diff)
        {
            q = -r - s;
        }
        else
        if (r_diff > s_diff)
        {
            r = -q - s;
        }
        return new Point(q, r);
    }

    public CheckerCell[][] getBoard() {
        return board;
    }
}



