package cs2410.assn3.Command;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import cs2410.assn3.Main.Main;

/**
 * Created by Kyle on 9/20/2016.
 */
public class CommandDirectory {
    //scanner... for scanning? Idk!! I use it everywhere...
    public static Scanner scan;

    /**
     * prints the menu on the console.
     */
    public void printMenu()
    {
        System.out.println();
        System.out.println("What would you like to do:");
        System.out.println("\t1. List directory contents");
        System.out.println("\t2. Add student to directory");
        System.out.println("\t3. Display average age of students");
        System.out.println("\t4. Quit");
        System.out.println();
        System.out.println();
    }

    /**
     * Lists the directory on the console.
     */
    public void listDirectory()
    {
        try {
            scan = new Scanner(new FileReader("Data/cs2410-directory.data"));
        }
        catch (FileNotFoundException e)
        {

        }
        while (scan.hasNext())
        {
            String firstName = scan.next();
            String lastName = scan.next();
            String age = scan.next();
            String number = scan.next();

            System.out.printf("%-12s %-12s %4s %15s", firstName, lastName, age, number);
            System.out.println();
        }
    }

    /**
     * takes 4 inputs, and sets them to first name, last name, age, and number
     * Sends to Main.addStudent to print to file
     */
    public void makeStudent()
    {
        System.out.print("First Name: ");
        String firstName = getString();
        System.out.print("Last Name: ");
        String lastName = getString();
        System.out.print("Age: ");
        String age = getString();
        System.out.print("Phone Number: ");
        String number = getString();

        Main.addStudent(firstName,lastName,age,number);
        System.out.println("The following student has been added:");
        System.out.println(firstName + " " + lastName + ", age: " + age + ", phone: " + number);
    }

    /**
     * sends to Main.averageAge to get average age. Prints to console
     */
    public void printAverageAge()
    {
        System.out.println(String.format("%s %.2f", "The average age of students is: ", Main.averageAge() ));
    }

    /*
     * Generic get string to be used for input.
     * @return string typed.
     */
    public String getString()
    {
        scan = new Scanner(System.in);
        return scan.next();
    }

    /**
     * takes input and does the work directing work based on the Menu
     */
    public CommandDirectory()
    {
        this.printMenu();
        scan = new Scanner(System.in);
        String input = scan.next();
        scan.nextLine();
        while (true) {
            if (input.equals("1")) {
                this.listDirectory();
                this.printMenu();
                input = getString();
            } else if (input.equals("2")) {
                this.makeStudent();
                this.printMenu();
                input = getString();
            } else if (input.equals("3")) {
                this.printAverageAge();
                this.printMenu();
                input = getString();
            } else if (input.equals("4")){
                System.exit(0);
            } else
            {
                System.out.println("Invalid input.");
                this.printMenu();
                input = getString();
            }
        }
    }

}
