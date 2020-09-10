package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class testCSS extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox vBox = new VBox();
        Button myg = new Button("bandymas");
        myg.setId("myg");
        vBox.getChildren().add(myg);
        vBox.setId("vb");
        vBox.setMinWidth(200);
        vBox.setMinHeight(200);
        Scene vaizdas = new Scene(vBox);

        System.out.println(getClass().getResource("stilius2.css").toExternalForm());
        vaizdas.getStylesheets().add(
                getClass().getResource("stilius2.css").toExternalForm()
        );
        primaryStage.setScene(vaizdas);
        primaryStage.setTitle("CSS bandymas");

        primaryStage.show();

    }
}
