package frontEnd;

import backEnd.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.*;

public class TaskuTopas extends VBox {

    private Nustatymai nustatymai = new Nustatymai();

    private Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
    private Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(nustatymai.getVartotojuFailoAdresas());
    private HashMap<String, Vartotojas> vartotojuMap = vartotojuArchyvas.istrauktiSarasa();
    private HashMap<String, Rungtynes> rungtyniuMap = rungtyniuArchyvas.istrauktiSarasa();


    public TaskuTopas(Vartotojas vartotojas) {
        piestiTopa(vartotojas);
    }


    private void piestiTopa(Vartotojas uzklausiantisVartotojas) {

        ArrayList<Vartotojas> vartotojuSarasas = new ArrayList<>();

        Set<Map.Entry<String, Vartotojas>> entrySet = vartotojuMap.entrySet();
        for (Map.Entry<String, Vartotojas> vartotojoEntry : entrySet) {
            vartotojuSarasas.add((Vartotojas) vartotojoEntry.getValue());
        }

        //isrikiuojama pagal turimus taskus nuo didziausio iki maziausio
        vartotojuSarasas.sort(Comparator.comparing(o -> skaiciuotiVisusVartotojoTaskus((Vartotojas) o)).reversed());

        for (Vartotojas vartotojas : vartotojuSarasas) {
            HBox topoEilute = new HBox();
            topoEilute.setSpacing(20);
            Text txtVartotojoTaskai = new Text("" + skaiciuotiVisusVartotojoTaskus(vartotojas));
            txtVartotojoTaskai.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
            txtVartotojoTaskai.setFill(Color.rgb(76, 147, 84));

            Text txtVartotojoVardasPavarde = new Text(vartotojas.getVardas() + " " + vartotojas.getPavarde());
            txtVartotojoVardasPavarde.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 12));
            topoEilute.getChildren().addAll(txtVartotojoTaskai, txtVartotojoVardasPavarde);

            if (vartotojas.getUsername().equals(uzklausiantisVartotojas.getUsername()))
                topoEilute.setStyle("-fx-background-color: #eaf2eb");
            getChildren().add(topoEilute);
        }

        //Likusi topo dalis užpildoma dryžiais, kad neliktų tuščios vietos (max topo dydis - 14 vnt)
        int likusiuEiluciuSkaicius = 14 - vartotojuSarasas.size();
        boolean arTamsesneEilute = false;
        for (int i = 0; i < likusiuEiluciuSkaicius; i++) {
            HBox topoEilute = new HBox(new Text(" "));
            if (arTamsesneEilute) topoEilute.setStyle("-fx-background-color: #ededed");
            getChildren().add(topoEilute);
            arTamsesneEilute = !arTamsesneEilute;
        }
    }

    public int skaiciuotiVisusVartotojoTaskus(Vartotojas vartotojas) {
        int taskuSuma = 0;

        Set<Map.Entry<String, Rungtynes>> entrySet = rungtyniuMap.entrySet();
        for (Map.Entry<String, Rungtynes> rungtyniuEntry : entrySet) {
            Rungtynes tikrinamosRungtynes = rungtyniuEntry.getValue();

            //jeigu spejimo nera ir dar nepaveluota - prideti nuli tasku
            if (!tikrinamosRungtynes.getVisiSpejimai().containsKey(vartotojas.getUsername()) && (!tikrinamosRungtynes.arVyksta() && !tikrinamosRungtynes.arIvyko())) {
                taskuSuma += 0;
            }

            //Jeigu spejimo nera, o jau paveluota - prideti default (0:0) spejima
            else if (!tikrinamosRungtynes.getVisiSpejimai().containsKey(vartotojas.getUsername()) && (tikrinamosRungtynes.arVyksta() || tikrinamosRungtynes.arIvyko())) {
                tikrinamosRungtynes.addSpejimas(vartotojas, new RezultatasGroupStage(0, 0));
                rungtyniuArchyvas.irasyti(tikrinamosRungtynes.getUnikalusRungtyniuKodas(), tikrinamosRungtynes);
            }

            //jeigu yra laiku atliktas spejimas (arba katik pridetas defaultinis)
            if (tikrinamosRungtynes.getVisiSpejimai().containsKey(vartotojas.getUsername())) {
                taskuSuma += new Taskai().skaiciuoti(rungtyniuEntry.getValue(), rungtyniuEntry.getValue().getVartotojoSpejimas(vartotojas));
            }
        }

        return taskuSuma;
    }


}



