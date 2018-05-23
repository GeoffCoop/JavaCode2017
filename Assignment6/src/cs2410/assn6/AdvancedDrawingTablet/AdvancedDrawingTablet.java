package cs2410.assn6.AdvancedDrawingTablet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


import static java.lang.Math.abs;

/**
 * Created by User on 11/1/2016.
 */
public class AdvancedDrawingTablet extends Application {

    private Pane fullPane = new Pane();
    private HBox toolbar = new HBox();
    private Pane drawPane = new Pane();
    private Scene scene = new Scene(fullPane, 600, 700);

    double x = 0;
    double y = 0;

    private ToggleGroup buttons = new ToggleGroup();
    private ToggleButton edit = new ToggleButton("Edit");
    private ToggleButton erase = new ToggleButton("Erase");
    private ToggleButton ellipse = new ToggleButton("Ellipse");
    private ToggleButton rectangle = new ToggleButton ("Rectangle");
    private ToggleButton freehand = new ToggleButton ("Freehand");

    private ComboBox<Integer> strokeWidth = new ComboBox(FXCollections.observableArrayList(1,3,5,7));
    private ColorPicker strokeColor = new ColorPicker();
    private ColorPicker fillColor = new ColorPicker();

    Shape selected;

    private EventHandler<ActionEvent> handler;

    @Override
    public void start (Stage primaryStage) throws Exception{

        buttonHandler();
        fullPane.prefHeightProperty().bind(scene.heightProperty());
        fullPane.prefWidthProperty().bind(scene.widthProperty());
        drawPane.prefHeightProperty().bind(fullPane.heightProperty());
        drawPane.prefWidthProperty().bind(fullPane.widthProperty());

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(drawPane.widthProperty());
        clip.heightProperty().bind(drawPane.heightProperty());

        drawPane.setClip(clip);
        buttons.getToggles().addAll(edit, erase, ellipse, rectangle, freehand);

        fillColor.setStyle("-fx-color-label-visible: false");
        fillColor.setValue(Color.BLACK);

        strokeColor.setStyle("-fx-color-label-visible: false");
        strokeWidth.setValue(1);

        Label fillLabel = new Label("Fill:");
        Label strokeLabel = new Label("Stroke:");

        toolbar.setLayoutX(0);
        toolbar.setLayoutY(0);
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(5,0,5,0));
        toolbar.getChildren().addAll(fillLabel,fillColor,strokeLabel,strokeColor,strokeWidth,edit,erase,ellipse, rectangle,freehand);

        drawPane.setLayoutY(25);

        fullPane.getChildren().add(toolbar);
        fullPane.getChildren().add(drawPane);

        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getX();
                y = event.getY();
                if(ellipse.isSelected()) {
                    Ellipse e = new Ellipse(x, y, 1, 1);
                    e.setStrokeWidth(strokeWidth.getValue());
                    e.setStroke(strokeColor.getValue());
                    e.setFill(fillColor.getValue());
                    drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (ellipse.isSelected()) {
                                initEllActionHandler(e);
                                e.setCenterY((event.getY() + y)/2 );
                                e.setCenterX((event.getX() + x)/2 );
                                e.setRadiusX(abs(event.getX() - x)/2);
                                e.setRadiusY(abs(event.getY() - y)/2);
                            }
                        }
                    });
                    drawPane.getChildren().add(e);
                }
                if (rectangle.isSelected()){
                    Rectangle r = new Rectangle(x,y,1,1);
                    r.setStrokeWidth(strokeWidth.getValue());
                    r.setStroke(strokeColor.getValue());
                    r.setFill(fillColor.getValue());

                    drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (rectangle.isSelected()){
                                initRectActionHandler(r);
                                if(x<event.getX()){
                                    r.setWidth(event.getX()-x);
                                }
                                else{
                                    r.setX(event.getX());
                                    r.setWidth(x-event.getX());
                                }
                                if (y<event.getY()){
                                    r.setHeight(event.getY()-y);
                                }
                                else {
                                    r.setY(event.getY());
                                    r.setHeight(y - event.getY());
                                }
                            }
                        }
                    });
                    drawPane.getChildren().add(r);
                }
                if (freehand.isSelected())
                {
                    Path p = new Path();
                    p.setStroke(strokeColor.getValue());
                    p.setStrokeWidth(strokeWidth.getValue());
                    p.getElements().addAll(new MoveTo(event.getX(),event.getY()));

                    drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(freehand.isSelected()) {
                                initFHActionHandler(p);
                                p.getElements().addAll(new LineTo(event.getX(), event.getY()));
                            }
                        }
                    });
                    drawPane.getChildren().add(p);
                }
            }
        });
        fillColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!selected.equals(null))selected.setFill(fillColor.getValue());
            }
        });
        strokeColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!selected.equals(null)) selected.setStroke(strokeColor.getValue());
            }
        });
        strokeWidth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!selected.equals(null))selected.setStrokeWidth(strokeWidth.getValue());
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initEllActionHandler (Ellipse e)
    {
        e.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(erase.isSelected()) drawPane.getChildren().remove(e);
                if (edit.isSelected()) {
                    selected = e;
                    fillColor.setValue((Color)e.getFill());
                    strokeColor.setValue((Color)e.getStroke());
                    strokeWidth.setValue((int) e.getStrokeWidth());
                }
                x = event.getX();
                y=event.getY();
                e.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        e.setTranslateX(e.getTranslateX() + event.getX() - x);
                        e.setTranslateY(e.getTranslateY() + event.getY() - y);
                    }
                });
            }
        });

    }
    private void initRectActionHandler (Rectangle e)
    {
        e.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(erase.isSelected()) drawPane.getChildren().remove(e);
                if (edit.isSelected()){
                    selected=e;
                    fillColor.setValue((Color)e.getFill());
                    strokeColor.setValue((Color)e.getStroke());
                    strokeWidth.setValue((int) e.getStrokeWidth());
                }
                x = event.getX();
                y=event.getY();
                e.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        e.setTranslateX(e.getTranslateX() + event.getX() - x);
                        e.setTranslateY(e.getTranslateY() + event.getY() - y);
                    }
                });
            }
        });
    }
    private void initFHActionHandler (Path p)
    {
        p.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (erase.isSelected()) drawPane.getChildren().remove(p);
                if (edit.isSelected()) {
                    selected=p;
                    strokeWidth.setValue((int)p.getStrokeWidth());
                    strokeColor.setValue((Color)p.getStroke());
                }
                x = event.getX();
                y=event.getY();
                p.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        p.setTranslateX(p.getTranslateX() + event.getX() - x);
                        p.setTranslateY(p.getTranslateY() + event.getY() - y);
                    }
                });
            }
        });
    }

    private void buttonHandler ()
    {
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == edit)
                {
                    //selected = new Rectangle();
                }
                if (event.getSource() == strokeColor) selected.setStroke(strokeColor.getValue());
            }
        };
    }
}
