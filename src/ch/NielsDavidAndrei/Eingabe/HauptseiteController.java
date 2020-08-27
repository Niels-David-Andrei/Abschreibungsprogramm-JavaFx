/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei.Eingabe;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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
    boolean linear = false;
    boolean direkt = false;
    double anschaffungswert;
    double nutzungsdauer;
    double prozent;
    double restwert;

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
            } else {
                direkt = false;
            }
        });
    }

    @FXML
    private void ausrechnen(ActionEvent event) {
        anschaffungswert = Double.parseDouble(tbox_anschaffungswert.getText());
        nutzungsdauer = Double.parseDouble(tbox_nutzungsdauer.getText());
        if (linear) {
            restwert = Double.parseDouble(tbox_restwert.getText());
            if (direkt) {
                double abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                double buchwert = restwert;
                System.out.println("Abschreibungsbetrag pro Jahr und Buchwert auf dem Anlagekonto: " + Double.toString(abschreibungsbetrag) + ", Buchwert ist: " + Double.toString(buchwert));
            } else {
                double abschreibungsbetrag = (anschaffungswert - restwert) / nutzungsdauer;
                double buchwert = restwert;
                System.out.println("Abschreibungsbetrag pro Jahr und Buchwert auf dem WB: " + Double.toString(abschreibungsbetrag) + ", Buchwert ist: " + Double.toString(buchwert));
            }
        } else {
            prozent = Double.parseDouble(tbox_abschreibungsprozentsatz.getText());
            if (direkt) {
                rechner(anschaffungswert, prozent, nutzungsdauer, "Anlagekonto");
            } else {
                rechner(anschaffungswert, prozent, nutzungsdauer, "WB");
            }
        }
    }

    private void rechner(double wert, double prozent, double jahre, String konto) {
        if (jahre == 0.0) {
            System.out.println("Auf " + konto + " wird jetzt verbucht " + Double.toString(wert));
        } else {
            double preceentile = 100.0 - prozent;
            double neuerwert = wert * (preceentile / 100);
            double abzug = wert - neuerwert;
            System.out.println("Der neue Wert ist: " + Double.toString(wert) + " neu Abgezogen wird: " + Double.toString(abzug));
            jahre--;
            rechner(neuerwert, prozent, jahre, konto);
        }
    }

}
