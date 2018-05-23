package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import javax.swing.*;

/**
 * Created by User on 11/16/2016.
 */
public class Toolbar extends HBox {
    ComboBox<String> display = new ComboBox<>();

    EventHandler<ActionEvent> handler;
    ToggleButton newCW = new ToggleButton("New Contract Worker");
    ToggleButton newHW = new ToggleButton("New Hourly Worker");
    ToggleButton newHobbit = new ToggleButton("New Hobbit");
    ToggleGroup createButtons = new ToggleGroup();

    public Toolbar ()
    {
        display.getItems().addAll("Math", "Income", "Hours Worked", "IQ", "Saying", "Carrots Picked", "Contracts Completed");
        display.setValue("Math");
        createButtons.getToggles().addAll(newCW, newHobbit, newHW);
        this.getChildren().addAll(display,newHW,newCW,newHobbit);
        this.setPadding(new Insets(5,5,5,5));
        this.setSpacing(5);

    }
}
