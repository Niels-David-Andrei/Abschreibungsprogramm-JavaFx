/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei;

import ch.NielsDavidAndrei.Eingabe.HauptseiteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Andrei Oleniuc
 */
public class Starter extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Eingabe/Eingabe.fxml"));
        Parent root;
        root = loader.load();
        
        HauptseiteController controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
