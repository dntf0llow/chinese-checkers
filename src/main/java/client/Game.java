package client;

import client.managers.FileManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {

    CheckerBoard board;
    CheckerCell selectedCell = null;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        initLogic();
        initGUI(stage);
    }

    private void initLogic() {
        ArrayList<String> lines = (FileManager.readFile("src/main/resources/board.txt"));
        board = new CheckerBoard(lines.toArray(new String[0]));

    }

    private void initGUI(Stage stage) {
        stage.setTitle("Chinese Checkers");
        stage.setResizable(false);
        Canvas canvas = new Canvas();

        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().addAll(canvas);
        stage.setScene(scene);

        for (int y = 0; y < CheckerBoard.ROWS; y++) {
            for (int x = 0; x < CheckerBoard.COLUMNS; x++) {
                CheckerCell cell = board.board[x][y];
               // if(board.board[x][y].isOnBoard()){
                    if(board.board[x][y] != null) {
                    Polygon shape = board.getDrawableShape(x, y);

                    shape.setFill(cell.color);
                    shape.setStroke(Color.BLACK);
                    shape.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                        if(selectedCell == null) {
                            selectedCell = cell;
                            System.out.println("null");
                        }
                        else{
                            System.out.println(board.cellsDistance(selectedCell, cell));
                            if (board.cellsDistance(selectedCell, cell) == 2) {
                                board.moveTo(selectedCell, cell);
                                selectedCell = null;
                                System.out.println("not null");


                            }
                        }
                    });
                    shape.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
                        shape.setStroke(Color.RED);
                        shape.setStrokeWidth(2);
                    });
                    shape.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
                        shape.setStroke(Color.BLACK);
                        shape.setStrokeWidth(1);
                    });
                    root.getChildren().add(shape);
                }

            }
        }

        stage.show();
    }


}
