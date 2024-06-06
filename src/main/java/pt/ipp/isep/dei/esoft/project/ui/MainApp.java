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
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setContentText("The current file is not saved. Save it?");
                    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

                    if (alert.showAndWait().get() == ButtonType.YES) {
                        saveGreenSpaces();
                        saveToDoList();
                        saveAgenda();
                    } else if (alert.getResult() == ButtonType.CANCEL) {
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

    private void saveToDoList() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/toDoList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getToDoList());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void saveAgenda() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/agenda.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getAgenda());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void saveGreenSpaces() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/greenSpaces.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getGreenSpacesRepository());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
