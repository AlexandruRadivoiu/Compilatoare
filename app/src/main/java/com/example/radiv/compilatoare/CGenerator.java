package com.example.radiv.compilatoare;

import com.example.radiv.compilatoare.Cherckers.CLexicChecker;

import java.util.List;

import bsh.Interpreter;


public class CGenerator {
    private void runString(String code) {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.set("context", this);//set any variable, you can refer to it directly from string
            interpreter.eval(code);//execute code
        } catch (Exception e) {//handle exception
            e.printStackTrace();
        }
    }

    private static StringBuilder codSursa;
    private static final String main =
            "String[] vectorString(String s) {\n" +
            " return s.split(\" \");\n" +
            "}\n" +
            "        String[] a;\n" +
            "        int pozitie=0;\n" +
            "        void main(){\n" +
            "        a = vectorString(input);\n" +
            "        " + CVar.startSimbol.nume + "();\n" +
            "        if(a[pozitie].equals(\"" + CVar.dolar + "\"))\n" +
            "        input=\"Propozitia este corecta!\";\n" +
            "        else\n" +
            "        input=\"Propozitia este gresita!\";\n" +
            "     }\n";
    private static final String sfarsit = "main();";

    private static final String inceput_functie = "       void ";
    private static final String inceput2_functie = "(){\n";
    private static final String sfarsit_functie = "     }\n";


    //   "        \n"+
    //           "        \n"+
    //           "        \n"+
    //           "        System.out.println(\"Hello World\");\n"+
    //           "        System.out.println(\"Hello World\");\n"+
    //           "        System.out.println(\"Hello World\");\n"+


    private static void next() {

    }


    private static StringBuilder getAppend(int i, int j) {
        StringBuilder returnString = new StringBuilder();

        String numarString = CVar.tabelAnaliza[i][j];
        numarString = numarString.substring(1);

        int numar = Integer.parseInt(numarString);

        int temp = 1;
        List<String> dreapta;
        for (Reguli regula : CVar.reguli) {
            if (regula.getStanga().equals(CVar.tabelAnaliza[i][0])) {
                //trebuie sa vad care
                if (numar != temp) {
                    temp++;
                    continue;
                }
                int inchide_paranteza = 0;
                if (regula.getDreapta() == null) {
                    if(CVar.tabelAnaliza[0][j].equals(CVar.dolar)){
                        returnString.append("        if(a[pozitie].equals(\"" + CVar.tabelAnaliza[0][j] + "\")){\n");
                        returnString.append("        return;\n");
                        break;
                    }
                    returnString.append("        if(a[pozitie].equals(\"" + CVar.tabelAnaliza[0][j] + "\")){\n");
                    returnString.append("        pozitie++;\n");
                  //  returnString.append(sfarsit_functie);
                    break;
                }

                dreapta = CUtil.getList(regula.getDreapta());
                returnString.append("        if(a[pozitie].equals(\"" + CVar.tabelAnaliza[0][j] + "\")){\n");
                if(CVar.tabelAnaliza[0][j].equals(CVar.dolar)){
                    returnString.append("        return;\n");
                    continue;
                }
                boolean first = true;
                for (String element : dreapta) {
                    if (CLexicChecker.isTerminal(element)) {
                        if(first) {
                            returnString.append("        pozitie++;\n");
                            first = !first;
                            continue;
                        }
                        returnString.append("        if(a[pozitie].equals(\"" + element + "\")){\n");
                        returnString.append("        pozitie++;\n");
                        inchide_paranteza++;
                    } else if (CLexicChecker.isNeterminal(element)) {
                        returnString.append("        " + element + "();\n");

                    } else continue;
                }
                while (inchide_paranteza != 0) {
                    returnString.append("        }\n");
                    inchide_paranteza--;
                }

                break;
            }

        }
        returnString.append(sfarsit_functie);
        return returnString;
    }

    /*+
                    "        " + CVar.startSimbol.nume + "();" +
                    "        if(a[pozitie].equals(" + CVar.dolar + ")\n" +
                    "        System.out.println(\"Propozitia este corecta!\");\n" +
                    "        else\n" +
                    "        System.out.println(\"Propozitia este gresita!\");\n" +
                    "     }\n" +
                    "}";*/


    public static void init() {
        codSursa = new StringBuilder();
        codSursa.append(main);

        //region asa
     /*   int nr_terminale = 0;
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
        */
        //endregion
        for (int i = 1; i < CVar.neterminale.size() + 1; i++) {
            //pentru fiecare neterminal din stanga
            codSursa.append(inceput_functie);
            codSursa.append(CVar.tabelAnaliza[i][0]);
            codSursa.append(inceput2_functie);
            for (int j = 1; j < CVar.terminale.size() + 2; j++) { //pentru fiecare coloana
                if (!CVar.tabelAnaliza[i][j].equals(CVar.eroare)) {
                    {
                        codSursa.append(getAppend(i, j));

                    }
                }
            }
            codSursa.append(sfarsit_functie);
        }
        codSursa.append(sfarsit);

        CVar.code = codSursa.toString();
    }

}
