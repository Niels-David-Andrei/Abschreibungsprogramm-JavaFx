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
package ch.NielsDavidAndrei.Ausgabe;

import ch.NielsDavidAndrei.Starter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Andrei Oleniuc
 */
public class AusgabeAnsichtController implements Initializable {

    private ObservableList<DegressivModel> data = FXCollections.observableArrayList();
    double anschaffungswert;
    double abschreibungsbetrag;
    double buchwert;
    String konto;
    Starter main;
    @FXML
    private TableView<DegressivModel> tabelle;
    @FXML
    private TableColumn<DegressivModel, Integer> jahrCL;
    @FXML
    private TableColumn<DegressivModel, Double> abzugCL;
    @FXML
    private TableColumn<DegressivModel, Double> betragCL;
    @FXML
    private Label kontolbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jahrCL.setCellValueFactory(new PropertyValueFactory<>("jahr"));
        abzugCL.setCellValueFactory(new PropertyValueFactory<>("abzug"));
        betragCL.setCellValueFactory(new PropertyValueFactory<>("betrag"));
        
    }

    public void setMainApp(Starter aThis) {
        this.main = aThis;
    }

    public void setData(double anschaffungswert, double abschreibungsbetrag, double buchwert, String konto, ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        System.out.println("Abschreibungsbetrag " + Double.toString(abschreibungsbetrag));
        System.out.println("Buchwert " + Double.toString(buchwert));
        System.out.println("Konto " + konto);
        for (int i = 0; i < degressivB.size(); i++) {
            double we = degressivB.get(i);
            double ab = abzug.get(i);
            data.add(new DegressivModel(i + 1, ab, we));
        }
        this.anschaffungswert = anschaffungswert;
        this.abschreibungsbetrag = abschreibungsbetrag;
        this.buchwert = buchwert;
        this.konto = konto;
        tabelle.setItems(data);
        
        if(konto.equals("Anlagekonto")){
            kontolbl.setText("Konto: Der Betrag wird auf\ndas Anlagekonto verbucht.");
        }else if (konto.equals("WB")){
            kontolbl.setText("Konto: Der Betrag wird auf\ndas WBKonto verbucht.");
        }
    }

}
