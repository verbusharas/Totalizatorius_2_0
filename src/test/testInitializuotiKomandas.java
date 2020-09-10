package test;

import backEnd.*;

public class testInitializuotiKomandas {
    public static void main(String[] args) {
        KomanduBiblioteka komanduBiblioteka = new KomanduBiblioteka();
        Archyvuotojas<KomanduBiblioteka> komanduBibliotekosArchyvas = new Archyvuotojas<>(new Nustatymai().getKomanduFailoAdresas());
        komanduBibliotekosArchyvas.irasyti(komanduBiblioteka);
    }
}
