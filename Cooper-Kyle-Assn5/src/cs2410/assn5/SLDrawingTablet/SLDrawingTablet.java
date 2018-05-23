package cs2410.assn5.SLDrawingTablet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static java.lang.Math.abs;

/**
 * Created by User on 10/20/2016.
 */
public class SLDrawingTablet extends Application {

    private Pane fullPane = new Pane();
    private Pane toolbar = new Pane ();//100 x 500
    private Pane drawpane = new Pane(); // 500 x 500
    private Scene scene = new Scene(fullPane, 500, 600);
    private Button draw, erase;
    private double x, y;
    boolean drawSwitch = true;
    //for buttons
    private EventHandler<ActionEvent> handler;

    @Override
    public void start(Stage primaryStage) throws Exception{
        buttonHandler();

        //full pane and clip in the draw portion
        fullPane.setPrefSize(500,600);
        Rectangle clip = new Rectangle(0,0,500,500);
        drawpane.setClip(clip);

        //toolbar settings
        toolbar.setPrefSize(500,100);
        toolbar.setLayoutX(0);
        toolbar.setLayoutY(0);
            //draw button settings
            draw = new Button("Draw");
            draw.setPrefSize(100,80);
            draw.setLayoutX(100);
            draw.setLayoutY(10);
            draw.setOnAction(handler);
            toolbar.getChildren().add(draw);
            //erase button settings
            erase = new Button("Erase");
            erase.setPrefSize(100,80);
            erase.setLayoutY(10);
            erase.setLayoutX(300);
            erase.setOnAction(handler);
            toolbar.getChildren().add(erase);
        //drawpane stuffs
        drawpane.setPrefSize(500,500);
        drawpane.setLayoutX(0);
        drawpane.setLayoutY(100);
        //add to full pane
        fullPane.getChildren().add(toolbar);
        fullPane.getChildren().add(drawpane);

        /**
         * Nested event handlers for drawing ellipses
         */
        drawpane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getX();
                y = event.getY();
                if(drawSwitch) {
                    Ellipse e = new Ellipse(x, y, 1, 1);
                    e.setStroke(Color.BLACK);
                    e.setFill(Color.SEASHELL);
                    drawpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (drawSwitch) {
                                initEllActionHandler(e);
                                e.setCenterY((event.getY() + y)/2 );
                                e.setCenterX((event.getX() + x)/2 );
                                e.setRadiusX(abs(event.getX() - x)/2);
                                e.setRadiusY(abs(event.getY() - y)/2);
                            }
                        }
                    });
                    drawpane.getChildren().add(e);
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Erase handler for each ellipse
     * @param e
     */
    private void initEllActionHandler (Ellipse e)
    {
        e.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!drawSwitch) drawpane.getChildren().remove(e);
            }
        });
    }

    /**
     * button handler... Need I say more?
     */
    private void buttonHandler() {
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (e.getSource() == draw) drawSwitch = true;
                else if (e.getSource() == erase) drawSwitch = false;
            }
        };
    }
}
