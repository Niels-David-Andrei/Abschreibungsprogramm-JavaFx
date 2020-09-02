/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei.Eingabe;

import ch.NielsDavidAndrei.Starter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author Andrei Oleniuc
 */
public class HauptseiteController implements Initializable {

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
    private boolean geschriebenbox1 = false;
    private boolean geschriebenbox2 = false;
    private boolean geschriebenbox3 = false;
    @FXML
    private Label restWerttxt;
    @FXML
    private Label abschreibungsTxt;
    boolean linear;
    boolean direkt = false;
    double anschaffungswert;
    double nutzungsdauer;
    double prozent;
    double restwert;
    String gewehlt = null;
    String AllowedChars = "[0-9@.]*";

    double wert;
    ArrayList<Double> abzug = new ArrayList<>();
    double abschreibungsbetrag;
    double buchwert;
    ArrayList<Double> degressiveB = new ArrayList<>();
    String konto;

    Starter main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbox_restwert.visibleProperty().set(false);
        tbox_abschreibungsprozentsatz.visibleProperty().set(false);
        restWerttxt.visibleProperty().set(false);
        abschreibungsTxt.visibleProperty().set(false);
        rd_linear.selectedProperty().addListener((observable, oldValue, newValue) -> {
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
        rd_degressiv.selectedProperty().addListener((observable, oldValue, newValue) -> {
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

        rd_direkt.selectedProperty().addListener((observ, old, newV) -> {
            if (newV) {
                direkt = true;
                gewehlt = "j";
            } else {
                direkt = false;
                gewehlt = "n";
            }
        });
    }

    @FXML
    private void ausrechnen(ActionEvent event) throws IOException {
        if (tbox_anschaffungswert.getText().isEmpty() || tbox_nutzungsdauer.getText().isEmpty() || !tbox_anschaffungswert.getText().matches(AllowedChars) || !tbox_nutzungsdauer.getText().matches(AllowedChars)) {
            System.out.println("Eingaben Falsch");
        } else {
            anschaffungswert = Double.parseDouble(tbox_anschaffungswert.getText());
            nutzungsdauer = Double.parseDouble(tbox_nutzungsdauer.getText());

            if (linear) {
                if (tbox_restwert.getText().isEmpty() || !tbox_restwert.getText().matches(AllowedChars) || Double.parseDouble(tbox_restwert.getText()) > anschaffungswert) {

                } else {
                    if (gewehlt == null) {

                    } else {
                        restwert = Double.parseDouble(tbox_restwert.getText());
                        if (direkt) {
                            abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                            buchwert = restwert;
                            konto = "Anlagekonto";
                            main.startAusgabe(abschreibungsbetrag, buchwert, konto, degressiveB, abzug);
                        } else {
                            abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                            buchwert = restwert;
                            konto = "WB";
                            main.startAusgabe(abschreibungsbetrag, buchwert, konto, degressiveB, abzug);
                        }
                    }
                }
            } else {
                if (tbox_abschreibungsprozentsatz.getText().isEmpty() || !tbox_abschreibungsprozentsatz.getText().matches(AllowedChars) || Double.parseDouble(tbox_abschreibungsprozentsatz.getText()) > 99) {

                } else {
                    if (gewehlt == null) {

                    } else {
                        prozent = Double.parseDouble(tbox_abschreibungsprozentsatz.getText());
                        if (direkt) {
                            rechner(anschaffungswert, prozent, nutzungsdauer, "Anlagekonto");
                            konto = "Anlagekonto";
                            main.startAusgabe(abschreibungsbetrag, buchwert, konto, degressiveB, abzug);
                        } else {
                            rechner(anschaffungswert, prozent, nutzungsdauer, "WB");
                            konto = "WB";
                            main.startAusgabe(abschreibungsbetrag, buchwert, konto, degressiveB, abzug);
                        }
                    }
                }
            }
        }
    }

    private void rechner(double wert, double prozent, double jahre, String konto) {
        if (jahre == 0 || wert < 1) {
            buchwert = wert;
            System.out.println("Auf " + konto + " wird jetzt verbucht " + Double.toString(wert));
        } else {
            double preceentile = 100.0 - prozent;
            double neuerwert = wert * (preceentile / 100);
            double abzug = wert - neuerwert;
            this.abzug.add(abzug);
            this.degressiveB.add(neuerwert);
            jahre--;
            rechner(neuerwert, prozent, jahre, konto);
        }
    }

    public void setMainApp(Starter main) {
        this.main = main;
    }
}
