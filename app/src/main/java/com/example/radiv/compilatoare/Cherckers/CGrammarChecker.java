package com.example.radiv.compilatoare.Cherckers;

import com.example.radiv.compilatoare.CChecker;
import com.example.radiv.compilatoare.CVar;
import com.example.radiv.compilatoare.Reguli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CGrammarChecker {
    /*
    public static boolean isAmbigous1(Map<String, List<String>> map) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                for (int i = 0; i < entry.getValue().size() - 1; i++) {
                    String x = entry.getValue().get(i);
                    if(x!=null)
                        for (int j = i + 1; j < entry.getValue().size(); j++) { //caut in continu;
                            String y = entry.getValue().get(j);
                            if(y.length()>0)
                                if (x.substring(0, 1).contains(y.substring(0, 1)))
                                    return true;
                        }
                }
            }
        }
        return false;
    }

    public static boolean isAmbigous2(Map<String, List<String>> map) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                String x = entry.getValue().get(i);
                if (!x.isEmpty()) {
                    if(entry.getKey().length()<=x.length())
                        if (entry.getKey().contains(x.substring(0, entry.getKey().length()))) {
                            return true;
                        }
                }
            }
        }
        return false;
    }



    public static Map<String, List<String>> doIt(int t, List<String> lastList, String Neterminal) {
        Map<String, List<String>> returnMap = new HashMap<>(); //fac o returnare
        String newNeterminal = Neterminal; //denumirea noului neterminal
        while (CVar.neterminale.contains(newNeterminal)) {
            newNeterminal += "'";
        }
        List<String> alist = new ArrayList<>(); //lista de stringuri pentru vechiul
        alist.add(lastList.get(0).substring(0, t) + newNeterminal);//adaugarea listei
        returnMap.put(Neterminal, alist); //maparea neterminalului cu lista

        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < lastList.size(); i++) {
            String actual = lastList.get(i);
            returnList.add(actual.substring(t, actual.length()));
        }
        returnMap.put(newNeterminal, returnList);
        CVar.neterminale.add(newNeterminal);
        // while (CChecker.isAmbigous1(returnMap)) {
        //  returnMap = doCorrectMap(returnMap);
        //}
        return returnMap;
    }

    public static Map<String, List<String>> doCorrectMap(Map<String, List<String>> returnMap) {
        Map<String, List<String>> newMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : returnMap.entrySet()) { //pentru fiecare set key-value
            if (entry.getValue().size() > 1) { //daca sunt mai multe treceri de la un Neterminal
                List<String> listaProblemeRezolvate = new ArrayList<>();
                List<String> listaProblemeActuale = new ArrayList<>();
                int size = entry.getValue().size();
                for (int i = 0; i < size; i++) { //pentru fiecare valoare din key
                    //lista pentru a salva trecerile care seamana
                    String x = entry.getValue().get(i); //punem valoarea in x
                    int nrLitereMatch = 0;
                    if (listaProblemeRezolvate.contains(x))
                        continue; //inseamna ca am ajuns la unul deja extras
                    int nrLitereMatchNou; //initializam nrliterenou
                    for (int t = 1; t <= x.length(); t++) {
                        nrLitereMatchNou = 0;
                        if (i != entry.getValue().size() - 1) {
                            for (int j = i + 1; j < entry.getValue().size(); j++) { //caut in continu;
                                String y = entry.getValue().get(j);
                                if (t > y.length())
                                    break;
                                if (x.substring(0, t).contains(y.substring(0, t))) {
                                    nrLitereMatchNou++;
                                    if (!listaProblemeActuale.contains(x))
                                        listaProblemeActuale.add(x);
                                    if (!listaProblemeActuale.contains(y))
                                        listaProblemeActuale.add(y);
                                }
                            }
                        }
                        if (nrLitereMatchNou > nrLitereMatch)
                            nrLitereMatch = nrLitereMatchNou;
                        else if (nrLitereMatchNou < nrLitereMatch) {
                            Map<String, List<String>> returnedMap = CChecker.doIt(t - 1, listaProblemeActuale, entry.getKey());
                            for (Map.Entry<String, List<String>> tempMap : returnedMap.entrySet()) {
                                if (!newMap.containsKey(tempMap.getKey())) {
                                    newMap.put(tempMap.getKey(), tempMap.getValue());
                                } else
                                    newMap.get(tempMap.getKey()).addAll(tempMap.getValue());
                            }
                            listaProblemeRezolvate.addAll(listaProblemeActuale);
                            listaProblemeActuale.clear();
                            break;
                        }
                    }
                    if (nrLitereMatch == 0)
                        if (!newMap.containsKey(entry.getKey())) {
                            List<String> altaLista = new ArrayList<>();
                            altaLista.add(entry.getValue().get(i));
                            newMap.put(entry.getKey(), altaLista);
                        } else {
                            newMap.get(entry.getKey()).add(entry.getValue().get(i));
                            //  break;
                        }
                }
            } else
                newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }

    public static Map<String, List<String>> doCorrect2Map(Map<String, List<String>> returnMap) {
        Map<String, List<String>> newMap = new HashMap<>();
        List<String> newList = new ArrayList<>();
        boolean problemafixata = false;
        for (Map.Entry<String, List<String>> entry : returnMap.entrySet()) {
            String newNeterminal;
            if (!problemafixata) {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    String x = entry.getValue().get(i);
                    if (entry.getKey().contains(x.substring(0, entry.getKey().length()))) {
                        problemafixata = true;
                        newNeterminal = entry.getKey(); //denumirea noului neterminal
                        while (CVar.neterminale.contains(newNeterminal)) {
                            newNeterminal += "'";
                        }
                        for (int j = 0; j < entry.getValue().size(); j++) {
                            if (i != j) {

                                String n = entry.getValue().get(j);
                                String y = entry.getValue().get(j);
                                y += newNeterminal;

                                // entry.getValue().remove(n);
                                //entry.getValue().add(y);
                                newList.add(y);
                            }
                        }
                        //entry.getValue().remove(i);
                        //         x=x.replace(x,x.substring(v.length(),x.length()));
                        List<String> list = new ArrayList<>();
                        x = x.replace(x, x.substring(entry.getKey().length(), x.length()));
                        x += newNeterminal;
                        String empty = "";
                        list.add(x);
                        list.add(empty);
                        if (!newMap.containsKey(entry.getKey()))
                            newMap.put(entry.getKey(), newList);
                        else newMap.get(entry.getKey()).addAll(newList);
                        if (!newMap.containsKey(newNeterminal))
                            newMap.put(newNeterminal, list);
                        else newMap.get(newNeterminal).addAll(list);

                        CVar.neterminale.add(newNeterminal);
                        break;
                    }
                }
            }
            if (!newMap.containsKey(entry.getKey()))
                newMap.put(entry.getKey(), entry.getValue());

        }
        return newMap;
    }

    public static Map<String, List<String>> eliminareAcelasiInceput(List<Reguli> reguliProductie) {
        Map<String, List<String>> returnMap = CChecker.doMap(reguliProductie);
        while (isAmbigous1(returnMap)) {
            returnMap = CChecker.doCorrectMap(returnMap);
        }
        while (isAmbigous2(returnMap)) {
            returnMap = CChecker.doCorrect2Map(returnMap);
        }
        return returnMap;
    }
    private static Map<String, List<String>> toMap(List<Reguli> reguliProductie) {
        Map<String, List<String>> returnMap = new HashMap<>();
        List<String> listaNeterminale = new ArrayList<>();
        for (Reguli regula : reguliProductie) {
            List<String> list = new ArrayList<>();
            if (!listaNeterminale.contains(regula.getStanga())) {
                listaNeterminale.add(regula.getStanga());
                list.add(regula.getDreapta());
                returnMap.put(regula.getStanga(), list);
            } else
                returnMap.get(regula.getStanga()).add(regula.getDreapta());
        }
        return returnMap;
    }
    public static boolean isOkay(List<Reguli> P)
    {
        Map<String,List<String>> Map = CGrammarChecker.toMap(P);
        while(CGrammarChecker.acelasiInceput)
    }
*/
}
