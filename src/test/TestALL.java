package test;

import backEnd.*;

import java.io.IOException;
import java.util.HashMap;

public class TestALL {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(new Nustatymai().getRungtyniuFailoAdresas());


//        Vartotojas vartotojas1 = new Vartotojas("pgriffin", "pgriffin1", "Peter", "Griffin");
//        Vartotojas vartotojas2 = new Vartotojas("mj", "mj1", "Michael", "Jordan");
//
//        vartotojuArchyvas.irasyti(vartotojas1.getUsername(), vartotojas1);
//        vartotojuArchyvas.irasyti(vartotojas2.getUsername(), vartotojas2);

//        Rungtynes rungtynes1 = new Rungtynes(1,20);
//        Rungtynes rungtynes2 = new Rungtynes(1,20);
//        Rungtynes rungtynesPO = new Rungtynes(1,20, RungtyniuTipas.PLAYOFF);
////
//        rungtyniuArchyvas.irasyti(rungtynes1.getUnikalusRungtyniuKodas(), rungtynes1);
//        rungtyniuArchyvas.irasyti(rungtynes2.getUnikalusRungtyniuKodas(), rungtynes2);
//        rungtyniuArchyvas.irasyti(rungtynesPO.getUnikalusRungtyniuKodas(), rungtynesPO);

        Vartotojas vartotojasA = (Vartotojas) vartotojuArchyvas.istrauktiSarasa().get("pgriffin");
        Vartotojas vartotojasB = (Vartotojas) vartotojuArchyvas.istrauktiSarasa().get("mj");

        Rungtynes rungtynesVienas = (Rungtynes) rungtyniuArchyvas.istrauktiSarasa().get("BRASUI201151730");
        Rungtynes rungtynesDu = (Rungtynes) rungtyniuArchyvas.istrauktiSarasa().get("PORSUI201151730");
        Rungtynes rungtynesTrysPO = (Rungtynes) rungtyniuArchyvas.istrauktiSarasa().get("CHIITA201151730");


        System.out.println(vartotojuArchyvas.istrauktiSarasa());
        System.out.println(rungtyniuArchyvas.istrauktiSarasa());


//        rungtynesVienas.addSpejimas(vartotojasA, new RezultatasGroupStage(4, 0));
//        rungtynesVienas.addSpejimas(vartotojasB, new RezultatasGroupStage(2, 0));
//        rungtynesVienas.addSpejimas(vartotojasA, new RezultatasGroupStage(4, 0));
//        rungtynesVienas.addSpejimas(vartotojasB, new RezultatasGroupStage(2, 0));
//        rungtynesVienas.pradetiRungtynes();
//        rungtynesVienas.setRezultatas(2,0);
//        rungtynesVienas.baigtiRungtynes();
//        rungtyniuArchyvas.irasyti(rungtynesVienas.getUnikalusRungtyniuKodas(), rungtynesVienas);

//        rungtynesTrysPO.addSpejimas(vartotojasA, new RezultatasPlayoffStage(2, 0));
//        rungtynesTrysPO.addSpejimas(vartotojasB, new RezultatasPlayoffStage(2, 2, 1,0));
        rungtyniuArchyvas.irasyti(rungtynesTrysPO.getUnikalusRungtyniuKodas(), rungtynesTrysPO);

        System.out.println(rungtynesVienas.getVisiSpejimai().size());
        System.out.println(rungtynesTrysPO.getVisiSpejimai().size());

        System.out.println(rungtynesVienas.getVisiSpejimai());
        System.out.println(rungtynesVienas.getVartotojoSpejimas(vartotojasA));
        System.out.println(rungtynesVienas.getVartotojoSpejimas(vartotojasB));

        System.out.println(rungtynesTrysPO.getVisiSpejimai());
        System.out.println(rungtynesTrysPO.getVartotojoSpejimas(vartotojasA));
        System.out.println(rungtynesTrysPO.getVartotojoSpejimas(vartotojasB));


        System.out.println("-------------------");
        System.out.println(rungtynesVienas);
        System.out.println("Rungtynių rezultatas: " + rungtynesVienas.getRezultatas());
        System.out.println(vartotojasA.getVardas() + " "
                + vartotojasA.getPavarde() + " spėjo " + rungtynesVienas.getVartotojoSpejimas(vartotojasA));
        System.out.println("Skirta taškų už spėjimą: "
                + new Taskai().skaiciuoti(rungtynesVienas, rungtynesVienas.getVartotojoSpejimas(vartotojasA)));
        System.out.println(vartotojasB.getVardas() + " "
                + vartotojasB.getPavarde() + " spėjo " + rungtynesVienas.getVartotojoSpejimas(vartotojasB));
        System.out.println("Skirta taškų už spėjimą: "
                + new Taskai().skaiciuoti(rungtynesVienas, rungtynesVienas.getVartotojoSpejimas(vartotojasB)));


    }

}
