package frontEnd;

import backEnd.*;
import javafx.animation.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpejimuHistoryFeed extends VBox {

    protected Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
    protected boolean expandable = true;
    protected boolean expanded = false;
    protected boolean showFlags = false;
    protected Vartotojas vartotojas;
    protected Rungtynes rungtynes;

    protected Font storasDidelisTekstas = Font.font("Century Gothic", FontWeight.BOLD, 16);
    protected Font storasMazasTekstas = Font.font("Century Gothic", FontWeight.BOLD, 14);

    public SpejimuHistoryFeed(Vartotojas vartotojas, Rungtynes rungtynes, boolean expandable) {
        this.vartotojas = vartotojas;
        this.rungtynes = rungtynes;
        this.expandable = expandable;
        piestiFeedSkydeli();
    }

    protected void piestiFeedSkydeli() {

        VBox taskuIrSpejimoStulpelis = getTaskuIrSpejimoStulpelis();
        VBox rungtyniuIrRezultatoStulpelis = new VBox();

        Separator skirtukas = new Separator();
        skirtukas.setOrientation(Orientation.VERTICAL);

        Text txtRungtyniuData = new Text();
        txtRungtyniuData.setFont(Font.font("Century Gothic", 14));

        Text txtRungtyniuRezultatas = new Text();
        txtRungtyniuRezultatas.setFont(storasMazasTekstas);

        ArrayList<Text> jauAtliktiSpejimai = getRungtyniuSpejimuIsklotines();

        rungtyniuIrRezultatoStulpelis.getChildren().addAll(txtRungtyniuData, txtRungtyniuRezultatas);

        HBox visasRungtyniuSkydelis = new HBox(taskuIrSpejimoStulpelis, skirtukas, rungtyniuIrRezultatoStulpelis);
        visasRungtyniuSkydelis.setSpacing(5);

        txtRungtyniuData.setText(rungtynes.getRungtyniuDataString());
        txtRungtyniuRezultatas.setText(getRezultatas());

        //Expand/collapse
        if (expandable) {
            setOnMouseEntered(e -> setStyle("-fx-background-color: #e8e8e8"));
            setOnMouseExited(e -> setStyle("-fx-background-color: transparent"));
            setOnMouseClicked(e -> {
                if (expanded) { //jeigu išplėstas tai sutraukti
                    expanded = false;
                    System.out.println(rungtynes.getRezultatas());
                    System.out.println("ar ivyko: " + rungtynes.arIvyko());
                    try {
                        //collapse atliekamas pašalinant iš stulpelių paskutinius "children"
                        // t.y. jau atliktų spėjimų išklotines
                        taskuIrSpejimoStulpelis.getChildren().remove(2);
                        rungtyniuIrRezultatoStulpelis.getChildren().remove(2);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("Index Out Of bounds");
                    }
                } else { //jeigu sutrauktas - išplėsti ir rodyti išklotinę
                    expanded = true;
                    setStyle("-fx-background-color: #e8e8e8");
                    //collapse atliekamas pridedant prie stulpelių paskutinius "children"
                    // t.y. jau atliktų spėjimų išklotines
                    taskuIrSpejimoStulpelis.getChildren().add(jauAtliktiSpejimai.get(0));
                    rungtyniuIrRezultatoStulpelis.getChildren().addAll(jauAtliktiSpejimai.get(1));
                }
            });
        }else setStyle("-fx-background-color: transparent");

            getChildren().addAll(visasRungtyniuSkydelis);
        }


        private ArrayList<Text> getRungtyniuSpejimuIsklotines () {
            //šiame metode gaunami 2 Text stulpeliu surasyti tekstai -
            // pirmame tasku stulpelis(isklotine), kitame spejimu stulpelis(isklotine)
            ArrayList<Text> grazinamiTekstai = new ArrayList<>();

            Text taskuIsklotine = new Text("\n");
            Text spejimuIsklotine = new Text("\n");

            HashMap<String, Rezultatas> spejimai = rungtynes.getVisiSpejimai();

            //iteruojama per šių rungtynių spėjimus ir kiekvienam spėjimui
            // taškų stulpelis papildomas +taškais, o
            // spėjimu stulpelis papildomas spėjamu rezultatu ir spėjančiu vartotoju
            Set<Map.Entry<String, Rezultatas>> entrySet = spejimai.entrySet();
            for (Map.Entry<String, Rezultatas> spejimoEntry : entrySet) {

                Vartotojas spejantisVartotojas = (Vartotojas) vartotojuArchyvas.istrauktiSarasa().get(spejimoEntry.getKey());
                taskuIsklotine.setText(taskuIsklotine.getText() + " +" + new Taskai(rungtynes, spejimoEntry.getValue()).getSkiriamiTaskai() + "\n");
                spejimuIsklotine.setText(spejimuIsklotine.getText() + spejimoEntry.getValue().toString() + "  "
                        + spejantisVartotojas.getVardas() + " " + spejantisVartotojas.getPavarde() + "\n");
            }

            grazinamiTekstai.add(taskuIsklotine);
            grazinamiTekstai.add(spejimuIsklotine);

            return grazinamiTekstai;
        }

        protected String getRezultatas () {
            return rungtynes.getKomanda1().getValstybe() + " "
                    + (rungtynes.arIvyko() ? rungtynes.getRezultatas().getTotalIvarciaiKomanda1() : "-")
                    + " : "
                    + (rungtynes.arIvyko() ? rungtynes.getRezultatas().getTotalIvarciaiKomanda2() : "-")
                    + " " + rungtynes.getKomanda2().getValstybe();
        }

        protected VBox getTaskuIrSpejimoStulpelis () {

            Text txtTaskai = new Text(rungtynes.arIvyko() ? "+" + new Taskai().skaiciuoti(rungtynes, rungtynes.getVartotojoSpejimas(vartotojas)) : " ");
            txtTaskai.setFont(storasDidelisTekstas);

            if (txtTaskai.getText().equals("+150")) {
                FillTransition mirksis = new FillTransition(Duration.millis(400), txtTaskai);
                mirksis.setFromValue(Color.rgb(100, 184, 110));
                mirksis.setToValue(Color.rgb(59, 106, 65));
                mirksis.setAutoReverse(true);
                mirksis.setCycleCount(Animation.INDEFINITE);
                mirksis.play();
            }


            Text txtSpejimas = new Text(rungtynes.getVartotojoSpejimas(vartotojas).getTotalIvarciaiKomanda1() + " : " + rungtynes.getVartotojoSpejimas(vartotojas).getTotalIvarciaiKomanda2());
            txtSpejimas.setFont(Font.font("Century Gothic", 14));

            VBox taskuIrSpejimoStulpelis = new VBox();
            taskuIrSpejimoStulpelis.setMinWidth(50);
            taskuIrSpejimoStulpelis.setAlignment(Pos.CENTER_RIGHT);
            taskuIrSpejimoStulpelis.getChildren().addAll(txtTaskai, txtSpejimas);

            return taskuIrSpejimoStulpelis;
        }


    }
