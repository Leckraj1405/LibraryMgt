package librarymgt;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class SortMovieRecord {

    
    /*
     * GUI Features
     * Variable declaration
     */
    static Stage mainWindow, customerWindow, releaseDateWindow;
    private static String finalSortType = "null";
    
     /*
      * default constructor
      */
    public SortMovieRecord() {}

    
    
         /*
          * setting main window, which cannot be resized
          * defining GridPane, and placing labels, textfields in the GridPane
          * introduced radio buttons for selection
          * submit button will validate the selection and pop out a sorted table
          * submit button placed inside an HBox
          */
    public static void SORT() {

        mainWindow = new Stage();
        mainWindow.setTitle("SORT RECORD");
        mainWindow.setResizable(false);
        mainWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        Label sortType = new Label(" Select Data To Sort ");
        GridPane.setConstraints(sortType, 1, 2);

        ToggleGroup sortRecord = new ToggleGroup();

        RadioButton customerNameButton = new RadioButton("Customer Name(Single Sort)");
        customerNameButton.setToggleGroup(sortRecord);
        customerNameButton.setSelected(true);

        RadioButton releaseDateButton = new RadioButton("Release Date & Movie Name(Double Sort)");
        releaseDateButton.setToggleGroup(sortRecord);

        VBox radioButton = new VBox(10, customerNameButton, releaseDateButton);
        GridPane.setConstraints(radioButton, 1, 3);

        Button backSort = new Button(" Back ");
        backSort.setOnAction(e -> {
            mainWindow.close();
        });

        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> {

            if (customerNameButton.isSelected()) {
                finalSortType = "Customer Name";
            } else {
                finalSortType = "Release Date";
            }
            mainWindow.close();
        });

        HBox buttons = new HBox(5, submit, backSort);
        pane.setConstraints(buttons, 1, 10);

        pane.getChildren().addAll(sortType, radioButton, buttons);

        mainWindow.getIcons().add(new Image("file:images/icon.jpg"));
        Scene scene = new Scene(pane, 600, 350);
        mainWindow.setScene(scene);
        scene.getStylesheets().add("file:stylesheet.css");
        mainWindow.showAndWait();
    }
    
    /**
     * @return the finalSortType
     */
    public static String getFinalSortType() {
        return finalSortType;
    }

}
