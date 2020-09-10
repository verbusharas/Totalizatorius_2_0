package test;

import backEnd.*;
import frontEnd.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class TestAdmin extends Application {

    static TableView<Rungtynes> rungtyniuLentele;
    static Nustatymai nustatymai = new Nustatymai();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        Stage langas = primaryStage;
        VBox pagrindinisAdminMaketas = new VBox();

        TabPane tabMaketas = new TabPane();

        Tab tabVartotojai = new Tab("Vartotojai");
        tabVartotojai.setContent(vartotojuAdministravimoLangas());

        Tab tabRungtynes = new Tab("Rungtynės");
        tabRungtynes.setContent(rungtyniuAdministravimoLangas());

        //Tabų nustatymai:
        tabMaketas.getTabs().addAll(tabVartotojai, tabRungtynes);
        tabMaketas.setSide(Side.LEFT);
        tabMaketas.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabMaketas.setTabMinHeight(50);
        tabMaketas.setTabMinWidth(200);
        tabMaketas.setRotateGraphic(false);

        Scene vaizdas = new Scene(tabMaketas);

        vaizdas.getStylesheets().add(getClass().getResource("tabStilius2.css").toExternalForm());
        langas.setScene(vaizdas);
        langas.setTitle("TestAdmin");

        langas.show();
    }

    private HBox vartotojuAdministravimoLangas() throws IOException, ClassNotFoundException {
        TableView<Vartotojas> lentele;

        //vardo stulpelis
        TableColumn<Vartotojas, String> vardasColumn = new TableColumn<>("Vardas");
        vardasColumn.setMinWidth(100);
        vardasColumn.setCellValueFactory(new PropertyValueFactory<>("vardas"));

        //pavarde stulpelis
        TableColumn<Vartotojas, String> pavardeColumn = new TableColumn<>("Pavardė");
        pavardeColumn.setMinWidth(100);
        pavardeColumn.setCellValueFactory(new PropertyValueFactory<>("pavarde"));

        //username stulpelis
        TableColumn<Vartotojas, String> usernameColumn = new TableColumn<>("Prisij.vardas");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        //password stulpelis
        TableColumn<Vartotojas, String> passwordColumn = new TableColumn<>("Slaptažodis");
        passwordColumn.setMinWidth(100);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        lentele = new TableView<>();
        lentele.setItems(getGyvasSarasas(nustatymai.getVartotojuFailoAdresas()));
        lentele.setMaxHeight(200);
        lentele.getColumns().addAll(vardasColumn, pavardeColumn, usernameColumn, passwordColumn);


        HBox hBox = new HBox();
        UserInfoPane userInfoPane = new UserInfoPane();

        lentele.setOnMouseClicked(e -> userInfoPane.kurtiSkydeli(lentele.getSelectionModel().getSelectedItem()));
        hBox.getChildren().addAll(lentele, userInfoPane);
        return hBox;

    }

    private HBox rungtyniuAdministravimoLangas() throws IOException, ClassNotFoundException {
        HBox rungtyniuAdministravimoLangas = new HBox();
        rungtyniuAdministravimoLangas.getChildren().addAll(komanduPuse(), rungtyniuPuse());
        return rungtyniuAdministravimoLangas;
    }

    private VBox komanduPuse() throws IOException, ClassNotFoundException {
        VBox kairesPusesMaketas = new VBox();
        Text komanduLentelesPaaiskinimas = new Text("Pasirinkite namų komandą iš sąrašo ir spauskite \"Pridėti\"\n" +
                "tuomet pasirinkite išvykos komandą ir vėl spauskite \"Pridėti\"");

        TableView<Komanda> lentele = kurtiKomanduLentele();
        Rungtynes tempRungtynes = new Rungtynes();

        //RUNGTYNIŲ KŪRIMO SKYDAS  --------------------------------------------------------------
        Text txtKlaiduEilute = new Text();
        HBox komanduAkistatosEilute = new HBox();
        HBox datosIrIdEilute = new HBox();
        HBox pridejimoMygtukoEilute = new HBox();

        //1. klaidų (EXCEPTIONS) rodymo eilutė
        txtKlaiduEilute.setFill(Color.RED);

        //2. Komandų akistatos eilutė
        Button btPridetiAKomanda = new Button("Pridėti komandą A →");
        Text txtAKomanda = new Text("[---]");
        Text txtVs = new Text(" vs. ");
        Text txtBKomanda = new Text("[---]");
        Button btPridetiBKomanda = new Button("← Pridėti komandą B");

        btPridetiAKomanda.setOnAction(e -> {
            try {
                tempRungtynes.setKomanda1(lentele.getSelectionModel().getSelectedItem());
                txtAKomanda.setText("[" + tempRungtynes.getKomanda1().getValstybeISO() + "]");
                txtKlaiduEilute.setText("");
                System.out.println("komanda A, viduj try:" + tempRungtynes.getKomanda1());
            } catch (NullPointerException Ex) {
                // nieko nedaryti, jei lentelėje niekas nepasirinkta
            } catch (VienodosKomandosException Ex2) {
                System.out.println(Ex2);
                txtKlaiduEilute.setText(Ex2.getPriezastis());
            }
            System.out.println("komanda A, po try:" + tempRungtynes.getKomanda1());

        });
        btPridetiBKomanda.setOnAction(e -> {
            try {
                tempRungtynes.setKomanda2(lentele.getSelectionModel().getSelectedItem());
                txtBKomanda.setText("[" + tempRungtynes.getKomanda2().getValstybeISO() + "]");
                txtKlaiduEilute.setText("");
                System.out.println("komanda B, viduj try:" + tempRungtynes.getKomanda2());
            } catch (NullPointerException Ex) {
                //nieko nedaryti, jei lentelėje niekas nepasirinkta
            } catch (VienodosKomandosException Ex2) {
                System.out.println(Ex2);
                txtKlaiduEilute.setText(Ex2.getPriezastis());
            }
            System.out.println("komanda B, po try:" + tempRungtynes.getKomanda2());

        });

        komanduAkistatosEilute.setAlignment(Pos.CENTER);
        komanduAkistatosEilute.setSpacing(8);
        komanduAkistatosEilute.getChildren().addAll(btPridetiAKomanda, txtAKomanda, txtVs, txtBKomanda, btPridetiBKomanda);

        //3. DATOS ir ID eilute
        final DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setMaxWidth(150);
        dateTimePicker.setPromptText("Rungtynių data");
        dateTimePicker.setOnAction(e -> {
                    LocalDateTime date = dateTimePicker.getDateTimeValue();
                    tempRungtynes.setRungtyniuData(date);
                }
        );

        TextField tfRungtyniuID = new TextField();
        tfRungtyniuID.setPromptText("Rungtynių ID");

        Button btGeneruotiID = new Button("?");
        btGeneruotiID.setOnAction(e -> {
            tempRungtynes.setUnikalusRungtyniuKodas(tempRungtynes.generateUnikalusRungtyniuKodas());
            tfRungtyniuID.setText(tempRungtynes.getUnikalusRungtyniuKodas());
        });

        datosIrIdEilute.setAlignment(Pos.CENTER);
        datosIrIdEilute.setSpacing(5);
        datosIrIdEilute.getChildren().addAll(dateTimePicker, tfRungtyniuID, btGeneruotiID);

        //PASKUTINĖ - Rungtynių pridėjimo eilutė
        pridejimoMygtukoEilute.setAlignment(Pos.CENTER);
        Button btPridetiRungtynes = new Button("Pridėti rungtynes →");
        btPridetiRungtynes.setMinWidth(350);
        btPridetiRungtynes.setMinHeight(40);
        btPridetiRungtynes.setOnAction(e -> {
            try {
                Rungtynes tempRungtynes2 = new Rungtynes();
                tempRungtynes2.setKomanda1(tempRungtynes.getKomanda1());
                tempRungtynes2.setKomanda2(tempRungtynes.getKomanda2());
                tempRungtynes2.setRungtyniuData(dateTimePicker.getDateTimeValue());
                tempRungtynes2.setUnikalusRungtyniuKodas(tfRungtyniuID.getText());
                pridetiRungtynes(tempRungtynes2);
                //Išvaloma visa info, paruošiama sekančiam pridėjimui:
                dateTimePicker.getEditor().clear();
                txtAKomanda.setText("[---]");
                txtBKomanda.setText("[---]");
                tfRungtyniuID.clear();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (VienodosKomandosException ex2) {
                System.out.println(ex2);
                txtKlaiduEilute.setText(ex2.getPriezastis());
            }
        });
        pridejimoMygtukoEilute.getChildren().addAll(btPridetiRungtynes);

        //VISOS KAIRĖS PUSĖS MOTININIO MAKETO NUSTATYMAI
        kairesPusesMaketas.setSpacing(10);
        kairesPusesMaketas.setPadding(new Insets(10, 10, 10, 10));
        kairesPusesMaketas.setAlignment(Pos.TOP_CENTER);
        // - sudedami visi elementai: ------------
        kairesPusesMaketas.getChildren().addAll(
                komanduLentelesPaaiskinimas,
                lentele,
                txtKlaiduEilute,
                komanduAkistatosEilute,
                datosIrIdEilute,
                pridejimoMygtukoEilute
        );

        return kairesPusesMaketas;

    }

    private VBox rungtyniuPuse() throws IOException, ClassNotFoundException {

        VBox rungtyniuPusesMaketas = new VBox();

        Text rungtyniuLentelesPaaiskinimas = new Text("Pasirinkite namų komandą iš sąrašo ir spauskite \"Pridėti\"\n" +
                "tuomet pasirinkite išvykos komandą ir vėl spauskite \"Pridėti\"");

        RungtyniuInfoPane rungtyniuInfoPane = new RungtyniuInfoPane();

        rungtyniuInfoPane.getBtSalinti().setOnAction(e -> {
            pasalintiRungtynes();
            rungtyniuInfoPane.valyti();
        });

        rungtyniuInfoPane.getBtPradeti().setOnAction(e -> {
            try { paspaustasPradeti(rungtyniuInfoPane); }
            catch (NullPointerException ex) { /*nieko nedaryti*/ }
        });

        rungtyniuInfoPane.getBtBaigti().setOnAction(e-> {
            paspaustasBaigti(rungtyniuInfoPane);
            }
        );

        rungtyniuLentele = kurtiRungtyniuLentele();
        rungtyniuLentele.setOnMouseClicked(e -> {
            try { rungtyniuInfoPane.kurtiSkydeli(rungtyniuLentele.getSelectionModel().getSelectedItem()); }
            catch (NullPointerException ex) {
                //Jei niekas nepasirinkta, o ant lentelės vistiek paspausta - nieko nedaryti
            }
        });

        rungtyniuPusesMaketas.getChildren().addAll(rungtyniuLentelesPaaiskinimas, rungtyniuLentele, rungtyniuInfoPane);
        rungtyniuPusesMaketas.setAlignment(Pos.TOP_CENTER);
        rungtyniuPusesMaketas.setSpacing(10);
        rungtyniuPusesMaketas.setPadding(new Insets(10, 10, 10, 10));

        return rungtyniuPusesMaketas;
    }

    public <T> ObservableList<T> getGyvasSarasas(String failoAdresas) {
        ObservableList<T> gyvasSarasas = FXCollections.observableArrayList();
        Archyvuotojas<T> archyvas = new Archyvuotojas<>(failoAdresas);
        HashMap<String, T> vartotojuHMap = archyvas.istrauktiSarasa();

        Set set = vartotojuHMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            gyvasSarasas.add((T) mentry.getValue());
        }
        return gyvasSarasas;
    }

    public ObservableList<Komanda> getVisosKomandos() throws IOException, ClassNotFoundException {
        ObservableList<Komanda> komanduLentele = FXCollections.observableArrayList();
        HashMap<String, Komanda> komanduHMap = new KomanduBiblioteka().getKomanduZemelapis();

        Set set = komanduHMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            komanduLentele.add((Komanda) mentry.getValue());
        }
        return komanduLentele;
    }

    public TableView kurtiKomanduLentele() throws IOException, ClassNotFoundException {
        TableView<Komanda> lentele = new TableView<>();
        //KOMANDŲ LENTELE ---------------------------------------------------------------------
        //Fifa reitingo stulpelis
        TableColumn<Komanda, String> fifaReitingasColumn = kurtiStulpeli("#", "komandosFifaReitingas", 50);
        //pavadinimas stulpelis
        TableColumn<Komanda, String> valstybeColumn = kurtiStulpeli("Pavadinimas", "valstybe", 160);
        //federacijos stulpelis
        TableColumn<Komanda, String> federacijosColumn = kurtiStulpeli("Konfederacija", "komandosFederacijaLT", 160);

        lentele.setItems(getVisosKomandos());
        lentele.setMaxHeight(200);
        lentele.getColumns().addAll(fifaReitingasColumn, valstybeColumn, federacijosColumn);
        lentele.getSortOrder().add(fifaReitingasColumn);
        //--------------------------------------------------------------------------------------

        return lentele;
    }

    public void pridetiRungtynes(Rungtynes rungtynes) throws IOException, ClassNotFoundException {

        Archyvuotojas<Rungtynes> archyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        archyvas.irasyti(rungtynes.getUnikalusRungtyniuKodas(), rungtynes);
        rungtyniuLentele.getItems().add(rungtynes);
        rungtyniuLentele.sort();

    }

    public void pasalintiRungtynes() {
        ObservableList<Rungtynes> visosRungtynes = rungtyniuLentele.getItems();
        Rungtynes rungtynes = rungtyniuLentele.getSelectionModel().getSelectedItem();
        visosRungtynes.remove(rungtynes);
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        HashMap<String, Rungtynes> rungtyniuSarasas = rungtyniuArchyvas.istrauktiSarasa();
        rungtyniuSarasas.remove(rungtynes.getUnikalusRungtyniuKodas());
        rungtyniuArchyvas.irasyti(rungtyniuSarasas);
    }

    public void paspaustasPradeti(RungtyniuInfoPane rungtyniuInfoPane) {
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(nustatymai.getVartotojuFailoAdresas());
        HashMap<String, Vartotojas> vartotojuSarasas = vartotojuArchyvas.istrauktiSarasa();
        Rungtynes rungtynes = rungtyniuLentele.getSelectionModel().getSelectedItem();

        rungtynes.pradetiRungtynes();

        //jeigu yra vartotojų kurie nespėjo rezultato - kiekvienam tokiam nustatomas spėjimas 0:0
        if (rungtynes.getVisiSpejimai().size() < vartotojuSarasas.size()) {
            Set<Map.Entry<String, Vartotojas>> entrySet = vartotojuSarasas.entrySet();
            for (Map.Entry<String, Vartotojas> entry : entrySet) {
                if (!rungtynes.getVisiSpejimai().containsKey(entry.getKey())) {
                    rungtynes.addSpejimas(entry.getValue(), new RezultatasGroupStage(0, 0));
                }
            }
        }
        rungtyniuArchyvas.irasyti(rungtynes.getUnikalusRungtyniuKodas(), rungtynes);
        rungtyniuLentele.setItems(getGyvasSarasas(nustatymai.getRungtyniuFailoAdresas()));
        rungtyniuInfoPane.disablePradeti();
        rungtyniuInfoPane.kurtiSkydeli(rungtynes);
    }

    public void paspaustasBaigti(RungtyniuInfoPane rungtyniuInfoPane) {
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        Rungtynes rungtynes = rungtyniuInfoPane.getRungtynes();
        System.out.println("Rungtynes ar vyksta: " + rungtynes.arVyksta());
        System.out.println("Rungtynes ar ivyko: " + rungtynes.arIvyko());
        System.out.println("Rungtynes: " + rungtynes.getKomanda1().getValstybe() + " vs. " + rungtynes.getKomanda2().getValstybe());
        System.out.println("Rungtynes: " + rungtynes.getRezultatas());
        if (rungtyniuInfoPane.ivestasRezultatas()) {
            rungtynes.baigtiRungtynes();
            rungtynes.setRezultatas(Integer.parseInt(rungtyniuInfoPane.getTfRezKomanda1().getText()), Integer.parseInt(rungtyniuInfoPane.getTfRezKomanda2().getText()));
            rungtyniuArchyvas.irasyti(rungtynes.getUnikalusRungtyniuKodas(), rungtynes);
            rungtyniuLentele.setItems(getGyvasSarasas(nustatymai.getRungtyniuFailoAdresas()));
            rungtyniuInfoPane.disableBaigti();
            rungtyniuInfoPane.kurtiSkydeli(rungtynes);
        }
    }

    public TableView kurtiRungtyniuLentele() throws IOException, ClassNotFoundException {
        //datos stulpelis
        TableColumn<Rungtynes, String> datosColumn = new TableColumn<>("Data");
        datosColumn.setMinWidth(90);
        datosColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rungtynes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Rungtynes, String> rungtynes) {
                return new SimpleStringProperty(rungtynes.getValue().getRungtyniuDataString());
            }
        });

        //komanda A stulpelis
        TableColumn<Rungtynes, String> komandaAColumn = new TableColumn<>("Komanda A");
        komandaAColumn.setMinWidth(90);
        komandaAColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rungtynes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Rungtynes, String> rungtynes) {
                return new SimpleStringProperty(rungtynes.getValue().getKomanda1().getValstybe());
            }
        });

        //komanda B stulpelis
        TableColumn<Rungtynes, String> komandaBColumn = new TableColumn<>("Komanda B");
        komandaBColumn.setMinWidth(90);
        komandaBColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rungtynes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Rungtynes, String> rungtynes) {
                return new SimpleStringProperty(rungtynes.getValue().getKomanda2().getValstybe());
            }
        });

        //unikalaus kodo stulpelis
        TableColumn<Rungtynes, String> kodasColumn = new TableColumn<>("ID");
        kodasColumn.setMinWidth(120);
        kodasColumn.setCellValueFactory(new PropertyValueFactory<>("unikalusRungtyniuKodas"));

        //rezultato stulpelis
        TableColumn<Rungtynes, String> rezultatasColumn = new TableColumn<>("Rez.");
        rezultatasColumn.setMinWidth(40);
        rezultatasColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rungtynes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Rungtynes, String> rungtynes) {
                String rezultatoCellText = "";
                if(!rungtynes.getValue().arIvyko() && !rungtynes.getValue().arVyksta()) rezultatoCellText = "-:-";
                else if (rungtynes.getValue().arVyksta()) rezultatoCellText = "vyksta";
                else rezultatoCellText = rungtynes.getValue().getRezultatas().toString();
                return new SimpleStringProperty(rezultatoCellText);
            }
        });

        //spejimu skaiciaus stulpelis
        TableColumn<Rungtynes, String> spejimuSkaiciausColumn = new TableColumn<>("Spėjo");
        spejimuSkaiciausColumn.setMinWidth(30);
        spejimuSkaiciausColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rungtynes, String>, ObservableValue<String>>() {
            Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(nustatymai.getVartotojuFailoAdresas());
            int registruotuVartotojuSkaicius = vartotojuArchyvas.istrauktiSarasa().size();

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Rungtynes, String> rungtynes) {
                return new SimpleStringProperty(String.valueOf(rungtynes.getValue().getVisiSpejimai().size()) + "/" + registruotuVartotojuSkaicius);
            }
        });

        TableView lentele = new TableView<>();
        lentele.setItems(getGyvasSarasas(nustatymai.getRungtyniuFailoAdresas()));
        lentele.setMaxHeight(200);
        lentele.setMaxWidth(510);
        lentele.getColumns().addAll(datosColumn, komandaAColumn, komandaBColumn, kodasColumn, rezultatasColumn, spejimuSkaiciausColumn);
        datosColumn.setSortType(TableColumn.SortType.DESCENDING);
        lentele.getSortOrder().add(datosColumn);

        return lentele;
    }

    public TableColumn<Komanda, String> kurtiStulpeli(String stulpelioPavadinimas, String infoLaukas, int minPlotis) {
        TableColumn<Komanda, String> stulpelis = new TableColumn<>(stulpelioPavadinimas);
        stulpelis.setMinWidth(minPlotis);
        stulpelis.setCellValueFactory(new PropertyValueFactory<>(infoLaukas));
        return stulpelis;
    }

}
