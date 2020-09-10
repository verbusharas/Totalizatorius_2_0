package frontEnd;

import Main.MainTotalizatorius2;
import backEnd.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.*;

public class AdminLangas {

    private TableView<Rungtynes> rungtyniuLentele;
    private Nustatymai nustatymai = new Nustatymai();
    Archyvuotojas<KomanduBiblioteka> komanduBibliotekosArchyvas = new Archyvuotojas<>(nustatymai.getKomanduFailoAdresas());
    private Scene vaizdas;
    private Hyperlink btSalinti = new Hyperlink( " pašalinti rungtynes [" +"\u00d7" + "]");
    private RungtyniuAdministravimoPane rungtyniuAdministravimoPane = new RungtyniuAdministravimoPane();
    private NustatymuLangas nustatymuLangas = new NustatymuLangas();

    public AdminLangas() {
        kurtiAdminLanga();
    }

    public void kurtiAdminLanga() {

        TabPane tabMaketas = new TabPane();

        Tab tabVartotojai = new Tab("Vartotojai");
        tabVartotojai.setContent(vartotojuAdministravimoLangas());
        tabVartotojai.setOnSelectionChanged(e->tabVartotojai.setContent(vartotojuAdministravimoLangas()));

        Tab tabRungtynes = new Tab("Rungtynės");
        tabRungtynes.setContent(rungtyniuAdministravimoLangas());
        tabRungtynes.setOnSelectionChanged(e->tabRungtynes.setContent(rungtyniuAdministravimoLangas()));

        Tab tabNustatymai = new Tab("Nustatymai");
        tabNustatymai.setContent(nustatymuLangas);
//        reguliuotiNustatymuMygtukus(tabRungtynes, tabVartotojai);

        Tab tabAtsijungti = new Tab("");
        tabAtsijungti.setOnSelectionChanged(e -> MainTotalizatorius2.logout());
        ImageView logoffSymbol = new ImageView(new Image("https://images2.imgbox.com/e3/96/iggLMHjA_o.png"));
        logoffSymbol.setPreserveRatio(true);
        logoffSymbol.setFitWidth(20);

        tabAtsijungti.setGraphic(logoffSymbol);
        tabAtsijungti.setId("logoff_tab");


        //Tabų nustatymai:
        tabMaketas.getTabs().addAll(tabVartotojai, tabRungtynes, tabNustatymai, tabAtsijungti);
        tabMaketas.setSide(Side.LEFT);
        tabMaketas.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabMaketas.setTabMinHeight(50);
        tabMaketas.setTabMinWidth(135);
        tabMaketas.setRotateGraphic(false);

        vaizdas = new Scene(tabMaketas);
        vaizdas.getStylesheets().add(getClass().getResource("tabStilius2.css").toExternalForm());

    }


    public RungtyniuAdministravimoPane getRungtyniuAdministravimoPane() {
        return rungtyniuAdministravimoPane;
    }

    private HBox vartotojuAdministravimoLangas() {
        return new VartotojuAdministravimoLangas();
    }

    private HBox rungtyniuAdministravimoLangas() {
        HBox rungtyniuAdministravimoLangas = new HBox();
        rungtyniuAdministravimoLangas.getChildren().addAll(komanduPuse(), rungtyniuPuse());
        return rungtyniuAdministravimoLangas;
    }

    private VBox komanduPuse() {

        VBox kairesPusesMaketas = new VBox();

        TableView<Komanda> lentele = getKomanduLentele();

        Rungtynes kuriamosRungtynes = new Rungtynes();

        //RUNGTYNIŲ KŪRIMO SKYDAS  --------------------------------------------------------------
        Button btPridetiRungtynes = new Button("REGISTRUOTI");
        btPridetiRungtynes.setMinSize(542, 40);

        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setId("dtp");
        dateTimePicker.setPrefWidth(170);
        dateTimePicker.setPromptText("Rungtynių data");
        dateTimePicker.setOnAction(e -> {
            LocalDateTime date = dateTimePicker.getDateTimeValue();
            kuriamosRungtynes.setRungtyniuData(date);
        });

        TextField tfRungtyniuID = new TextField();
        tfRungtyniuID.setPromptText("Rungtynių ID");
        tfRungtyniuID.setId("tfRungtyniuID");

        Button btGeneruotiID = new Button("?");
        btGeneruotiID.setOnAction(e -> {
            kuriamosRungtynes.setUnikalusRungtyniuKodas(kuriamosRungtynes.generateUnikalusRungtyniuKodas());
            tfRungtyniuID.setText(kuriamosRungtynes.getUnikalusRungtyniuKodas());
        });

        VBox kairinisAkistatosPanelis = new VBox();
        VBox centrinisAkistatosPanelis = new VBox();
        VBox desininisAkistatosPanelis = new VBox();
        HBox komanduAkistatosLangas = new HBox(kairinisAkistatosPanelis, centrinisAkistatosPanelis, desininisAkistatosPanelis);

        KomandosInfoPane kaireSelectedKomanda = new KomandosInfoPane();
        kaireSelectedKomanda.aktyvuoti();
        kairinisAkistatosPanelis.getChildren().add(kaireSelectedKomanda);
        kairinisAkistatosPanelis.setMinWidth(185);
        kairinisAkistatosPanelis.setPadding(new Insets(3, 10, 10, 10));

        KomandosInfoPane desineSelectedKomanda = new KomandosInfoPane();
        desininisAkistatosPanelis.getChildren().add(desineSelectedKomanda);
        desininisAkistatosPanelis.setMinWidth(185);
        desininisAkistatosPanelis.setPadding(new Insets(3, 10, 10, 10));

        kaireSelectedKomanda.setOnMouseClicked(e -> {
            kaireSelectedKomanda.aktyvuoti();
            desineSelectedKomanda.deaktyvuoti();
        });
        desineSelectedKomanda.setOnMouseClicked(e -> {
            desineSelectedKomanda.aktyvuoti();
            kaireSelectedKomanda.deaktyvuoti();
        });

        CheckBox cbAtkrintamosios = new CheckBox();
        Text txtAtkrintamosios = new Text("Atkrintamosios?");

        Text txtVS = new Text("VS");
        txtVS.setFont(Font.font("Century Gothic", FontWeight.BOLD, 40));

        HBox eiluteID = new HBox(tfRungtyniuID, btGeneruotiID);

        HBox eiluteAtkrintamosios = new HBox(txtAtkrintamosios, cbAtkrintamosios);
        eiluteAtkrintamosios.setSpacing(10);
        eiluteAtkrintamosios.setAlignment(Pos.CENTER);

        centrinisAkistatosPanelis.getChildren().addAll(dateTimePicker, eiluteID, eiluteAtkrintamosios, txtVS);
        centrinisAkistatosPanelis.setMinWidth(120);
        centrinisAkistatosPanelis.setPadding(new Insets(10, 0, 10, 0));
        centrinisAkistatosPanelis.setSpacing(10);
        centrinisAkistatosPanelis.setAlignment(Pos.TOP_CENTER);
        centrinisAkistatosPanelis.setMargin(txtVS, new Insets(50, 0, 0, 0));

        VBox visasNaujuRungtyniuKurimoPanelis = new VBox(gamintiAntrastineJuosta("NAUJŲ RUNGTYNIŲ KŪRIMAS"), komanduAkistatosLangas, btPridetiRungtynes);
        visasNaujuRungtyniuKurimoPanelis.setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3);");

        lentele.setOnMouseClicked(e -> {
            System.out.println("centrinio kurimo panelio ilgis: " + centrinisAkistatosPanelis.getWidth());
            try {
                if (kaireSelectedKomanda.isAktyvus()) {
                    kaireSelectedKomanda.piestiKomanda(lentele.getSelectionModel().getSelectedItem());
                    try {
                        kuriamosRungtynes.setKomanda1(lentele.getSelectionModel().getSelectedItem());
                    } catch (VienodosKomandosException ex) {
                        System.out.println("Klaida: " + ex);
                    }
                } else if (desineSelectedKomanda.isAktyvus()) {
                    desineSelectedKomanda.piestiKomanda(lentele.getSelectionModel().getSelectedItem());
                    try {
                        kuriamosRungtynes.setKomanda2(lentele.getSelectionModel().getSelectedItem());
                    } catch (VienodosKomandosException ex) {
                        System.out.println("Klaida: " + ex);
                    }
                }
            } catch (NullPointerException ex) {
                //nieko nedaryti, jei netyčia paspausta ant keistos lentelės vietos
            }
        });
        btPridetiRungtynes.setOnAction(e -> {
            try {
                Rungtynes tempRungtynes2 = new Rungtynes();
                tempRungtynes2.setKomanda1(kuriamosRungtynes.getKomanda1());
                tempRungtynes2.setKomanda2(kuriamosRungtynes.getKomanda2());
                tempRungtynes2.setRungtyniuData(dateTimePicker.getDateTimeValue());
                if (tfRungtyniuID.getText().equals("")) {
                    tempRungtynes2.setUnikalusRungtyniuKodas(tempRungtynes2.generateUnikalusRungtyniuKodas());
                } else {
                    tempRungtynes2.setUnikalusRungtyniuKodas(tfRungtyniuID.getText());
                }
                pridetiRungtynes(tempRungtynes2);
                //Išvaloma visa info, paruošiama sekančiam pridėjimui:
                dateTimePicker.getEditor().clear();
                tfRungtyniuID.clear();
                kaireSelectedKomanda.piestiTuscia();
                kaireSelectedKomanda.aktyvuoti();
                desineSelectedKomanda.piestiTuscia();
                desineSelectedKomanda.deaktyvuoti();
            } catch (VienodosKomandosException ex2) {
                System.out.println(ex2);
            }
        });

        kairesPusesMaketas.setSpacing(10);
        kairesPusesMaketas.setPadding(new Insets(10, 10, 10, 10));
        kairesPusesMaketas.setAlignment(Pos.TOP_CENTER);
        kairesPusesMaketas.setMargin(lentele, new Insets(-10, 0, 0, 0));
        kairesPusesMaketas.getChildren().addAll(gamintiAntrastineJuosta("KOMANDŲ BIBLIOTEKA"), lentele, visasNaujuRungtyniuKurimoPanelis);
        return kairesPusesMaketas;
    }

    private VBox rungtyniuPuse() {

        VBox desinesPusesMaketas = new VBox();

        rungtyniuAdministravimoPane.getBtPradeti().setOnAction(e -> {
            try {
                paspaustasPradeti(rungtyniuAdministravimoPane);
            } catch (NullPointerException ex) { /*nieko nedaryti*/ }
        });

        rungtyniuAdministravimoPane.getBtBaigti().setOnAction(e -> {
                    paspaustasBaigti(rungtyniuAdministravimoPane);
                }
        );

        rungtyniuLentele = getRungtyniuLentele();
        rungtyniuLentele.setOnMouseClicked(e -> {
            try {
//                rungtyniuInfoPane.kurtiSkydeli(rungtyniuLentele.getSelectionModel().getSelectedItem());
                rungtyniuAdministravimoPane.setRungtynes(rungtyniuLentele.getSelectionModel().getSelectedItem());
            } catch (NullPointerException ex) {
                //Jei niekas nepasirinkta, o ant lentelės vistiek paspausta - nieko nedaryti
            }
        });

        desinesPusesMaketas.getChildren().addAll(gamintiAntrastineJuosta("REGISTRUOTŲ RUNGTYNIŲ TVARKARAŠTIS"), rungtyniuLentele, gamintiAntrastineJuostaSuX("RUNGTYNIŲ ADMINISTRAVIMAS"));
        desinesPusesMaketas.getChildren().add(rungtyniuAdministravimoPane);
        desinesPusesMaketas.setAlignment(Pos.TOP_CENTER);
        desinesPusesMaketas.setSpacing(10);
        desinesPusesMaketas.setPadding(new Insets(10, 10, 10, 10));
        desinesPusesMaketas.setMargin(rungtyniuAdministravimoPane, new Insets(-10, 0, 0, 0));

        //naujo dizaino rungtyniu info panelis:
        return desinesPusesMaketas;
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

    public ObservableList<Komanda> getVisosKomandos() {
        ObservableList<Komanda> komanduLentele = FXCollections.observableArrayList();
//        HashMap<String, Komanda> komanduHMap = new KomanduBiblioteka().getKomanduZemelapis();
        KomanduBiblioteka komanduBiblioteka = komanduBibliotekosArchyvas.istraukti();
        HashMap<String, Komanda> komanduHMap = komanduBiblioteka.getKomanduZemelapis();

        Set set = komanduHMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            komanduLentele.add((Komanda) mentry.getValue());
        }
        return komanduLentele;
    }

    public TableView getKomanduLentele() {
        TableView<Komanda> lentele = new TableView<>();
        //KOMANDŲ LENTELE ---------------------------------------------------------------------
        //Fifa reitingo stulpelis
        TableColumn<Komanda, String> fifaReitingasColumn = kurtiStulpeli("#", "komandosFifaReitingas", 40);
        //pavadinimas stulpelis
        TableColumn<Komanda, String> valstybeColumn = kurtiStulpeli("Pavadinimas", "valstybe", 190);
        //federacijos stulpelis
        TableColumn<Komanda, String> federacijosColumn = kurtiStulpeli("Konfederacija", "komandosFederacijaLT", 250);

        lentele.setItems(getVisosKomandos());
        lentele.setMaxHeight(250);
        lentele.getColumns().addAll(fifaReitingasColumn, valstybeColumn, federacijosColumn);
        lentele.getSortOrder().add(fifaReitingasColumn);
        //--------------------------------------------------------------------------------------

        return lentele;
    }

    public void pridetiRungtynes(Rungtynes rungtynes) {

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
        rungtyniuAdministravimoPane.setRungtynes(rungtyniuLentele.getSelectionModel().getSelectedItem());
    }

    public void paspaustasPradeti(RungtyniuAdministravimoPane rungtyniuAdministravimoPane) {
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
        rungtyniuAdministravimoPane.setRungtynes(rungtynes);
    }

    public void paspaustasBaigti(RungtyniuAdministravimoPane rungtyniuAdministravimoPane){

        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        Rungtynes rungtynes = rungtyniuAdministravimoPane.getRodomosRungtynes();
        System.out.println("Rungtynes ar vyksta: " + rungtynes.arVyksta());
        System.out.println("Rungtynes ar ivyko: " + rungtynes.arIvyko());
        System.out.println("Rungtynes: " + rungtynes.getKomanda1().getValstybe() + " vs. " + rungtynes.getKomanda2().getValstybe());
        System.out.println("Rungtynes: " + rungtynes.getRezultatas());
        if (rungtyniuAdministravimoPane.ivestasRezultatas()) {
            rungtynes.baigtiRungtynes();
            rungtynes.setRezultatas(Integer.parseInt(rungtyniuAdministravimoPane.getTfIvarciaiKomanda1().getText()), Integer.parseInt(rungtyniuAdministravimoPane.getTfIvarciaiKomanda2().getText()));
            rungtyniuArchyvas.irasyti(rungtynes.getUnikalusRungtyniuKodas(), rungtynes);
            rungtyniuLentele.setItems(getGyvasSarasas(nustatymai.getRungtyniuFailoAdresas()));
            rungtyniuAdministravimoPane.setRungtynes(rungtynes);
        }
    }

    public TableView getRungtyniuLentele() {
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
                if (!rungtynes.getValue().arIvyko() && !rungtynes.getValue().arVyksta()) rezultatoCellText = "-:-";
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
        lentele.setMaxHeight(220);
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

    public Scene getVaizdas() {
        return vaizdas;
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

    private HBox gamintiAntrastineJuostaSuX(String antraste) {
        HBox antrastineJuosta = new HBox();
        antrastineJuosta.setPadding(new Insets(5, 5, 5, 15));
        antrastineJuosta.setStyle("-fx-background-color: #000000");
        Text txtPendingSpejimuTitle = new Text(antraste);
        txtPendingSpejimuTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));
        txtPendingSpejimuTitle.setFill(Color.WHITE);
        antrastineJuosta.setSpacing(150);
        antrastineJuosta.getChildren().addAll(txtPendingSpejimuTitle, btSalinti);
        btSalinti.setFont(Font.font("Century Gothic", FontWeight.BOLD, 11));
        btSalinti.setOnAction(e->{
            try {
                pasalintiRungtynes();
            }catch (NullPointerException ex) {
                //jei vartotojas paspaude salinti, nepasirinkes rungtyniu - nieko nedaryti
                System.out.println("Klaida: bandoma šalinti rungtynes nieko nepasirinkus");
            }
        });
        return antrastineJuosta;
    }



}
