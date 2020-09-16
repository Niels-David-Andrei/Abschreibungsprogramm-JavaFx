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
 * FXML Controller class
 * Diese Klasse dient als Kontroller für die Eingabeseite.
 */
public class HauptseiteController implements Initializable {

    //View Elements
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

    //Data
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
    private Label stern;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Visuals
        stern.visibleProperty().set(false);
        tbox_restwert.visibleProperty().set(false);
        tbox_abschreibungsprozentsatz.visibleProperty().set(false);
        restWerttxt.visibleProperty().set(false);
        abschreibungsTxt.visibleProperty().set(false);
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
        //Select Dirket or Indirekt
        rd_direkt.selectedProperty().addListener((observ, old, newV) -> {
            if (newV) {
                direkt = true;
                gewehlt = "j";
            } else {
                direkt = false;
                gewehlt = "n";
            }
        });
        rd_indirekt.selectedProperty().addListener((observ, old, newV) -> {
            if (newV) {
                direkt = false;
                gewehlt = "j";
            } else {
                direkt = true;
                gewehlt = "n";
            }
        });
    }

    @FXML
    private void ausrechnen(ActionEvent event) throws IOException {
        if (checkEingabe(tbox_anschaffungswert.getText()) || checkEingabe(tbox_nutzungsdauer.getText())) {
            System.out.println("Eingaben Falsch");
        } else {
            anschaffungswert = Double.parseDouble(tbox_anschaffungswert.getText());
            nutzungsdauer = Double.parseDouble(tbox_nutzungsdauer.getText());
            if (linear) {
                if (checkRestWert(tbox_restwert.getText())) {
                } else {
                    if (gewehlt == null) {
                        System.out.println("Eingaben Falsch");
                    } else {
                        restwert = Double.parseDouble(tbox_restwert.getText());
                        if (direkt) {
                            abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                            buchwert = restwert;
                            konto = "Anlagekonto";
                            startAusgabe();
                        } else {
                            abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                            buchwert = restwert;
                            konto = "WB";
                            startAusgabe();
                        }
                    }
                }
            } else {
                if (checkProzent(tbox_abschreibungsprozentsatz.getText())) {
                } else {
                    if (gewehlt == null) {
                        System.out.println("Eingaben Falsch");
                    } else {
                        prozent = Double.parseDouble(tbox_abschreibungsprozentsatz.getText());
                        if (direkt) {
                            rechner(anschaffungswert, prozent, nutzungsdauer);
                            konto = "Anlagekonto";
                            startAusgabe();
                        } else {
                            rechner(anschaffungswert, prozent, nutzungsdauer);
                            konto = "WB";
                            startAusgabe();
                        }
                    }
                }
            }
        }
    }

    //Rechner der Degressiven Methode
    private void rechner(double wert, double prozent, double jahre) {
        if (jahre == 0 || wert < 1) {
            buchwert = wert;
        } else {
            double preceentile = 100.0 - prozent;
            double neuerwert = wert * (preceentile / 100);
            double abzug = wert - neuerwert;
            this.abzug.add(abzug);
            this.degressiveB.add(neuerwert);
            jahre--;
            rechner(neuerwert, prozent, jahre);

        }
    }

    //Set Mainapp for this Controller
    public void setMainApp(Starter main) {
        this.main = main;
    }

    //Start ausgabe
    private void startAusgabe() throws IOException {
        main.setData(anschaffungswert, abschreibungsbetrag, buchwert, konto, degressiveB, abzug);
        main.startAusgabe();
    }

    //Check Methodes
    private boolean checkEingabe(String eingabe) {
        if (eingabe.isEmpty() || !eingabe.matches(AllowedChars) || Double.parseDouble(eingabe) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRestWert(String eingabe) {
        if (eingabe.isEmpty() || !eingabe.matches(AllowedChars) || Double.parseDouble(eingabe) > anschaffungswert) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkProzent(String eingabe) {
        if (eingabe.isEmpty() || !eingabe.matches(AllowedChars) || Double.parseDouble(eingabe) > 99 || Double.parseDouble(eingabe) == 0) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void min(ActionEvent event) throws IOException {
        main.setToMin();
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
}
