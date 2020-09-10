package frontEnd;

import backEnd.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class KomandosInfoPane extends VBox {

    //Klasė KomandosInfoPane naudojama Administratoriaus lange, kuriant rungtynes
    //Iš sąrašo pasirinktai komandai sukuriamas skydelis

    private Font boldAntrastinisFontas = Font.font("Century Gothic", FontWeight.BOLD, 15);
    private Font boldFontas = Font.font("Century Gothic", FontWeight.BOLD, 13);
    private Font regularFontas = Font.font("Century Gothic", FontWeight.NORMAL, 11);
    private Font thinFontas = Font.font("Century Gothic", FontWeight.THIN, 11);
    private Font didelisFontas = Font.font("Century Gothic", FontWeight.BOLD, 40);
    private boolean tuscias = true;
    private boolean aktyvus = false;
    private String stiliusNeaktyvausPilno = "-fx-background-color: transparent";
    private String stiliusNeaktyvausTuscio = "-fx-background-color: transparent; -fx-border-color: #9f9f9f; -fx-border-style: dashed;";
    private String stiliusAktyvausPilno = "-fx-background-color: #f5f5f5";
    private String stiliusAktyvausTuscio = "-fx-background-color: #f5f5f5; -fx-border-color: #9f9f9f; -fx-border-style: dashed;";
    private String stiliusLaikinas = "";

    public KomandosInfoPane() {
        setMinHeight(230);
        piestiTuscia();
        nustatytiInteraktyvuma();
    }

    private void nustatytiInteraktyvuma() {
        setOnMouseEntered(e -> {
            stiliusLaikinas = getStyle();
            if (!aktyvus) {
                setStyle(stiliusAktyvausTuscio);
            }
        });
        setOnMouseExited(e -> {
            if (!aktyvus)
                setStyle(stiliusLaikinas);
        });
    }

    public void aktyvuoti() {
        aktyvus = true;
        if (tuscias) {
            setStyle(stiliusAktyvausTuscio);
            piestiTuscia();
        } else setStyle(stiliusAktyvausPilno);
    }

    public void deaktyvuoti() {
        aktyvus = false;
        if (tuscias) {
            setStyle(stiliusNeaktyvausTuscio);
            piestiTuscia();
        } else setStyle(stiliusNeaktyvausPilno);
    }

    public void piestiKomanda(Komanda komanda) {
        getChildren().clear();
        tuscias = false;
        Text txtKomandosPavadinimas = new Text(formatuotiPavadinima(komanda.getValstybe()));
        txtKomandosPavadinimas.setTextAlignment(TextAlignment.CENTER);
        Text txtKomandosFifaReitingas = new Text("FIFA reitingas: " + String.valueOf(komanda.getKomandosFifaReitingas()) + " vieta");
        Text txtKomandosFifaTaskai = new Text("FIFA taškų: " + String.valueOf(komanda.getKomandosTaskai()));
        Text txtKomandosFederacija = new Text("\n" + komanda.getKomandosFederacija());
        Text txtKomandosFederacijaLT = new Text(wrapStulpeliu(komanda.getKomandosFederacijaLT()) + "\n");
        txtKomandosFederacijaLT.setTextAlignment(TextAlignment.CENTER);
        Text txtKomandosISO = new Text("[" + komanda.getValstybeISO() + "]");

        ImageView veliava = new ImageView(new Image(komanda.getValstybesVeliavosURL()));
        veliava.setOpacity(0.5);

        StackPane isoAntVeliavos = new StackPane(txtKomandosISO, veliava);

        txtKomandosPavadinimas.setFont(boldAntrastinisFontas);
        txtKomandosFifaReitingas.setFont(regularFontas);
        txtKomandosFifaTaskai.setFont(regularFontas);
        txtKomandosFederacija.setFont(regularFontas);
        txtKomandosFederacijaLT.setFont(regularFontas);
        txtKomandosISO.setFont(didelisFontas);
        veliava.setPreserveRatio(true);
        veliava.setFitWidth(100);

        getChildren().addAll(txtKomandosPavadinimas, txtKomandosFifaReitingas, txtKomandosFifaTaskai, txtKomandosFederacija, txtKomandosFederacijaLT, isoAntVeliavos);
        setPadding(new Insets(0, 10, 10, 10));

    }

    private String formatuotiPavadinima(String valstybe) {
        valstybe = valstybe.replace("&#244;", "ô");
        valstybe = valstybe.replace("&#39;", "\'");
        valstybe = valstybe.replace("&#231;", "ç");
        valstybe = valstybe.replace("&#227;", "ã");
        valstybe = valstybe.replace("&#233;", "é");
        valstybe = valstybe.replace("&#237;", "í");
        valstybe = valstybe.replace(" And ", " & ");
        valstybe = valstybe.replace(" and ", " & ");

        if (valstybe.length()>13) {
            String[] zodziai = valstybe.split(" ");
            if (zodziai.length == 2) {
                valstybe = zodziai[0] + "\n" + zodziai[1];
            } else if (zodziai.length > 2) {
                String antraEilute = "";
                for (int i = 2; i < zodziai.length; i++) {
                    antraEilute += zodziai[i]+" ";
                }
                valstybe = zodziai[0] + " " + zodziai[1] + "\n" + antraEilute;
            }

        }
        return valstybe.toUpperCase();
    }

    public void piestiTuscia() {
        tuscias = true;
        getChildren().clear();
        Text txtNepaspaustas = new Text();
        if (aktyvus) txtNepaspaustas.setText("Pasirink komandą\niš bibliotekos aukščiau");
        else txtNepaspaustas.setText("Paspausk čia\nir rinkis kitą komandą\niš bibliotekos aukščiau");

        txtNepaspaustas.setTextAlignment(TextAlignment.CENTER);
        txtNepaspaustas.setFill(Color.rgb(160, 160, 160));
        txtNepaspaustas.setFont(thinFontas);

        getChildren().add(txtNepaspaustas);
        setAlignment(Pos.CENTER);
    }


    private String wrapStulpeliu(String eilute) {
        System.out.println("eilutes ilgis:" + eilute.length());
        switch (eilute.length()) {
            case 67:
                return eilute.substring(0, 25) + "\n" + eilute.substring(25, 46) + "\n" + eilute.substring(46, eilute.length());
            case 37:
                return eilute.substring(0, 16) + "\n" + eilute.substring(16, eilute.length());
            case 36:
                return eilute.substring(0, 15) + "\n" + eilute.substring(15, eilute.length());
            case 31:
                return eilute.substring(0, 17) + "\n" + eilute.substring(17, eilute.length());
            case 29:
                return eilute.substring(0, 7) + "\n" + eilute.substring(7, eilute.length());
            case 28:
                return eilute.substring(0, 14) + "\n" + eilute.substring(14, eilute.length());
        }
        return eilute;
    }

    public boolean isTuscias() {
        return tuscias;
    }

    public boolean isAktyvus() {
        return aktyvus;
    }
}
