package librarymgt;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Table {

    
    /*
     * GUI Features
     */
    static Stage tableWindow;
    static TableView table;  

    /*
     * default constructor
     */
    public Table() {
    }
    
    
    
    /*
     * creates a new window to display table
     * declared all the columns necessary for the table, and how they get their values 
     * the table is added in a VBox
     */
    public static void tableConfig(Movies[] moviesArray) {

        tableWindow = new Stage();
        tableWindow.setResizable(false); 
        tableWindow.setTitle("View All Records - Table");

        TableColumn<Movies, Integer> customerIDColumn = new TableColumn("CustomerID");  
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        TableColumn<Movies, String> customerNameColumn = new TableColumn("CustomerName");
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Movies, Integer> phoneNumberColumn = new TableColumn("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Movies, Integer> movieIDColumn = new TableColumn("MovieID");
        movieIDColumn.setCellValueFactory(new PropertyValueFactory<>("movieID"));

        TableColumn<Movies, String> movieDirectorColumn = new TableColumn("Director");
        movieDirectorColumn.setCellValueFactory(new PropertyValueFactory<>("movieDirector"));

        TableColumn<Movies, String> genreColumn = new TableColumn("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Movies, String> movieNameColumn = new TableColumn("MovieName");
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));

        TableColumn<Movies, Integer> releaseDateColumn = new TableColumn("ReleaseDate");
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        table = new TableView<>();
        tableData(moviesArray);
        table.getColumns().addAll(customerIDColumn, customerNameColumn, phoneNumberColumn, movieIDColumn, movieDirectorColumn, genreColumn, movieNameColumn, releaseDateColumn);

        VBox vbox = new VBox();
        vbox.getChildren().add(table);

        tableWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene tableScene = new Scene(vbox, 800, 800);
        tableWindow.setScene(tableScene);
        tableScene.getStylesheets().add("file:stylesheet.css");
        tableWindow.show();

    }

     /*
      * table for searching data by Movie Name
      */
    public static void movieNameTable(Movies[] moviesArray, String movieName) {

        tableWindow = new Stage();
        tableWindow.setResizable(false);
        tableWindow.setTitle("View All Records - Table");

        TableColumn<Movies, Integer> customerIDColumn = new TableColumn("CustomerID");
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        TableColumn<Movies, String> customerNameColumn = new TableColumn("CustomerName");
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Movies, Integer> phoneNumberColumn = new TableColumn("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Movies, Integer> movieIDColumn = new TableColumn("MovieID");
        movieIDColumn.setCellValueFactory(new PropertyValueFactory<>("movieID"));

        TableColumn<Movies, String> movieDirectorColumn = new TableColumn("Director");
        movieDirectorColumn.setCellValueFactory(new PropertyValueFactory<>("movieDirector"));

        TableColumn<Movies, String> genreColumn = new TableColumn("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Movies, String> movieNameColumn = new TableColumn("MovieName");
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));

        TableColumn<Movies, Integer> releaseDateColumn = new TableColumn("ReleaseDate");
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        table = new TableView<>();
        tableSearchMovieName(moviesArray, movieName);
        table.getColumns().addAll(customerIDColumn, customerNameColumn, phoneNumberColumn, movieIDColumn, movieDirectorColumn, genreColumn, movieNameColumn, releaseDateColumn);

        VBox vbox = new VBox();
        vbox.getChildren().add(table);

        tableWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene tableScene = new Scene(vbox, 800, 800);
        tableWindow.setScene(tableScene);
        tableScene.getStylesheets().add("file:stylesheet.css");
        tableWindow.show();

    }

        /*
         * table for searching data by Movie Genre
         */
    public static void movieGenreTable(Movies[] moviesArray, String movieGenre) {

        tableWindow = new Stage();
        tableWindow.setResizable(false);
        tableWindow.setTitle("View All Records - Table");

        TableColumn<Movies, Integer> customerIDColumn = new TableColumn("CustomerID");
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        TableColumn<Movies, String> customerNameColumn = new TableColumn("CustomerName");
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Movies, Integer> phoneNumberColumn = new TableColumn("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Movies, Integer> movieIDColumn = new TableColumn("MovieID");
        movieIDColumn.setCellValueFactory(new PropertyValueFactory<>("movieID"));

        TableColumn<Movies, String> movieDirectorColumn = new TableColumn("Director");
        movieDirectorColumn.setCellValueFactory(new PropertyValueFactory<>("movieDirector"));

        TableColumn<Movies, String> genreColumn = new TableColumn("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Movies, String> movieNameColumn = new TableColumn("MovieName");
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));

        TableColumn<Movies, Integer> releaseDateColumn = new TableColumn("ReleaseDate");
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        table = new TableView<>();
        tableSearchMovieGenre(moviesArray, movieGenre);
        table.getColumns().addAll(customerIDColumn, customerNameColumn, phoneNumberColumn, movieIDColumn, movieDirectorColumn, genreColumn, movieNameColumn, releaseDateColumn);

        VBox vbox = new VBox();
        vbox.getChildren().add(table);

        tableWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene tableScene = new Scene(vbox, 800, 800);
        tableWindow.setScene(tableScene);
        tableScene.getStylesheets().add("file:stylesheet.css");
        tableWindow.show();
    }

    
        /*
         * Display table data
         */
    public static Movies[] tableData(Movies[] MovieArray) {
        for (int i = 0; i < MovieArray.length; i++) {
            if (MovieArray[i].getCustomerID() != 0) {
                table.getItems().add(new Movies(MovieArray[i].getCustomerID(), MovieArray[i].getCustomerName(), MovieArray[i].getPhoneNumber(), MovieArray[i].getMovieID(), MovieArray[i].getMovieDirector(), MovieArray[i].getGenre(), MovieArray[i].getMovieName(), MovieArray[i].getReleaseDate()));
            }
        }
        return MovieArray;
    }

    
    
        /*
         * Display the data according to Movie Name
         */
    public static Movies[] tableSearchMovieName(Movies[] MovieArray, String movieName) {
        for (int i = 0; i < MovieArray.length; i++) {
            if (MovieArray[i].getMovieName().equals(movieName)) {
                table.getItems().add(new Movies(MovieArray[i].getCustomerID(), MovieArray[i].getCustomerName(), MovieArray[i].getPhoneNumber(), MovieArray[i].getMovieID(), MovieArray[i].getMovieDirector(), MovieArray[i].getGenre(), MovieArray[i].getMovieName(), MovieArray[i].getReleaseDate()));
                break;
            }
        }
        return MovieArray;
    }

    
        /*
         * Display the data according to Movie Genre
         */
    public static Movies[] tableSearchMovieGenre(Movies[] MovieArray, String movieGenre) {
        for (int i = 0; i < MovieArray.length; i++) {
            if (MovieArray[i].getGenre().equals(movieGenre)) {
                table.getItems().add(new Movies(MovieArray[i].getCustomerID(), MovieArray[i].getCustomerName(), MovieArray[i].getPhoneNumber(), MovieArray[i].getMovieID(), MovieArray[i].getMovieDirector(), MovieArray[i].getGenre(), MovieArray[i].getMovieName(), MovieArray[i].getReleaseDate()));
            }
        }
        return MovieArray;
    }
}
