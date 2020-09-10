package backEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class KomanduBiblioteka implements Serializable {

    private ArrayList<String> valstybiuSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuTrumpiniuSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuIdSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuTaskuSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuVeliavuURLSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuFederacijuSarasas = new ArrayList<>();

    private int komanduSkaicius;
    private ArrayList<Komanda> komanduRegistras;
    private HashMap<String, Komanda> komanduZemelapis;

    public KomanduBiblioteka() {
        atnaujintiKomanduBiblioteka();
    }

    public void atnaujintiKomanduBiblioteka(){
        KomanduBibliotekosAtnaujintojas komanduBibliotekosAtnaujintojas = new KomanduBibliotekosAtnaujintojas();
        valstybiuSarasas = komanduBibliotekosAtnaujintojas.getValstybiuSarasas();
        valstybiuTrumpiniuSarasas = komanduBibliotekosAtnaujintojas.getValstybiuTrumpiniuSarasas();
        valstybiuIdSarasas = komanduBibliotekosAtnaujintojas.getValstybiuIdSarasas();
        valstybiuTaskuSarasas = komanduBibliotekosAtnaujintojas.getValstybiuTaskuSarasas();
        valstybiuVeliavuURLSarasas = komanduBibliotekosAtnaujintojas.getValstybiuVeliavuURLSarasas();
        valstybiuFederacijuSarasas = komanduBibliotekosAtnaujintojas.getValstybiuFederacijuSarasas();
        komanduSkaicius = komanduBibliotekosAtnaujintojas.getKomanduSkaicius();
        komanduRegistras = komanduBibliotekosAtnaujintojas.getKomanduRegistras();
        komanduZemelapis = komanduBibliotekosAtnaujintojas.getKomanduZemelapis();
    }

    public HashMap<String, Komanda> getKomanduZemelapis() {
        return komanduZemelapis;
    }

    public Komanda getRandomKomanda(int reitingasNuo, int reitingasIki) {
        return komanduRegistras.get((int)(reitingasNuo + Math.random() * (reitingasIki - reitingasNuo + 1)));
    }

}
