package com.example.radiv.compilatoare;

public class Reguli {
    String stanga;
    String dreapta;

    public Reguli() {
    }

    public Reguli(String stanga, String dreapta) {
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public String getStanga() {
        return stanga;
    }

    public void setStanga(String stanga) {
        this.stanga = stanga;
    }

    public String getDreapta() {
        return dreapta;
    }

    public void setDreapta(String dreapta) {
        this.dreapta = dreapta;
    }
}
