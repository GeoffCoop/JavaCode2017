package cs2410.assn3.Main;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import cs2410.assn3.gui.GUIDirectory;
import cs2410.assn3.Command.CommandDirectory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Kyle on 9/14/2016.
 */
public class Main {
    private static Scanner scan;
    private static PrintWriter output;

    /**
     * creates a student from four parameters by adding to data file
     * @param fN first name
     * @param lN last nmem
     * @param age age
     * @param Number phone number
     */
    public static void addStudent(String fN, String lN, String age, String Number)
    {
        try{
            output = new PrintWriter(new FileOutputStream("Data/cs2410-directory.data",true));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        output.println(fN + " " + lN + " " + age + " " + Number);
        output.close();

    }

    /**
     * creates a vector of ages, turns to strings to ints, divides by size.
     * @return returns age
     */
    public static double averageAge()
    {
        try{
            scan = new Scanner(new FileReader("Data/cs2410-directory.data"));
            Vector ages = new Vector();
            while(scan.hasNext())
            {
                scan.next();
                scan.next();
                ages.add(scan.next()); //make an int
                scan.next();
            }
            ages.toString();
            double sum = 0;
            for(Object i : ages)
            {
                sum += Integer.parseInt(i.toString());
            }
            sum /= ages.size();

            return sum;
        }
        catch(FileNotFoundException e)
        {
            return 0;
        }

    }

    /**
     * Does nothing. It was a mistake, but I rolled with it... m' bad... m' bad...
     */
    public Main() {

    }

    /**
     * main. You know... like MAIN main... That main?
     * @param args
     */
    public static void main(String [] args)
    {

        CommandDirectory go = new CommandDirectory();

    }
}