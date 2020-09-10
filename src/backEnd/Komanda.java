package backEnd;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Komanda implements Serializable {

    private String  valstybeISO;
    private String  valstybe;
    private String  valstybesVeliavosURL;
//    private ImageView valstybesVeliava = new ImageView();
    private int     komandosFifaReitingas;
    private int     komandosFifaID;
    private int     komandosTaskai;
    private String  komandosFederacija;
    private String  komandosFederacijaLT;
    private int     komandosPajegumoKrepselis;


    public Komanda () {
        //KOMANDOS KONSTRUKTORIUS KLAIDOS ATVEJU
        this.valstybeISO = "N_A";
        this.valstybe = "N_A";
        this.valstybesVeliavosURL = "N_A";
        this.komandosFifaReitingas = 0;
        this.komandosTaskai =  0;
        this.komandosFederacija = "N_A";
        this.komandosFederacijaLT = "N_A";
        this.komandosFifaID = 0;
        komandosPajegumoKrepselis = -1;
    }

    public Komanda (String valstybeISO, String valstybe, String valstybesVeliavosURL,
                     int komandosFifaReitingas, int komandosTaskai, String komandosFederacija, int komandosFifaID) {
    this.valstybeISO = valstybeISO;
    this.valstybe = valstybe;
    this.valstybesVeliavosURL = valstybesVeliavosURL;
//    this.valstybesVeliava = new ImageView(new Image(valstybesVeliavosURL));
    this.komandosFifaReitingas = komandosFifaReitingas;
    this.komandosTaskai =  komandosTaskai;
    this.komandosFederacija = komandosFederacija;
    this.komandosFederacijaLT = isSifruot(komandosFederacija);
    this.komandosFifaID = komandosFifaID;
    komandosPajegumoKrepselis = komandosFifaReitingas<51 ? komandosFifaReitingas/10+1 : 0; // LAIKINAS, po to reikia padaryti skaičiavimą pagal fed.asociacijas
}



    private String isSifruot(String komandosFederacija) {

        switch (komandosFederacija) {
            case "CONMEBOL":
                return "Pietų Amerikos Futbolo Konfederacija";

            case "CAF":
                return "Afrikos futbolo konfederacija";

            case "CONCACAF":
                return "Šiaurės, Centr. Amerikos ir Karibų asociacijų futbolo konfederacija";

            case "UEFA":
                return "Europos futbolo federacijų asociacija";

            case "OFC":
                return "Okeanijos futbolo konfederacija";

            case "AFC":
                return "Azijos futbolo konfederacija";

            default:  return "Neatpažinta konfederacija";
        }
    }

    public String getValstybeISO() {
        return valstybeISO;
    }

    public void setValstybeISO(String valstybeISO) {
        this.valstybeISO = valstybeISO;
    }

    public String getValstybe() {
        return valstybe;
    }

    public void setValstybe(String valstybe) {
        this.valstybe = valstybe;
    }

    public int getKomandosFifaReitingas() {
        return komandosFifaReitingas;
    }

    public void setKomandosFifaReitingas(int komandosFifaReitingas) {
        this.komandosFifaReitingas = komandosFifaReitingas;
    }

    public int getKomandosTaskai() {
        return komandosTaskai;
    }

    public void setKomandosTaskai(int komandosTaskai) {
        this.komandosTaskai = komandosTaskai;
    }

    public String getKomandosFederacija() {
        return komandosFederacija;
    }

    public String getValstybesVeliavosURL() {
        return valstybesVeliavosURL;
    }

    public void setKomandosFederacija(String komandosFederacija) {
        this.komandosFederacija = komandosFederacija;
    }

    public int getKomandosPajegumoKrepselis() {
        return komandosPajegumoKrepselis;
    }

    public void setKomandosPajegumoKrepselis(int komandosPajegumoKrepselis) {
        this.komandosPajegumoKrepselis = komandosPajegumoKrepselis;
    }

//    public ImageView getValstybesVeliava() {
//        return valstybesVeliava;
//    }

    public String getKomandosFederacijaLT() {
        return komandosFederacijaLT;
    }

    @Override
    public String toString () {

    return String.format("%s %3d%s%s%s%-36s%s %4d%s %7d%s %-8s%s %d%s %s" , "\n", komandosFifaReitingas, ". [", valstybeISO, "] ",
            valstybe, "  .. Fifa taškų:", komandosTaskai, ". Fifa ID:", komandosFifaID,  ". Federacija:", komandosFederacija,
            ".. Pajėgumo krepšelis:", komandosPajegumoKrepselis, ". Vėliava:", valstybesVeliavosURL);

    }


}
