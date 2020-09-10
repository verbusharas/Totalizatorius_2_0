package frontEnd;

import backEnd.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VartotojuAdministravimoLangas extends HBox {
    private Nustatymai nustatymai = new Nustatymai();
    private TableView<Vartotojas> lentele;
    private UserInfoPane userInfoPane = new UserInfoPane();

    public VartotojuAdministravimoLangas() {
        piestiVartotojuAdministravimoLanga();
    }

    public void piestiVartotojuAdministravimoLanga() {


        //vardo stulpelis
        TableColumn<Vartotojas, String> vardasColumn = new TableColumn<>("Vardas");
        vardasColumn.setMinWidth(167);
        vardasColumn.setCellValueFactory(new PropertyValueFactory<>("vardas"));

        //pavarde stulpelis
        TableColumn<Vartotojas, String> pavardeColumn = new TableColumn<>("Pavardė");
        pavardeColumn.setMinWidth(162);
        pavardeColumn.setCellValueFactory(new PropertyValueFactory<>("pavarde"));

        //username stulpelis
        TableColumn<Vartotojas, String> usernameColumn = new TableColumn<>("Prisij.vardas");
        usernameColumn.setMinWidth(162);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        //password stulpelis
        TableColumn<Vartotojas, String> passwordColumn = new TableColumn<>("Slaptažodis");
        passwordColumn.setMinWidth(162);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        //rezultato stulpelis - kažkodėl sulėtino programą???????
        TableColumn<Vartotojas, String> taskuColumn = new TableColumn<>("Taškai");
        taskuColumn.setMinWidth(80);
        taskuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vartotojas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vartotojas, String> vartotojas) {
                String laikinasTaskai = "150";
                //                String taskai = String.valueOf(new TaskuTopas(vartotojas.getValue()).skaiciuotiVisusVartotojoTaskus(vartotojas.getValue()));
                return new SimpleStringProperty(laikinasTaskai);
            }
        });


        lentele = new TableView<>();
        lentele.setItems(getGyvasSarasas(nustatymai.getVartotojuFailoAdresas()));
        lentele.setMaxHeight(235);
        lentele.getColumns().addAll(taskuColumn, vardasColumn, pavardeColumn, usernameColumn, passwordColumn);
        lentele.getSortOrder().add(taskuColumn);
        taskuColumn.setSortType(TableColumn.SortType.DESCENDING);
        lentele.sort();

        ImageView paveiksliukas = new ImageView(new Image("https://images2.imgbox.com/cc/e0/d02GY7eT_o.jpg"));
        paveiksliukas.setPreserveRatio(true);


        VBox lentelesPusesMaketas = new VBox(gamintiAntrastineJuostaSuPlius("REGISTRUOTŲ VARTOTOJŲ SĄRAŠAS"),lentele, gamintiAntrastineJuosta("VARTOTOJO INFORMACIJA"), userInfoPane);

        lentele.setOnMouseClicked(e -> userInfoPane.kurtiSkydeli(lentele.getSelectionModel().getSelectedItem()));
        getChildren().addAll(lentelesPusesMaketas, paveiksliukas);
        setPadding(new Insets(10));
        setSpacing(10);

        userInfoPane.getBtIstrintiVartotoja().setOnAction(e->istrintiVartotoja());
    }

    public void istrintiVartotoja() {
        ObservableList<Vartotojas> visiVartotojai = lentele.getItems();
        Vartotojas vartotojas = lentele.getSelectionModel().getSelectedItem();
        visiVartotojai.remove(vartotojas);
        //pirmiausia išvalomos rungtynes nuo vartotojo spėjimų:
        Archyvuotojas<Rungtynes> rungtyniuArchyvas = new Archyvuotojas<>(nustatymai.getRungtyniuFailoAdresas());
        HashMap<String, Rungtynes> rungtyniuSarasas = rungtyniuArchyvas.istrauktiSarasa();
        Set<Map.Entry<String, Rungtynes>> entrySet = rungtyniuSarasas.entrySet();
        for(Map.Entry<String, Rungtynes> rungtyniuEntry : entrySet) {
            if(rungtyniuEntry.getValue().getVisiSpejimai().containsKey(vartotojas.getUsername())) {
                rungtyniuEntry.getValue().removeSpejimas(vartotojas);
                rungtyniuSarasas.put(rungtyniuEntry.getKey(),rungtyniuEntry.getValue());
            }
        }
        rungtyniuArchyvas.irasyti(rungtyniuSarasas);

        Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(nustatymai.getVartotojuFailoAdresas());
        HashMap<String, Vartotojas> vartotojuSarasas = vartotojuArchyvas.istrauktiSarasa();
        vartotojuSarasas.remove(vartotojas.getUsername());
        vartotojuArchyvas.irasyti(vartotojuSarasas);
        userInfoPane.kurtiTusciaSkydeli();
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

    private HBox gamintiAntrastineJuostaSuPlius(String antraste) {
        HBox antrastineJuosta = new HBox();
        antrastineJuosta.setPadding(new Insets(0, 0, 0, 15));
        antrastineJuosta.setAlignment(Pos.CENTER_LEFT);
        antrastineJuosta.setStyle("-fx-background-color: #000000");
        Text txtPendingSpejimuTitle = new Text(antraste);
        txtPendingSpejimuTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));
        txtPendingSpejimuTitle.setFill(Color.WHITE);
        Button btPridetiVartotoja = new Button ("[+] REGISTRUOTI NAUJĄ VARTOTOJĄ");
        btPridetiVartotoja.setOnAction(e->{
            register();
            lentele.setItems(getGyvasSarasas(nustatymai.getVartotojuFailoAdresas()));
        });
        antrastineJuosta.getChildren().addAll(txtPendingSpejimuTitle, btPridetiVartotoja);
        antrastineJuosta.setSpacing(250);
        return antrastineJuosta;
    }

    private void register() {
        new RegistracijosPopUp();
        lentele.setItems(getGyvasSarasas(nustatymai.getVartotojuFailoAdresas()));
    }

}
