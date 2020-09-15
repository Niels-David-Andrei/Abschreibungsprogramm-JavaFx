/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            data.add(new DegressivModel(i + 1, Double.parseDouble(df.format(ab)), Double.parseDouble(df.format(we))));
            jahrA.setText(Integer.toString(i + 1));
            abzugA.setText(df.format(ab));
            restA.setText(df.format(we));
        }
        this.anschaffungswert = anschaffungswert;
        this.abschreibungsbetrag = abschreibungsbetrag;
        this.buchwert = buchwert;
        this.konto = konto;
        tabelle.setItems(data);
        
        if (konto.equals("Anlagekonto")) {
            kontolbl.setText("Konto: Der Betrag wird auf\ndas Anlagekonto verbucht.");
        } else if (konto.equals("WB")) {
            kontolbl.setText("Konto: Der Betrag wird auf\ndas WBKonto verbucht.");
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
}
