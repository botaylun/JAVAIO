import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Notepad");

        BorderPane pane = new BorderPane();
        textArea = new TextArea();
        pane.setCenter(textArea);

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");

        fileMenu.getItems().addAll(openItem, saveItem);
        menuBar.getMenus().add(fileMenu);
        pane.setTop(menuBar);

        openItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                openFile(file);
            }
        });

        saveItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                saveFile(file);
            }
        });

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile(File file) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            textArea.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(textArea.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}