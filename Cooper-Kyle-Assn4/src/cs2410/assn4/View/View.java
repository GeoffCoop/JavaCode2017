package cs2410.assn4.View;
/**
 * Created by Kyle on 10/5/2016.
 */
import cs2410.assn4.Manager.Manager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class View extends Application{

    private ImageView view = new ImageView();
    private Button next, prev, add, del;
    //for buttons etc
    private EventHandler<ActionEvent> handler;
    //because I didn't know how else to do it.
    private static Manager manager = new Manager();
    private Pane pane = new Pane();
    private Scene scene = new Scene(pane, 500, 650);
    @Override
    /**
     * Do I need to JavaDoc start?
     */
    public void start(Stage primaryStage)
    {
        initializeHandler();

        next = new Button("Next >> ");
        next.setOnAction(handler);
        next.setPrefWidth(100);
        next.setLayoutX(380);
        next.setLayoutY(600);

        prev = new Button("<< Previous");
        prev.setOnAction(handler);
        prev.setPrefWidth(100);
        prev.setLayoutX(20);
        prev.setLayoutY(600);

        add = new Button("Add");
        add.setOnAction(handler);
        add.setPrefWidth(100);
        add.setLayoutX(140);
        add.setLayoutY(600);

        del = new Button("Delete");
        del.setOnAction(handler);
        del.setPrefWidth(100);
        del.setLayoutX(260);
        del.setLayoutY(600);

        pane.getChildren().add(next);
        pane.getChildren().add(prev);
        pane.getChildren().add(add);
        pane.getChildren().add(del);

        view.setLayoutY(25);
        view.setLayoutX(50);

        primaryStage.setScene(scene);


        //Image goes in here
        Image image = new Image(manager.imageList.get(0),400,0,true,false);
        view.setImage(image);

        pane.getChildren().add(view);

        primaryStage.show();
        //for closing
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override public void handle(WindowEvent e)
            {
                try{
                    PrintWriter write = new PrintWriter(new FileOutputStream("data/images.data"));
                    for (int i = 0; i < manager.imageList.size(); i++)
                    {
                        write.println(manager.imageList.get(i));
                    }
                    write.close();
                }
                catch (FileNotFoundException f){}
                manager.quit();
            }
        });
    }
    //to handle buttons
    private void initializeHandler()
    {
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(event.getSource() == next)
                {
                    view.setImage(manager.nextImage());
                }
                else if (event.getSource() == prev)
                {
                    view.setImage(manager.prevImage());
                }
                else if (event.getSource() == add)
                {
                    TextInputDialog add = new TextInputDialog();
                    add.setTitle("New Image");
                    add.setContentText("Enter the url of the image you would like to add.");
                    manager.addImage(add.showAndWait().get());
                    view.setImage(new Image (manager.imageList.get(manager.iterator),400,0,true,false));
                    if (manager.imageList.size() > 0) {
                        del.setDisable(false);
                        next.setDisable(false);
                        prev.setDisable(false);
                    }
                }
                else if (event.getSource() == del)
                {
                    manager.delImage(manager.imageList.get(manager.iterator));
                    if (manager.imageList.size() < 1)
                    {
                        view.setImage(new Image ("http://www.birthdaydirect.com/images/no_image.jpg", 400,0,true,false));
                        del.setDisable(true);
                        next.setDisable(true);
                        prev.setDisable(true);
                    }
                    else view.setImage(manager.nextImage());
                }
            }

        };

    }
}
