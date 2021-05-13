package librarymgt;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javax.swing.JOptionPane;

public class AddMovieRecord {

    /*
    * Features of GUI
     */
    static Stage window;
    static TextField CustomerID;
    static TextField Customer_Name;
    static TextField Phone_Number;
    static TextField MovieID;
    static TextField Movie_Director;
    static TextField Movie_Name;
    static TextField Release_Date;

    /*
    * Variable Declaration
     */
    static ComboBox<String> comboboxGenre;
    static int finalcusID;
    static int finalmovID;
    static int finalphoneNo;
    static int finalreleaseDa;
    static boolean allCorrectDataEntered = false;
    static boolean condition1, condition2, condition3, condition4;
    static boolean condition5 = true;

    /*
    * Default Constructor
     */
    public AddMovieRecord() {
    }

    /*
    * Main GUI for AddMovieRecord class
    * Window cannot be resized, with title and modality
     */
    public static void GUI(Movies[] moviesArray) {
        window = new Stage();
        window.setTitle("ADD RECORD");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        /*
         * GridPane Declaration
         */
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        Label custID = new Label("Enter Customer ID: ");
        GridPane.setConstraints(custID, 1, 2);

        CustomerID = new TextField();
        GridPane.setConstraints(CustomerID, 2, 2);

        /*
         * Location of Label custName on GridPane
         */
        Label custName = new Label("Enter Customer Name: ");
        GridPane.setConstraints(custName, 1, 3);

        /*
         * Location of TextField on GridPane
         */
        Customer_Name = new TextField();
        GridPane.setConstraints(Customer_Name, 2, 3);

        Label phoneNum = new Label("Enter Phone Number: ");
        GridPane.setConstraints(phoneNum, 1, 4);

        Phone_Number = new TextField();
        GridPane.setConstraints(Phone_Number, 2, 4);

        Label movieID = new Label("Enter Movie ID: ");
        GridPane.setConstraints(movieID, 1, 5);

        MovieID = new TextField();
        GridPane.setConstraints(MovieID, 2, 5);

        Label movieDirec = new Label("Enter Movie Director: ");
        GridPane.setConstraints(movieDirec, 1, 6);

        Movie_Director = new TextField();
        GridPane.setConstraints(Movie_Director, 2, 6);

        Label movieName = new Label("Enter Movie Name: ");
        GridPane.setConstraints(movieName, 1, 7);

        Movie_Name = new TextField();
        GridPane.setConstraints(Movie_Name, 2, 7);

        Label releaseDa = new Label("Enter Release Date: ");
        GridPane.setConstraints(releaseDa, 1, 8);

        Release_Date = new TextField();
        GridPane.setConstraints(Release_Date, 2, 8);

        Label genre = new Label(" Select Genre: ");
        GridPane.setConstraints(genre, 4, 2);

        /*
         * Creates ComboBox for User Selection
         */
        comboboxGenre = new ComboBox<>();
        comboboxGenre.getItems().addAll("Action", "Comedy", "Thriller", "Horror");
        comboboxGenre.setValue("Action");
        GridPane.setConstraints(comboboxGenre, 5, 2);

        /*
         * Back Button to Close GridPane
         */
        Button backAdd = new Button(" Back ");
        backAdd.setOnAction(e -> {
            window.close();
            allCorrectDataEntered = false;
        });

        /*
         * Creates a Submit button, and calls all appropriate validation checks
         */
        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> {

            validateCusID(CustomerID.getText(), moviesArray);
            validateMovID(MovieID.getText(), moviesArray);
            validatePhoneNo(Phone_Number.getText());
            validateReleaseDa(Release_Date.getText());
            textfieldvalidation();

            if (condition1 == true && condition2 == true && condition3 == true && condition4 == true && condition5 == true) {
                window.close();
            }

        });

        /*
         * Creates HBox and state location of buttons
         */
        HBox buttons = new HBox(5, submit, backAdd);
        GridPane.setConstraints(buttons, 4, 9);

        pane.getChildren().addAll(custID, CustomerID, custName, Customer_Name, phoneNum, Phone_Number, movieID, MovieID, movieDirec, Movie_Director, movieName, Movie_Name, releaseDa, Release_Date, genre, comboboxGenre, buttons);

        window.getIcons().add(new Image("file:images/icon.jpg"));
        Scene s1 = new Scene(pane, 1100, 550);
        window.setScene(s1);
        s1.getStylesheets().add("file:stylesheet.css");
        window.showAndWait();

    }

    /*
         * CustomerID Validation
         * If duplicate ID have been entered, error occur
         * If ID less than 0, then Invalid ID
     */
    private static void validateCusID(String a, Movies[] moviesArray) {

        try {
            finalcusID = Integer.parseInt(a);
            condition1 = true;
            for (int i = 0; i < moviesArray.length; i++) {
                if (moviesArray[i].getCustomerID() == finalcusID) {
                    JOptionPane.showMessageDialog(null, "Customer ID already exists!!!");
                    CustomerID.clear();
                    condition1 = false;
                    break;
                }
            }

            if (finalcusID <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid Customer ID!!!");
                CustomerID.clear();
                condition1 = false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Invalid Customer ID ");
            CustomerID.clear();
            condition1 = false;
        }
    }

    /*
         * PhoneNo validation
         * If PhoneNo less than 0, error occur
     */
    private static void validatePhoneNo(String a) {
        condition2 = true;
        try {
            finalphoneNo = Integer.parseInt(a);

            if (finalphoneNo <= 0) {

                JOptionPane.showMessageDialog(null, " Invalid Phone Number ");
                Phone_Number.clear();
                condition2 = false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Invalid Phone Number ");
            Phone_Number.clear();
            condition2 = false;
        }

    }

    /*
         * MovieID Validation
         * If MovieID less than 0, error occur
     */
    private static void validateMovID(String a, Movies[] moviesArray) {
        condition3 = true;
        try {
            finalmovID = Integer.parseInt(a);

            if (finalmovID <= 0) {
                JOptionPane.showMessageDialog(null, " Invalid Movie ID ");
                MovieID.clear();
                condition3 = false;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Invalid Movie ID ");
            MovieID.clear();
            condition3 = false;

        }

    }

    /*
         * ReleaseDate Validation
         * If ReleaseDate less than 0, error occur
     */
    private static void validateReleaseDa(String a) {

        try {
            condition4 = true;
            finalreleaseDa = Integer.parseInt(a);

            if (finalreleaseDa <= 0) {

                JOptionPane.showMessageDialog(null, "Invalid Release Date!!!");
                Release_Date.clear();
                condition4 = false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Invalid Release Date ");
            Release_Date.clear();
            condition4 = false;
        }

    }

    /*
         * TextFields cannot be blank
         * If Customer Name contains invalid characters, error occur, and TextField is cleared
     */
    public static void textfieldvalidation() {
        if (Customer_Name.getText().trim().isEmpty()) {  // validation to ensure that textfield is not empty
            JOptionPane.showMessageDialog(null, " Customer Name field cannot be blank ");
            condition5 = false;
        } else if (!Customer_Name.getText().matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(null, " Invalid Customer Name");  //ensuring correct data being entered
            Customer_Name.clear();  //textfield cleared if invalid data entered
            condition5 = false;
        }
        if (Movie_Director.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, " Movie Director field cannot be blank ");
            condition5 = false;

        } else if (!Movie_Director.getText().matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(null, " Invalid Director Name");
            Movie_Director.clear();
            condition5 = false;

        }
        if (Movie_Name.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, " Movie Name field cannot be blank ");
            condition5 = false;
        } else if (!Movie_Name.getText().matches("^[a-zA-Z0-9]+$")) {

            JOptionPane.showMessageDialog(null, " Invalid Movie Name ");
            Movie_Name.clear();
            condition5 = false;
        }

    }

    /*
         * Getters
     */
    public static int returncusID() {
        return finalcusID;
    }

    public static String returncusName() {
        return Customer_Name.getText();
    }

    public static int returnphoneNo() {
        return finalphoneNo;
    }

    public static int returnmovID() {
        return finalmovID;
    }

    public static String returnmovDirec() {
        return Movie_Director.getText();
    }

    public static String returnmovName() {
        return Movie_Name.getText();
    }

    public static int returnreleaseDa() {
        return finalreleaseDa;
    }

    public static String returnGenre() {
        return comboboxGenre.getValue();
    }

    /*
         * Validation Checks
     */
    public static boolean beginToAdd() {

        if (condition1 == true && condition2 == true && condition3 == true && condition4 == true && condition5 == true) {

            allCorrectDataEntered = true;

        }
        return allCorrectDataEntered;
    }

    /*
         * Display Method
     */
    public static void display() {

        System.out.println();

    }
}
