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
package ch.NielsDavidAndrei;

import java.util.ArrayList;

/**
 * Diese Klasse ist eine Daten Haltungsklasse welche benutzt wird um Übergabe
 * Daten zu halten
 */
public class Objekt {

    //Objekt Daten und Variablen
    private double anschaffungswert;
    private double abschreibungsbetrag;
    private double buchwert;
    private String konto;
    //Die ArrayLists für die Abzüge und Beträge bei Dregressiv
    private ArrayList<Double> degressivB = new ArrayList<>();
    private ArrayList<Double> abzug = new ArrayList<>();

    //Construktor
    public Objekt() {
    }

    //Getter und Setter für die Variablen
    public double getAnschaffungswert() {
        return anschaffungswert;
    }

    public void setAnschaffungswert(double anschaffungswert) {
        this.anschaffungswert = anschaffungswert;
    }

    public double getAbschreibungsbetrag() {
        return abschreibungsbetrag;
    }

    public void setAbschreibungsbetrag(double abschreibungsbetrag) {
        this.abschreibungsbetrag = abschreibungsbetrag;
    }

    public double getBuchwert() {
        return buchwert;
    }

    public void setBuchwert(double buchwert) {
        this.buchwert = buchwert;
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
    }

    public ArrayList<Double> getDegressivB() {
        return degressivB;
    }

    public void setDegressivB(ArrayList<Double> degressivB) {
        this.degressivB = degressivB;
    }

    public ArrayList<Double> getAbzug() {
        return abzug;
    }

    public void setAbzug(ArrayList<Double> abzug) {
        this.abzug = abzug;
    }

}
