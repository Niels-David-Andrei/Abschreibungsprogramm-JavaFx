/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei.Ausgabe;

import ch.NielsDavidAndrei.Starter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Andrei Oleniuc
 */
public class AusgabeAnsichtController implements Initializable {

    Double abschreibungsbetrag;
    Double buchwert;
    Starter main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMainApp(Starter aThis) {
        this.main = aThis;
    }

    public void setData(double abschreibungsbetrag, double buchwert, double wert, double abzug) {
        System.out.println(abschreibungsbetrag);
        System.out.println(buchwert);
        System.out.println(wert);
        System.out.println(abzug);
    }

}
