package cs2410.view;

import cs2410.control.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by User on 11/29/2016.
 */
public class ViewClass extends Application{
    final int WIDTH = 20;
    final int HEIGHT = 20;
    Controller controller = new Controller(WIDTH,HEIGHT);
    public Scoreboard scoreboard = new Scoreboard(controller);
    //public Scoreboard scoreboard = new Scoreboard(controller);
    BorderPane fullPane = new BorderPane();
    GridPane board = new GridPane();

    Scene scene = new Scene(fullPane);
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller.setScoreboard(scoreboard);
        fullPane.setTop(scoreboard);
        fullPane.setCenter(board);
        fullPane.setPadding(new Insets(25,25,25,25));

        setBoard(controller.cells);
        /*Thread timeThread = new Thread (new goTimer ());
        timeThread.start();
        timeThread.run();
        */

        scoreboard.start.setOnAction(event -> {board = new GridPane(); fullPane.setCenter(board); controller.newBoard(); setBoard(controller.cells);});

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                scoreboard.stopTimer();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    void setBoard(ArrayList<MineCell> cells)
    {
        int iterator = 0;
        Collections.shuffle(cells);
        for (int i = 0; i < WIDTH; ++i)
        {
            for (int j = 0; j < HEIGHT; ++j)
            {
                cells.get(iterator).setCoordinate(i,j);
                board.add(cells.get(iterator++),i,j);
            }
        }
    }





}