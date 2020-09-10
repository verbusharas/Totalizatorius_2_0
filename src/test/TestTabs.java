package test;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestTabs extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TabPane tabMaketas = new TabPane();

        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color:white");
        Text tekstas1 = new Text();
        Button btVienas = new Button("Vienas");
        Button btDu = new Button("Du");
        btVienas.setOnAction(e->tekstas1.setText(btVienas.getText()));
        btDu.setOnAction(e->tekstas1.setText(btDu.getText()));
        hBox.getChildren().addAll(btVienas,btDu, tekstas1);

        VBox vBox2 = new VBox();
        vBox2.setStyle("-fx-background-color:white");
        Button btTrys = new Button("Trys");
        Button btKeturi = new Button("Keturi");
        Text tekstas2 = new Text();
        btTrys.setOnAction(e->tekstas2.setText(btTrys.getText()));
        btKeturi.setOnAction(e->tekstas2.setText(btKeturi.getText()));
        vBox2.getChildren().addAll(btTrys, btKeturi, tekstas2);

        Tab tabVartotojai = new Tab("Vartotojai");
        tabVartotojai.setContent(hBox);

        Tab tabRungtynes = new Tab("Rungtynės");
        tabRungtynes.setContent(vBox2);


        tabMaketas.getTabs().addAll(tabVartotojai, tabRungtynes);
        tabMaketas.setSide(Side.LEFT);
        tabMaketas.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabMaketas.setTabMinHeight(50);
        tabMaketas.setTabMinWidth(200);
        tabMaketas.setRotateGraphic(false);

        VBox vBox = new VBox(tabMaketas);
        Scene vaizdas = new Scene(vBox);

        vaizdas.getStylesheets().add(getClass().getResource("tabStilius2.css").toExternalForm());
        primaryStage.setScene(vaizdas);
        primaryStage.setTitle("Tab bandymas");

        primaryStage.show();



    }
}
