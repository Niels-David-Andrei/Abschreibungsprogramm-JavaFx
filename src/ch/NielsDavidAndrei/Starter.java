//////////////////////////////////////////////////////////////
// Authoren: Andrei, David, Niels
// Name: DasBesteAbschreibungsprogrammÜberhaupt
// Erstellungs Datum: 19.08.20
// Letzte Änderung: 16.09.20
// Version: 1.0.0
// Beschreibung: Dieses Programm dient der Berrechnung der Abschreibungen.
//               Dabei wird zwischen linearen und degressiven Abschreibungen unterschieden. 
//               Ausserdem kann entschieden werden ob es direkt oder indirekt abgeschrieben werden soll.
//////////////////////////////////////////////////////////////
package ch.NielsDavidAndrei;

import ch.NielsDavidAndrei.Ausgabe.AusgabeAnsichtController;
import ch.NielsDavidAndrei.Eingabe.HauptseiteController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    Objekt werte = new Objekt();

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
        stage.setScene(scene);
        stage.show();
        controller.setMainApp(this);
    }

    public void startAusgabe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ausgabe/AusgabeAnsicht.fxml"));
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

        AusgabeAnsichtController controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.setScene(scene);
        stage.show();
        controller.setMainApp(this);
        controller.setData(werte.getAnschaffungswert(),
                werte.getAbschreibungsbetrag(),
                werte.getBuchwert(),
                werte.getKonto(),
                werte.getDegressivB(),
                werte.getAbzug()
        );
    }

    public void setData(double anschaffungswert, double abschreibungsbetrag, double buchwert, String konto, ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        werte.setAnschaffungswert(anschaffungswert);
        werte.setAbschreibungsbetrag(abschreibungsbetrag);
        werte.setBuchwert(buchwert);
        werte.setKonto(konto);
        werte.setDegressivB(degressivB);
        werte.setAbzug(abzug);
    }

    public void setToMin() throws IOException {
        stage.setIconified(true);
    }

    public void setToMax() throws IOException {
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
}
