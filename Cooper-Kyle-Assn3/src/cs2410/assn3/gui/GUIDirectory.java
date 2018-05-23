package cs2410.assn3.gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import cs2410.assn3.Main.Main;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * Created by Kyle on 9/20/2016.
 */
public class GUIDirectory extends Application{
    //le scanner
    private Scanner scan = new Scanner (System.in);

    /**
     * Pulls up menu choice dialog
     */
    public void menu()
    {
        ArrayList<String> options = new ArrayList();
        options.add("List Directory");
        options.add("Add Student");
        options.add("Show Average Age");
        options.add("Exit");

        ChoiceDialog main = new ChoiceDialog("List Directory", options);
        main.setTitle("Super Lame GUI Directory");
        main.setContentText("Choose an action:");
        Optional<String> director = main.showAndWait();
        System.out.print(director.get());
        if(director.get().equals("List Directory"))
        {
            listDirectory();
        }
        else if (director.get().equals("Add Student"))
        {
            makeStudent();
        }
        else if (director.get().equals("Show Average Age"))
        {
            double age = Main.averageAge();
            Alert ageA = new Alert(Alert.AlertType.INFORMATION);
            ageA.setTitle("Average Age");
            String text = new String("The average age of students is: ");
            ageA.setContentText(String.format("%s %.2f",text,age ));
            ageA.showAndWait();
        }
        else { System.exit(0); }
    }

    /**
     * enters a new student to .data file
     */
    private void makeStudent()
    {
        TextInputDialog firstName = new TextInputDialog();
        firstName.setTitle("");
        firstName.setContentText("First Name: ");
        String first = firstName.showAndWait().get();

        TextInputDialog lastName = new TextInputDialog();
        lastName.setTitle("");
        lastName.setContentText("Last Name: ");
        String last = lastName.showAndWait().get();

        TextInputDialog ageD = new TextInputDialog();
        ageD.setTitle("");
        ageD.setContentText("Age: ");
        String age = ageD.showAndWait().get();

        TextInputDialog numberD = new TextInputDialog();
        numberD.setTitle("");
        numberD.setContentText("Number: ");
        String number = numberD.showAndWait().get();

        Main.addStudent(first, last, age, number);
        Alert conf = new Alert(Alert.AlertType.INFORMATION);
        conf.setContentText(first + " " + last + " has been added to the directory.");
        conf.showAndWait();
    }

    /**
     * start
     * @param primaryStage
     */
    public void start(Stage primaryStage)
    {
        while(true)
        {
            menu();
        }
    }

    /**
     * Lists the contents of .data file in formatted list
     */
    private void listDirectory()
    {
        Alert directory = new Alert(Alert.AlertType.INFORMATION);
        directory.getDialogPane().getStylesheets().add("resources/monospace.css");
        directory.setTitle("Directory");
        directory.setHeaderText("List of Students");
        String content = new String();
        try{
            scan = new Scanner(new FileReader("Data/cs2410-directory.data"));
        }
        catch(FileNotFoundException e)
        {

        }
        while (scan.hasNext())
        {
            String firstName = scan.next();
            String lastName = scan.next();
            String age = scan.next();
            String number = scan.next();

            content += String.format("%-12s %-12s %4s %15s\n", firstName, lastName, age, number);
        }
        directory.setContentText(content);
        Optional<ButtonType> ok = directory.showAndWait();
    }



}


