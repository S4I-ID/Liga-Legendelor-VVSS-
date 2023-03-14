package inventory;

import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            InventoryRepository repo = new InventoryRepository();
            InventoryService service  = new InventoryService(repo);
            System.out.println(service.getAllProducts());
            System.out.println(service.getAllParts());
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));

            Parent root=loader.load();
            MainScreenController ctrl=loader.getController();
            ctrl.setService(service);

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.NONE);
            alert.setTitle("Data file error");
            alert.setHeaderText("File data is not available");
            alert.setContentText("An error has occurred while reading the file");
        }


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
