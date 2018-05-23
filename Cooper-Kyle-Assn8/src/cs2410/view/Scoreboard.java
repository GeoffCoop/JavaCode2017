package cs2410.view;

import cs2410.control.Controller;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.util.Timer;
import java.util.TimerTask;

/**time
 * Created by User on 11/30/2016.
 */
public class Scoreboard extends BorderPane{
    public Button start = new Button("Start");
    public Label bombsLabel = new Label("Bombs Remaining: ");
    public Label timeLabel = new Label("Time: ");
    Label bombCount = new Label();
    Label timeCount = new Label();
    Timer timer = new Timer();

    public int bombs = 100;
    public Integer time = 0;


    VBox bombsPane = new VBox();
    VBox timerPane = new VBox();

    public Scoreboard(Controller controller)
    {
        bombCount.textProperty().bindBidirectional(new SimpleStringProperty(Integer.toString(bombs)));
        timeCount.textProperty().bind(new SimpleIntegerProperty(time).asString());

        //Bindings.bindBidirectional(timeCount.textProperty(), new SimpleIntegerProperty(time), new NumberStringConverter());

        bombsPane.getChildren().addAll(bombsLabel,bombCount);
        bombsPane.setPadding(new Insets(25,0,25,25));

        timerPane.getChildren().addAll(timeLabel,timeCount);
        timerPane.setPadding(new Insets(25,25,25,25));

        this.setCenter(start);
        this.setRight(timerPane);
        this.setLeft(bombsPane);
    }


    public void startTimer ()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time++;
            }
        },1000,1000);
    }
    public void stopTimer()
    {
        timer.cancel();
    }

}
