package backEnd;

import java.util.ArrayList;
import java.util.Scanner;

public class Zvalgas {


    public ArrayList<String> ieskoti(String urlAdresas, String prefix1, String suffix) {
        ArrayList<String> radiniuSarasas = new ArrayList<>();
        String radinys = "";
        try {
            java.net.URL url = new java.net.URL(urlAdresas);
            Scanner input = new Scanner(url.openStream());
            while (input.hasNext()) {
                int nuo = 0;
                int iki = 0;
                String eilute = input.nextLine();

                if (eilute.contains(prefix1)) {
                    nuo = eilute.indexOf(prefix1, nuo) + prefix1.length();
                    iki = eilute.indexOf(suffix, nuo);
                    radinys = eilute.substring(nuo, iki);
                    radiniuSarasas.add(radinys);
                }
            }
        } catch (Exception ex) {
            System.out.println("Klaida: " + ex.getMessage());
        }
        return radiniuSarasas;
    }


    // Overloadintas metodas, tam atvejui, kai prefixai gali buti 2 skirtingi ir abu tinkantys
    public ArrayList<String> ieskoti(String urlAdresas, String prefix1, String prefix2, String suffix) {

        ArrayList<String> radiniuSarasas = new ArrayList<>();
        String radinys = "";

        try {
            java.net.URL url = new java.net.URL(urlAdresas);
            Scanner input = new Scanner(url.openStream());

            while (input.hasNext()) {
                int nuo = 0;
                int iki = 0;
                String eilute = input.nextLine();

                if (eilute.contains(prefix1)) {
                    nuo = eilute.indexOf(prefix1, nuo) + prefix1.length();
                    iki = eilute.indexOf(suffix, nuo);
                    radinys = eilute.substring(nuo, iki);
                    radiniuSarasas.add(radinys);

                }

                if (eilute.contains(prefix2)) {
                    nuo = eilute.indexOf(prefix2, nuo) + prefix2.length();
                    iki = eilute.indexOf(suffix, nuo);
                    radinys = eilute.substring(nuo, iki);
                    radiniuSarasas.add(radinys);

                }
            }
        } catch (Exception ex) {
            System.out.println("Klaida: " + ex.getMessage());
        }
        return radiniuSarasas;
    }


    public ArrayList<String> ieskotiPaveiksliuku(String urlAdresas, String prefix1, String prefix2, String suffix) {

        ArrayList<String> radiniuSarasas = new ArrayList<>();
        String radinys = "";

        try {
            java.net.URL url = new java.net.URL(urlAdresas);
            Scanner input = new Scanner(url.openStream());

            while (input.hasNext()) {
                int nuo = 0;
                int iki = 0;
                String eilute = input.nextLine();

                if (eilute.contains(prefix1)) {
                    //peršokame į dar sekančią eilutę, nes tik joje yra reikalingas uRL:
                    eilute = input.nextLine();
                    nuo = eilute.indexOf(prefix2, nuo) + prefix2.length();
                    iki = eilute.indexOf(suffix, nuo);
                    radinys = eilute.substring(nuo, iki);
                    radiniuSarasas.add(radinys);
                }

            }
        } catch (Exception ex) {
            System.out.println("Klaida: " + ex.getMessage());
        }
        return radiniuSarasas;
    }


}



