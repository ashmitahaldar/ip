package mayobot.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mayobot.MayoBot;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private MayoBot mayoBot;

    private Image userImage = cropImage(new Image(this.getClass().getResourceAsStream("/images/user.png")), 1000, 1000);
    private Image mayoBotImage = cropImage(
            new Image(this.getClass().getResourceAsStream("/images/mayobot.png")),
            1000,
            1000);

    /**
     * Initializes the main window components after FXML loading is complete.
     *
     * This method is automatically called by JavaFX after the FXML file has been loaded.
     * It configures the scroll pane to automatically scroll to the bottom when new content
     * is added to the dialog container, and sets up the user input field with a placeholder prompt.
     *
     * Specifically, this method:
     * <ul>
     *   <li>Binds the scroll pane's vertical value to the dialog container's height property
     *       to enable auto-scrolling to the latest messages</li>
     *   <li>Sets a prompt text for the user input field to guide user interaction</li>
     * </ul>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Type here...");
    }

    /** Injects the MayoBot instance */
    public void setMayoBot(MayoBot mayoBot) {
        this.mayoBot = mayoBot;
        dialogContainer.getChildren().add(DialogBox.getMayoBotDialog(mayoBot.getWelcome(), mayoBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = mayoBot.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getMayoBotDialog(response, mayoBotImage));
        userInput.clear();

        // Check for whether the latest command was a "bye" command to exit the application
        if (mayoBot.isExit()) {
            try {
                Thread.sleep(250); // Pauses for 250 milliseconds (0.25 second)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.exit();
        }
    }

    private Image cropImage(Image originalImage, int width, int height) {
        PixelReader pixelReader = originalImage.getPixelReader();
        WritableImage croppedImage = new WritableImage(pixelReader, 0, 0,
                Math.min((int) originalImage.getWidth(), width),
                Math.min((int) originalImage.getHeight(), height));
        return croppedImage;
    }
}
