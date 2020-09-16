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

    //Unser Fenster welches von Mehrern Methoden verwendet werden kann
    private Stage stage;
    //Die Position des Fensters auf dem Desktop
    private double xOffset = 0;
    private double yOffset = 0;
    //Das Werte Objekt welche alle übergabe Daten hält
    Objekt werte = new Objekt();

    //Start Methode welche zu beginn des Programms aufgerufen wird
    @Override
    public void start(Stage stage) throws Exception {
        //Setzte die Stage Local
        this.stage = stage;
        //Den Loader holen des FXML um Damit weiter zu arbeiten
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Eingabe/Eingabe.fxml"));
        Parent root;
        root = loader.load();
        //Macht das Fenster bewegbar ohne Nervige Standard Leiste
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

        //Kreiert eine neue Instanz des Controller durch den Loader
        HauptseiteController controller = loader.getController();

        //Setzt den Inhalt des Fensters und entfernt die Trackliste, dazu wird es gezeigt und der name des Fenster wird auch gesetzt
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        //Setzt die Mainapp im Controller damit der Controller Methoden des Starters aufzurufen
        controller.setMainApp(this);
    }

    /**
     * @param args the command line arguments
     */
    //Erste Methode das Programm Launched
    public static void main(String[] args) {
        launch(args);
    }

    //Startet die Eingabe zu einem Späteren Zeitpunkt, falls zum beispiel eine Zweite Berechnung stattfinden soll
    public void startEingabe() throws IOException {
        //Den Loader holen des FXML um Damit weiter zu arbeiten
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Eingabe/Eingabe.fxml"));
        Parent root;
        root = loader.load();

        //Macht das Fenster bewegbar ohne Nervige Standard Leiste
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

        //Kreiert eine neue Instanz des Controller durch den Loader
        HauptseiteController controller = loader.getController();

        //Setzt den Inhalt des Fensters und entfernt die Trackliste, dazu wird es gezeigt und der name des Fenster wird auch gesetzt
        final Scene scene = new Scene(root);
        stage.setTitle("Eingabe");
        stage.setScene(scene);
        stage.show();

        //Setzt die Mainapp im Controller damit der Controller Methoden des Starters aufzurufen
        controller.setMainApp(this);
    }

    //Startet die Ausgabe des Projektes zu egal welchem Zeitpunkt
    public void startAusgabe() throws IOException {
        //Den Loader holen des FXML um Damit weiter zu arbeiten
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ausgabe/AusgabeAnsicht.fxml"));
        Parent root;
        root = loader.load();

        //Macht das Fenster bewegbar ohne Nervige Standard Leiste
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

        //Kreiert eine neue Instanz des Controller durch den Loader
        AusgabeAnsichtController controller = loader.getController();

        //Setzt den Inhalt des Fensters und entfernt die Trackliste, dazu wird es gezeigt und der name des Fenster wird auch gesetzt
        final Scene scene = new Scene(root);
        stage.setTitle("Ausgabe");
        stage.setScene(scene);
        stage.show();

        //Setzt die Mainapp im Controller damit der Controller Methoden des Starters aufzurufen
        controller.setMainApp(this);

        //Setzt die Daten welche übergeben werden durch die Methode .setData(),
        //diese nimmt die Daten welche in dem Wert Objekt gespeichert werden.
        controller.setData(werte.getAnschaffungswert(),
                werte.getAbschreibungsbetrag(),
                werte.getBuchwert(),
                werte.getKonto(),
                werte.getDegressivB(),
                werte.getAbzug()
        );
    }

    //Setzt die Daten des Werte Objektes welches alle Übergabe Daten beinhaltet
    public void setData(double anschaffungswert, double abschreibungsbetrag, double buchwert, String konto, ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        werte.setAnschaffungswert(anschaffungswert);
        werte.setAbschreibungsbetrag(abschreibungsbetrag);
        werte.setBuchwert(buchwert);
        werte.setKonto(konto);
        werte.setDegressivB(degressivB);
        werte.setAbzug(abzug);
    }

    //Minimiert das Fenster
    public void setToMin() throws IOException {
        stage.setIconified(true);
    }

    //Setzt das Fenster auf die Maximale grösse
    public void setToMax() throws IOException {
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
}
