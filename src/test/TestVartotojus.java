package test;

import backEnd.*;

import java.io.IOException;
import java.util.HashMap;

public class TestVartotojus {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());

        System.out.println(vartotojuArchyvas.istrauktiSarasa());
//        Archyvuotojas<HashMap<String, Vartotojas>> vartotojuArchyvuotojas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
//        HashMap<String,Vartotojas> registruotiVartotojai = vartotojuArchyvuotojas.istrauktiSarasa();
//
//        Vartotojas vartotojas = new Vartotojas("jonux", "jonux1", "Jonas", "Jonietis");
//        vartotojuArchyvuotojas.irasyti(vartotojas.getUsername(), vartotojas);
//
//        System.out.println(registruotiVartotojai);
//        System.out.println(vartotojuArchyvuotojas.istrauktiSarasa());

    }

}
