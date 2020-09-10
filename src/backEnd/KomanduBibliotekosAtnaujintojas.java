package backEnd;

import java.util.ArrayList;
import java.util.HashMap;

public class KomanduBibliotekosAtnaujintojas {

    private String url = "https://www.fifa.com/fifa-world-ranking/ranking-table/men/";
    private ArrayList<String> valstybiuSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuTrumpiniuSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuIdSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuTaskuSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuVeliavuURLSarasas = new ArrayList<>();
    private ArrayList<String> valstybiuFederacijuSarasas = new ArrayList<>();

    private int komanduSkaicius;
    private ArrayList<Komanda> komanduRegistras;
    private HashMap<String, Komanda> komanduZemelapis;

    public KomanduBibliotekosAtnaujintojas() {
        atnaujintiKomanduInfo();
        registruotiKomandas();
        registruotiKomanduZemelapi();
    }

    public void atnaujintiKomanduInfo() {

        Zvalgas zvalgas = new Zvalgas();

        valstybiuTrumpiniuSarasas = zvalgas.ieskoti(url, Raktazodziai.getIsoPrefix(), Raktazodziai.getIsoSuffix());
        System.out.println("Valstybių trumpinių sąrašas atnaujintas sėkmingai. Įrašų skaičius: " + valstybiuTrumpiniuSarasas.size());

        valstybiuSarasas = zvalgas.ieskoti(url, Raktazodziai.getPavadinimasPrefix1(), Raktazodziai.getPavadinimasPrefix2(), Raktazodziai.getPavadinimasSuffix());
        System.out.println("Valstybių sąrašas atnaujintas sėkmingai. Įrašų skaičius: " + valstybiuSarasas.size());

        valstybiuIdSarasas = zvalgas.ieskoti(url, Raktazodziai.getiDPrefix(), Raktazodziai.getiDSuffix());
        System.out.println("Valstybių komandų Fifa ID sąrašas atnaujintas sėkmingai. Įrašų skaičius: " + valstybiuIdSarasas.size());

        valstybiuTaskuSarasas = zvalgas.ieskoti(url, Raktazodziai.getTaskaiPrefix(), Raktazodziai.getTaskaiSuffix());
        System.out.println("Valstybių komandų turimų taškų sąrašas atnaujintas sėkmingai. Įrašų skaičius: " + valstybiuTaskuSarasas.size());

        valstybiuVeliavuURLSarasas = zvalgas.ieskotiPaveiksliuku(url, Raktazodziai.getVeliavaPrefix1(), Raktazodziai.getVeliavaPrefix2(), Raktazodziai.getVeliavaSuffix());
        System.out.println("Valstybių vėliavų URL sąrašas atnaujintas sėkmingai. Įrašų skaičius: " + valstybiuVeliavuURLSarasas.size());

        valstybiuFederacijuSarasas = zvalgas.ieskoti(url, Raktazodziai.getFederacijuPrefix(), Raktazodziai.getFederacijuSuffix());
        System.out.println("Valstybių komandų federacijų asociacijų sąrašas atnaujintas sėkmingai. Įrašų skaičius: " + valstybiuFederacijuSarasas.size());

        komanduSkaicius = valstybiuSarasas.size();

    }

    public ArrayList<String> getValstybiuSarasas() {
        return valstybiuSarasas;
    }

    public ArrayList<String> getValstybiuTrumpiniuSarasas() {
        return valstybiuTrumpiniuSarasas;
    }

    public ArrayList<String> getValstybiuIdSarasas() {
        return valstybiuIdSarasas;
    }

    public ArrayList<String> getValstybiuTaskuSarasas() {
        return valstybiuTaskuSarasas;
    }

    public ArrayList<String> getValstybiuVeliavuURLSarasas() {
        return valstybiuVeliavuURLSarasas;
    }

    public ArrayList<String> getValstybiuFederacijuSarasas() {
        return valstybiuFederacijuSarasas;
    }

    private void registruotiKomandas() {
        komanduRegistras = new ArrayList<>();
        for (int i = 0; i < komanduSkaicius; i++) {
            komanduRegistras.add(new Komanda(
                    valstybiuTrumpiniuSarasas.get(i),
                    valstybiuSarasas.get(i),
                    valstybiuVeliavuURLSarasas.get(i),
                    i+1,
                    Integer.parseInt(valstybiuTaskuSarasas.get(i)),
                    valstybiuFederacijuSarasas.get(i),
                    Integer.parseInt(valstybiuIdSarasas.get(i)))
            );
        }
    }

    private void registruotiKomanduZemelapi() {
        komanduZemelapis = new HashMap<>();
        for (int i = 0; i < komanduSkaicius; i++) {

            komanduZemelapis.put(valstybiuTrumpiniuSarasas.get(i), new Komanda(
                    valstybiuTrumpiniuSarasas.get(i),
                    valstybiuSarasas.get(i),
                    valstybiuVeliavuURLSarasas.get(i),
                    i + 1,
                    Integer.parseInt(valstybiuTaskuSarasas.get(i)),
                    valstybiuFederacijuSarasas.get(i),
                    Integer.parseInt(valstybiuIdSarasas.get(i)))
            );
        }
    }


    public ArrayList<Komanda> getKomanduRegistras() {
        return komanduRegistras;
    }

    public HashMap<String, Komanda> getKomanduZemelapis() {
        return komanduZemelapis;
    }

    public int getKomanduSkaicius() {
        return komanduSkaicius;
    }
//
//    public Komanda getRandomKomanda(int reitingasNuo, int reitingasIki) {
//
//        return komanduRegistras.get((int)(reitingasNuo + Math.random() * (reitingasIki - reitingasNuo + 1)));
//    }
//

}
