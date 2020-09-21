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
package ch.NielsDavidAndrei.Eingabe;

import ch.NielsDavidAndrei.Starter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class Diese Klasse dient als Kontroller für die Eingabeseite.
 */
public class HauptseiteController implements Initializable {

    //Visuelle Elemente
    @FXML
    private JFXButton btn_calulate;
    @FXML
    private JFXTextField tbox_anschaffungswert;
    @FXML
    private JFXTextField tbox_restwert;
    @FXML
    private JFXTextField tbox_nutzungsdauer;
    @FXML
    private JFXTextField tbox_abschreibungsprozentsatz;
    @FXML
    private JFXRadioButton rd_linear;
    @FXML
    private ToggleGroup abschreibungsmethode;
    @FXML
    private JFXRadioButton rd_degressiv;
    @FXML
    private JFXRadioButton rd_direkt;
    @FXML
    private ToggleGroup abschreibungsart;
    @FXML
    private JFXRadioButton rd_indirekt;
    @FXML
    private Label restWerttxt;
    @FXML
    private Label abschreibungsTxt;
    @FXML
    private Label stern;

    //Daten welche Relevant sind
    private boolean linear;
    private boolean direkt = false;
    private double anschaffungswert;
    private double nutzungsdauer;
    private double prozent;
    private double restwert;
    private String gewehlt = null;
    private String AllowedChars = "[0-9@.]*";
    private ArrayList<Double> abzug = new ArrayList<>();
    private double abschreibungsbetrag;
    private double buchwert;
    private ArrayList<Double> degressiveB = new ArrayList<>();
    private String konto;
    private Starter main;
    @FXML
    private Label fehlerTxt;

    //Inizialisiert das fxml und erfüllt Visuelle und Auswahl aufgaben
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Lässt alle Visuellen Objekte welche nicht zu sehen sein sollen auf Visibility false
        stern.visibleProperty().set(false);
        tbox_restwert.visibleProperty().set(false);
        tbox_abschreibungsprozentsatz.visibleProperty().set(false);
        restWerttxt.visibleProperty().set(false);
        abschreibungsTxt.visibleProperty().set(false);
        fehlerTxt.visibleProperty().set(false);
        //Degressiv oder Linear listener
        rd_linear.selectedProperty().addListener((observable, oldValue, newValue) -> {
            stern.visibleProperty().set(true);
            if (newValue) {
                tbox_restwert.visibleProperty().set(true);
                tbox_abschreibungsprozentsatz.visibleProperty().set(false);
                restWerttxt.visibleProperty().set(true);
                abschreibungsTxt.visibleProperty().set(false);
                linear = true;
            } else {
                tbox_abschreibungsprozentsatz.visibleProperty().set(true);
                tbox_restwert.visibleProperty().set(false);
                restWerttxt.visibleProperty().set(false);
                abschreibungsTxt.visibleProperty().set(true);
                linear = false;
            }
        });
        //Degressiv oder Linear listener
        rd_degressiv.selectedProperty().addListener((observable, oldValue, newValue) -> {
            stern.visibleProperty().set(true);
            if (newValue) {
                tbox_restwert.visibleProperty().set(false);
                tbox_abschreibungsprozentsatz.visibleProperty().set(true);
                restWerttxt.visibleProperty().set(false);
                abschreibungsTxt.visibleProperty().set(true);
                linear = false;
            } else {
                tbox_abschreibungsprozentsatz.visibleProperty().set(false);
                tbox_restwert.visibleProperty().set(true);
                restWerttxt.visibleProperty().set(true);
                abschreibungsTxt.visibleProperty().set(false);
                linear = true;
            }
        });
        //Select Dirket or Indirekt durch einen Listener
        rd_direkt.selectedProperty().addListener((observ, old, newV) -> {
            //Setzte Direkt je nach Eingabe
            if (newV) {
                direkt = true;
                gewehlt = "j";
            } else {
                direkt = false;
                gewehlt = "n";
            }
        });
        rd_indirekt.selectedProperty().addListener((observ, old, newV) -> {
            //Setzte Indirket je nach Eingabe
            if (newV) {
                direkt = false;
                gewehlt = "j";
            } else {
                direkt = true;
                gewehlt = "n";
            }
        });
    }

    //Rechnet die Daten aus bei nach bestätigen der Eingabe, ausserdem fängt es Falsche eingaben ab
    @FXML
    private void ausrechnen(ActionEvent event) throws IOException {
        //Checkt die Eingaben
        if (checkEingabe(tbox_anschaffungswert.getText()) || checkEingabe(tbox_nutzungsdauer.getText())) {
            //Setzt die Fehler Meldung damit der User diese sieht
            fehlerTxt.visibleProperty().set(true);
        } else {
            //Speichert die Daten
            anschaffungswert = Double.parseDouble(tbox_anschaffungswert.getText());
            nutzungsdauer = Double.parseDouble(tbox_nutzungsdauer.getText());
            //Checkt ob Linear oder Degressiv
            if (linear) {
                //Checkt die Restwert Eingabe
                if (checkRestWert(tbox_restwert.getText())) {
                    //Setzt die Fehler Meldung damit der User diese sieht
                    fehlerTxt.visibleProperty().set(true);
                } else {
                    if (gewehlt == null) {
                        //Setzt die Fehler Meldung damit der User diese sieht
                        fehlerTxt.visibleProperty().set(true);
                    } else {
                        //Holt sich den Restwert
                        restwert = Double.parseDouble(tbox_restwert.getText());
                        //Checkt ob Direkt oder Indirekt
                        if (direkt) {
                            //Rechnet mit den Eingaben die Ausgabe
                            abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                            buchwert = restwert;
                            konto = "Anlagekonto";
                            //Setzt die Fehler Meldung damit der User diese nicht sieht
                            fehlerTxt.visibleProperty().set(false);
                            //Startet die ausgabe
                            startAusgabe();
                        } else {
                            //Rechnet mit den Eingaben die Ausgabe
                            abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                            buchwert = restwert;
                            konto = "WB";
                            //Setzt die Fehler Meldung damit der User diese nicht sieht
                            fehlerTxt.visibleProperty().set(false);
                            //Startet die ausgabe
                            startAusgabe();
                        }
                    }
                }
            } else {
                //Checkt die Prozentsatz Eingabe
                if (checkProzent(tbox_abschreibungsprozentsatz.getText())) {
                    //Setzt die Fehler Meldung damit der User diese sieht
                    fehlerTxt.visibleProperty().set(true);
                } else {
                    if (gewehlt == null) {
                        //Setzt die Fehler Meldung damit der User diese sieht
                        fehlerTxt.visibleProperty().set(true);
                    } else {
                        //Holt sich die Prozentsatz eingabe
                        prozent = Double.parseDouble(tbox_abschreibungsprozentsatz.getText());
                        //Checkt ob Direkt oder Indirekt
                        if (direkt) {
                            //Rechnet mit den Eingaben die Ausgabe
                            rechner(anschaffungswert, prozent, nutzungsdauer);
                            konto = "Anlagekonto";
                            fehlerTxt.visibleProperty().set(false);
                            //Startet die ausgabe
                            startAusgabe();
                        } else {
                            //Rechnet mit den Eingaben die Ausgabe
                            rechner(anschaffungswert, prozent, nutzungsdauer);
                            konto = "WB";
                            fehlerTxt.visibleProperty().set(false);
                            //Startet die ausgabe
                            startAusgabe();
                        }
                    }
                }
            }
        }
    }

    //Rechner der Degressiven Methode
    private void rechner(double wert, double prozent, double jahre) {
        //Schaut ob es Jahre gibt wenn nicht dann bricht die Methode ab
        if (jahre == 0 || wert < 1) {
            //Setzt denn Buchwert welcher am Ende übrig ist
            buchwert = wert;
        } else {
            //Rechnet ein Jahr aus und springt zum nächsten 
            //mit den neuen Daten
            double preceentile = 100.0 - prozent;
            double neuerwert = wert * (preceentile / 100);
            double abzug = wert - neuerwert;
            this.abzug.add(abzug);
            this.degressiveB.add(neuerwert);
            jahre--;
            rechner(neuerwert, prozent, jahre);

        }
    }

    //Setzt den Starter für diesen Kontroller
    public void setMainApp(Starter main) {
        this.main = main;
    }

    //Starter die Ausgabe und setzt die Werte des Werte Objektes im Starter
    private void startAusgabe() throws IOException {
        main.setData(anschaffungswert, abschreibungsbetrag, buchwert, konto, degressiveB, abzug);
        main.startAusgabe();
    }

    //Schaut ob die Eingabe korrekt ausgewählt worden ist
    private boolean checkEingabe(String eingabe) {
        if (eingabe.isEmpty() || !eingabe.matches(AllowedChars) || Double.parseDouble(eingabe) == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Schaut ob der Restwert korrekt ausgewählt worden ist
    private boolean checkRestWert(String eingabe) {
        if (eingabe.isEmpty() || !eingabe.matches(AllowedChars) || Double.parseDouble(eingabe) > anschaffungswert) {
            return true;
        } else {
            return false;
        }
    }

    //Schaut ob der Prozentsatz korrekt ausgewählt worden ist
    private boolean checkProzent(String eingabe) {
        if (eingabe.isEmpty() || !eingabe.matches(AllowedChars) || Double.parseDouble(eingabe) > 99 || Double.parseDouble(eingabe) == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Minimiert das Fenster
    @FXML
    private void min(ActionEvent event) throws IOException {
        main.setToMin();
    }

    //Schliesst das Programm
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
}
