package view;

import control.Control;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.Callable;

/**
 * Created by User on 11/14/2016.
 */
public class ViewClass extends Application{
    Toolbar toolbar = new Toolbar();

    BorderPane fullPane = new BorderPane();
    //label
    Label displayLabel = new Label();
    BorderPane addPane = new BorderPane();
    GridPane gridPane = new GridPane();
    HBox buttonPane = new HBox();
    //button
    Button cancel = new Button("Cancel");
    Button okay = new Button("Okay");

    Scene scene = new Scene(fullPane);
    Label nameLabel = new Label("Name: ");
    TextField nameField = new TextField();
    Label mathLabel = new Label("Math: ");
    TextField mathField = new TextField();
    Label sayLabel = new Label("Saying: ");
    TextField sayField = new TextField();
    Label incLabel = new Label("Income: ");
    TextField incField = new TextField();
    Label carLabel = new Label("Carrots: ");
    TextField carField = new TextField();
    Label hWorkLabel = new Label("Hours Worked");
    TextField hWorkField = new TextField();
    Label contLabel = new Label("Contracts Completed: ");
    TextField contField = new TextField();
    Label iqLabel = new Label("IQ: ");
    TextField iqField = new TextField();

    //name, math, say, IQ, income, carrots, hours worked, contracts completed


    @Override
    public void start(Stage primaryStage) throws Exception {
        Control control = new Control();
        toolbar.display.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toolbar.createButtons.selectToggle(null);
                if(toolbar.display.getValue().equals("Math")) displayLabel.setText(control.getMathList(control.list));
                else if(toolbar.display.getValue().equals("Income"))displayLabel.setText(control.getIncomeList(control.list));
                else if(toolbar.display.getValue().equals("Hours Worked")) displayLabel.setText(control.getHoursList(control.list));
                else if(toolbar.display.getValue().equals("IQ")) displayLabel.setText(control.getIQList(control.list));
                else if(toolbar.display.getValue().equals("Saying")) displayLabel.setText(control.getSayList(control.list));
                else if(toolbar.display.getValue().equals("Carrots Picked")) displayLabel.setText(control.getCarrotsList(control.list));
                else if(toolbar.display.getValue().equals("Contracts Completed")) displayLabel.setText(control.getContractsList(control.list));
                fullPane.setCenter(displayLabel);
            }
                // displayLabel.setText(control.getIncomeList(control.list));

        });

        okay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (toolbar.newHobbit.isSelected())
                {
                    control.createNewHobbit(
                            nameField.getText(),
                            mathField.getText(),
                            sayField.getText(),
                            carField.getText()
                    );
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hobbit Created");
                    alert.setHeaderText("Completed");
                    alert.setContentText(nameField.getText() +" the Hobbit was created.");
                    alert.showAndWait();
                    CTF();
                }
                else if (toolbar.newHW.isSelected())
                {
                    control.createNewHourlyWorker(
                            nameField.getText(),
                            mathField.getText(),
                            incField.getText(),
                            hWorkField.getText(),
                            iqField.getText(),
                            sayField.getText()
                    );
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hourly Worker Created");
                    alert.setHeaderText("Completed");
                    alert.setContentText(nameField.getText() +" the Hourly Worker was created.");
                    alert.showAndWait();
                    CTF();
                }
                else if (toolbar.newCW.isSelected())
                {//name, String math, String income, String pIQ, String say, String contracts
                    control.createNewContractWorker(
                            nameField.getText(),
                            mathField.getText(),
                            incField.getText(),
                            iqField.getText(),
                            sayField.getText(),
                            contField.getText()
                    );
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Contract Worker Created");
                    alert.setHeaderText("Completed");
                    alert.setContentText(nameField.getText() +" the Contract Worker was created.");
                    alert.showAndWait();
                    CTF();
                }
            }
        });

        cancel.setOnAction(e-> CTF());

        buttonPane.getChildren().addAll(okay,cancel);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
        buttonPane.setSpacing(10);
        buttonPane.setPadding(new Insets(5,5,5,5));

        gridPane.setAlignment(Pos.CENTER);

        addPane.setBottom(buttonPane);

        toolbar.newCW.setOnAction(e->setAddContractWorker());
        toolbar.newHW.setOnAction(e->setAddHourlyWorker());
        toolbar.newHobbit.setOnAction(e->setAddHobbit());

        fullPane.topProperty().set(toolbar);
        fullPane.centerProperty().set(displayLabel);
        fullPane.setMinHeight(250);
        fullPane.setMinWidth(250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //Display Area - prints out messages

    //Add Area - lets user create new object
    private void setAddHobbit()
    {
        gridPane.getChildren().clear();
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameField,1,0);
        gridPane.add(mathLabel,0,1);
        gridPane.add(mathField,1,1);
        gridPane.add(sayLabel,0,2);
        gridPane.add(sayField,1,2);
        gridPane.add(carLabel,0,3);
        gridPane.add(carField,1,3);
        addPane.setCenter(gridPane);
        fullPane.setCenter(addPane);
        for(Node l : gridPane.getChildren())
        {
            if (l instanceof Label) ((Label) l).setAlignment(Pos.BOTTOM_RIGHT);
        }
    }
    private void setAddContractWorker()
    {
        gridPane.getChildren().clear();
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameField,1,0);
        gridPane.add(mathLabel,0,1);
        gridPane.add(mathField,1,1);
        gridPane.add(sayLabel,0,2);
        gridPane.add(sayField,1,2);
        gridPane.add(incLabel,0,3);
        gridPane.add(incField,1,3);
        gridPane.add(contLabel,0,4);
        gridPane.add(contField,1,4);
        gridPane.add(iqLabel,0,5);
        gridPane.add(iqField,1,5);
        addPane.setCenter(gridPane);
        fullPane.setCenter(addPane);
    }
    private void setAddHourlyWorker()
    {
        gridPane.getChildren().clear();
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameField,1,0);
        gridPane.add(mathLabel,0,1);
        gridPane.add(mathField,1,1);
        gridPane.add(sayLabel,0,2);
        gridPane.add(sayField,1,2);
        gridPane.add(incLabel,0,3);
        gridPane.add(incField,1,3);
        gridPane.add(hWorkLabel,0,4);
        gridPane.add(hWorkField,1,4);
        gridPane.add(iqLabel,0,5);
        gridPane.add(iqField,1,5);
        addPane.setCenter(gridPane);
        fullPane.setCenter(addPane);
    }
    private void CTF()
    {
        nameField.clear();
        mathField.clear();
        sayField.clear();
        incField.clear();
        contField.clear();
        carField.clear();
        hWorkField.clear();
        iqField.clear();
    }
}
