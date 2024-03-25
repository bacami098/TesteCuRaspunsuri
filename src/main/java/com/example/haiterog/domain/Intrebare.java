package com.example.haiterog.domain;

import java.io.Serializable;

public class Intrebare extends Entity implements Serializable
{
    public Intrebare(int id, String text, String raspunsA, String raspunsB, String raspunsC, String corect, int punctaj) {
        super(id);
        this.text = text;
        this.raspunsA = raspunsA;
        this.raspunsB = raspunsB;
        this.raspunsC = raspunsC;
        this.corect = corect;
        this.punctaj = punctaj;
    }

    private String text;
    private String raspunsA;
    private String raspunsB;
    private String raspunsC;
    private String corect;
    private int punctaj;

    public String getText() {
        return text;
    }

    public String getRaspunsA() {
        return raspunsA;
    }

    public String getRaspunsB() {
        return raspunsB;
    }

    public String getRaspunsC() {
        return raspunsC;
    }

    public String getCorect() {
        return corect;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRaspunsA(String raspunsA) {
        this.raspunsA = raspunsA;
    }

    public void setRaspunsB(String raspunsB) {
        this.raspunsB = raspunsB;
    }

    public void setRaspunsC(String raspunsC) {
        this.raspunsC = raspunsC;
    }

    public void setCorect(String corect) {
        this.corect = corect;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "id='" + this.getId() + '\'' +
                ", text='" + text + '\'' +
                ", raspunsA='" + raspunsA + '\'' +
                ", raspunsB='" + raspunsB + '\'' +
                ", raspunsC='" + raspunsC + '\'' +
                ", corect='" + corect + '\'' +
                ", punctaj=" + punctaj +
                '}';
    }
}
