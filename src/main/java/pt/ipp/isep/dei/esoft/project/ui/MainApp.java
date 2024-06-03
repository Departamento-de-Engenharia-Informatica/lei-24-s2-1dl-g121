package pt.ipp.isep.dei.esoft.project.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.run();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuUI.fxml"));

            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setTitle("Musgo Sublime");
            stage.setScene(scene);
            stage.setResizable(false);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

                    alerta.setTitle("Application");
                    alerta.setHeaderText("Confirm Action.");
                    alerta.setContentText("Do you really wish to cloes the application?");

                    ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
                    ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            stage.show();
        } catch (IOException ex) {
           createErrorAlert(ex).show();
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Alert createErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Application");
        alert.setHeaderText("Problems starting the application.");
        alert.setContentText(ex.getMessage());

        return alert;
    }
}
