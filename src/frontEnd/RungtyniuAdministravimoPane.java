package frontEnd;

import backEnd.*;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RungtyniuAdministravimoPane extends VBox {

    private Font boldAntrastinisFontas = Font.font("Century Gothic", FontWeight.BOLD, 15);
    private Font boldFontas = Font.font("Century Gothic", FontWeight.BOLD, 13);
    private Font regularFontas = Font.font("Century Gothic", FontWeight.NORMAL, 11);
    private Font thinFontas = Font.font("Century Gothic", FontWeight.THIN, 11);
    private Font didelisFontas = Font.font("Century Gothic", FontWeight.BOLD, 40);
    private TextField tfIvarciaiKomanda1 = new TextField();
    private TextField tfIvarciaiKomanda2 = new TextField();
    private Button btPradeti = new Button("PRADĖTI RUNGTYNES");
    private Button btBaigti = new Button("BAIGTI RUNGTYNES");
    private Hyperlink btSalinti = new Hyperlink("\u00d7" + " Pašalinti rungtynes");
    private Rungtynes rodomosRungtynes = new Rungtynes();

    public RungtyniuAdministravimoPane() {
        setMinHeight(300);
        piestiTusciaSkydeli();
    }


    public RungtyniuAdministravimoPane(Rungtynes rungtynes) {
        setMinHeight(300);
        setRungtynes(rungtynes);

    }

    public void setRungtynes(Rungtynes rungtynes) {
        this.rodomosRungtynes = rungtynes;
        getChildren().clear();
        Komanda komanda1 = rungtynes.getKomanda1();
        Komanda komanda2 = rungtynes.getKomanda2();

        setPadding(new Insets(10));
        setSpacing(10);

        VBox komanda1Box = new VBox();
        VBox komanda2Box = new VBox();
        VBox rezultatoBox = new VBox();
        VBox spejimuVarduBox = new VBox();
        VBox spejimuSkaiciuBox = new VBox();
        VBox rezervinisBox = new VBox();
        HBox mygtukuEilute = new HBox();
        GridPane rungtyniuTinklelis = new GridPane();

        //KOMANDU BOXAI
        Text txtKomandosTitle1 = new Text(formatuotiPavadinima(komanda1.getValstybe()));
        txtKomandosTitle1.setFont(boldAntrastinisFontas);
        txtKomandosTitle1.setFill(Color.WHITE);
        txtKomandosTitle1.setTextAlignment(TextAlignment.CENTER);
        Text txtKomandosTitle2 = new Text(formatuotiPavadinima(komanda2.getValstybe()));
        txtKomandosTitle2.setFont(boldAntrastinisFontas);
        txtKomandosTitle2.setFill(Color.WHITE);
        txtKomandosTitle2.setTextAlignment(TextAlignment.CENTER);

        ImageView veliava1 = new ImageView(new Image(komanda1.getValstybesVeliavosURL()));
        veliava1.setOpacity(0.5);
        veliava1.setPreserveRatio(true);
        veliava1.setFitWidth(100);
        ImageView veliava2 = new ImageView(new Image(komanda2.getValstybesVeliavosURL()));
        veliava2.setOpacity(0.5);
        veliava2.setPreserveRatio(true);
        veliava2.setFitWidth(100);

        Text txtKomandosISO1 = new Text("[" + komanda1.getValstybeISO() + "]");
        txtKomandosISO1.setFont(didelisFontas);
        txtKomandosISO1.setFill(Color.WHITE);
        Text txtKomandosISO2 = new Text("[" + komanda2.getValstybeISO() + "]");
        txtKomandosISO2.setFont(didelisFontas);
        txtKomandosISO2.setFill(Color.WHITE);

        StackPane veliavosIrIsoPane1 = new StackPane();
        StackPane veliavosIrIsoPane2 = new StackPane();
        veliavosIrIsoPane1.getChildren().addAll(txtKomandosISO1, veliava1);
        veliavosIrIsoPane1.setAlignment(Pos.CENTER);
        veliavosIrIsoPane2.getChildren().addAll(txtKomandosISO2, veliava2);
        veliavosIrIsoPane2.setAlignment(Pos.CENTER);

        komanda1Box.getChildren().addAll(txtKomandosTitle1, veliavosIrIsoPane1);
        komanda1Box.setAlignment(Pos.CENTER);
        komanda1Box.setSpacing(10);
        komanda1Box.setMinSize(150, 120);
        komanda1Box.setMaxWidth(150);
        komanda2Box.getChildren().addAll(txtKomandosTitle2, veliavosIrIsoPane2);
        komanda2Box.setAlignment(Pos.CENTER);
        komanda2Box.setSpacing(10);
        komanda2Box.setPrefWidth(150);
        komanda2Box.setMaxWidth(150);

        //REZULTATO BOXAS
        Text txtRungtyniuData = new Text(rungtynes.getRungtyniuDataString());
        txtRungtyniuData.setFont(thinFontas);
        txtRungtyniuData.setFill(Color.WHITE);


        Text txtRungtyniuBusena = new Text("BŪSENA");
        txtRungtyniuBusena.setFont(boldFontas);
        txtRungtyniuBusena.setFill(Color.WHITE);
        Hyperlink btReset = new Hyperlink("reset");


        tfIvarciaiKomanda1.setPrefWidth(55);
        tfIvarciaiKomanda1.setPrefHeight(55);
        tfIvarciaiKomanda1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 30));
        tfIvarciaiKomanda1.setStyle("-fx-border-color: grey; -fx-border-width: 0 0 0 0; -fx-background-color: #9bbc91; -fx-text-fill: white; -fx-padding: 1,1,1,1;");
        tfIvarciaiKomanda1.setAlignment(Pos.CENTER);

        tfIvarciaiKomanda2.setPrefWidth(55);
        tfIvarciaiKomanda2.setPrefHeight(55);
        tfIvarciaiKomanda2.setFont(Font.font("Century Gothic", FontWeight.BOLD, 30));
        tfIvarciaiKomanda2.setStyle("-fx-border-color: grey; -fx-border-width: 0 0 0 0; -fx-background-color: #9bbc91; -fx-text-fill: white; -fx-padding: 1,1,1,1;");
        tfIvarciaiKomanda2.setAlignment(Pos.CENTER);


        Text dvitaskis = new Text(":");
        dvitaskis.setFill(Color.WHITE);
        dvitaskis.setFont(didelisFontas);

        HBox rezultatoEilute = new HBox(tfIvarciaiKomanda1, dvitaskis, tfIvarciaiKomanda2);
        rezultatoEilute.setSpacing(5);
        rezultatoBox.getChildren().addAll(txtRungtyniuData, txtRungtyniuBusena, rezultatoEilute);
        rezultatoBox.setMinWidth(150);
        rezultatoBox.setSpacing(5);
        rezultatoBox.setAlignment(Pos.CENTER);

        //SPEJIMU ISKLOTINE
        Text txtSpejimuVarduIsklotine = new Text("");
        txtSpejimuVarduIsklotine.setFont(thinFontas);
        txtSpejimuVarduIsklotine.setFill(Color.WHITE);
        Text txtSpejimuSkaiciuIsklotine = new Text("");
        txtSpejimuSkaiciuIsklotine.setFont(thinFontas);
        txtSpejimuSkaiciuIsklotine.setFill(Color.WHITE);
        Text txtSpejimuPrizuIsklotine = new Text("");
        txtSpejimuPrizuIsklotine.setFont(regularFontas);
        txtSpejimuPrizuIsklotine.setFill(Color.WHITE);

        HashMap<String, Rezultatas> spejimai = rungtynes.getVisiSpejimai();
        Set<Map.Entry<String, Rezultatas>> entrySet = spejimai.entrySet();
        for (Map.Entry<String, Rezultatas> entry : entrySet) {
            Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
            Vartotojas spejantisVartotojas = (Vartotojas) vartotojuArchyvas.istrauktiSarasa().get(entry.getKey());

            //rezultatas i isklotine
            txtSpejimuSkaiciuIsklotine.setText(txtSpejimuSkaiciuIsklotine.getText()
                    + entry.getValue().getTotalIvarciaiKomanda1()
                    + "          :          "
                    + entry.getValue().getTotalIvarciaiKomanda2() + "\n");

            //vardas i isklotine
            txtSpejimuVarduIsklotine.setText(txtSpejimuVarduIsklotine.getText()
                    + spejantisVartotojas.getVardas() + " " + spejantisVartotojas.getPavarde() + "\n");

            //prizas i isklotine
            txtSpejimuPrizuIsklotine.setText(txtSpejimuPrizuIsklotine.getText() + (rungtynes.arIvyko() ? (" +" + new Taskai().skaiciuoti(rungtynes, entry.getValue())) : "") + "\n");
        }

        spejimuVarduBox.getChildren().add(txtSpejimuVarduIsklotine);
        spejimuVarduBox.setAlignment(Pos.CENTER_RIGHT);
        spejimuSkaiciuBox.getChildren().add(txtSpejimuSkaiciuIsklotine);
        spejimuSkaiciuBox.setAlignment(Pos.CENTER);
        rezervinisBox.getChildren().add(txtSpejimuPrizuIsklotine);
        rezervinisBox.setAlignment(Pos.CENTER_LEFT);

        //APATINIAI MYGTUKAI
        btPradeti.setPrefWidth(225);
        btBaigti.setPrefWidth(225);
        mygtukuEilute.getChildren().addAll(btPradeti, btBaigti);
        mygtukuEilute.setAlignment(Pos.BOTTOM_CENTER);


        setStyle("-fx-background-color: linear-gradient(#396e3f, #5ec36a); ");


        System.out.println(rungtynes);
        System.out.println("arVyksta: " + rungtynes.arVyksta() + " arIvyko: " + rungtynes.arIvyko());

        //jei rungtynes neprasidejo
        if (rungtynes.arIvyko() == false && rungtynes.arVyksta() == false) {
            tfIvarciaiKomanda1.setDisable(true);
            tfIvarciaiKomanda2.setDisable(true);
            tfIvarciaiKomanda1.setText("-");
            tfIvarciaiKomanda2.setText("-");
            btPradeti.setDisable(false);
            btBaigti.setDisable(true);
            System.out.println("manau kad rungtynes dar neprasidejo");
            txtRungtyniuBusena.setText("NEPRASIDĖJO");
        }

        //jei rungtynes vyksta
        if (rungtynes.arIvyko() == false && rungtynes.arVyksta() == true) {
            tfIvarciaiKomanda1.setDisable(false);
            tfIvarciaiKomanda2.setDisable(false);
            tfIvarciaiKomanda1.clear();
            tfIvarciaiKomanda2.clear();
            btPradeti.setDisable(true);
            btBaigti.setDisable(false);
            txtRungtyniuBusena.setText("VYKSTA");
            FillTransition mirksis = new FillTransition(Duration.millis(400), txtRungtyniuBusena);
            mirksis.setFromValue(Color.rgb(0, 0, 0));
            mirksis.setToValue(Color.rgb(255, 255, 255));
            mirksis.setAutoReverse(true);
            mirksis.setCycleCount(Animation.INDEFINITE);
            mirksis.play();

            System.out.println("manau kad rungtynes vyksta");
        }

        //jei rungtynes pasibaige
        if (rungtynes.arIvyko() == true && rungtynes.arVyksta() == false) {
            tfIvarciaiKomanda1.setDisable(true);
            tfIvarciaiKomanda2.setDisable(true);
            tfIvarciaiKomanda1.setText(String.valueOf(rungtynes.getRezultatas().getTotalIvarciaiKomanda1()));
            tfIvarciaiKomanda2.setText(String.valueOf(rungtynes.getRezultatas().getTotalIvarciaiKomanda2()));
            btPradeti.setDisable(true);
            btBaigti.setDisable(true);
            System.out.println("manau kad rungtynes jau pasibaige");
            txtRungtyniuBusena.setText("BAIGĖSI");
        }


        rungtyniuTinklelis.add(komanda1Box, 0, 0);
        rungtyniuTinklelis.add(rezultatoBox, 1, 0);
        rungtyniuTinklelis.add(komanda2Box, 2, 0);
        rungtyniuTinklelis.add(spejimuVarduBox, 0, 1);
        rungtyniuTinklelis.add(spejimuSkaiciuBox, 1, 1);
        rungtyniuTinklelis.add(rezervinisBox, 2, 1);
        rungtyniuTinklelis.setHgap(20);
        rungtyniuTinklelis.setVgap(10);
        rungtyniuTinklelis.setAlignment(Pos.CENTER);
        getChildren().addAll(rungtyniuTinklelis, mygtukuEilute);

    }


    public void piestiTusciaSkydeli() {
        getChildren().clear();
        String stiliusMain = "-fx-background-color: linear-gradient(#396e3f, #5da966); -fx-border-style: dashed; -fx-border-color: #5ba664; -fx-border-insets: 5;";
        String stilius = "-fx-border-style: dashed; -fx-border-color: #5ba664; -fx-border-insets: 5;";
        setStyle(stiliusMain);
        VBox tuscioSkydelioDizainas = new VBox();

        HBox tuscioSkydelioSvieslente = new HBox();
        tuscioSkydelioSvieslente.setMinHeight(50);
        tuscioSkydelioSvieslente.setStyle(stilius);
        tuscioSkydelioSvieslente.setAlignment(Pos.CENTER);

        VBox tuscioSkydelioKaireLenta = new VBox();
        tuscioSkydelioKaireLenta.setStyle(stilius);
        tuscioSkydelioKaireLenta.setMinHeight(50);
        tuscioSkydelioKaireLenta.setMinWidth(150);

        VBox tuscioSkydelioDesineLenta = new VBox();
        tuscioSkydelioDesineLenta.setStyle(stilius);
        tuscioSkydelioDesineLenta.setMinHeight(50);
        tuscioSkydelioDesineLenta.setMinWidth(150);

        tuscioSkydelioSvieslente.getChildren().addAll(tuscioSkydelioKaireLenta, tuscioSkydelioDesineLenta);

        HBox tuscioSkydelioIsklotine = new HBox();
        tuscioSkydelioIsklotine.setStyle(stilius);
        tuscioSkydelioIsklotine.setMinHeight(120);
        tuscioSkydelioIsklotine.setAlignment(Pos.CENTER);
        Text tuscioTekstas = new Text("Pasirink rungtynes iš tvarkaraščio aukščiau");
        tuscioTekstas.setFont(regularFontas);
        tuscioTekstas.setFill(Color.rgb(91,166,100));
        tuscioSkydelioIsklotine.getChildren().add(tuscioTekstas);

        tuscioSkydelioDizainas.getChildren().addAll(tuscioSkydelioSvieslente, tuscioSkydelioIsklotine);
        getChildren().add(tuscioSkydelioDizainas);
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

        if (valstybe.length() > 13) {
            String[] zodziai = valstybe.split(" ");
            if (zodziai.length == 2) {
                valstybe = zodziai[0] + "\n" + zodziai[1];
            } else if (zodziai.length > 2) {
                String antraEilute = "";
                for (int i = 2; i < zodziai.length; i++) {
                    antraEilute += zodziai[i] + " ";
                }
                valstybe = zodziai[0] + " " + zodziai[1] + "\n" + antraEilute;
            }

        }
        return valstybe.toUpperCase();
    }

    public TextField getTfIvarciaiKomanda1() {
        return tfIvarciaiKomanda1;
    }

    public TextField getTfIvarciaiKomanda2() {
        return tfIvarciaiKomanda2;
    }

    public Button getBtPradeti() {
        return btPradeti;
    }

    public Button getBtBaigti() {
        return btBaigti;
    }

    public Rungtynes getRodomosRungtynes() {
        return rodomosRungtynes;
    }

    public boolean ivestasRezultatas() {
        if ((!tfIvarciaiKomanda1.getText().equals("") && !tfIvarciaiKomanda1.getText().equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public void rodytiBaigtasRungtynes() {

    }
}

