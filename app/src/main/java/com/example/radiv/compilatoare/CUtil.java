
package com.example.radiv.compilatoare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

public class CUtil {
    public static Class generateClass(String className, String theCode)
            throws CannotCompileException {
       // ClassPool pool = ClassPool.getDefault();
        ClassPool pool = new ClassPool(true);
      //  pool.importPackage("java.lang");
        CtClass cc = pool.makeClass(className);


        cc.addField(CtField.make("private static Ljava.lang.String[] a;", cc));
        cc.addField(CtField.make("  private static int pozitie=0;", cc));
        cc.addMethod(CtMethod.make(theCode, cc));
        return cc.toClass();
    }

    public static String[] vectorString(String s) {
        return s.split(" ");
    }

    public static List<CNeterminal> getNeterminale(String input) {
        String[] parts = input.split(" ");
        List<CNeterminal> returnList = new ArrayList<CNeterminal>();
        for (String part : parts) {
            returnList.add(new CNeterminal(part));
        }
        return returnList;
    }

    public static List<Reguli> toList(Map<String, List<String>> P) {
        List<Reguli> returnList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry :
                P.entrySet()) {
            for (String value :
                    entry.getValue()) {
                returnList.add(new Reguli(entry.getKey(), value));
            }
        }
        return returnList;
    }

    public static String afiseazaReguli(Map<String, List<String>> P) {
        String result = "";
        for (Map.Entry<String, List<String>> entry : P.entrySet()) {
            for (String string : entry.getValue()) {
                if (string == "")
                    string = "epsilon";
                result += entry.getKey() + "  :  " + string + "\n";
            }

        }


        return result;
    }

    public static CNeterminal getStart(String input) {
        return new CNeterminal(input);
    }

    public static List<String> getList(String input) {
        if (input == null)
            return new ArrayList<>(Arrays.asList(""));
        String[] parts = input.split(" ");
        List<String> returnList = new ArrayList<>();
        for (String part : parts) {
            if(parts.length>1) {
                if (!part.isEmpty()) {
                    returnList.add(part);
                }
                else
                    continue;
            }
            else
                returnList.add(part);
        }
        return returnList;
    }

    public static List<Reguli> convertReguliProductie(Map<String,List<String>>reguliProductie){
        List<Reguli> reguli = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : reguliProductie.entrySet()) {
            for (String x : entry.getValue()) {
                Reguli nou = new Reguli(entry.getKey(),x);
                reguli.add(nou);
            }
        }
        return reguli;
    }
    public static List<String> afisareReguliProductie(Map<String, List<String>> P) {
        List<String> returnList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : P.entrySet()) {
            for (String x : entry.getValue()) {
                returnList.add(entry.getKey() + " trece in " + x);
            }
        }
        return returnList;
    }
}
