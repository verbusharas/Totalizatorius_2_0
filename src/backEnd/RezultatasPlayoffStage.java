package backEnd;

import java.io.Serializable;

public class RezultatasPlayoffStage extends RezultatasGroupStage implements Serializable {


    private int ivarciaiK1pagrindinisLaikas = 0;
    private int ivarciaiK2pagrindinisLaikas = 0;
    private int ivarciaiK1pratesimas = 0;
    private int ivarciaiK2pratesimas = 0;
    private int ivarciaiK1baudiniuSerija = 0;
    private int ivarciaiK2baudiniuSerija = 0;
    private boolean pratesimas = false;
    private boolean baudiniai = false;

    //    Konstruktorius atvejui kai atkrintamosiose nugalėtojas paaiškėja per pagrindinį laiką
    public RezultatasPlayoffStage(int ivarciaiKomanda1, int ivarciaiKomanda2) {
        super(ivarciaiKomanda1, ivarciaiKomanda2);
        this.ivarciaiK1pagrindinisLaikas = ivarciaiKomanda1;
        this.ivarciaiK2pagrindinisLaikas = ivarciaiKomanda2;
    }

    //Konstruktorius rungtynių pratęsimo atveju (del saugumo imamas pagrindinio laiko pirmos komandos ivarciu skaicius)
    public RezultatasPlayoffStage(int ivarciaiK1pagrindinisLaikas, int ivarciaiK2pagrindinisLaikas,
                           int ivarciaiK1pratesimas, int ivarciaiK2pratesimas) {
        super(ivarciaiK1pagrindinisLaikas + ivarciaiK1pratesimas, ivarciaiK1pagrindinisLaikas + ivarciaiK2pratesimas);
        this.ivarciaiK1pagrindinisLaikas = ivarciaiK1pagrindinisLaikas;
        this.ivarciaiK2pagrindinisLaikas = ivarciaiK1pagrindinisLaikas;
        this.ivarciaiK1pratesimas = ivarciaiK1pratesimas;
        this.ivarciaiK2pratesimas = ivarciaiK2pratesimas;
        this.ivarciaiKomanda1 = ivarciaiK1pagrindinisLaikas + ivarciaiK1pratesimas;
        this.ivarciaiKomanda2 = ivarciaiK1pagrindinisLaikas + ivarciaiK2pratesimas;
        pratesimas = true;
    }

    //Konstruktorius rungtynių pratęsimo ir baudinių serijos atveju (del saugumo imami  pirmos komandos ivarciu skaiciai)
    public RezultatasPlayoffStage(int ivarciaiK1pagrindinisLaikas, int ivarciaiK2pagrindinisLaikas,
                           int ivarciaiK1pratesimas, int ivarciaiK2pratesimas,
                           int ivarciaiK1baudiniuSerija, int ivarciaiK2baudiniuSerija) {
        super(ivarciaiK1pagrindinisLaikas + ivarciaiK1pratesimas + ivarciaiK1baudiniuSerija, ivarciaiK1pagrindinisLaikas + ivarciaiK1pratesimas + ivarciaiK2baudiniuSerija);
        this.ivarciaiK1pagrindinisLaikas = ivarciaiK1pagrindinisLaikas;
        this.ivarciaiK2pagrindinisLaikas = ivarciaiK1pagrindinisLaikas;
        this.ivarciaiK1pratesimas = ivarciaiK1pratesimas;
        this.ivarciaiK2pratesimas = ivarciaiK1pratesimas;
        this.ivarciaiK1baudiniuSerija = ivarciaiK1baudiniuSerija;
        this.ivarciaiK2baudiniuSerija = ivarciaiK2baudiniuSerija;
        this.ivarciaiKomanda1 = ivarciaiK1pagrindinisLaikas + ivarciaiK1pratesimas + ivarciaiK1baudiniuSerija;
        this.ivarciaiKomanda2 = ivarciaiK1pagrindinisLaikas + ivarciaiK1pratesimas + ivarciaiK2baudiniuSerija;
        pratesimas = true;
        baudiniai = true;
    }

    public int getIvarciaiK1pagrindinisLaikas() {
        return ivarciaiK1pagrindinisLaikas;
    }

    public int getIvarciaiK2pagrindinisLaikas() {
        return ivarciaiK2pagrindinisLaikas;
    }

    @Override
    public String toString() {
        if (baudiniai) return "" + ivarciaiKomanda1 + " : " + ivarciaiKomanda2 + "\n"
                + "RT  " + ivarciaiK1pagrindinisLaikas + " : " + ivarciaiK2pagrindinisLaikas + "\n"
                + "ET  " + ivarciaiK1pratesimas + " : " + ivarciaiK2pratesimas + "\n"
                + "PSO " + ivarciaiK1baudiniuSerija + " : " + ivarciaiK2baudiniuSerija;
        else if (pratesimas) return "" + ivarciaiKomanda1 + " : " + ivarciaiKomanda2 + "\n"
                + "RT  " + ivarciaiK1pagrindinisLaikas + " : " + ivarciaiK2pagrindinisLaikas + "\n"
                + "ET  " + ivarciaiK1pratesimas + " : " + ivarciaiK2pratesimas + "\n";
        else return "" + ivarciaiKomanda1 + " : " + ivarciaiKomanda2;
    }


}
