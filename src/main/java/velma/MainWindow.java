package velma;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Velma velma;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/andrew.jpeg"));
    private Image velmaImage = new Image(this.getClass().getResourceAsStream("/images/Velma.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getVelmaDialog("Hello! I'm Velma. How can I help you today?", velmaImage)
        );
    }

    /** Injects the Velma instance */
    public void setVelma(Velma v) {
        velma = v;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            userInput.clear();
            playSendMessageSound();
            exit(input);
        } else {
            String response = velma.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getVelmaDialog(response, velmaImage)
            );
            playSendMessageSound();
            userInput.clear();
        }
    }

    private void playSendMessageSound() {
        String soundPath = this.getClass().getResource("/sounds/fart-01.wav").toString();
        AudioClip sendSound = new AudioClip(soundPath);
        sendSound.play();
    }


    /**
     * Displays a goodbye message and exits the application
     */
    private void exit(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVelmaDialog("Goodbye! Hope to see you again soon!", velmaImage));
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> System.exit(0));
        delay.play();
    }

}
