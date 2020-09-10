package Main;

import backEnd.Archyvuotojas;
import backEnd.Nustatymai;
import backEnd.Vartotojas;
import frontEnd.AdminLangas;
import frontEnd.LoginLangas;
import frontEnd.VartotojoProfilis;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainTotalizatorius2 extends Application {

    private static Vartotojas prisijungesVartotojas;
    private static Nustatymai nustatymai = new Nustatymai();
    private static Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
    private static Stage pradinisLangas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        spausdintiVartotojuLoginus();

        pradinisLangas = primaryStage;
        pradinisLangas.setScene(new LoginLangas().getScene());
        pradinisLangas.setTitle("Totalizatorius");
        pradinisLangas.getIcons().add(new Image("https://images2.imgbox.com/59/3f/BGHFEbmR_o.png"));
        pradinisLangas.setResizable(false);
        pradinisLangas.show();
    }

    public static void loginUser(Vartotojas vartotojas) {
        VartotojoProfilis prisijungusioProfilis = new VartotojoProfilis(vartotojas);
        Scene vartotojoProfilioScene = prisijungusioProfilis.getScene();
        vartotojoProfilioScene.getStylesheets().add(MainTotalizatorius2.class.getResource("tabStilius_final.css").toExternalForm());
        pradinisLangas.setScene(prisijungusioProfilis.getScene());
        pradinisLangas.centerOnScreen();
        pradinisLangas.setTitle("Totalizatorius - " + vartotojas.getVardas() + " " + vartotojas.getPavarde());
    }

    public static void loginAdmin() {
        pradinisLangas.setScene(new AdminLangas().getVaizdas());
        pradinisLangas.centerOnScreen();
        pradinisLangas.setTitle("Totalizatorius - Administratorius");
    }

    public static void logout() {
        pradinisLangas.setScene(new LoginLangas().getScene());
        pradinisLangas.centerOnScreen();
        pradinisLangas.setTitle("Totalizatorius");
    }

    private static void spausdintiVartotojuLoginus() {
        HashMap<String, Vartotojas> vartotojuSarasas = vartotojuArchyvas.istrauktiSarasa();
        System.out.println("Vartotojų prisijungimai:");
        Set<Map.Entry<String, Vartotojas>> irasuSet = vartotojuSarasas.entrySet();
        for (Map.Entry<String, Vartotojas> vartotojoIrasas : irasuSet) {
            System.out.print("ID: " + vartotojoIrasas.getValue().getUsername() + ", PW: " + vartotojoIrasas.getValue().getPassword() + " || ");
        }
    }

}
