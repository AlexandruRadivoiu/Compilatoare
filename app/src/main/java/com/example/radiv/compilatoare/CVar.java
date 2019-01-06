package com.example.radiv.compilatoare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CVar {
    public static String[][] tabelAnaliza;
    public static List<String> terminale;
    public static List<String> neterminale;
    public static List<String> main_neterminale;
    public static List<String> dolarList = new ArrayList<String>() {
        {
            add("$");
        }
    };
    public static String eroare = "Eroare";
    public static String potrivire ="Potrivire";
    public static String dolar = "$";
    public static String accept = "Accept";
    public static String code;
    public static StringBuilder input;


    public static Map<String, List<String>> reguliProductie;
    public static CNeterminal startSimbol;
    public static List<Reguli> reguli;
}
