package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImgTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ImageView veliava;
        veliava = new ImageView();
        veliava.setImage(new Image("https://api.fifa.com/api/v1/picture/flags-sq-4/gua"));
        StackPane maketas = new StackPane();
        maketas.getChildren().add(veliava);
        Scene vaizdas = new Scene(maketas);
        primaryStage.setScene(vaizdas);
        primaryStage.show();

    }
}
