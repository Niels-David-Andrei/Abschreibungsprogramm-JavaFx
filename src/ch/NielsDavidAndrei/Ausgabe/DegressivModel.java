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

/**
 * Für die Werte die ausgegeben werden müssen, wenn die Abschreibung Degressiv
 * getätigt werden soll. Dazu gehören Jahr, Abzug und der neue Betrag
 */
public class DegressivModel {

    //Variablen
    private int jahr;
    private double abzug;
    private double betrag;

    //Konstruktor welche bei der Instanzierung aufgerufen wird. 
    //Speichert auch alle neuen Werte des Objektes ab
    public DegressivModel(int jahre, double abzug, double betrag) {
        this.jahr = jahre;
        this.abzug = abzug;
        this.betrag = betrag;
    }

    //Getter und Setter der Variablen
    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public double getAbzug() {
        return abzug;
    }

    public void setAbzug(double abzug) {
        this.abzug = abzug;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

}
