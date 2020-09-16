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

    private static DecimalFormat df = new DecimalFormat("0.00");
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
    private Label abzuglbl;
    private Label betraglbl;
    private Label akontolbl;
    private Label wbkontolbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jahrCL.setCellValueFactory(new PropertyValueFactory<>("jahr"));
        abzugCL.setCellValueFactory(new PropertyValueFactory<>("abzug"));
        betragCL.setCellValueFactory(new PropertyValueFactory<>("betrag"));
    }

    public void setMainApp(Starter aThis) {
        this.main = aThis;
    }

    // Für die Ausgabe 
    public void setData(double anschaffungswert, double abschreibungsbetrag, double buchwert, String konto, ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        System.out.println("Abschreibungsbetrag " + Double.toString(abschreibungsbetrag));
        System.out.println("Buchwert " + Double.toString(buchwert));
        System.out.println("Konto " + konto);
        this.anschaffungswert = anschaffungswert;
        this.abschreibungsbetrag = abschreibungsbetrag;
        this.buchwert = buchwert;
        this.konto = konto;
        if (degressivB.isEmpty()) {
            hideDegressivObjects();
            linear();
        } else {
            hideLinearObjects();
            degressiv(degressivB, abzug);
        }
        if (konto.equals("Anlagekonto")) {
            kontolbl.setText("Der Betrag wird auf das Anlagekonto verbucht.");
        } else if (konto.equals("WB")) {
            kontolbl.setText("Der Betrag wird auf das WB Konto verbucht.");
        }
    }

    @FXML
    private void close(ActionEvent event) throws Exception {
        System.exit(0);
    }

    @FXML
    private void min(ActionEvent event) throws IOException {
        main.setToMin();
    }

    @FXML
    public void clickItem(MouseEvent event) {
        System.out.println(tabelle.getSelectionModel().getSelectedItem().getJahr());
        System.out.println(tabelle.getSelectionModel().getSelectedItem().getAbzug());
        System.out.println(tabelle.getSelectionModel().getSelectedItem().getBetrag());
        jahrA.setText(Integer.toString(tabelle.getSelectionModel().getSelectedItem().getJahr()));
        abzugA.setText(df.format(tabelle.getSelectionModel().getSelectedItem().getAbzug()));
        restA.setText(df.format(tabelle.getSelectionModel().getSelectedItem().getBetrag()));
    }

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

    private void degressiv(ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        for (int i = 0; i < degressivB.size(); i++) {
            double we = degressivB.get(i);
            double ab = abzug.get(i);
            data.add(new DegressivModel(i + 1, Double.parseDouble(df.format(ab)), Double.parseDouble(df.format(we))));
            jahrA.setText(Integer.toString(i + 1));
            abzugA.setText(df.format(ab));
            restA.setText(df.format(we));
        }
        tabelle.setItems(data);

    }

    private void linear() {
        if (konto.equals("Anlagekonto")) {
            linearKonto.setText("Es wird auf das Anlagekonto verbucht");
        } else if (konto.equals("WB")) {
            linearKonto.setText("Es wird auf das WB Konto verbucht");
        }
        abzugLinear.setText(df.format(abschreibungsbetrag) + " CHF");
        linearBuchwert.setText(df.format(buchwert) + " CHF");
        double verbuchteJahre = anschaffungswert - buchwert;
        verbuchteJahre /= abschreibungsbetrag;
        System.out.println((int) verbuchteJahre);
        if ((int) verbuchteJahre == 1) {
            linear4.setText("Jahr:");
        }
        linearjahr.setText(Integer.toString((int) verbuchteJahre));

    }

    @FXML
    private void zurück(ActionEvent event) throws IOException {
        main.startEingabe();
    }
}
