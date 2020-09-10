package frontEnd;

import backEnd.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.Comparator;

public class NustatymuLangas extends HBox {

    private Text txtVartotojuFailoAdresas = new Text("Vartotojų failo adresas:");
    private TextField tfVartotojuFailoAdresas = new TextField();
    private Text checkMarkVartotoju = getCheckMark();

    private Text txtKomanduFailoAdresas = new Text("Komandų failo adresas:");
    private TextField tfKomanduFailoAdresas = new TextField();
    private Text checkMarkKomandu = getCheckMark();

    private Text txtRungtyniuFailoAdresas = new Text("Rungtynių failo adresas:");
    private TextField tfRungtyniuFailoAdresas = new TextField();
    private Text checkMarkRungtyniu = getCheckMark();

    private Text txtTaskaiUzTiksluSpejima = new Text("Taškai už visiškai tikslų spėjimą:");
    private Text txtA = new Text("(A)");
    private Text checkMarkA = getCheckMark();

    private TextField tfTaskaiUzTiksluSpejima = new TextField();
    private Text txtTaskaiUzIvarciuSkirtuma = new Text("Taškai už atspėtą įvarčių skirtumą:");
    private Text txtB = new Text("(B)");
    private Text checkMarkB = getCheckMark();

    private TextField tfTaskaiUzIvarciuSkirtuma = new TextField();
    private Text txtTaskaiUzNugaletoja = new Text("Taškai už teisingai atspėtą nugalėtoją:");
    private Text txtC = new Text("(C)");
    private Text checkMarkC = getCheckMark();

    private TextField tfTaskaiUzNugaletoja = new TextField();
    private Text txtTaskaiKompensaciniaiMAX = new Text("Maksimalus kompensac. taškų kiekis:");
    private Text txtD_max = new Text("(D_max)");
    private Text checkMarkD_max = getCheckMark();

    private TextField tfTaskaiKompensaciniaiMAX = new TextField();
    private Text txtTaskaiKompensaciniaiKOEF = new Text("Kompensac. taškų mažinimo koef.:");
    private Text txtD_koef = new Text("(D_koef)");
    private Text checkMarkD_koef = getCheckMark();

    private TextField tfTaskaiKompensaciniaiKOEF = new TextField();
    private Text txtTaskaiKompensaciniaiAPSAUGA = new Text("Kompensac. taškų mažinimo apsauga:");
    private TextField tfTaskaiKompensaciniaiAPSAUGA = new TextField();
    private Text txtD_cap = new Text("(D_cap)");
    private Text checkMarkD_cap = getCheckMark();

    private Button btAtnaujintiKomandas = new Button("Atnaujinti");
    private Button btKeistiRungtyniuFaila = new Button("Keisti");
    private Button btKeistiVartotojuFaila = new Button("Keisti");

    private GridPane failuTinklelis = new GridPane();
    private GridPane taskuTinklelis = new GridPane();

    private Button btAtstatyti = new Button("Atstatyti");
    private Button btIssaugoti = new Button("Išsaugoti");
    private Button btPerskaiciuoti = new Button("Perskaičiuoti");

    Nustatymai nustatymai = new Nustatymai();

    private double failoTextFieldIlgis = 450;
    private double taskuTextFieldIlgis = 50;


    public NustatymuLangas() {
        piestiNustatymuLanga();
    }

    public void piestiNustatymuLanga() {

        VBox nustatymuStulpas = new VBox();
        VBox artworkStulpas = new VBox();

        setFailuLaukeliuTekstus();
        setTaskulaukeliuTekstus();
        pildytiFailuGrid();
        pildytiTaskuGrid();
        setActionsFailai();
        setActionsTaskai();

        ImageView artworkNustatymu = new ImageView(new Image("https://images2.imgbox.com/14/eb/3jiyUuz7_o.jpg"));

        failuTinklelis.setVgap(10);
        failuTinklelis.setHgap(10);

        Text txtSkaiciavimoFormule = new Text("Skiriami taškai: A + B + C + (D_max - |rez - spėj| * D_koef)" +
                "\nSkliausteliuose - kompensac. taškai už nedaug prašautą spėjimą." +
                "\nKompensuojama tik tada, jei |rez - spėj| < D_cap");
        txtSkaiciavimoFormule.setTextAlignment(TextAlignment.CENTER);

        taskuTinklelis.setVgap(15);
        taskuTinklelis.setHgap(10);

        HBox mygtukuEilute = new HBox();

        btAtstatyti.setPrefWidth(160);
        btIssaugoti.setPrefWidth(160);
        mygtukuEilute.getChildren().addAll(btAtstatyti, btIssaugoti);
        mygtukuEilute.setSpacing(10);

        VBox taskuKairePuse = new VBox(txtSkaiciavimoFormule, taskuTinklelis, mygtukuEilute);
        taskuKairePuse.setSpacing(15);
        VBox taskuDesinePuse = imituotiTaskus();

        HBox visiTaskuNustatymai = new HBox();
        visiTaskuNustatymai.getChildren().addAll(taskuKairePuse, taskuDesinePuse);
        visiTaskuNustatymai.setSpacing(30);

        nustatymuStulpas.getChildren().addAll(gamintiAntrastineJuosta("DUOMENŲ SAUGOJIMO VIETOS NUSTATYMAI"),
                failuTinklelis, gamintiAntrastineJuosta("TOTALIZATORIAUS TAŠKŲ SKAIČIAVIMO NUSTATYMAI"), visiTaskuNustatymai);
        nustatymuStulpas.setSpacing(20);

        artworkStulpas.getChildren().addAll(artworkNustatymu);
        artworkStulpas.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(nustatymuStulpas, artworkNustatymu);
        setPadding(new Insets(10));
        setSpacing(10);
    }

    private void setFailuLaukeliuTekstus() {
        tfVartotojuFailoAdresas.setText(nustatymai.getVartotojuFailoAdresas());
        tfVartotojuFailoAdresas.setPrefWidth(failoTextFieldIlgis);
        tfKomanduFailoAdresas.setText(nustatymai.getKomanduFailoAdresas());
        tfRungtyniuFailoAdresas.setText(nustatymai.getRungtyniuFailoAdresas());
    }

    private void setTaskulaukeliuTekstus() {
        tfTaskaiUzTiksluSpejima.setText(String.valueOf(nustatymai.getTaskaiUzTiksluSpejima()));
        tfTaskaiUzTiksluSpejima.setMaxWidth(taskuTextFieldIlgis);
        tfTaskaiUzNugaletoja.setText(String.valueOf(nustatymai.getTaskaiUzNugaletoja()));
        tfTaskaiUzNugaletoja.setMaxWidth(taskuTextFieldIlgis);
        tfTaskaiUzIvarciuSkirtuma.setText(String.valueOf(nustatymai.getTaskaiUzIvarciuSkirtuma()));
        tfTaskaiUzIvarciuSkirtuma.setMaxWidth(taskuTextFieldIlgis);
        tfTaskaiKompensaciniaiMAX.setText(String.valueOf(nustatymai.getTaskaiKompensaciniaiMAX()));
        tfTaskaiKompensaciniaiMAX.setMaxWidth(taskuTextFieldIlgis);
        tfTaskaiKompensaciniaiKOEF.setText(String.valueOf(nustatymai.getTaskaiKompensaciniaiKOEF()));
        tfTaskaiKompensaciniaiKOEF.setMaxWidth(taskuTextFieldIlgis);
        tfTaskaiKompensaciniaiAPSAUGA.setText(String.valueOf(nustatymai.getTaskaiKompensaciniaiAPSAUGA()));
        tfTaskaiKompensaciniaiAPSAUGA.setMaxWidth(taskuTextFieldIlgis);
    }

    private void pildytiFailuGrid() {
        failuTinklelis.add(txtVartotojuFailoAdresas, 0, 0);
        failuTinklelis.add(tfVartotojuFailoAdresas, 1, 0);
        failuTinklelis.add(btKeistiVartotojuFaila, 3, 0);

        failuTinklelis.add(checkMarkVartotoju, 2, 0);
        btKeistiVartotojuFaila.setPrefWidth(100);

        failuTinklelis.add(txtKomanduFailoAdresas, 0, 1);
        failuTinklelis.add(tfKomanduFailoAdresas, 1, 1);

        failuTinklelis.add(checkMarkKomandu, 2, 1);
        failuTinklelis.add(btAtnaujintiKomandas, 3, 1);
        btAtnaujintiKomandas.setPrefWidth(100);

        failuTinklelis.add(txtRungtyniuFailoAdresas, 0, 2);
        failuTinklelis.add(tfRungtyniuFailoAdresas, 1, 2);
        failuTinklelis.add(checkMarkRungtyniu, 2, 2);
        failuTinklelis.add(btKeistiRungtyniuFaila, 3, 2);
        btKeistiRungtyniuFaila.setPrefWidth(100);
    }

    private void pildytiTaskuGrid() {
        taskuTinklelis.add(txtTaskaiUzTiksluSpejima, 0, 0);
        taskuTinklelis.add(tfTaskaiUzTiksluSpejima, 1, 0);
        taskuTinklelis.add(checkMarkA, 3, 0);
        taskuTinklelis.add(txtA, 2, 0);

        taskuTinklelis.add(txtTaskaiUzIvarciuSkirtuma, 0, 1);
        taskuTinklelis.add(tfTaskaiUzIvarciuSkirtuma, 1, 1);
        taskuTinklelis.add(checkMarkB, 3, 1);
        taskuTinklelis.add(txtB, 2, 1);

        taskuTinklelis.add(txtTaskaiUzNugaletoja, 0, 2);
        taskuTinklelis.add(tfTaskaiUzNugaletoja, 1, 2);
        taskuTinklelis.add(checkMarkC, 3, 2);
        taskuTinklelis.add(txtC, 2, 2);

        taskuTinklelis.add(txtTaskaiKompensaciniaiMAX, 0, 3);
        taskuTinklelis.add(tfTaskaiKompensaciniaiMAX, 1, 3);
        taskuTinklelis.add(checkMarkD_max, 3, 3);
        taskuTinklelis.add(txtD_max, 2, 3);

        taskuTinklelis.add(txtTaskaiKompensaciniaiKOEF, 0, 4);
        taskuTinklelis.add(tfTaskaiKompensaciniaiKOEF, 1, 4);
        taskuTinklelis.add(checkMarkD_koef, 3, 4);
        taskuTinklelis.add(txtD_koef, 2, 4);

        taskuTinklelis.add(txtTaskaiKompensaciniaiAPSAUGA, 0, 5);
        taskuTinklelis.add(tfTaskaiKompensaciniaiAPSAUGA, 1, 5);
        taskuTinklelis.add(checkMarkD_cap, 3, 5);
        taskuTinklelis.add(txtD_cap, 2, 5);
    }

    private void setActionsFailai() {
        btAtnaujintiKomandas.setOnAction(e -> {
            atnaujintiKomandas();
            checkMarkKomandu.setVisible(true);
        });
        tfKomanduFailoAdresas.setOnMouseEntered(e -> checkMarkKomandu.setVisible(false));

        btKeistiRungtyniuFaila.setOnAction(e -> {
            nustatymai.setRungtyniuFailoAdresas(tfRungtyniuFailoAdresas.getText());
            checkMarkRungtyniu.setVisible(true);
        });
        tfRungtyniuFailoAdresas.setOnMouseEntered(e -> checkMarkRungtyniu.setVisible(false));

        btKeistiVartotojuFaila.setOnAction(e -> {
            nustatymai.setVartotojuFailoAdresas(tfVartotojuFailoAdresas.getText());
            checkMarkVartotoju.setVisible(true);
        });
        tfVartotojuFailoAdresas.setOnMouseEntered(e -> checkMarkVartotoju.setVisible(false));
    }

    private void setActionsTaskai() {
        btIssaugoti.setOnAction(e -> {
            issaugotiTaskuNustatymus();
            rodytiTaskuCheckMarks();
            btPerskaiciuoti.fire();
        });

        btAtstatyti.setOnAction(e-> {
            atstatytiDefaultTaskuNustatymus();
            setTaskulaukeliuTekstus();
            btPerskaiciuoti.fire();
        });
        tfTaskaiUzTiksluSpejima.setOnMouseEntered(e -> sleptiTaskuCheckMarks());
        tfTaskaiUzIvarciuSkirtuma.setOnMouseEntered(e -> sleptiTaskuCheckMarks());
        tfTaskaiUzNugaletoja.setOnMouseEntered(e -> sleptiTaskuCheckMarks());
        tfTaskaiKompensaciniaiMAX.setOnMouseEntered(e -> sleptiTaskuCheckMarks());
        tfTaskaiKompensaciniaiKOEF.setOnMouseEntered(e -> sleptiTaskuCheckMarks());
        tfTaskaiKompensaciniaiAPSAUGA.setOnMouseEntered(e -> sleptiTaskuCheckMarks());
    }

    private void atnaujintiKomandas() {
        KomanduBiblioteka komanduBiblioteka = new KomanduBiblioteka();
        Archyvuotojas<KomanduBiblioteka> komanduBibliotekosArchyvas = new Archyvuotojas<>(nustatymai.getKomanduFailoAdresas());
        komanduBibliotekosArchyvas.irasyti(komanduBiblioteka);
    }

    private VBox gamintiAntrastineJuosta(String antraste) {
        VBox antrastineJuosta = new VBox();
        antrastineJuosta.setPadding(new Insets(5, 5, 5, 15));
        antrastineJuosta.setStyle("-fx-background-color: #000000");
        Text txtPendingSpejimuTitle = new Text(antraste);
        txtPendingSpejimuTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));
        txtPendingSpejimuTitle.setFill(Color.WHITE);
        antrastineJuosta.getChildren().add(txtPendingSpejimuTitle);
        return antrastineJuosta;
    }

    private VBox imituotiTaskus() {

        Text taskuPaaiskinimas = new Text("Žemiau pateikiamias taškų skaičiavimas pagal dabartinius nustatymus.\n" +
                "Jei nori pasibandyti kitokias reikšmes - pakeisk skiriamų balų kiekius\n" +
                "arba imituojamą rezultatą ir spausk \"perskaičiuoti\".\n" +
                "Atradęs tinkamiausią nustatymų kombinaciją - spausk \"išsaugoti\".");
        taskuPaaiskinimas.setTextAlignment(TextAlignment.CENTER);

        HBox imitacinioRezEilute = new HBox();
        Text txtImitacinisRezultatas = new Text("Imituojamas rezultatas:");
        TextField tfRez1 = new TextField("4");
        tfRez1.setPrefWidth(50);
        Text dvitaskis = new Text(":");
        TextField tfRez2 = new TextField("5");
        tfRez2.setPrefWidth(50);
        btPerskaiciuoti.setPrefWidth(100);
        imitacinioRezEilute.getChildren().addAll(txtImitacinisRezultatas, tfRez1, dvitaskis, tfRez2, btPerskaiciuoti);
        imitacinioRezEilute.setSpacing(5);
        imitacinioRezEilute.setAlignment(Pos.CENTER);

        HBox taskuMockUp = new HBox();
        skaiciuotiTaskus(taskuMockUp, Integer.valueOf(tfRez1.getText()), Integer.valueOf(tfRez2.getText()));

        btPerskaiciuoti.setOnAction(e -> skaiciuotiTaskus(taskuMockUp, Integer.valueOf(tfRez1.getText()), Integer.valueOf(tfRez2.getText())));

        VBox visasImitacinisBlokas = new VBox(taskuPaaiskinimas, imitacinioRezEilute, taskuMockUp);
        visasImitacinisBlokas.setAlignment(Pos.CENTER);
        visasImitacinisBlokas.setSpacing(20);

        return visasImitacinisBlokas;
    }

    public void skaiciuotiTaskus(HBox taskuMaketas, int rezA, int rezB) {
        //išsaugoma atsarginė dabartinių galiojančių nustatymų kopija
        int backUpTaskaiUztiksluSpejima = nustatymai.getTaskaiUzTiksluSpejima();
        int backUpTaskaiUzIvarciuSkirtuma = nustatymai.getTaskaiUzIvarciuSkirtuma();
        int backUpTaskaiUzNugaletoja = nustatymai.getTaskaiUzNugaletoja();
        int backUpTaskaiKompensaciniaiMAX = nustatymai.getTaskaiKompensaciniaiMAX();
        int backUpgetTaskaiKompensaciniaiKOEF = nustatymai.getTaskaiKompensaciniaiKOEF();
        int backUptTaskaiKompensaciniaiAPSAUGA = nustatymai.getTaskaiKompensaciniaiAPSAUGA();

        //išsaugomi taškų nustatymai pagal užpildytus laukelius
        issaugotiTaskuNustatymus();

        Rezultatas rezultatas = new RezultatasGroupStage(rezA, rezB);

        ArrayList<Taskai> imituotuTaskuSarasas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                imituotuTaskuSarasas.add(new Taskai(rezultatas, new RezultatasGroupStage(i, j)));
            }
        }

        HBox imituotosIsklotines = new HBox();
        imituotuTaskuSarasas.sort(Comparator.comparing(o -> o.getSkiriamiTaskai()));
        Text taskuIsklotineKairinisStulpelis = new Text();
        taskuIsklotineKairinisStulpelis.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 11));
        for (int i = imituotuTaskuSarasas.size() - 1; i >= imituotuTaskuSarasas.size() / 2; i--) {
            taskuIsklotineKairinisStulpelis.setText(taskuIsklotineKairinisStulpelis.getText() + imituotuTaskuSarasas.get(i) + "\n");
        }
        Text taskuIsklotineDesininisStulpelis = new Text();
        taskuIsklotineDesininisStulpelis.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 11));
        for (int i = imituotuTaskuSarasas.size() / 2 - 1; i >= 0; i--) {
            taskuIsklotineDesininisStulpelis.setText(taskuIsklotineDesininisStulpelis.getText() + imituotuTaskuSarasas.get(i) + "\n");
        }

        //atstatoma galiojanti nustatymų versija
        nustatymai.setTaskaiUzTiksluSpejima(backUpTaskaiUztiksluSpejima);
        nustatymai.setTaskaiUzIvarciuSkirtuma(backUpTaskaiUzIvarciuSkirtuma);
        nustatymai.setTaskaiUzNugaletoja(backUpTaskaiUzNugaletoja);
        nustatymai.setTaskaiKompensaciniaiMAX(backUpTaskaiKompensaciniaiMAX);
        nustatymai.setTaskaiKompensaciniaiKOEF(backUpgetTaskaiKompensaciniaiKOEF);
        nustatymai.setTaskaiKompensaciniaiAPSAUGA(backUptTaskaiKompensaciniaiAPSAUGA);

        taskuMaketas.getChildren().clear();
        taskuMaketas.getChildren().addAll(taskuIsklotineKairinisStulpelis, taskuIsklotineDesininisStulpelis);
        taskuMaketas.setSpacing(60);
    }

    private Text getCheckMark() {
        Text cm = new Text("✓");
        cm.setVisible(false);
        cm.setFont(Font.font("Century Gothic", FontWeight.BOLD, 25));
        cm.setFill(Color.GREEN);
        return cm;
    }

    private void issaugotiTaskuNustatymus(){
        nustatymai.setTaskaiUzTiksluSpejima(Integer.valueOf(tfTaskaiUzTiksluSpejima.getText()));
        nustatymai.setTaskaiUzIvarciuSkirtuma(Integer.valueOf(tfTaskaiUzIvarciuSkirtuma.getText()));
        nustatymai.setTaskaiUzNugaletoja(Integer.valueOf(tfTaskaiUzNugaletoja.getText()));
        nustatymai.setTaskaiKompensaciniaiMAX(Integer.valueOf(tfTaskaiKompensaciniaiMAX.getText()));
        nustatymai.setTaskaiKompensaciniaiKOEF(Integer.valueOf(tfTaskaiKompensaciniaiKOEF.getText()));
        nustatymai.setTaskaiKompensaciniaiAPSAUGA(Integer.valueOf(tfTaskaiKompensaciniaiAPSAUGA.getText()));
    }

    private void atstatytiDefaultTaskuNustatymus(){
        nustatymai.setTaskaiUzTiksluSpejima(50);
        nustatymai.setTaskaiUzIvarciuSkirtuma(50);
        nustatymai.setTaskaiUzNugaletoja(25);
        nustatymai.setTaskaiKompensaciniaiMAX(25);
        nustatymai.setTaskaiKompensaciniaiKOEF(5);
        nustatymai.setTaskaiKompensaciniaiAPSAUGA(6);
    }

    private void sleptiTaskuCheckMarks() {
        checkMarkA.setVisible(false);
        checkMarkB.setVisible(false);
        checkMarkC.setVisible(false);
        checkMarkD_max.setVisible(false);
        checkMarkD_koef.setVisible(false);
        checkMarkD_cap.setVisible(false);
    }

    private void rodytiTaskuCheckMarks() {
        checkMarkA.setVisible(true);
        checkMarkB.setVisible(true);
        checkMarkC.setVisible(true);
        checkMarkD_max.setVisible(true);
        checkMarkD_koef.setVisible(true);
        checkMarkD_cap.setVisible(true);
    }


}