package frontEnd;

import backEnd.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class UserInfoPane extends VBox {

    //Klasė UserInfoPane naudojama Administratoriaus aplinkoje, administruojant vartotojus
    //lentelėje pasirinkus vartotoją - šalia rodomas vartotojo informacijos panelis

    private Nustatymai nustatymai = new Nustatymai();
    private Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
    private Button btIstrintiVartotoja = new Button("IŠTRINTI VARTOTOJĄ");

    public UserInfoPane() {
        setMinWidth(400);
        kurtiTusciaSkydeli();
    }

    public void kurtiSkydeli(Vartotojas vartotojas) {

        HBox vartotojoTitleMaketas = new HBox();
        VBox vartotojoTextInfoMaketas = new VBox();
        VBox vartotojoKairinisMaketas = new VBox();

        ImageView profilioFoto = new ImageView(new Image("https://images2.imgbox.com/a7/ad/AOEANJJV_o.png"));

        profilioFoto.setPreserveRatio(true);
        profilioFoto.setFitWidth(80);
        Text vartotojoTaskai = new Text(String.valueOf(new TaskuTopas(vartotojas).skaiciuotiVisusVartotojoTaskus(vartotojas)));
        vartotojoTaskai.setFont(Font.font("Century Gothic" , FontWeight.BOLD, 25));
        vartotojoTaskai.setFill(Color.WHITE);

        StackPane profilioFotoIrTaskai = new StackPane(profilioFoto,vartotojoTaskai);
        profilioFotoIrTaskai.setAlignment(Pos.BOTTOM_CENTER);

        Text txtVardasPavarde = new Text(vartotojas.getVardas().toUpperCase() + " " + vartotojas.getPavarde().toUpperCase());
        txtVardasPavarde.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        Text txtUsername = new Text(vartotojas.getUsername());
        txtUsername.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 18));

        Text txtPaskutinisPrisijungimas = new Text("Paskutinis prisijungimas: " + vartotojas.getPaskutinisPrisijungimas());
        txtPaskutinisPrisijungimas.setTextAlignment(TextAlignment.CENTER);
        Text txtPaskutinisAtsijungimas = new Text("Paskutinis atsijungimas:  " + vartotojas.getPaskutinisAtsijungimas());
        txtPaskutinisAtsijungimas.setTextAlignment(TextAlignment.CENTER);
        Text txtProfilioSukurimoLaikas = new Text("Sukurtas:  " + vartotojas.getProfilioSukurimoLaikas());
        txtProfilioSukurimoLaikas.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 12));

        btIstrintiVartotoja.setMaxWidth(Double.MAX_VALUE);

        vartotojoTitleMaketas.getChildren().clear();
        vartotojoTextInfoMaketas.getChildren().addAll(txtVardasPavarde,txtUsername, txtProfilioSukurimoLaikas, btIstrintiVartotoja);
        vartotojoTextInfoMaketas.setSpacing(5);
        vartotojoTextInfoMaketas.setAlignment(Pos.TOP_LEFT);
        vartotojoTextInfoMaketas.setMinWidth(250);
        vartotojoTitleMaketas.getChildren().addAll(profilioFotoIrTaskai, vartotojoTextInfoMaketas);
        vartotojoTitleMaketas.setMinWidth(370);
        vartotojoTitleMaketas.setSpacing(20);

        ScrollPane prisijungimuScrollas = new ScrollPane(new VBox(new Text(vartotojas.gautiPrisijungimuIsklotine())));
        prisijungimuScrollas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        prisijungimuScrollas.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        prisijungimuScrollas.setStyle("-fx-background-color: transparent");

        vartotojoKairinisMaketas.getChildren().addAll(vartotojoTitleMaketas, txtPaskutinisAtsijungimas, txtPaskutinisPrisijungimas, prisijungimuScrollas);
        vartotojoKairinisMaketas.setSpacing(10);

        ScrollPane feedHistoryScrollas = new ScrollPane();
        VBox spejimuSkydeliai = spejimuSkydeliai(vartotojas);
        spejimuSkydeliai.setMinHeight(310);
        spejimuSkydeliai.setMaxHeight(310);
        spejimuSkydeliai.setPrefWidth(340);
        spejimuSkydeliai.setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3); -fx-border-color: #d2d2d2; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-style: dashed;");
        feedHistoryScrollas.setContent(spejimuSkydeliai);
        feedHistoryScrollas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        feedHistoryScrollas.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        feedHistoryScrollas.setStyle("-fx-background-color: transparent");

        HBox visasUserInfoPane = new HBox();
        visasUserInfoPane.setMinHeight(310);
        visasUserInfoPane.setMaxHeight(310);
        visasUserInfoPane.getChildren().addAll(vartotojoKairinisMaketas, feedHistoryScrollas);
        visasUserInfoPane.setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3);");

        setPadding(new Insets(10, 10, 10, 10));
        setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3);");
        getChildren().clear();
        getChildren().addAll(visasUserInfoPane);
    }

    public void kurtiTusciaSkydeli() {
        getChildren().clear();
        HBox visasUserInfoPane = new HBox();
        visasUserInfoPane.setMinHeight(315);
        visasUserInfoPane.setMaxHeight(315);
        visasUserInfoPane.setStyle("-fx-background-color: linear-gradient(#ffffff,    #c3c3c3);");

        ImageView profilioFoto = new ImageView(new Image("https://images2.imgbox.com/01/96/QV8BTxnO_o.png"));
        VBox tuscioKaire = new VBox(profilioFoto);
        tuscioKaire.setPadding(new Insets(10));
        Text txtTuscioUzrasas = new Text("Pasirink vartotoją iš vartotojų sąrašo aukščiau");
        txtTuscioUzrasas.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 12));
        txtTuscioUzrasas.setFill(Color.rgb(210,210,210));
        VBox tuscioDesine = new VBox(txtTuscioUzrasas);
        tuscioDesine.setAlignment(Pos.TOP_CENTER);
        tuscioDesine.setPadding(new Insets(40));
        tuscioDesine.setMaxWidth(250);

        VBox tuscioDesiniausias = new VBox();
        tuscioDesiniausias.setStyle("-fx-border-color: #d2d2d2; -fx-border-insets: 5; -fx-border-width: 1; -fx-border-style: dashed;");
        tuscioDesiniausias.setPrefWidth(290);

        visasUserInfoPane.getChildren().addAll(tuscioKaire,tuscioDesine,tuscioDesiniausias);
        profilioFoto.setPreserveRatio(true);
        profilioFoto.setFitWidth(80);

        setSpacing(30);
        getChildren().addAll(visasUserInfoPane);
    }

    private VBox spejimuSkydeliai(Vartotojas vartotojas) {
        VBox feedHistory = new VBox();
        ArrayList<Rungtynes> spetuRungtyniuSarasas = getSpetosRungtynes(vartotojas);
        //RIKIUOTI PAGAL DATA:
        spetuRungtyniuSarasas.sort(Comparator.comparing(o -> o.getRungtyniuData()));
        //REVERSE LIST WORKAROUND:
        for (int i = spetuRungtyniuSarasas.size() - 1; i >= 0; i--) {
            feedHistory.getChildren().add(new SpejimuHistoryFeed(vartotojas, spetuRungtyniuSarasas.get(i), false));
        }
        return feedHistory;
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

    public Button getBtIstrintiVartotoja() {
        return btIstrintiVartotoja;
    }
}
