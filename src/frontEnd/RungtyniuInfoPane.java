package frontEnd;

import backEnd.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RungtyniuInfoPane extends HBox {

    //Klasė RungtyniųInfoPane naudojama administratoriaus, kai administruojamos iš lentelės pasirinktos rungtynės
    //Šita klasė sena, reikės updatinti su žalia, šiuolaikiškesne versija

    private Hyperlink btSalinti = new Hyperlink("\u00d7" + " Pašalinti rungtynes");
    private Hyperlink btUndo = new Hyperlink ("\u270D");
    private Button btPradeti = new Button("Pradėti rungtynes");
    private Button btBaigti = new Button("Baigti rungtynes");
    private TextField tfRezKomanda1 = new TextField();
    private TextField tfRezKomanda2 = new TextField();
    private Text klaidosText = new Text("");
    private Text txtRungtyniuStatusas = new Text("");
    private Rungtynes rungtynes;

    public RungtyniuInfoPane() {
        valyti();
    }

    public RungtyniuInfoPane(Rungtynes pasirinktosRungtynes) {
        setStyle("-fx-background-color: #ffffff;\n"
                + "-fx-border-color: lightgrey;\n"
                + "-fx-border-insets: 0;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-style: dashed;\n"
        );
        setPadding(new Insets(5, 5, 5, 5));
        setMinWidth(500);
        setMaxWidth(400);

        kurtiSkydeli(pasirinktosRungtynes);
    }

    public void kurtiSkydeli(Rungtynes pasirinktosRungtynes) {

        rungtynes = pasirinktosRungtynes;

        klaidosText.setText("");

        if (!rungtynes.arVyksta() && !rungtynes.arIvyko()) {
            btPradeti.setDisable(false);
            btBaigti.setDisable(true);
        } else if(rungtynes.arVyksta() && !rungtynes.arIvyko()) {
            btPradeti.setDisable(true);
            btBaigti.setDisable(false);
        } else if(!rungtynes.arVyksta() && rungtynes.arIvyko()) {
            btPradeti.setDisable(true);
            btBaigti.setDisable(true);
        }

        Komanda komanda1 = rungtynes.getKomanda1();
        Komanda komanda2 = rungtynes.getKomanda2();

        ImageView veliava1 = new ImageView(new Image(komanda1.getValstybesVeliavosURL()));
        veliava1.setPreserveRatio(true);
        veliava1.setFitWidth(20);
        ImageView veliava2 = new ImageView(new Image(komanda2.getValstybesVeliavosURL()));
        veliava2.setPreserveRatio(true);
        veliava2.setFitWidth(20);

        Text komanduPavadinimai = new Text(komanda1.getValstybe() + " vs. " + komanda2.getValstybe());
        komanduPavadinimai.setFont(Font.font("Century Gothic" /*, FontWeight.BOLD*/, 14));

        HBox datosEilute = new HBox();
        datosEilute.setSpacing(5);
        datosEilute.setAlignment(Pos.CENTER);
        Text rungtyniuData = new Text(rungtynes.getRungtyniuDataString());
        txtRungtyniuStatusas.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12));
        datosEilute.getChildren().addAll(rungtyniuData, txtRungtyniuStatusas);

        Text rungtyniuRezultatas = new Text();
        rungtyniuRezultatas.setFont(Font.font("Century Gothic", FontWeight.BOLD, 15));

        HBox rezultatoIvedimoEilute =  new HBox();

        btUndo.setFont(Font.font("Arial", 25));
        tfRezKomanda1.setMaxWidth(40);
        Text txtDvitaskis = new Text(":" );
        tfRezKomanda2.setMaxWidth(40);
        Button btRandom = new Button("?");

        rezultatoIvedimoEilute.setSpacing(5);
        rezultatoIvedimoEilute.setAlignment(Pos.CENTER);
        rezultatoIvedimoEilute.getChildren().addAll(tfRezKomanda1, txtDvitaskis, tfRezKomanda2, btRandom);

        btPradeti.setMinWidth(150);
        btBaigti.setMinWidth(150);
        klaidosText.setFill(Color.RED);

        HBox veliavuEilute = new HBox();
        veliavuEilute.setAlignment(Pos.CENTER);
        veliavuEilute.setSpacing(10);
        veliavuEilute.getChildren().addAll(veliava1, komanduPavadinimai, veliava2);

        Text spejimuIsklotine = new Text("");
        HashMap<String, Rezultatas> spejimai = rungtynes.getVisiSpejimai();
        Set<Map.Entry<String, Rezultatas>> entrySet = spejimai.entrySet();
        for (Map.Entry<String, Rezultatas> entry : entrySet) {
            Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
            Vartotojas spejantisVartotojas = (Vartotojas) vartotojuArchyvas.istrauktiSarasa().get(entry.getKey());
            spejimuIsklotine.setText(spejimuIsklotine.getText() + entry.getValue().toString() + " "
                    + spejantisVartotojas.getVardas() + " " + spejantisVartotojas.getPavarde()
                    + (rungtynes.arIvyko() ? (" +" + new Taskai().skaiciuoti(rungtynes, entry.getValue())) : "") + "\n");
        }

        VBox kairysStulpelis = new VBox();

        kairysStulpelis.getChildren().add(btSalinti);
        kairysStulpelis.getChildren().add(veliavuEilute);
        kairysStulpelis.getChildren().add(datosEilute);

        //Jei rungtynės dar NEPRADĖTOS - vietoje rezultato rodomas "-:-"
        if (!rungtynes.arIvyko() && !rungtynes.arVyksta()) {
            kairysStulpelis.getChildren().add(rungtyniuRezultatas);
            rungtyniuRezultatas.setText("-:-");
            kairysStulpelis.getChildren().add(btPradeti);
            kairysStulpelis.getChildren().add(btBaigti);
            txtRungtyniuStatusas.setText("");
        }
        //Jei rungtynės jau PRADĖTOS - vietoje rezultato rodoma rezultato įvedimo eilutė
        else if (rungtynes.arVyksta()){
            kairysStulpelis.getChildren().add(rezultatoIvedimoEilute);
            kairysStulpelis.getChildren().add(btPradeti);
            kairysStulpelis.getChildren().add(btBaigti);
            txtRungtyniuStatusas.setText("(vyksta)");
        }
        //Jei rungtynės BAIGTOS - rodomas rezultatas ir vietoje pradžios-baigimo mygtukų "reset/išvalymo" hyperlink mygtukas
        else {
            kairysStulpelis.getChildren().add(rungtyniuRezultatas);
            rungtyniuRezultatas.setText(rungtynes.getRezultatas().toString());
            kairysStulpelis.getChildren().add(btUndo);
            txtRungtyniuStatusas.setText("");
        }

        kairysStulpelis.getChildren().add(klaidosText);
        kairysStulpelis.setSpacing(5);
        kairysStulpelis.setMinWidth(250);
        kairysStulpelis.setPadding(new Insets(2, 2, 2, 2));
        kairysStulpelis.setAlignment(Pos.CENTER);

        VBox desinysStulpelis = new VBox();
        desinysStulpelis.getChildren().addAll(spejimuIsklotine);
        desinysStulpelis.setMinWidth(200);
        desinysStulpelis.setPadding(new Insets(2, 2, 2, 2));
        desinysStulpelis.setAlignment(Pos.CENTER_LEFT);

        getChildren().clear();
        getChildren().addAll(kairysStulpelis, desinysStulpelis);
        setSpacing(20);

    }

    public Hyperlink getBtSalinti() {
        return btSalinti;
    }

    public void valyti() {
        getChildren().clear();
        setStyle("-fx-background-color: #ffffff;\n"
                + "-fx-border-color: lightgrey;\n"
                + "-fx-border-insets: 0;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-style: dashed;\n"
        );
        setPadding(new Insets(5, 5, 5, 5));
        Text promptPriesPasirenkantRungtynes = new Text("Pasirinkite rungtynes");
        promptPriesPasirenkantRungtynes.setFill(Color.LIGHTGRAY);
        promptPriesPasirenkantRungtynes.setFont(Font.font("Century Gothic" /*, FontWeight.BOLD*/, 12));
        getChildren().add(promptPriesPasirenkantRungtynes);

        setMinHeight(200);
        setMinWidth(500);
    }

    public boolean ivestasRezultatas() {
        if ((!tfRezKomanda1.getText().equals("") && !tfRezKomanda2.getText().equals(""))) {
            return true;
        }
        else {
            klaidosText.setText("Būtina įvesti rezultatą!");
            return false;
        }
    }


    public Button getBtPradeti() {
        return btPradeti;
    }

    public void disablePradeti() {
        btPradeti.setDisable(true);
    }

    public void enablePradeti() {
        btPradeti.setDisable(false);
    }


    public Button getBtBaigti() {
        return btBaigti;
    }

    public void disableBaigti() {
        btBaigti.setDisable(true);
    }

    public void enableBaigti() {
        btBaigti.setDisable(false);
    }


    public Hyperlink getBtUndo() {
        return btUndo;
    }

    public TextField getTfRezKomanda1() {
        return tfRezKomanda1;
    }

    public TextField getTfRezKomanda2() {
        return tfRezKomanda2;
    }

    public Rungtynes getRungtynes() {
        return rungtynes;
    }
}
