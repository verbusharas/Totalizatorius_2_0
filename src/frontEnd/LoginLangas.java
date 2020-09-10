package frontEnd;

import Main.MainTotalizatorius2;
import backEnd.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.IOException;
import java.util.HashMap;


public class LoginLangas {

    private Nustatymai nustatymai = new Nustatymai();
    private Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(nustatymai.getVartotojuFailoAdresas());
    private Scene loginScene;
    private Button btPrisijungti;
    private Hyperlink btRegistruotis;
    private TextField tfUsername;
    private PasswordField tfPassword;
    private Text vartotojoKlaida = new Text("");
    private Text slaptazodzioKlaida = new Text("");

    public LoginLangas() {
        kurtiLoginScene();
    }

    public void kurtiLoginScene() {

        //LOGOTIPAS
        ImageView logo = new ImageView(new Image("https://images2.imgbox.com/8f/da/LgiL9tun_o.jpg"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(210);

        logo.setOnMouseClicked(e -> System.out.println("spausta ant logo"));

        //TITULINIS UŽRAŠAS
        Text uzrasas = new Text("Vartotojo prisijungimas");
        uzrasas.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));

        //VARTOTOJO VARDO ĮVESTIES LAUKELIS IR PARUOŠTA TEKSTO DALIS NEEGZISTUOJANČIO VARTOTOJO ATVEJU

        vartotojoKlaida.setFill(Color.RED);
        TextFlow tflUsername = new TextFlow(new Text("Vartotojo vardas: "), vartotojoKlaida);
        tfUsername = new TextField();
        tfUsername.setPromptText("Vartotojo vardas");

        //SLAPTAŽODŽIO ĮVESTIES LAUKELIS IR PARUOŠTA TEKSTO DALIS NETEISINGO SLAPTAŽODŽIO ATVEJU
        slaptazodzioKlaida.setFill(Color.RED);
        TextFlow tflPassword = new TextFlow(new Text("Slaptažodis: "), slaptazodzioKlaida);
        tfPassword = new PasswordField();
        tfPassword.setPromptText("Slaptažodis");

        //PRISIJUNGIMO MYGTUKAS
        btPrisijungti = new Button("Prisijungti");
        btPrisijungti.setMaxWidth(Double.MAX_VALUE);
        btPrisijungti.setDefaultButton(true);
        btPrisijungti.setOnAction(e -> {
            try {
                login(tfUsername.getText(), tfPassword.getText());
            } catch (UsernameException ex) {
                vartotojoKlaida.setText(ex.getPriezastis());
            } catch (PasswordException ex) {
                slaptazodzioKlaida.setText(ex.getPriezastis());
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        //NAUJO VARTOTOJO REGISTRACIJOS EILUTĖ
        btRegistruotis = new Hyperlink("Registruotis");
        TextFlow siulymasRegistruotis = new TextFlow(new Text("Naujas vartotojas? "), btRegistruotis);
        btRegistruotis.setOnAction(e -> register());

        //PRISIJUNGIMO ELEMENTŲ IŠDĖSTYMO MAKETAS
        VBox prisijungimoMaketas = new VBox();
        prisijungimoMaketas.setPadding(new Insets(10, 20, 10, 10));
        prisijungimoMaketas.setSpacing(2);
        prisijungimoMaketas.setMinWidth(250);
        prisijungimoMaketas.setMargin(vartotojoKlaida, new Insets(10, 10, 0, 0));
        prisijungimoMaketas.setMargin(tflPassword, new Insets(10, 10, 0, 0));
        prisijungimoMaketas.setMargin(btPrisijungti, new Insets(10, 0, 0, 0));
        prisijungimoMaketas.getChildren().addAll(uzrasas, tflUsername, tfUsername, tflPassword, tfPassword, btPrisijungti, siulymasRegistruotis);

        //PAGRINDINIS VISO VAIZDO MAKETAS
        HBox pagrindinisMaketas = new HBox();
        pagrindinisMaketas.setStyle("-fx-background-color: #ffffff;\n");
        pagrindinisMaketas.getChildren().addAll(logo, prisijungimoMaketas);

        loginScene = new Scene(pagrindinisMaketas);
        loginScene.getStylesheets().add(LoginLangas.class.getResource("tabStilius2.css").toExternalForm());
    }

    public Scene getScene() {
        return loginScene;
    }

    private void login(String ivestasUsername, String ivestasPassword) throws UsernameException, PasswordException, IOException, ClassNotFoundException {

        HashMap<String, Vartotojas> vartotojuSarasas = vartotojuArchyvas.istrauktiSarasa();
        System.out.println(ivestasUsername + " " + ivestasPassword);

        if (ivestasUsername.equals("admin")) {
            if (ivestasPassword.equals("")) throw new PasswordException("Neužpildytas laukelis!");
            if (ivestasPassword.equals("admin")) MainTotalizatorius2.loginAdmin();

        } else {
            if (ivestasUsername.equals("")) {
                throw new UsernameException("Neužpildytas laukelis!");
            }
            if (vartotojuSarasas.containsKey(ivestasUsername)) {
                if (vartotojuSarasas.get(ivestasUsername).getPassword().equals(ivestasPassword)) {
                    Vartotojas prisijungesVartotojas = vartotojuSarasas.get(ivestasUsername);
                    System.out.println(prisijungesVartotojas);
                    prisijungesVartotojas.naujasPrisijungimas();
                    vartotojuArchyvas.irasyti(prisijungesVartotojas.getUsername(), prisijungesVartotojas);
                    MainTotalizatorius2.loginUser(prisijungesVartotojas);
                } else if (ivestasPassword.equals("")) {
                    throw new PasswordException("Neužpildytas laukelis!");
                } else throw new PasswordException("Neteisingas slaptažodis!");
            } else throw new UsernameException("Tokio vartotojo nėra!");
        }
    }

    private void register() {
        new RegistracijosPopUp();
    }

    public void valytiLaukelius() {
        tfUsername.clear();
        tfPassword.clear();
    }

}
