package librarymgt;

import java.io.FileInputStream;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfirmBox {

    static boolean answer;

    /*
    * default constructor
     */
    public ConfirmBox() {
    }

    public static boolean display(String title, String message) throws Exception {
        Stage window = new Stage();

        /*
         * Main GUI for ConfirmBox class
         * Window cannot be resized, with title and modality
         */
        window.setResizable(false); //dialog Box cannot be resized
        window.initModality(Modality.APPLICATION_MODAL);  //blocks input events from being delivered to all windows from the same application
        window.setTitle(title);
        window.getIcons().add(new Image("file:images/icon.jpg"));
        window.setMinWidth(400);

        Label label = new Label();
        label.setText(message);

        /*
         * Inserts an icon in the button along with the text
         * Setting its width and height
         */
        ImageView yesIcon = new ImageView(new Image(new FileInputStream("images/yes.png")));
        yesIcon.setFitWidth(25);
        yesIcon.setFitHeight(25);
        Button yesButton = new Button("Yes", yesIcon);

        ImageView noIcon = new ImageView(new Image(new FileInputStream("images/no.png")));
        noIcon.setFitWidth(25);
        noIcon.setFitHeight(25);
        Button noButton = new Button("No", noIcon);

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        /*
         * Defining VBox, and adding labels and buttons in it
         * showAndWait shows this stage and waits for it to be hidden (closed) before returning
         */
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        scene.getStylesheets().add("file:stylesheet.css");
        window.showAndWait();

        return answer;
    }
}
