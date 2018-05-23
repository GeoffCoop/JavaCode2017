package cs2410.view;

import cs2410.control.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by User on 11/30/2016.
 */
public class MineCell extends Button{
    public int coordinate = 0;
    public boolean bomb = false;
    static enum States {READY, FLAGGED, UNKNOWN};
    public static boolean lost = false;
    States state = States.READY;
    private boolean clicked = false;
    ArrayList<MineCell> myGroup;
    Controller myController;
    ImageView stateImage = new ImageView();
    public MineCell(boolean isBomb, ArrayList<MineCell> myGroupP, Controller controller)
    {
        //this.textProperty().bind(new SimpleIntegerProperty(coordinate).asString());
        this.getStylesheets().addAll("css/button.css");
        this.setPrefSize(25,25);

        myController = controller;
        //this.setPrefSize(100,25);
        bomb = isBomb;
        if(isBomb)
        {
            this.setId("bomb");
        }
        else
        {
            this.setId("clear");
        }
        myGroup = myGroupP;

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (controller.start == false) {myController.scoreboard.startTimer();}
                controller.start = true;
                if (event.getButton() == MouseButton.SECONDARY)
                {
                    if (state == States.READY)
                    {
                        state = States.FLAGGED;
                        stateImage.setImage(controller.flag);
                        stateImage.setFitHeight(heightProperty().get()-10);
                        stateImage.setFitWidth(widthProperty().get()-10
                        );
                        setGraphic(stateImage);
                        controller.scoreboard.bombs -= 1;
                    }
                    else if (state == States.FLAGGED)
                    {
                        state = States.UNKNOWN;
                        stateImage.setImage(controller.unknown);
                        setGraphic(stateImage);
                    }
                    else if (state == States.UNKNOWN) {
                        state = States.READY;
                        controller.scoreboard.bombs +=1;
                        setGraphic(null);
                    }
                }
                else if (event.getButton() == MouseButton.PRIMARY )
                {
                    if(state == States.READY) {
                        int result = mineClick(myGroupP);
                        if (result > 0) textProperty().set(String.valueOf(result));
                        else if (result == -1) {
                            lose();
                        } //you lose{}
                        else if (result == 0) {
                            zeroClick(myGroupP);
                        } // click all others nearby
                    }

                }
            }
        });
    }
    public MineCell(ArrayList<MineCell> myGroupP, Controller controller)
    {
        this(false, myGroupP, controller);
    }

    public void setCoordinate(int i, int j)
    {
        coordinate = 100 * i + j;
    }

    private int mineClick(ArrayList<MineCell> myMines)
    {
        checkWin();
        clicked = true;
        this.setDisable(true);
        if (this.bomb) return -1;
        else {
                int ret = 0;
            for (MineCell mine: myMines){
                if (mine.coordinate == coordinate - 101)
                {if (mine.bomb) ++ret;}
                else if (mine.coordinate == coordinate - 1)
                    {if (mine.bomb) ++ret;}
                    else if (mine.coordinate == coordinate + 99)
                {if (mine.bomb) ++ret;}
                else if (mine.coordinate == coordinate + 100)
                {if (mine.bomb) ++ret;}
                else if (mine.coordinate == coordinate + 101)
                {if (mine.bomb) ++ret;}
                    else if (mine.coordinate == coordinate + 1)
                {if (mine.bomb) ++ret;}
                    else if (mine.coordinate == coordinate - 99)
                {if (mine.bomb) ++ret;}
                    else if (mine.coordinate == coordinate - 100)
                {if (mine.bomb) ++ret;}
            }
            return ret;
            //tr t tl l bl b br r
        }
    }
    private void zeroClick(ArrayList<MineCell>  myMines)
    {
        for (MineCell mine: myMines) {
            if (mine.coordinate == coordinate - 101) {
                mine.fireEvent(new MouseEvent (MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
            } else if (mine.coordinate == coordinate - 1) {
                mine.activate();
            } else if (mine.coordinate == coordinate + 99) {
                mine.activate();
            } else if (mine.coordinate == coordinate + 100) {
                mine.activate();
            } else if (mine.coordinate == coordinate + 101) {
                mine.activate();
            } else if (mine.coordinate == coordinate + 1) {
                mine.activate();
            } else if (mine.coordinate == coordinate - 99) {
                mine.activate();
            } else if (mine.coordinate == coordinate - 100) {
                mine.activate();            }
        }
    }

    private void activate ()
    {
        if (!this.clicked)this.fireEvent(new MouseEvent (MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));

    }

    private void lose()
    {
        for(MineCell cell: myGroup)
        {
            cell.activate();
        }
        if (!lost) {
            lost = true;

            myController.scoreboard.stopTimer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You Lose!!!");
            alert.setHeaderText("You have died.");
            alert.setContentText("It took you " + myController.scoreboard.time + " seconds to lose.");
            alert.showAndWait();
        }
    }
    private void checkWin()
    {
        boolean victory = true;
        if(!lost)
        {
            for (MineCell mine : myGroup)
            {
                if (mine.bomb && state.equals(States.FLAGGED)) {
                    if (!mine.bomb && state.equals(States.READY)) {}
                    else { victory = false; break;}
                }
                else {victory = false; break;}
            }
            if (victory) win();
        }
    }

    private void win()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOU WON");
        alert.setHeaderText("You have successfully found all the mines!");
        alert.setContentText("Congradulations. You have done what few have the patience for. You deserve to be commended.");
        alert.showAndWait();
    }
}
