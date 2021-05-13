package librarymgt;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javax.swing.JOptionPane;
import javafx.scene.image.Image;

public class SearchMovieRecord {

    /*
     * GUI Features
     * Variable declaration, along with a ComboBox
     */
    private static Stage mainWindow, movieWindow, genreWindow;
    private static String finalSearchType;
    private static ComboBox<String> comboboxMovieName;
    private static ComboBox<String> comboboxGenre;
    private static String finalMovieName, finalMovieGenre;

    /*
     * default constructor
     */
    public SearchMovieRecord() {
    }

    
    /*
     * Setting window with title, and modality
     * Defining GridPane, ToggleGroup and Radio Buttons
     * Adding the data in a VBox
     * The Submit button triggers the appropriate event based on user selection
     */
    public static void SEARCH() {
        mainWindow = new Stage();
        mainWindow.setTitle("SEARCH RECORD");
        mainWindow.setResizable(false);
        mainWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        Label searchType = new Label(" Select Type of Searching ");
        GridPane.setConstraints(searchType, 1, 2);

        ToggleGroup searchRecord = new ToggleGroup();

        RadioButton movieNameButton = new RadioButton("Movie Name");
        movieNameButton.setToggleGroup(searchRecord);
        movieNameButton.setSelected(true);

        RadioButton genreButton = new RadioButton("Movie Genre");
        genreButton.setToggleGroup(searchRecord);

        VBox radioButton = new VBox(10, movieNameButton, genreButton);
        GridPane.setConstraints(radioButton, 1, 3);

        Button backSearch = new Button(" Back ");
        backSearch.setOnAction(e -> {
            mainWindow.close();
        });

        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> {
            if (movieNameButton.isSelected()) {
                finalSearchType = "Movie name";
            } else {
                finalSearchType = "Genre";
            }
            mainWindow.close();
        });
        HBox buttons = new HBox(5, submit, backSearch);
        pane.setConstraints(buttons, 1, 10);

        pane.getChildren().addAll(searchType, radioButton, buttons);

        mainWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene scene = new Scene(pane, 600, 350);
        mainWindow.setScene(scene);
        scene.getStylesheets().add("file:stylesheet.css");
        mainWindow.showAndWait();

    }

    
    
    /*
     * Setting window with title, and modality
     * Defining ComboBox to select Movie Name
     * Adding the ComboBox in a GridPane, and the buttons in an HBox
     * The Submit button triggers the appropriate event based on user selection, firstly by validating the input
     */
    public static void movieNameWindow(Movies[] moviesArray) {

        movieWindow = new Stage();
        movieWindow.setTitle("SELECT MOVIE NAME");
        movieWindow.setResizable(false);
        movieWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        Label movieName = new Label(" Select Movie Name ");
        GridPane.setConstraints(movieName, 1, 2);

        comboboxMovieName = new ComboBox<>();
        comboboxMovieName.setPromptText("Select Movie Name");

        for (int i = 0; i < moviesArray.length; i++) {
            if (moviesArray[i].getMovieID() != 0) {
                comboboxMovieName.getItems().add(moviesArray[i].getMovieName());
            }
        }
        
        GridPane.setConstraints(comboboxMovieName, 5, 2);

        Button backSearch = new Button(" Back ");
        backSearch.setOnAction(e -> {
            movieWindow.close();
        });

        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> movieNameValidation(comboboxMovieName.getValue()));

        HBox buttons = new HBox(5, submit, backSearch);
        pane.setConstraints(buttons, 1, 10);

        pane.getChildren().addAll(movieName, buttons, comboboxMovieName);

        movieWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene scene2 = new Scene(pane, 600, 350);
        movieWindow.setScene(scene2);
        scene2.getStylesheets().add("file:stylesheet.css");
        movieWindow.showAndWait();
    }

    
    
    /*
     * Setting window with title, and modality
     * Defining ComboBox to select Movie Genre
     * Adding the ComboBox in a GridPane, and the buttons in an HBox
     * The Submit button triggers the appropriate event based on user selection, firstly by validating the input
     */
    public static void movieGenreWindow(Movies[] moviesArray) {

        genreWindow = new Stage();
        genreWindow.setTitle("SELECT MOVIE GENRE");
        genreWindow.setResizable(false);
        genreWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        Label movieGenre = new Label(" Select Movie Genre ");
        GridPane.setConstraints(movieGenre, 1, 2);

        comboboxGenre = new ComboBox<>();
        comboboxGenre.setPromptText("Select Movie genre");

        comboboxGenre.getItems().addAll("Action", "Comedy", "Thriller", "Horror");
        comboboxGenre.setPromptText("Select Movie Genre");

        GridPane.setConstraints(comboboxGenre, 5, 2);

        Button backSearch = new Button(" Back ");
        backSearch.setOnAction(e -> {
            genreWindow.close();
        });

        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> movieGenreValidation(comboboxGenre.getValue()));

        HBox buttons = new HBox(5, submit, backSearch);
        pane.setConstraints(buttons, 1, 10);

        pane.getChildren().addAll(movieGenre, buttons, comboboxGenre);

        genreWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene scene2 = new Scene(pane, 600, 350);
        genreWindow.setScene(scene2);
        scene2.getStylesheets().add("file:stylesheet.css");
        genreWindow.showAndWait();
    }

    
    
    /*
     * If no movie name has been selected, error message displayed
     */
    public static void movieNameValidation(String text) {
        if (text == null) {
            JOptionPane.showMessageDialog(null, "This field cannot be blank!!!");
            movieWindow.close();
        } else {
            finalMovieName = text;
            movieWindow.close();
        }
    }

    
    /*
     * If no movie genre has been selected, error message displayed
     */
    public static void movieGenreValidation(String text) {
        if (text == null) {
            JOptionPane.showMessageDialog(null, "This field cannot be blank!!!");
            genreWindow.close();
        } else {
            finalMovieGenre = text;
            genreWindow.close();
        }
    }
    
    
    /**
     * @return the finalSearchType
     */
    public static String getFinalSearchType() {
        return finalSearchType;
    }

    /**
     * @return the finalMovieName
     */
    public static String getFinalMovieName() {
        return finalMovieName;
    }

    /**
     * @return the finalMovieGenre
     */
    public static String getFinalMovieGenre() {
        return finalMovieGenre;
    }
}
