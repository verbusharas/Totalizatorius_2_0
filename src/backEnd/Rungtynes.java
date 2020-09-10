package backEnd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Rungtynes implements Serializable {

    private Nustatymai nustatymai = new Nustatymai();
    private Archyvuotojas<KomanduBiblioteka> komanduBibliotekosArchyvas = new Archyvuotojas<>(nustatymai.getKomanduFailoAdresas());
    private KomanduBiblioteka komanduBiblioteka = komanduBibliotekosArchyvas.istraukti();
    private Komanda komanda1;
    private Komanda komanda2;

    private String unikalusRungtyniuKodas;
    private LocalDateTime rungtyniuData;

//    private HashMap<Vartotojas, Spejimas> spejimai = new HashMap<>();
    private boolean ivyko = false;
    private boolean vyksta = false;

    private RungtyniuTipas rungtyniuTipas = RungtyniuTipas.GROUP; //default tipas - grupinės varžybos be pratęsimo, su lygiųjų galimybe

    private Rezultatas rezultatas;

    private HashMap<String, Rezultatas> rezultatoSpejimai = new HashMap<>();

    //RUNGTYNES PAGAL NURODYTAS KOMANDAS
    public Rungtynes(Komanda komanda1, Komanda komanda2) {
        this.komanda1 = komanda1;
        this.komanda2 = komanda2;
    }

    //RUNGTYNES PAGAL NURODYTUS ISO IR UNIKALŲ RUNGTYNIŲ KODĄ
    public Rungtynes(String komandos1_ISO, String komandos2_ISO, String unikalusRungtyniuKodas) throws VienodosKomandosException {
        if (komandos1_ISO == komandos2_ISO)
            throw new VienodosKomandosException("KLAIDA: rungtynes turi sudaryti skirtingos komandos!");
        else {
            komanda1 = komanduBiblioteka.getKomanduZemelapis().containsKey(komandos1_ISO) ?
                    komanduBiblioteka.getKomanduZemelapis().get(komandos1_ISO) : new Komanda();
            do {
                komanda2 = komanduBiblioteka.getKomanduZemelapis().containsKey(komandos2_ISO) ?
                        komanduBiblioteka.getKomanduZemelapis().get(komandos2_ISO) : new Komanda();
            } while (komanda2 == komanda1);
        }
        rungtyniuData = LocalDateTime.of(2020, 11, 5, 17, 30, 0);
        this.unikalusRungtyniuKodas = unikalusRungtyniuKodas;
    }

    //RUNGTYNES PAGAL NURODYTUS ISO IR UNIKALŲ RUNGTYNIŲ KODĄ SU RUNGTYNIŲ TIPO OVERLOAD
    public Rungtynes(String komandos1_ISO, String komandos2_ISO, String unikalusRungtyniuKodas, RungtyniuTipas rungtyniuTipas) throws VienodosKomandosException {

        if (komandos1_ISO == komandos2_ISO)
            throw new VienodosKomandosException("KLAIDA: rungtynes turi sudaryti skirtingos komandos!");
        else {
            komanda1 = komanduBiblioteka.getKomanduZemelapis().containsKey(komandos1_ISO) ?
                    komanduBiblioteka.getKomanduZemelapis().get(komandos1_ISO) : new Komanda();
            do {
                komanda2 = komanduBiblioteka.getKomanduZemelapis().containsKey(komandos2_ISO) ?
                        komanduBiblioteka.getKomanduZemelapis().get(komandos2_ISO) : new Komanda();
            } while (komanda2 == komanda1);
        }
        rungtyniuData = LocalDateTime.of(2020, 11, 5, 17, 30, 0);
        this.unikalusRungtyniuKodas = unikalusRungtyniuKodas;
        this.rungtyniuTipas = rungtyniuTipas;
    }

    //RANDOM RUNGTYNES
    public Rungtynes() {
        komanda1 = komanduBiblioteka.getRandomKomanda(0, 210);
        do komanda2 = komanduBiblioteka.getRandomKomanda(0, 210);
        while (komanda1.equals(komanda2));
        rungtyniuData = LocalDateTime.now();
        unikalusRungtyniuKodas = generateUnikalusRungtyniuKodas();
    }

    //RANDOM RUNGTYNES NURODYTO REITINGO INTERVALE
    public Rungtynes(int reitingasNuo, int reitingasIki) {

        komanda1 = komanduBiblioteka.getRandomKomanda(reitingasNuo, reitingasIki);
        do komanda2 = komanduBiblioteka.getRandomKomanda(reitingasNuo, reitingasIki);
        while (komanda1.equals(komanda2));
        rungtyniuData = LocalDateTime.of(2020, 11, 5, 17, 30, 0);
        unikalusRungtyniuKodas = generateUnikalusRungtyniuKodas();
    }

    //RANDOM RUNGTYNES NURODYTO REITINGO INTERVALE SU RUNGTYNIU TIPO OVERLOAD
    public Rungtynes(int reitingasNuo, int reitingasIki, RungtyniuTipas rungtyniuTipas) {

        komanda1 = komanduBiblioteka.getRandomKomanda(reitingasNuo, reitingasIki);
        do komanda2 = komanduBiblioteka.getRandomKomanda(reitingasNuo, reitingasIki);
        while (komanda1.equals(komanda2));
        rungtyniuData = LocalDateTime.of(2020, 11, 5, 17, 30, 0);
        unikalusRungtyniuKodas = generateUnikalusRungtyniuKodas();
        this.rungtyniuTipas = rungtyniuTipas;
    }

    public Komanda getKomanda1() {
        return komanda1;
    }

    public void setKomanda1(Komanda komanda1) throws VienodosKomandosException {
        if (komanda1 == this.komanda2)
            throw new VienodosKomandosException("KLAIDA: rungtynes turi sudaryti skirtingos komandos!");
        else this.komanda1 = komanda1;
    }

    public Komanda getKomanda2() {
        return komanda2;
    }

    public void setKomanda2(Komanda komanda2) throws VienodosKomandosException {
        if (komanda2 == this.komanda1)
            throw new VienodosKomandosException("KLAIDA: rungtynes turi sudaryti skirtingos komandos!");
        else this.komanda2 = komanda2;
    }

    public String getUnikalusRungtyniuKodas() {
        return unikalusRungtyniuKodas;
    }

    public void setUnikalusRungtyniuKodas(String unikalusRungtyniuKodas) {
        this.unikalusRungtyniuKodas = unikalusRungtyniuKodas;
    }

    public String generateUnikalusRungtyniuKodas() {
        String kodas = komanda1.getValstybeISO() + komanda2.getValstybeISO()
                + rungtyniuData.getYear()/100
                + rungtyniuData.getMonthValue()
                + rungtyniuData.getDayOfMonth()
                + rungtyniuData.getHour()
                + rungtyniuData.getMinute();
        return kodas;
    }

    public void setRungtyniuData(LocalDateTime dataIrLaikas) {
        this.rungtyniuData = dataIrLaikas;
    }

    public LocalDateTime getRungtyniuData() {
        return rungtyniuData;
    }

    public String getRungtyniuDataString() {
        DateTimeFormatter datosFormatas = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm");
        return rungtyniuData.format(datosFormatas);
    }

    public void pradetiRungtynes() {
        switch (rungtyniuTipas) {
            case PLAYOFF: rezultatas =  new RezultatasPlayoffStage(0,0); break;
            case LEGS: rezultatas =     new RezultatasPlayoffsWithLegs(0,0); break;
            default: rezultatas =       new RezultatasGroupStage(0,0); break;
        }
        vyksta = true;
    }

    public void baigtiRungtynes(){
        vyksta = false;
        ivyko = true;
    }

    public void setRezultatas(int komandos1ivarciai, int komandos2ivarciai) {
        rezultatas.setGalutinisRezultatas(komandos1ivarciai, komandos2ivarciai);
    }

    public Rezultatas getRezultatas() {
        return rezultatas;
    }

    public boolean arIvyko() {
        return ivyko;
    }

    public boolean arVyksta() {
        return vyksta;
    }

    public RungtyniuTipas getRungtyniuTipas() {
        return rungtyniuTipas;
    }

    public void setRungtyniuTipas(RungtyniuTipas rungtyniuTipas) {
        this.rungtyniuTipas = rungtyniuTipas;
    }

    public void addSpejimas(Vartotojas spejantisVartotojas, Rezultatas spejamasRezultatas) {
        rezultatoSpejimai.put(spejantisVartotojas.getUsername(), spejamasRezultatas);
    }

    public void removeSpejimas (Vartotojas istrintasVartotojas) {
        rezultatoSpejimai.remove(istrintasVartotojas.getUsername());
    }

    public HashMap<String, Rezultatas> getVisiSpejimai() {
    return rezultatoSpejimai;
    }

    public Rezultatas getVartotojoSpejimas(Vartotojas vartotojas) {
        return rezultatoSpejimai.get(vartotojas.getUsername());
    }

    @Override
    public String toString() {
        return "Rungtynes{" +
                komanda1.getValstybe() + " vs " + komanda2.getValstybe() +
                ", unikalusRungtyniuKodas='" + unikalusRungtyniuKodas + '\'' +
                ", rungtyniuData=" + rungtyniuData.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")) +
                '}' + "\n";
    }
}
