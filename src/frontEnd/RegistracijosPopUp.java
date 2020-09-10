package frontEnd;

import backEnd.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RegistracijosPopUp {

    public RegistracijosPopUp() {
        kurtiRegisterWindow();
    }

    public void kurtiRegisterWindow() {
        Stage langas = new Stage(StageStyle.UTILITY);

        VBox registracijosMaketas = new VBox();
        registracijosMaketas.setPadding(new Insets(10, 10, 10, 10));
        registracijosMaketas.setSpacing(2);
        registracijosMaketas.setMinWidth(200);

        Text uzrasas = new Text("Naujo vartotojo registracija");
        uzrasas.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));

        Text klaida = new Text();
        klaida.setFill(Color.RED);


        Label lbUsername = new Label("Prisijungimo vardas:");
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Prisijungimo vardas");

        Label lbPassword = new Label("Slaptažodis:");
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("Slaptažodis");

        Label lbPasswordConfirm = new Label("Slaptažodis pakartotinai:");
        PasswordField tfPasswordConfirm = new PasswordField();
        tfPasswordConfirm.setPromptText("Slaptažodis pakartotinai");

        Label lbVardas = new Label("Vardas:");
        TextField tfVardas = new TextField();
        tfVardas.setPromptText("Vardas");

        Label lbPavarde = new Label("Pavardė:");
        TextField tfPavarde = new TextField();
        tfPavarde.setPromptText("Pavardė");

        Button btRegistruoti = new Button("Registruotis");
        btRegistruoti.setMaxWidth(Double.MAX_VALUE);
        btRegistruoti.setDefaultButton(true);
        btRegistruoti.setOnAction(e -> {
            try {
                String arSekmingai = registruotiNaujaVartotoja(tfUsername.getText(), tfPassword.getText(), tfPasswordConfirm.getText(),
                        tfVardas.getText(), tfPavarde.getText(), "Bandymas5.dat");
                switch (arSekmingai) {
                    case "nesutampa slaptazodziai":
                        System.out.println("Klaida. Nesutampa slaptažodžiai");
                        klaida.setText("Slaptažodžiai nesutampa!");
                        break;
                    case "username uzimtas":
                        System.out.println("Klaida. Toks prisijungimo vardas jau yra");
                        klaida.setText("Toks prisijungimo vardas jau yra!");
                        break;
                    case "sekmingai":
                        System.out.println("Registracija sėkminga");
                        langas.close();
                        break;
                    case "neuzpildyta":
                        System.out.println("Neužpildytas laukelis (null)");
                        break;
                    case "neuzpildyta2":
                        System.out.println("Neužpildytas laukelis (\"\")");
                        klaida.setText("Reikia užpildyti visus laukelius!");
                        break;

                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });


        registracijosMaketas.setMargin(lbUsername, new Insets(10, 10, 0, 0));
        registracijosMaketas.setMargin(lbPassword, new Insets(10, 10, 0, 0));
        registracijosMaketas.setMargin(lbPasswordConfirm, new Insets(10, 10, 0, 0));
        registracijosMaketas.setMargin(lbVardas, new Insets(10, 10, 0, 0));
        registracijosMaketas.setMargin(lbPavarde, new Insets(10, 10, 0, 0));
        registracijosMaketas.setMargin(btRegistruoti, new Insets(10, 0, 0, 0));

        registracijosMaketas.setStyle("-fx-background-color: #ffffff;\n");


        registracijosMaketas.getChildren().addAll(uzrasas, lbUsername, tfUsername, lbPassword, tfPassword, lbPasswordConfirm, tfPasswordConfirm,
                lbVardas, tfVardas, lbPavarde, tfPavarde, klaida, btRegistruoti);

        langas.initModality(Modality.APPLICATION_MODAL);
        langas.setMinWidth(200);
        langas.setTitle("Naujo vartotojo registracija");
        Scene registracijosScena = new Scene(registracijosMaketas);
        registracijosScena.getStylesheets().add(RegistracijosPopUp.class.getResource("tabStilius2.css").toExternalForm());
        langas.setScene(registracijosScena);
        langas.showAndWait();
    }


    private static String registruotiNaujaVartotoja(String username, String password, String passwordConfirm, String vardas,
                                                    String pavarde, String failoAdresas) throws IOException, ClassNotFoundException {

        Archyvuotojas<Vartotojas> vartotojuArchyvas = new Archyvuotojas<>(new Nustatymai().getVartotojuFailoAdresas());
        HashMap<String, Vartotojas> vartotojuSarasas = vartotojuArchyvas.istrauktiSarasa();


        if (username == null || password == null || passwordConfirm == null || vardas == null || pavarde == null)
            return "neuzpildyta";
        else if (username.equals("") || password.equals("") || passwordConfirm.equals("") || vardas.equals("") || pavarde.equals(""))
            return "neuzpildyta2";
        if (!password.equals(passwordConfirm)) return "nesutampa slaptazodziai";
        else if (vartotojuSarasas.containsKey(username)) return "username uzimtas";
        else {


            vartotojuArchyvas.irasyti(username, new Vartotojas(username, password, vardas, pavarde));


            Set set = vartotojuSarasas.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                System.out.print("Raktas: " + mentry.getKey() + ", o reikšmė: ");
                System.out.println(mentry.getValue());
            }
        }

        return "sekmingai";
    }


}
