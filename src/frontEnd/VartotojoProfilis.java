package frontEnd;

import Main.MainTotalizatorius2;
import backEnd.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.*;

public class VartotojoProfilis {

    private Vartotojas vartotojas;
    private Scene vartotojoAplinka;
    private Hyperlink atsijungti;

    private Nustatymai nustatymai = new Nustatymai();
    private Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
    private Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(nustatymai.getVartotojuFailoAdresas());

    private VBox feedHistory = new VBox();

    public VartotojoProfilis(Vartotojas vartotojas) {
        this.vartotojas = vartotojas;
        kurtiVartotojoAplinka(vartotojas);
    }

    public void kurtiVartotojoAplinka(Vartotojas vartotojas) {

        HBox pagrindinisMaketas = new HBox();
        VBox kairinioBlokoMaketas = new VBox();
        VBox desininioBlokoMaketas = new VBox();
        HBox virsutinioBlokoMaketas = new HBox();

        VBox taskuTopasSuAntraste = new VBox();
        taskuTopasSuAntraste.setSpacing(10);
        taskuTopasSuAntraste.getChildren().addAll(gamintiAntrastineJuosta("TOTALIZATORIAUS TOP"), new TaskuTopas(vartotojas));
        taskuTopasSuAntraste.setMinWidth(200);
        virsutinioBlokoMaketas.getChildren().addAll(userInfoBlokas(), taskuTopasSuAntraste);
        virsutinioBlokoMaketas.setSpacing(10);

        ScrollPane feedPendingScrollas = new ScrollPane();
        feedPendingScrollas.setContent(spejimuSkydeliai());
        feedPendingScrollas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        feedPendingScrollas.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        feedPendingScrollas.setFitToHeight(true);
        feedPendingScrollas.setStyle("-fx-background-color: linear-gradient(#396d3f, #4c9354); ");

        kairinioBlokoMaketas.getChildren().addAll(virsutinioBlokoMaketas, gamintiAntrastineJuosta("RUNGTYNĖS LAUKIANČIOS MANO SPĖJIMŲ:"), feedPendingScrollas);
        kairinioBlokoMaketas.setMargin(kairinioBlokoMaketas.getChildren().get(1), new Insets(10, 0, 0, 0));

        feedHistory.setSpacing(10);
        feedHistory.setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3);");
        feedHistory.setPrefHeight(462);
        feedHistory.setPrefWidth(250);
        ScrollPane feedHistoryScrollas = new ScrollPane();
        feedHistoryScrollas.setContent(feedHistory);
        feedHistoryScrollas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        feedHistoryScrollas.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        feedHistoryScrollas.setStyle("-fx-background-color: transparent");
        desininioBlokoMaketas.getChildren().addAll(gamintiAntrastineJuosta("MANO SPĖJIMŲ ISTORIJA:"), feedHistoryScrollas);

        pagrindinisMaketas.getChildren().addAll(kairinioBlokoMaketas, desininioBlokoMaketas);
        pagrindinisMaketas.setPadding(new Insets(10));
        pagrindinisMaketas.setSpacing(10);
        pagrindinisMaketas.setMaxHeight(510);

        vartotojoAplinka = new Scene(pagrindinisMaketas);
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

    public VBox userInfoBlokas() {
        //TITLE BLOCK: VARDAS, PAVARDE, TASKAI
        HBox userTitleBlockMaketas = new HBox();
        VBox userTaskuLangelis = new VBox();
        VBox userCredentialsLangelis = new VBox();

        Text txtVardasPavarde = new Text(vartotojas.getVardas().toUpperCase() + " " + vartotojas.getPavarde().toUpperCase());

        txtVardasPavarde.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        if ((vartotojas.getVardas().length() + vartotojas.getPavarde().length()) > 13)
            txtVardasPavarde.setText(vartotojas.getVardas().toUpperCase().substring(0, 1) + ". " + vartotojas.getPavarde().toUpperCase());
        txtVardasPavarde.setFontSmoothingType(FontSmoothingType.GRAY);

        Text txtUsername = new Text(vartotojas.getUsername());
        txtUsername.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 18));

        Text txtUserTaskai = new Text("" + new TaskuTopas(vartotojas).skaiciuotiVisusVartotojoTaskus(vartotojas));
        txtUserTaskai.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        txtUserTaskai.setFill(Color.WHITE);

        userTaskuLangelis.setStyle("-fx-background-color: #000000");
        userTaskuLangelis.getChildren().add(txtUserTaskai);
        userTaskuLangelis.setAlignment(Pos.CENTER);
        userTaskuLangelis.setMinWidth(35);
        userCredentialsLangelis.getChildren().addAll(txtVardasPavarde, txtUsername);
        userCredentialsLangelis.setSpacing(-2);
        userTitleBlockMaketas.setSpacing(10);
        userTitleBlockMaketas.getChildren().addAll(userTaskuLangelis, userCredentialsLangelis);
        //-----------------------------------------

        HBox logoutEilute = new HBox();
        ImageView atsijungtiSymbol = new ImageView(new Image("https://images2.imgbox.com/e3/96/iggLMHjA_o.png"));
        atsijungtiSymbol.setPreserveRatio(true);
        atsijungtiSymbol.setFitWidth(12);
        atsijungti = new Hyperlink("atsijungti");
        atsijungti.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 15));
        atsijungti.setOnAction(e -> {
            vartotojas.naujasAtsijungimas();
            vartotojuArchyvas.irasyti(vartotojas.getUsername(), vartotojas);
            MainTotalizatorius2.logout();
        });
        logoutEilute.setSpacing(1);
        logoutEilute.setAlignment(Pos.CENTER_LEFT);
        logoutEilute.getChildren().addAll(atsijungtiSymbol, atsijungti);

        Text txtTotalizatoriausTaisykliuTitle = new Text("\nTotalizatoriaus taisyklės");
        txtTotalizatoriausTaisykliuTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));

        String taisykliuTekstas =
                "Taškų skaičiavimas (max = " + (nustatymai.getTaskaiUzNugaletoja() + nustatymai.getTaskaiUzIvarciuSkirtuma() + nustatymai.getTaskaiUzTiksluSpejima() + nustatymai.getTaskaiKompensaciniaiMAX())
                        + " tašk.):\n" +
                        "Teisingai atspėjus nugalėtoją +" + nustatymai.getTaskaiUzNugaletoja() + " tašk." + "\n" +
                        "Teisingai atspėjus įv. skirtumą +" + nustatymai.getTaskaiUzIvarciuSkirtuma() + " tašk." + "\n" +
                        "Atspėjus tikslų rezultatą +" + nustatymai.getTaskaiUzTiksluSpejima() + " tašk." + "\n" +
                        "Bonus +(" + nustatymai.getTaskaiKompensaciniaiMAX() + " - |rez - spėj| * "
                        + nustatymai.getTaskaiKompensaciniaiKOEF() + ") tašk.\n\n" +
                        "Pavėluotas spėjimas\n prilyginamas spėjimui 0:0." + "\n\n" +
                        "Laukiančių rungtynių saraše\n įvesk spėjamą rezultatą ir spausk \"+\"";
        Text txtTotalizatoriausTaisykles = new Text(taisykliuTekstas);
        txtTotalizatoriausTaisykles.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 11));


        VBox userInfoMaketas = new VBox();
        userInfoMaketas.setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3);");
        userInfoMaketas.setPadding(new Insets(10, 10, 10, 10));
        userInfoMaketas.setSpacing(2);
        userInfoMaketas.getChildren().addAll(userTitleBlockMaketas, logoutEilute, txtTotalizatoriausTaisykliuTitle, txtTotalizatoriausTaisykles);
        return userInfoMaketas;
    }

    private VBox spejimuSkydeliai() {

        VBox feedPending = new VBox();
        feedPending.setSpacing(10);
        feedPending.setPadding(new Insets(10, 0, 0, 0));
        feedPending.setStyle("-fx-background-color: linear-gradient(#396d3f, #4c9354);");

        ArrayList<Rungtynes> pendingRungtyniuSarasas = getPendingRungtynes(vartotojas);
        //RIKIUOTI PAGAL DATA (Apversti nereikia, nes spejamos anksciausios):
        pendingRungtyniuSarasas.sort(Comparator.comparing(o -> o.getRungtyniuData()));

        //WOW ... pradžia WOW..
        LinkedList<SpejimuPendingFeed> boksai = new LinkedList<>();
        for (Rungtynes rungtynes : pendingRungtyniuSarasas) {
            boksai.add(new SpejimuPendingFeed(vartotojas, rungtynes));
        }
        for (SpejimuPendingFeed boksas : boksai) {
            feedPending.getChildren().add(boksas);
            boksas.getBtRegistruotiSpejima().setOnAction(e -> {

                if ((!boksas.getTfIvarciai1().getText().equals("") && !boksas.getTfIvarciai2().getText().equals(""))) {
                    System.out.println("Paspaustas boksas Nr.: " + boksai.indexOf(boksas));
                    feedPending.getChildren().remove(boksai.indexOf(boksas));
                    boksai.remove(boksas);
                    Rungtynes spejamosRungtynes = boksas.getRungtynes();
                    int spejamiIvarciai1 = Integer.parseInt(boksas.getTfIvarciai1().getText());
                    int spejamiIvarciai2 = Integer.parseInt(boksas.getTfIvarciai2().getText());
                    spejamosRungtynes.addSpejimas(vartotojas, new RezultatasGroupStage(spejamiIvarciai1, spejamiIvarciai2));
                    rungtyniuArchyvas.irasyti(spejamosRungtynes.getUnikalusRungtyniuKodas(), spejamosRungtynes);
                    feedHistory.getChildren().add(new SpejimuHistoryFeed(vartotojas, spejamosRungtynes,true));
                } else System.out.println("tusti laukeliai");
            });

        }
        //WOW ... pabaiga WOW..

        ArrayList<Rungtynes> spetuRungtyniuSarasas = getSpetosRungtynes(vartotojas);
        //RIKIUOTI PAGAL DATA:
        spetuRungtyniuSarasas.sort(Comparator.comparing(o -> o.getRungtyniuData()));
        //REVERSE LIST WORKAROUND:
        for (int i = spetuRungtyniuSarasas.size() - 1; i >= 0; i--) {
            feedHistory.getChildren().add(new SpejimuHistoryFeed(vartotojas, spetuRungtyniuSarasas.get(i), true));
        }

        feedPending.setPrefWidth(440);
        return feedPending;
    }

    private ArrayList<Rungtynes> getSpetosRungtynes(Vartotojas vartotojas) {
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        HashMap<String, Rungtynes> visuRungtyniuSarasas = rungtyniuArchyvas.istrauktiSarasa();
        ArrayList<Rungtynes> spetuRungtyniuSarasas = new ArrayList<>();

        Set<Map.Entry<String, Rungtynes>> entrySet = visuRungtyniuSarasas.entrySet();
        for (Map.Entry<String, Rungtynes> entry : entrySet) {
            if (entry.getValue().getVisiSpejimai().containsKey(vartotojas.getUsername())) {
                spetuRungtyniuSarasas.add(entry.getValue());
            }
        }

        return spetuRungtyniuSarasas;
    }

    private ArrayList<Rungtynes> getPendingRungtynes(Vartotojas vartotojas) {
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        HashMap<String, Rungtynes> visuRungtyniuSarasas = rungtyniuArchyvas.istrauktiSarasa();
        ArrayList<Rungtynes> nespetuRungtyniuSarasas = new ArrayList<>();

        Set<Map.Entry<String, Rungtynes>> entrySet = visuRungtyniuSarasas.entrySet();
        for (Map.Entry<String, Rungtynes> entry : entrySet) {
            Rungtynes tikrinamosRungtynes = entry.getValue();
            if (!tikrinamosRungtynes.getVisiSpejimai().containsKey(vartotojas.getUsername()) && !tikrinamosRungtynes.arIvyko() && !tikrinamosRungtynes.arVyksta()) {
                nespetuRungtyniuSarasas.add(tikrinamosRungtynes);
            } else if (!tikrinamosRungtynes.getVisiSpejimai().containsKey(vartotojas.getUsername()) && (tikrinamosRungtynes.arIvyko() || tikrinamosRungtynes.arVyksta())) {
                //Jei i rungtyniu spejima paveluota - priskiriamas default 0:0 spejimas:
                tikrinamosRungtynes.addSpejimas(vartotojas, new RezultatasGroupStage(0, 0));
                rungtyniuArchyvas.irasyti(tikrinamosRungtynes.getUnikalusRungtyniuKodas(), tikrinamosRungtynes);
            }
        }
        return nespetuRungtyniuSarasas;
    }

    public Scene getScene() {
        return vartotojoAplinka;
    }

    public Hyperlink getAtsijungti() {
        return atsijungti;
    }
}
