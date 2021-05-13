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

public class DeleteMovieRecord {

    
     /*
    * Features of GUI
     */
    static Stage window;
    static ComboBox<String> comboboxDelete;
    static Label message;
    
    /*
    * Variable Declaration
     */
    private static boolean allCorrectDataEntered = false;
    private static String finalMovieName;

    /*
    * default constructor
     */
    public DeleteMovieRecord() {
    }
    
    /*
    * Main GUI for DeleteMovieRecord class
    * Window cannot be resized, with title and modality
     */
    public static void GUI(Movies[] moviesArray) {
        window = new Stage();
        window.setTitle("DELETE RECORD");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        
         /*
         * GridPane Declaration
         */
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        
         /*
         * Location of Label movieID on GridPane
         */
        Label movieID = new Label(" Select Movie Name to Delete: ");
        GridPane.setConstraints(movieID, 1, 2);

        comboboxDelete = new ComboBox<>();
        comboboxDelete.setPromptText("Select Movie name");

        for (int i = 0; i < moviesArray.length; i++) {
            if (moviesArray[i].getMovieID() != 0) {
                comboboxDelete.getItems().add(moviesArray[i].getMovieName());
            }
        }

        GridPane.setConstraints(comboboxDelete, 5, 2);

        
         /*
         * Back Button to Close GridPane
         */
        Button backDel = new Button(" Back ");
        backDel.setOnAction(e -> {
            window.close();
        });

        
         /*
         * Creates a Submit button, and calls all appropriate validation checks
         */
        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> validation(comboboxDelete.getValue()));
        HBox buttons = new HBox(5, submit, backDel);
        pane.setConstraints(buttons, 1, 10);

        pane.getChildren().addAll(movieID, comboboxDelete, buttons);

        window.getIcons().add(new Image("file:images/icon.jpg"));
        Scene s1 = new Scene(pane, 800, 300);
        window.setScene(s1);
        s1.getStylesheets().add("file:stylesheet.css");
        window.showAndWait();
    }

    
        /*
         * If correct data is entered, current window closes
         */
    public static void validation(String text) {

        if (text == null) {
            JOptionPane.showMessageDialog(null, "Invalid Movie name");
            window.close();
        } else {
            finalMovieName = text;
            allCorrectDataEntered = true;  
            window.close();
        }
    }

    
    
     /**
     * @return the movieNameToBeDeleted
     */
    public static String returnMovieNameToBeDeleted() {
        return finalMovieName;
    }

    /**
     * @return the allCorrectDataEntered
     */
    public static boolean isAllCorrectDataEntered() {
        return allCorrectDataEntered;
    }

}
