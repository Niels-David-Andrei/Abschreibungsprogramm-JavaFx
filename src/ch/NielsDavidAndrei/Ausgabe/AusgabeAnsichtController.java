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
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Andrei Oleniuc
 */
public class AusgabeAnsichtController implements Initializable {

    //Decimal Formatierer
    private static DecimalFormat df = new DecimalFormat("0.00");

    //ObservableList für das Anzeigen der Tabelle
    private ObservableList<DegressivModel> data = FXCollections.observableArrayList();

    //Relevante Werte für Ausgabe
    private double anschaffungswert;
    private double abschreibungsbetrag;
    private double buchwert;
    private String konto;

    //Starter Objekt
    private Starter main;

    //Tabelle und ihre Kolonnen 
    @FXML
    private TableView<DegressivModel> tabelle;
    @FXML
    private TableColumn<DegressivModel, Integer> jahrCL;
    @FXML
    private TableColumn<DegressivModel, Double> abzugCL;
    @FXML
    private TableColumn<DegressivModel, Double> betragCL;

    //Visuellen Ausgaben 
    @FXML
    private Label kontolbl;
    @FXML
    private Label jahrA;
    @FXML
    private Label abzugA;
    @FXML
    private Label restA;
    @FXML
    private Rectangle rec2;
    @FXML
    private Label beschreibung;
    @FXML
    private Label jahrinfo;
    @FXML
    private Label abzuginfo;
    @FXML
    private Label restinfo;
    @FXML
    private Rectangle rec3;
    @FXML
    private Label linear2;
    @FXML
    private Label linear3;
    @FXML
    private Label linear4;
    @FXML
    private Label linear5;
    @FXML
    private Label linearKonto;
    @FXML
    private Label linearjahr;
    @FXML
    private Label abzugLinear;
    @FXML
    private Label linearBuchwert;
    @FXML
    private Circle linearDot1;
    @FXML
    private Circle linearDot2;
    @FXML
    private Circle linearDot3;

    //Inizilisierug bei welcher die Werte der Tabelle gestzt werden, um nacher Daten reinladen zu können
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jahrCL.setCellValueFactory(new PropertyValueFactory<>("jahr"));
        abzugCL.setCellValueFactory(new PropertyValueFactory<>("abzug"));
        betragCL.setCellValueFactory(new PropertyValueFactory<>("betrag"));
    }

    //Setzt den Starter um mit Methoden des Starters aufrufen zu können
    public void setMainApp(Starter aThis) {
        this.main = aThis;
    }

    // Für die Ausgabe 
    public void setData(double anschaffungswert, double abschreibungsbetrag, double buchwert, String konto, ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        //Setzte die Werte
        this.anschaffungswert = anschaffungswert;
        this.abschreibungsbetrag = abschreibungsbetrag;
        this.buchwert = buchwert;
        this.konto = konto;

        //Schaut ob es Degessiv ist oder nicht
        if (degressivB.isEmpty()) {
            hideDegressivObjects();
            linear();
        } else {
            hideLinearObjects();
            degressiv(degressivB, abzug);
        }

        //Schaut ob es Dirket ist oder nicht
        if (konto.equals("Anlagekonto")) {
            kontolbl.setText("Der Betrag wird auf das Anlagekonto verbucht.");
        } else if (konto.equals("WB")) {
            kontolbl.setText("Der Betrag wird auf das WB Konto verbucht.");
        }
    }

    //Schliesst das Programm
    @FXML
    private void close(ActionEvent event) throws Exception {
        System.exit(0);
    }

    //Minimiert das Programm durch die Methode im Starter
    @FXML
    private void min(ActionEvent event) throws IOException {
        main.setToMin();
    }

    //Zeigt das Bestimmte Jahr mit den Werten, bei der Degressiven Tabelle
    @FXML
    public void clickItem(MouseEvent event) {
        jahrA.setText(Integer.toString(tabelle.getSelectionModel().getSelectedItem().getJahr()));
        abzugA.setText(df.format(tabelle.getSelectionModel().getSelectedItem().getAbzug()));
        restA.setText(df.format(tabelle.getSelectionModel().getSelectedItem().getBetrag()));
    }

    //Versteckt und Deaktiviert alle Visuellen Objekte falls Linear gewählt worden ist.
    //Dies bedeutet alles was mit Degressiv zu tun hat wird verschwinden
    private void hideDegressivObjects() {
        tabelle.visibleProperty().set(false);
        tabelle.disableProperty().set(true);
        jahrA.visibleProperty().set(false);
        jahrA.disableProperty().set(true);
        abzugA.visibleProperty().set(false);
        abzugA.disableProperty().set(true);
        restA.visibleProperty().set(false);
        restA.disableProperty().set(true);
        rec2.visibleProperty().set(false);
        rec2.disableProperty().set(true);
        beschreibung.visibleProperty().set(false);
        beschreibung.disableProperty().set(true);
        jahrinfo.visibleProperty().set(false);
        jahrinfo.disableProperty().set(true);
        abzuginfo.visibleProperty().set(false);
        abzuginfo.disableProperty().set(true);
        restinfo.visibleProperty().set(false);
        restinfo.disableProperty().set(true);
    }

    //Versteckt und Deaktiviert alle Visuellen Objekte falls Degressiv gewählt worden ist.
    //Dies bedeutet alles was mit Linear zu tun hat wird verschwinden
    private void hideLinearObjects() {
        rec3.visibleProperty().set(false);
        rec3.disableProperty().set(true);
        linear2.visibleProperty().set(false);
        linear2.disableProperty().set(true);
        linear3.visibleProperty().set(false);
        linear3.disableProperty().set(true);
        linear4.visibleProperty().set(false);
        linear4.disableProperty().set(true);
        linear5.visibleProperty().set(false);
        linear5.disableProperty().set(true);
        linearKonto.visibleProperty().set(false);
        linearKonto.disableProperty().set(true);
        linearjahr.visibleProperty().set(false);
        linearjahr.disableProperty().set(true);
        abzugLinear.visibleProperty().set(false);
        abzugLinear.disableProperty().set(true);
        linearBuchwert.visibleProperty().set(false);
        linearBuchwert.disableProperty().set(true);
        linearDot1.visibleProperty().set(false);
        linearDot1.disableProperty().set(true);
        linearDot2.visibleProperty().set(false);
        linearDot2.disableProperty().set(true);
        linearDot3.visibleProperty().set(false);
        linearDot3.disableProperty().set(true);

    }

    //Füllt die Tabelle durch die beiden ArrayLists, dazu werden die Daten noch auf zwei Decimalstellen gerundet
    private void degressiv(ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        for (int i = 0; i < degressivB.size(); i++) {
            //Hollt sich nur ein Jahr un deren Werte
            double we = degressivB.get(i);
            double ab = abzug.get(i);
            //Fügt diese zu Data hinzu 
            data.add(new DegressivModel(i + 1, Double.parseDouble(df.format(ab)), Double.parseDouble(df.format(we))));
            //Setzt sie Visuel ausserhalb der Tabelle
            jahrA.setText(Integer.toString(i + 1));
            abzugA.setText(df.format(ab));
            restA.setText(df.format(we));
        }
        //Setzt die Items der Tabelle
        tabelle.setItems(data);

    }

    //Zeigt alle Informationen aus welche Revelant sind falls Linear
    private void linear() {
        //Überprüft ob des Dirket oder Indirekt ist
        if (konto.equals("Anlagekonto")) {
            linearKonto.setText("Es wird auf das Anlagekonto verbucht");
        } else if (konto.equals("WB")) {
            linearKonto.setText("Es wird auf das WB Konto verbucht");
        }
        //Setzt denn Jährlichen Abzug und Rundet denn auch noch
        abzugLinear.setText(df.format(abschreibungsbetrag) + " CHF");
        //Setzt den Linearen Buchwert und Rundet denn auch noch
        linearBuchwert.setText(df.format(buchwert) + " CHF");
        //Rechnet die Anzahl Jahre aus durch dem Anschaffungswert, 
        //dem Buchwert und dem Abschreibungsbetrag
        double verbuchteJahre = anschaffungswert - buchwert;
        verbuchteJahre /= abschreibungsbetrag;
        System.out.println((int) verbuchteJahre);

        //Schaut ob nur ein Jahr gewählt worden ist und ändert das Nomen zu einzahl
        if ((int) verbuchteJahre == 1) {
            linear4.setText("Jahr:");
        }
        //Setzt die Jahre
        linearjahr.setText(Integer.toString((int) verbuchteJahre));

    }

    //Bring einem zurück zu der Eingabe
    @FXML
    private void zurück(ActionEvent event) throws IOException {
        main.startEingabe();
    }
}
