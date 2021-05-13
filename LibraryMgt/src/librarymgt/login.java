package librarymgt;

import java.io.FileInputStream;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class login {

    /*
     * GUI Features Boolean Variable for Validation
     */
    static Label login, message;
    static TextField username;
    static PasswordField password;
    static Stage windowLogin;
    static boolean passwordPassed = false;

    /*
     * default constructor
     */
    public login() {
    }

    public static void loginpassword() throws Exception {
        windowLogin = new Stage();

        windowLogin.setTitle(" Login - Library System ");
        windowLogin.setResizable(false);

        /*
         * GridPane declaration Positioning labels, textfields, and passwordfields in
         * their appropriate locations Padding to leave space between contents
         */
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        login = new Label("Username:");
        GridPane.setConstraints(login, 1, 2);
        username = new TextField();
        username.setPromptText("Enter username here");
        GridPane.setConstraints(username, 2, 2);

        Label pass = new Label("Password:");
        GridPane.setConstraints(pass, 1, 3);
        message = new Label();
        GridPane.setConstraints(message, 2, 5);
        password = new PasswordField();
        password.setPromptText("Enter password here");
        GridPane.setConstraints(password, 2, 3);

        /*
         * Icon placed inside the button, setting its height and width
         */
        ImageView submitIcon = new ImageView(new Image(new FileInputStream("images/submit.png")));
        submitIcon.setFitWidth(25);
        submitIcon.setFitHeight(25);
        Button submit = new Button("Log in", submitIcon);
        submit.setOnAction(e -> valid(username.getText(), password.getText()));
        GridPane.setConstraints(submit, 2, 7);

        grid.getChildren().addAll(login, username, pass, password, submit, message);
        Scene s1 = new Scene(grid, 600, 300);

        /*
         * stops any further processing of the event by other events attaches stylesheet
         * for window
         */
        windowLogin.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        windowLogin.setScene(s1);
        s1.getStylesheets().add("file:stylesheet.css");
        windowLogin.getIcons().add(new Image("file:images/icon.jpg"));
        windowLogin.showAndWait();

    }

    /*
     * username and password validation if invalid values entered, error message is
     * displayed it will redirect the user to the main menu if username and password
     * is correct
     */
    private static void valid(String a, String b) {

        if (a.equalsIgnoreCase("username") && b.equals("password")) {
            windowLogin.close();
            passwordPassed = true;

        } else {
            message.setText("Invalid Credentials!");
            message.setTextFill(Color.rgb(210, 39, 30));
            username.clear();
            password.clear();

        }

    }

    /*
     * boolean to exit the application
     */
    private static void closeProgram() {
        try {

            Boolean answer = ConfirmBox.display(" Exit ", " Exit Application? ");
            if (answer) {
                windowLogin.close();
                Platform.exit();
            }

        } catch (Exception e) {
        }
    }
}
