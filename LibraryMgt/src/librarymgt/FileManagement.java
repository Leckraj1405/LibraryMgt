package librarymgt;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class FileManagement {

    private static Scanner a;

    /*
     * default constructor
     */
    public FileManagement() {
    }

    /*
     * To read data from file Movies.txt If file not found, error message displayed
     */
    public static void openDoc() {
        try {
            a = new Scanner(new java.io.File("Movies.txt"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
    }

    /*
     * reading from the file
     */
    public static void readDoc(Movies[] moviesArray) {
        int customerID;
        String customerName;
        int phoneNumber;
        int movieID;
        String movieDirector;
        String genre;
        String movieName;
        int releaseDate;

        /*
         * Loops untils there is data to read Read data and converts them into neccesary
         * type
         */
        int index = 0;
        while (a.hasNext()) {
            customerID = Integer.parseInt(a.next());
            customerName = a.next();
            phoneNumber = Integer.parseInt(a.next());
            movieID = Integer.parseInt(a.next());
            movieDirector = a.next();
            genre = a.next();
            movieName = a.next();
            releaseDate = Integer.parseInt(a.next());

            moviesArray[index] = new Movies(customerID, customerName, phoneNumber, movieID, movieDirector, genre,
                    movieName, releaseDate);
            index++;
        }

    }

    /*
     * Writing to the file
     */
    public static void writeDoc(Movies[] moviesArray) throws IOException {

        File theFile = new File("Movies.txt");
        PrintStream myWriter = new PrintStream(theFile);

        for (int i = 0; i < moviesArray.length; i++) {
            if (!(moviesArray[i].getCustomerID() == 0)) {
                myWriter.print(moviesArray[i].getCustomerID());
                myWriter.print(" ");
                myWriter.print(moviesArray[i].getCustomerName());
                myWriter.print(" ");
                myWriter.print(moviesArray[i].getPhoneNumber());
                myWriter.print(" ");
                myWriter.print(moviesArray[i].getMovieID());
                myWriter.print(" ");
                myWriter.print(moviesArray[i].getMovieDirector());
                myWriter.print(" ");
                myWriter.print(moviesArray[i].getGenre());
                myWriter.print(" ");
                myWriter.print(moviesArray[i].getMovieName());
                myWriter.print(" ");
                myWriter.println(moviesArray[i].getReleaseDate());
            }
        }
        myWriter.close();
    }

    /*
     * Close Method
     */
    public static void closeDoc() {
        a.close();
    }

}
