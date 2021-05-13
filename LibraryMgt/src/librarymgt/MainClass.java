package librarymgt;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class MainClass extends Application {

    /*
     * GUI Features
     */
    Stage window;
    Scene scene1;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Super-Fly Records - CD Library!");
        launch(args);
    }

    /*
     * Primary Window, it requires login to be true to access menu
     * Array size 20
     * File Management class calls in to read or write
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        login.loginpassword();

        if (login.passwordPassed == true) {

            Movies[] moviesArray = new Movies[20];
            for (int i = 0; i < moviesArray.length; i++) {
                moviesArray[i] = new Movies();
            }

            FileManagement.openDoc();
            FileManagement.readDoc(moviesArray);
            FileManagement.closeDoc();

            /*
             * Window cannot be resized
             * Creates HBox, to place label1
             */
            window = primaryStage;
            window.setResizable(false);
            Label label1 = new Label(" Super-Fly Records - CD Library! ");
            label1.setPadding(new Insets(100, 50, 50, 100));
            HBox hbox = new HBox();
            hbox.getChildren().addAll(label1);

            /*
             * Load an icon into addRecord button
             * Icon and button size set
             */
            ImageView addIcon = new ImageView(new Image(new FileInputStream("images/add.png")));
            addIcon.setFitWidth(25);
            addIcon.setFitHeight(25);
            Button addRecord = new Button(" ADD RECORDS ", addIcon);
            addRecord.setOnAction(e -> Add(moviesArray));
            addRecord.setPrefSize(250, 50); // (x, y) coordinates

            //Delete Record Button
            ImageView deleteIcon = new ImageView(new Image(new FileInputStream("images/delete.png")));
            deleteIcon.setFitWidth(25);
            deleteIcon.setFitHeight(25);
            Button deleteRecord = new Button(" DELETE RECORDS ", deleteIcon);
            deleteRecord.setOnAction(e -> delete(moviesArray));
            deleteRecord.setPrefSize(250, 50); // It also specifies button size

            //View All Records Button
            ImageView viewIcon = new ImageView(new Image(new FileInputStream("images/view.png")));
            viewIcon.setFitWidth(25);
            viewIcon.setFitHeight(25);
            Button viewRecord = new Button(" VIEW ALL RECORDS ", viewIcon);
            viewRecord.setOnAction(e -> Table.tableConfig(moviesArray));
            viewRecord.setPrefSize(250, 50);

            //Search Records Button
            ImageView searchIcon = new ImageView(new Image(new FileInputStream("images/search.png")));
            searchIcon.setFitWidth(25);
            searchIcon.setFitHeight(25);
            Button searchRecord = new Button(" SEARCH RECORDS ", searchIcon);
            searchRecord.setOnAction(e -> search(moviesArray));
            searchRecord.setPrefSize(250, 50);

            //Sort Records Button
            ImageView sortIcon = new ImageView(new Image(new FileInputStream("images/sort.png")));
            sortIcon.setFitWidth(25);
            sortIcon.setFitHeight(25);
            Button sortRecord = new Button(" SORT RECORDS ", sortIcon);
            sortRecord.setOnAction(e -> sort(moviesArray));
            sortRecord.setPrefSize(250, 50);

            //Exit Application Button
            ImageView exitIcon = new ImageView(new Image(new FileInputStream("images/exit.png")));
            exitIcon.setFitWidth(25);
            exitIcon.setFitHeight(25);
            Button exitLibrary = new Button(" EXIT APPLICATION ", exitIcon);
            exitLibrary.setOnAction(e -> closeProgram());
            exitLibrary.setPrefSize(250, 50);

            /*
             * Close Request
             */
            window.setOnCloseRequest(e -> {
                e.consume(); //stops any further processing of the event by other events
                closeProgram();
            });

            /*
             * Footer declaration with color, and font style
             */
            Text footer = new Text("    Leckraj Luckhun - M00677980     ");
            footer.setFont(Font.font("Courier New", 25));
            footer.setFill(Color.WHITE);

            /*
             * Layout 1 - children(buttons and labels) are laid out in vertical column
             * Aligning the Add Record button in center
             * Spacing between buttons
             */
            VBox vbox = new VBox(20);
            vbox.getChildren().addAll(hbox, addRecord, deleteRecord, viewRecord, searchRecord, sortRecord, exitLibrary, footer);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefWidth(10);

            window.getIcons().add(new Image("file:images/icon.jpg"));
            scene1 = new Scene(vbox, 750, 700);

            /*
             * Default Scene when Program runs
             */
            window.setScene(scene1);
            scene1.getStylesheets().add("file:stylesheet.css");
            window.setTitle(" CD Library Menu ");
            window.show();

        }

    }

    /*
     * Confirmation to exit application
     */
    private void closeProgram() {
        try {
            Boolean answer = ConfirmBox.display(" Exit ", " Exit Application? ");
            if (answer) {
                window.close();
            }
        } catch (Exception e) {
        }
    }

    /*
     * Adding records to array
     * Writing to file
     */
    public static void Add(Movies[] moviesArray) {
        AddMovieRecord.GUI(moviesArray);
        if (AddMovieRecord.beginToAdd() == true) {
            int index = freeIndex(moviesArray);
            moviesArray[index].setCustomerID(AddMovieRecord.returncusID());
            moviesArray[index].setCustomerName(AddMovieRecord.returncusName());
            moviesArray[index].setPhoneNumber(AddMovieRecord.returnphoneNo());
            moviesArray[index].setMovieID(AddMovieRecord.returnmovID());
            moviesArray[index].setMovieDirector(AddMovieRecord.returnmovDirec());
            moviesArray[index].setMovieName(AddMovieRecord.returnmovName());
            moviesArray[index].setReleaseDate(AddMovieRecord.returnreleaseDa());
            moviesArray[index].setGenre(AddMovieRecord.returnGenre());

            try {
                FileManagement.writeDoc(moviesArray);
            } catch (Exception e) {
            }
        }
    }

    /*
     *  Checks for free index to insert record
     */
    public static int freeIndex(Movies[] moviesArray) {
        int index = 0;
        for (int i = 0; index < moviesArray.length; i++) {

            if (moviesArray[i].getCustomerID() == 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    /*
     *  Calls Delete GUI
     *  If operation is aborted, code will not execute
     */
    public static void delete(Movies[] moviesArray) {

        DeleteMovieRecord.GUI(moviesArray);

        if (DeleteMovieRecord.isAllCorrectDataEntered() == true) {
            String movieName = DeleteMovieRecord.returnMovieNameToBeDeleted();
            for (int i = 0; i < moviesArray.length; i++) {
                if (moviesArray[i].getMovieName().equals(movieName)) {
                    moviesArray[i].setCustomerID(0);
                    moviesArray[i].setCustomerName(null);
                    moviesArray[i].setPhoneNumber(0);
                    moviesArray[i].setMovieID(0);
                    moviesArray[i].setMovieDirector(null);
                    moviesArray[i].setGenre(null);
                    moviesArray[i].setMovieName(null);
                    moviesArray[i].setReleaseDate(0);

                    try {
                        FileManagement.writeDoc(moviesArray);
                    } catch (Exception e) {
                    }
                    break;
                }
            }
        }
    }

    /*
     *  Calls Search GUI
     *  Switch statement to choose from
     */
    public static void search(Movies[] moviesArray) {
        SearchMovieRecord.SEARCH();
        if (SearchMovieRecord.getFinalSearchType() != null) {
            switch (SearchMovieRecord.getFinalSearchType()) {
                case "Movie name":

                    SearchMovieRecord.movieNameWindow(moviesArray);
                    if (SearchMovieRecord.getFinalMovieName() != null) {
                        Table.movieNameTable(moviesArray, SearchMovieRecord.getFinalMovieName());
                    }
                    break;
                default:
                    SearchMovieRecord.movieGenreWindow(moviesArray);
                    if (SearchMovieRecord.getFinalMovieGenre() != null) {
                        Table.movieGenreTable(moviesArray, SearchMovieRecord.getFinalMovieGenre());
                    }
                    break;
            }
        }
    }

    /*
     *  Temporary variables for swapping process
     *  Switch statement to choose from
     */
    public static void sort(Movies[] moviesArray) {
        SortMovieRecord.SORT();
        String tempCustomerName, tempMovieDirector, tempGenre, tempMovieName;
        int tempCustomerID, tempPhoneNumber, tempMovieID, tempReleaseDate;

        /*
         *  Check data alphabetically
         *  Displays table after selectng type of Sorting
         *  For ReleaseDate, it sorts the records numerically
         *  Calls the new Sorted Table
         */
        switch (SortMovieRecord.getFinalSortType()) {
            case "Customer Name":
                for (int i = 0; i < moviesArray.length; i++) {
                    // inner loop check
                    for (int j = i + 1; j < moviesArray.length; j++) {
                        if (moviesArray[i].getCustomerName().compareToIgnoreCase(moviesArray[j].getCustomerName()) > 0) {

                            // swapping process
                            tempCustomerID = moviesArray[i].getCustomerID();
                            tempCustomerName = moviesArray[i].getCustomerName();
                            tempPhoneNumber = moviesArray[i].getPhoneNumber();
                            tempMovieID = moviesArray[i].getMovieID();
                            tempMovieDirector = moviesArray[i].getMovieDirector();
                            tempGenre = moviesArray[i].getGenre();
                            tempMovieName = moviesArray[i].getMovieName();
                            tempReleaseDate = moviesArray[i].getReleaseDate();

                            moviesArray[i].setCustomerID(moviesArray[j].getCustomerID());
                            moviesArray[i].setCustomerName(moviesArray[j].getCustomerName());
                            moviesArray[i].setPhoneNumber(moviesArray[j].getPhoneNumber());
                            moviesArray[i].setMovieID(moviesArray[j].getMovieID());
                            moviesArray[i].setMovieDirector(moviesArray[j].getMovieDirector());
                            moviesArray[i].setGenre(moviesArray[j].getGenre());
                            moviesArray[i].setMovieName(moviesArray[j].getMovieName());
                            moviesArray[i].setReleaseDate(moviesArray[j].getReleaseDate());

                            moviesArray[j].setCustomerID(tempCustomerID);
                            moviesArray[j].setCustomerName(tempCustomerName);
                            moviesArray[j].setPhoneNumber(tempPhoneNumber);
                            moviesArray[j].setMovieID(tempMovieID);
                            moviesArray[j].setMovieDirector(tempMovieDirector);
                            moviesArray[j].setGenre(tempGenre);
                            moviesArray[j].setMovieName(tempMovieName);
                            moviesArray[j].setReleaseDate(tempReleaseDate);
                        }
                    }
                }
                Table.tableConfig(moviesArray);
                break;

            case "Release Date":

                for (int i = 0; i < moviesArray.length; i++) {
                    // inner loop check
                    for (int j = i + 1; j < moviesArray.length; j++) {
                        // checks numerically
                        if (moviesArray[i].getReleaseDate() > moviesArray[j].getReleaseDate()) {

                            // swapping process
                            tempCustomerID = moviesArray[i].getCustomerID();
                            tempCustomerName = moviesArray[i].getCustomerName();
                            tempPhoneNumber = moviesArray[i].getPhoneNumber();
                            tempMovieID = moviesArray[i].getMovieID();
                            tempMovieDirector = moviesArray[i].getMovieDirector();
                            tempGenre = moviesArray[i].getGenre();
                            tempMovieName = moviesArray[i].getMovieName();
                            tempReleaseDate = moviesArray[i].getReleaseDate();

                            moviesArray[i].setCustomerID(moviesArray[j].getCustomerID());
                            moviesArray[i].setCustomerName(moviesArray[j].getCustomerName());
                            moviesArray[i].setPhoneNumber(moviesArray[j].getPhoneNumber());
                            moviesArray[i].setMovieID(moviesArray[j].getMovieID());
                            moviesArray[i].setMovieDirector(moviesArray[j].getMovieDirector());
                            moviesArray[i].setGenre(moviesArray[j].getGenre());
                            moviesArray[i].setMovieName(moviesArray[j].getMovieName());
                            moviesArray[i].setReleaseDate(moviesArray[j].getReleaseDate());

                            moviesArray[j].setCustomerID(tempCustomerID);
                            moviesArray[j].setCustomerName(tempCustomerName);
                            moviesArray[j].setPhoneNumber(tempPhoneNumber);
                            moviesArray[j].setMovieID(tempMovieID);
                            moviesArray[j].setMovieDirector(tempMovieDirector);
                            moviesArray[j].setGenre(tempGenre);
                            moviesArray[j].setMovieName(tempMovieName);
                            moviesArray[j].setReleaseDate(tempReleaseDate);

                        } else if (moviesArray[i].getReleaseDate() == moviesArray[j].getReleaseDate()) {
                            if (moviesArray[i].getMovieName().compareToIgnoreCase(moviesArray[j].getMovieName()) > 0) {
                                // swapping process
                                tempCustomerID = moviesArray[i].getCustomerID();
                                tempCustomerName = moviesArray[i].getCustomerName();
                                tempPhoneNumber = moviesArray[i].getPhoneNumber();
                                tempMovieID = moviesArray[i].getMovieID();
                                tempMovieDirector = moviesArray[i].getMovieDirector();
                                tempGenre = moviesArray[i].getGenre();
                                tempMovieName = moviesArray[i].getMovieName();
                                tempReleaseDate = moviesArray[i].getReleaseDate();

                                moviesArray[i].setCustomerID(moviesArray[j].getCustomerID());
                                moviesArray[i].setCustomerName(moviesArray[j].getCustomerName());
                                moviesArray[i].setPhoneNumber(moviesArray[j].getPhoneNumber());
                                moviesArray[i].setMovieID(moviesArray[j].getMovieID());
                                moviesArray[i].setMovieDirector(moviesArray[j].getMovieDirector());
                                moviesArray[i].setGenre(moviesArray[j].getGenre());
                                moviesArray[i].setMovieName(moviesArray[j].getMovieName());
                                moviesArray[i].setReleaseDate(moviesArray[j].getReleaseDate());

                                moviesArray[j].setCustomerID(tempCustomerID);
                                moviesArray[j].setCustomerName(tempCustomerName);
                                moviesArray[j].setPhoneNumber(tempPhoneNumber);
                                moviesArray[j].setMovieID(tempMovieID);
                                moviesArray[j].setMovieDirector(tempMovieDirector);
                                moviesArray[j].setGenre(tempGenre);
                                moviesArray[j].setMovieName(tempMovieName);
                                moviesArray[j].setReleaseDate(tempReleaseDate);
                            }
                        }
                    }
                }

                Table.tableConfig(moviesArray);
        }
    }
}
