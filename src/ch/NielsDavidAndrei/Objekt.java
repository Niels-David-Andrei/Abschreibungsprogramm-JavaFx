/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei;

import java.util.ArrayList;

/**
 *
 * @author Andrei Oleniuc
 */
public class Objekt {

    double anschaffungswert;
    double abschreibungsbetrag;
    double buchwert;
    String konto;
    ArrayList<Double> degressivB = new ArrayList<>();
    ArrayList<Double> abzug = new ArrayList<>();

    public Objekt() {
    }

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
