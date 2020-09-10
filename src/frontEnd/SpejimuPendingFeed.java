package frontEnd;

import backEnd.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpejimuPendingFeed extends VBox {

    private Nustatymai nustatymai = new Nustatymai();
    protected boolean expanded = false;
    protected Vartotojas vartotojas;
    protected Rungtynes rungtynes;
    protected boolean showFlags = true;
    private TextField tfIvarciai1 = new TextField();
    private TextField tfIvarciai2 = new TextField();
    public Button btRegistruotiSpejima = new Button("+");

    protected Font storasDidelisTekstas = Font.font("Century Gothic", FontWeight.BOLD, 16);

    public SpejimuPendingFeed(Vartotojas vartotojas, Rungtynes rungtynes) {
        this.vartotojas = vartotojas;
        this.rungtynes = rungtynes;
        piestiFeed();
    }

    protected void piestiFeed() {
        VBox taskuIrSpejimoStulpelis = getTaskuIrSpejimoStulpelis();

        ColorAdjust nespalvota = new ColorAdjust();
        nespalvota.setSaturation(-1);

        VBox rungtyniuIrRezultatoStulpelis = new VBox();
        rungtyniuIrRezultatoStulpelis.setPadding(new Insets(0,0,0,5));
        rungtyniuIrRezultatoStulpelis.setStyle("-fx-border-width: 0 0 0 1; -fx-border-color: white;");

        HBox visasRungtyniuSkydelis = new HBox(taskuIrSpejimoStulpelis, rungtyniuIrRezultatoStulpelis);
        visasRungtyniuSkydelis.setSpacing(5);

        Text txtRungtyniuData = new Text();
        txtRungtyniuData.setFont(Font.font("Century Gothic", 14));
        txtRungtyniuData.setFill(Color.WHITE);

        //KOMANDŲ PAVADINIMO, VĖLIAVŲ IR REZULTATO EILUTĖ
        Text txtRungtyniuRezultatas = new Text();
        txtRungtyniuRezultatas.setFont(getStorasMazasTekstas());
        txtRungtyniuRezultatas.setFill(Color.WHITE);
        ImageView veliava1 = new ImageView(new Image(rungtynes.getKomanda1().getValstybesVeliavosURL()));
        veliava1.setPreserveRatio(true);
        veliava1.setFitWidth(20);
        veliava1.setEffect(nespalvota);

        ImageView veliava2 = new ImageView(new Image(rungtynes.getKomanda2().getValstybesVeliavosURL()));
        veliava2.setPreserveRatio(true);
        veliava2.setFitWidth(20);
        veliava2.setEffect(nespalvota);

        //HBox rezultatoEilutesMaketas
        HBox rezultatoEilutesMaketas = new HBox(showFlags ? veliava1 : new Text(""), txtRungtyniuRezultatas, (showFlags ? veliava2 : new Text("")));
        rezultatoEilutesMaketas.setAlignment(Pos.BOTTOM_CENTER);
        rezultatoEilutesMaketas.setSpacing(5);

        ArrayList<Text> vartotojuSpejimai = getSpejamusRezultatus();

        rungtyniuIrRezultatoStulpelis.getChildren().addAll(txtRungtyniuData, rezultatoEilutesMaketas);

        //Laukelių reikšmės:

        String rungtyniuData = rungtynes.getRungtyniuDataString();
        String rungtyniuRezultatas = getRezultatas();

        txtRungtyniuData.setText(rungtyniuData);
        txtRungtyniuRezultatas.setText(rungtyniuRezultatas);

        //Expand/collapse

        setOnMouseEntered(e -> setStyle("-fx-background-color: #4a8751"));
        setOnMouseExited(e -> setStyle("-fx-background-color: transparent"));
        setOnMouseClicked(e -> {
            if (expanded) { //jeigu išplėstas tai sutraukti
                expanded = false;
                System.out.println(rungtynes.getRezultatas());
                System.out.println("ar ivyko: " + rungtynes.arIvyko());
                try {
                    taskuIrSpejimoStulpelis.getChildren().remove(2);
                    rungtyniuIrRezultatoStulpelis.getChildren().remove(2);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Index Out Of bounds");
                }
            } else { //jeigu sutrauktas - išplėsti ir rodyti išklotinę
                expanded = true;
                setStyle("-fx-background-color: #4a8751");
                taskuIrSpejimoStulpelis.getChildren().add(vartotojuSpejimai.get(0));
                rungtyniuIrRezultatoStulpelis.getChildren().addAll(vartotojuSpejimai.get(1));
            }
        });

        getChildren().addAll(visasRungtyniuSkydelis);
    }


    private ArrayList<Text> getSpejamusRezultatus() {
        Text taskuIsklotine = new Text("\n");
        Text spejimuIsklotine = new Text("\n");
        ArrayList<Text> grazinamiTekstai = new ArrayList<>();
        HashMap<String, Rezultatas> spejimai = rungtynes.getVisiSpejimai();

        if (spejimai.size() > 0) {
            Set<Map.Entry<String, Rezultatas>> entrySet = spejimai.entrySet();
            for (Map.Entry<String, Rezultatas> entry : entrySet) {

                Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
                Vartotojas spejantisVartotojas = (Vartotojas) vartotojuArchyvas.istrauktiSarasa().get(entry.getKey());

                taskuIsklotine.setText(taskuIsklotine.getText() + (rungtynes.arIvyko() ? (" +" + new Taskai().skaiciuoti(rungtynes, entry.getValue())) : "") + "\n");
                spejimuIsklotine.setText(spejimuIsklotine.getText() + entry.getValue().toString() + "  "
                        + spejantisVartotojas.getVardas() + " " + spejantisVartotojas.getPavarde() + "\n");
            }

            taskuIsklotine.setFill(Color.WHITE);
            spejimuIsklotine.setFill(Color.WHITE);
            grazinamiTekstai.add(taskuIsklotine);
            grazinamiTekstai.add(spejimuIsklotine);
        } else {
            grazinamiTekstai.add(new Text(" "));
            Text txtSpejimuNera = new Text("Rezultato dar niekas nespėjo.");
            txtSpejimuNera.setFill(Color.WHITE);
            grazinamiTekstai.add(txtSpejimuNera);
        }

        return grazinamiTekstai;
    }


    protected String getRezultatas() {
        return rungtynes.getKomanda1().getValstybe() + " "
                + (rungtynes.arIvyko() ? rungtynes.getRezultatas().getTotalIvarciaiKomanda1() : "-")
                + " : "
                + (rungtynes.arIvyko() ? rungtynes.getRezultatas().getTotalIvarciaiKomanda2() : "-")
                + " " + rungtynes.getKomanda2().getValstybe();
    }

    protected VBox getTaskuIrSpejimoStulpelis() {

        VBox taskuIrSpejimoStulpelis = new VBox();
        HBox spejimoEilute = new HBox();


        HBox spejimoIvestiesEilute = new HBox();
        btRegistruotiSpejima.setOnAction(e -> System.out.println("metodas A"));
        btRegistruotiSpejima.setFont(Font.font("Century Gothic", FontWeight.BOLD, 15));

        tfIvarciai1.setMaxWidth(30);
        tfIvarciai1.setStyle("-fx-border-color: grey; -fx-border-width: 0 0 0 0; -fx-background-color: #5ea866; -fx-text-fill: white");
        tfIvarciai1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 15));

        Text dvitaskis = new Text(":");
        dvitaskis.setFill(Color.WHITE);

        tfIvarciai2.setStyle("-fx-border-color: grey; -fx-border-width: 0 0 0 0; -fx-background-color: #5ea866; -fx-text-fill: white;-fx-text-fill: white");
        tfIvarciai2.setMaxWidth(30);
        tfIvarciai2.setFont(Font.font("Century Gothic", FontWeight.BOLD, 15));
        spejimoIvestiesEilute.setSpacing(3);
        spejimoIvestiesEilute.getChildren().addAll(tfIvarciai1, dvitaskis, tfIvarciai2);

//        btRegistruotiSpejima.setOnAction(e-> registruotiSpejima(Integer.parseInt(tfIvarciai1.getText()), Integer.parseInt(tfIvarciai2.getText())));


//        taskuIrSpejimoStulpelis.getChildren().addAll(btRegistruotiSpejima,spejimoIvestiesEilute);
//        return taskuIrSpejimoStulpelis;
        spejimoEilute.getChildren().addAll(btRegistruotiSpejima, spejimoIvestiesEilute);
        spejimoEilute.setAlignment(Pos.CENTER_RIGHT);
        taskuIrSpejimoStulpelis.getChildren().addAll(spejimoEilute, new Text(""));
        return taskuIrSpejimoStulpelis;
    }

    public TextField getTfIvarciai1() {
        return tfIvarciai1;
    }

    public TextField getTfIvarciai2() {
        return tfIvarciai2;
    }

    public Button getBtRegistruotiSpejima() {
        return btRegistruotiSpejima;
    }

    public Font getStorasMazasTekstas() {
        return Font.font("Century Gothic", FontWeight.BOLD, 14);
    }

    public Rungtynes getRungtynes() {
        return rungtynes;
    }
}
