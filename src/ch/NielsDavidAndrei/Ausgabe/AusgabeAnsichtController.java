/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei.Ausgabe;

import ch.NielsDavidAndrei.Starter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    Double abschreibungsbetrag;
    Double buchwert;
    Starter main;
    @FXML
    private TableView<DegressivModel> tabelle;
    @FXML
    private TableColumn<DegressivModel, Integer> jahrCL;
    @FXML
    private TableColumn<DegressivModel, Double> abzugCL;
    @FXML
    private TableColumn<DegressivModel, Double> betragCL;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jahrCL.setCellValueFactory(new PropertyValueFactory<>("jahr"));
        abzugCL.setCellValueFactory(new PropertyValueFactory<>("abzug"));
        betragCL.setCellValueFactory(new PropertyValueFactory<>("betrag"));
    }

    public void setMainApp(Starter aThis) {
        this.main = aThis;
    }

    public void setData(double abschreibungsbetrag, double buchwert, String konto, ArrayList<Double> degressivB, ArrayList<Double> abzug) {
        System.out.println("Abschreibungsbetrag " + Double.toString(abschreibungsbetrag));
        System.out.println("Buchwert " + Double.toString(buchwert));
        System.out.println("Konto " + konto);
        for (int i = 0; i < degressivB.size(); i++) {
            double we = degressivB.get(i);
            double ab = abzug.get(i);
//            for (Double b : degressivB) {
//                System.out.println("Neuer Wert nach einem Jahr " + Double.toString(b));
//                we = b;
//            }
//            for (Double b : abzug) {
//                System.out.println("Neuer Abzug nach einem Jahr " + Double.toString(b));
//                ab = b;
//            }
            data.add(new DegressivModel(i + 1, ab, we));
        }
        tabelle.setItems(data);
    }

}
