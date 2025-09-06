package MayoBot;

import MayoBot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for MayoBot using FXML.
 */
public class Main extends Application {

    private MayoBot mayoBot = new MayoBot("./data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setMayoBot(mayoBot);  // inject the MayoBot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image cropImage(Image originalImage, int width, int height) {
        PixelReader pixelReader = originalImage.getPixelReader();
        WritableImage croppedImage = new WritableImage(pixelReader, 0, 0,
                Math.min((int)originalImage.getWidth(), width),
                Math.min((int)originalImage.getHeight(), height));
        return croppedImage;
    }
}
