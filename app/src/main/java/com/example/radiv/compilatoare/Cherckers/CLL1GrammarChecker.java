package com.example.radiv.compilatoare.Cherckers;

import com.example.radiv.compilatoare.CUtil;
import com.example.radiv.compilatoare.CVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CLL1GrammarChecker {

    private static List<String> r = new ArrayList<>();

    private static void initTabelAnaliza() {
        int nr_terminale = 0;
        CVar.tabelAnaliza = new String[CVar.terminale.size() + CVar.neterminale.size() + 2][CVar.terminale.size() + 2];
        for (String[] row : CVar.tabelAnaliza)
            Arrays.fill(row, CVar.eroare);
        CVar.tabelAnaliza[0][0] = "";
        CVar.tabelAnaliza[CVar.terminale.size() + CVar.neterminale.size() + 1][CVar.terminale.size() + 1] = CVar.accept;
        for (int j = 1; j < CVar.terminale.size() + 2; j++) {
            if (j == CVar.terminale.size() + 1) {
                CVar.tabelAnaliza[0][j] = CVar.dolar;
                continue;
            }
            CVar.tabelAnaliza[0][j] = CVar.terminale.get(j - 1);
        }
        for (int i = 1; i < CVar.terminale.size() + CVar.neterminale.size() + 2; i++) {
            if (i == CVar.terminale.size() + CVar.neterminale.size() + 1) {
                CVar.tabelAnaliza[i][0] = CVar.dolar;
            } else {
                if (i < CVar.neterminale.size() + 1)
                    CVar.tabelAnaliza[i][0] = CVar.neterminale.get(i - 1);
                else {
                    CVar.tabelAnaliza[i][0] = CVar.terminale.get(nr_terminale);
                    CVar.tabelAnaliza[i][i - CVar.neterminale.size()] = CVar.potrivire;
                    nr_terminale++;
                }
            }
        }
    }

    private static void setTabelAnaliza(String neterminal, List<String> terminale, String R) {
        for (String terminal : terminale) {
            int i;
            for (i = 1; i < CVar.tabelAnaliza[0].length; i++) {
                if (CVar.tabelAnaliza[0][i].equals(terminal))
                    break;
            }
            for (String[] row : CVar.tabelAnaliza) {
                if (row[0].equals(neterminal)) {
                    row[i] = R;
                    break;
                }
            }
        }
        int a = 2;
    }


    //   double[] doubleArray = { 7.0, 9.0, 5.0, 1.0, 3.0 };
    //  System.out.println(Arrays.toString(doubleArray));
    private static Map<String, List<String>> mReguli;


    private static List<String> FOLLOW(String Neterminal) {


        List<String> returnList = new ArrayList<>();
        List<String> resolved = new ArrayList<>();
        /*{
            int numar_bucla = 0;
            for (String item : bucla) {
                if (item.equals(Neterminal))
                    numar_bucla++;
            }
            if(numar_bucla >5)
                return returnList;
        }*/
        if(bucla.contains(Neterminal))
            return returnList;

        bucla.add(Neterminal);
        for (Map.Entry<String, List<String>> entry : mReguli.entrySet()) {
            for (String D : entry.getValue()) {
                List<String> listElementeParteaDreapta = CUtil.getList(D);
                if (listElementeParteaDreapta != null)
                    for (int i = 0; i < listElementeParteaDreapta.size(); i++) {
                        if (listElementeParteaDreapta.get(i).equals(Neterminal)) {
                            if (i != listElementeParteaDreapta.size() - 1) {
                                if (CLexicChecker.isTerminal(listElementeParteaDreapta.get(i + 1))) {
                                    returnList.add(listElementeParteaDreapta.get(i + 1));
                                    continue;
                                } else if (CLexicChecker.isNeterminal(listElementeParteaDreapta.get(i + 1))) {
                                    for (Map.Entry<String, List<String>> entry2 : mReguli.entrySet()) {  //il caut si il trimit
                                        if (entry2.getKey().equals(listElementeParteaDreapta.get(i + 1))) {
                                            if (!resolved.contains(listElementeParteaDreapta.get(i + 1))) {
                                                resolved.add(listElementeParteaDreapta.get(i + 1));
                                                returnList.addAll(getSimboluriDirector(entry2));
                                            }
                                            continue;
                                        }
                                    }
                                }
                            } else {
                                if (!entry.getKey().equals(listElementeParteaDreapta.get(i)))
                                {

                                    if(listElementeParteaDreapta.get(i).equals(Neterminal));
                                    returnList.addAll(FOLLOW(entry.getKey()));


                                }
                            }

                        }
                    }
            }
        }
        return returnList;
    }

    private static List<String> FIRST(String primul_element) {
        List<String> returnList = new ArrayList<>();
        if (CLexicChecker.isTerminal(primul_element)) { //daca este  terminal adaugam
            returnList.add(primul_element);
            return returnList;
        } else if (CLexicChecker.isNeterminal(primul_element)) { //daca e neterminal trebuie sa l analizez ca la inceput
            for (Map.Entry<String, List<String>> entry : mReguli.entrySet()) {  //il caut si il trimit
                if (entry.getKey().equals(primul_element)) {
                    returnList.addAll(getSimboluriDirector(entry));
                    return returnList;
                }
            }
        }


        return returnList;
    }

    private static List<String> getFirstOrFollow(String Neterminal, String primul_element) { //deci cu asta ma duc in primul element dintr o liniee
        List<String> returnList = new ArrayList<>();
        //region coment
        /*if (CLexicChecker.isTerminal(primul_element)) { //daca este  terminal adaugam
            returnList.add(primul_element);
            return returnList;
        } else if (CLexicChecker.isNeterminal(primul_element)) { //daca e neterminal trebuie sa l analizez ca la inceput
            for (Map.Entry<String, List<String>> entry : mReguli.entrySet()) {  //il caut si il trimit
                if (entry.getKey().equals(primul_element)) {
                    returnList.addAll(getSimboluriDirector(entry));
                    return returnList;
                }
            }
        } */
        //endregion
        if (!primul_element.isEmpty()) {
            returnList.addAll(FIRST(primul_element));
            return returnList;
        } else {
            returnList.addAll(FOLLOW(Neterminal));
            return returnList;
        }
    }


   private static List<String> bucla;
    private static List<String> getSimboluriDirector(Map.Entry<String, List<String>> entry) {
        List<String> returnList = new ArrayList<>();
        int nr_regula = 1;

        for (String D : entry.getValue()) { //pentru fiecare regula dreapta din neterminal
            List<String> listElementeParteaDreapta = CUtil.getList(D);
            List<String> reg = new ArrayList<>();
            reg.addAll(getFirstOrFollow(entry.getKey(), listElementeParteaDreapta.get(0)));
            returnList.addAll(reg);
            setTabelAnaliza(entry.getKey(), reg, "R" + String.valueOf(nr_regula));
            if (D == null) {

                setTabelAnaliza(entry.getKey(), CVar.dolarList, "R" + String.valueOf(nr_regula));
            }
            nr_regula++;
        }
        return returnList;
    }

    public static boolean isOkay(Map<String, List<String>> P) {
        //in functia asta returnez true daca intersectie multimilor director este vida sau nu
        mReguli = P;
        Map<String, List<String>> multimeSimboluriDirectori = new HashMap<>();
        initTabelAnaliza();
        for (Map.Entry<String, List<String>> entry : P.entrySet()) { //pentru fiecare neterminal !
            bucla  = new ArrayList<>();
            List<String> intersectie = new ArrayList<>(); //cu asta verific daca exista D-ul

            intersectie.addAll(getSimboluriDirector(entry));
            //    List<String> checkIntersectie = intersectie;
            //  for (String i : checkIntersectie) {
            //    checkIntersectie.remove(i);
            //  if (checkIntersectie.contains(i)) {
            //    System.out.println("naspa");
            //}
            //grav tata
            //}
            //daca lista are elemente comune RETURN FALSE;
            int d1 = 2;
            int d3 = 2;
            int d4 = 2;
        }
        int c = 2;
        return true;
    }
}
