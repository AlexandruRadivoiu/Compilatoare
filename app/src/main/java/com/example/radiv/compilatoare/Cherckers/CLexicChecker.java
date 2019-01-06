package com.example.radiv.compilatoare.Cherckers;

import com.example.radiv.compilatoare.CVar;

public class CLexicChecker {

    public static boolean isNeterminal(String s)
    {

        if(CVar.neterminale.contains(s))
            return true;
        return false;
    }
    public static boolean isTerminal(String s)
    {
        if(CVar.terminale.contains(s))
            return true;
        return false;
    }
}
