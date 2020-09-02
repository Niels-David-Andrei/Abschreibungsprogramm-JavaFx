/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei;

import ch.NielsDavidAndrei.Ausgabe.AusgabeAnsichtController;
import ch.NielsDavidAndrei.Eingabe.HauptseiteController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Andrei Oleniuc
 */
public class Starter extends Application {

    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Eingabe/Eingabe.fxml"));
        Parent root;
        root = loader.load();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        HauptseiteController controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setMainApp(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void startEingabe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Eingabe/Eingabe.fxml"));
        Parent root;
        root = loader.load();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        HauptseiteController controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setMainApp(this);
    }

    public void startAusgabe(double abschreibungsbetrag, double buchwert, double wert, double abzug) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ausgabe/AusgabeAnsicht.fxml"));
        Parent root;
        root = loader.load();

        AusgabeAnsichtController controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.setScene(scene);
        stage.show();
        controller.setMainApp(this);
        controller.setData(abschreibungsbetrag, buchwert, wert, abzug);
    }
}
