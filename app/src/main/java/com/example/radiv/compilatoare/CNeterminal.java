package com.example.radiv.compilatoare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CNeterminal {
    private boolean start = false;
    String nume;

    int tipe;
    Map<Integer, List<String>> terminale;
    Map<Integer, List<CNeterminal>> neterminale;
    Map<Integer, List<CNeterminal>> main_neterminale;

    public CNeterminal(String nume) {
        this.nume = nume;
    }/*
   public CNeterminal(String nume,String input){
        this.nume=nume;
        int key = 0 ;
        List<CNeterminal> lista;
       String[] parts = input.split(" ");
       for (String part: parts) {
            if(part.equals(part.toUpperCase())) {
                new CNeterminal(part);
                neterminale.put(key,lista);
            }
       }
    }*/

}
