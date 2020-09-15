/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.NielsDavidAndrei.Ausgabe;

/**
 *
 * @author Andrei Oleniuc
 */
public class DegressivModel {

    private int jahr;
    private double abzug;
    private double betrag;

    public DegressivModel(int jahre, double abzug, double betrag) {
        this.jahr = jahre;
        this.abzug = abzug;
        this.betrag = betrag;
    }

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
