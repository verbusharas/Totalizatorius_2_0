//package frontEnd;
//
//import backEnd.*;
//import javafx.geometry.Insets;
//import javafx.scene.*;
//import javafx.scene.control.*;
//import javafx.scene.image.*;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.*;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//
//
//public class PradinisPasirinkimoMeniuLangas {
//
//    public static Scene vaizdas() throws IOException, ClassNotFoundException {
//
//        //LOGOTIPAS
//        ImageView logo = new ImageView (new Image("https://image.freepik.com/free-vector/soccer-logo-sport-logo-football-logo_7085-195.jpg"));
//        logo.setPreserveRatio(true);
//        logo.setFitWidth(200);
//
//        //TITULINIS UŽRAŠAS
//        Text uzrasas = new Text ("Vartotojo prisijungimas");
//        uzrasas.setFont(Font.font("Century Gothic" , FontWeight.BOLD, 18));
//
//        //VARTOTOJO VARDO ĮVESTIES LAUKELIS IR PARUOŠTA TEKSTO DALIS NEEGZISTUOJANČIO VARTOTOJO ATVEJU
//        Text vartotojoKlaida = new Text("");
//        vartotojoKlaida.setFill(Color.RED);
//        TextFlow tflUsername = new TextFlow(new Text("Vartotojo vardas: "), vartotojoKlaida);
//        TextField tfUsername = new TextField();
//        tfUsername.setStyle("-fx-background-radius: 12;");
//        tfUsername.setPromptText("Vartotojo vardas");
//
//        //SLAPTAŽODŽIO ĮVESTIES LAUKELIS IR PARUOŠTA TEKSTO DALIS NETEISINGO SLAPTAŽODŽIO ATVEJU
//        Text slaptazodzioKlaida = new Text("");
//        slaptazodzioKlaida.setFill(Color.RED);
//        TextFlow tflPassword = new TextFlow(new Text("Slaptažodis: "), slaptazodzioKlaida);
//        PasswordField tfPassword = new PasswordField();
//        tfPassword.setStyle("-fx-background-radius: 12; ");
//        tfPassword.setPromptText("Slaptažodis");
//
//        //PRISIJUNGIMO MYGTUKAS
//        Button btPrisijungti = new Button("Prisijungti");
//        btPrisijungti.setStyle("-fx-background-radius: 12; -fx-background-color: linear-gradient(#f78222, #d36c18); -fx-text-fill: #404040;");
//        btPrisijungti.setMaxWidth(Double.MAX_VALUE);
//        btPrisijungti.setOnAction(e-> {
//            try {
//                login(tfUsername.getText(), tfPassword.getText(), "Bandymas5.dat");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        });
//
//        //NAUJO VARTOTOJO REGISTRACIJOS EILUTĖ
//        Hyperlink registracijosNuoroda = new Hyperlink("Registruotis");
//        registracijosNuoroda.setOnAction(e -> new RegistracijosPopUp());
//        TextFlow siulymasRegistruotis = new TextFlow(new Text("Naujas vartotojas? "), registracijosNuoroda);
//
//        //PRISIJUNGIMO ELEMENTŲ IŠDĖSTYMO MAKETAS
//        VBox prisijungimoMaketas = new VBox();
//        prisijungimoMaketas.setPadding(new Insets(10,20,10,10));
//        prisijungimoMaketas.setSpacing(2);
//        prisijungimoMaketas.setMinWidth(250);
//        prisijungimoMaketas.setMargin(vartotojoKlaida, new Insets(10,10,0,0));
//        prisijungimoMaketas.setMargin(tflPassword, new Insets(10,10,0,0));
//        prisijungimoMaketas.setMargin(btPrisijungti, new Insets(10,0,0,0));
//        prisijungimoMaketas.getChildren().addAll(uzrasas, tflUsername, tfUsername, tflPassword, tfPassword, btPrisijungti, siulymasRegistruotis);
//
//        //PAGRINDINIS VISO VAIZDO MAKETAS
//        HBox pagrindinisMaketas = new HBox();
//        pagrindinisMaketas.setStyle("-fx-background-color: #ffffff;\n");
//        pagrindinisMaketas.getChildren().addAll(logo, prisijungimoMaketas);
//
//        //PARODYTI EGZISTUOJANČIUS VARTOTOJUS
//        spausdintiVartotojus("Bandymas5.dat");
//
//        //SUGENERUOTA SCENA GRAŽINAMA KVIETĖJUI
//        return new Scene(pagrindinisMaketas);
//    }
//
//    private static void login(String username, String password, String failoAdresas) throws IOException, ClassNotFoundException {
//        HashMap<String, Vartotojas> vartotojuSarasas = delVartotojuArchyvuotojas.istraukti(failoAdresas);
//        if (vartotojuSarasas.containsKey(username)) {
//            if (vartotojuSarasas.get(username).getPassword().equals(password)){
//                System.out.println("Prisijungimas sėkmingas.");
//            } else System.out.println("Neteisingas slaptažodis");
//
//        } else System.out.println("Tokio vartotojo nėra");
//
//
//    }
//
//    private static void spausdintiVartotojus(String failoAdresas) throws IOException, ClassNotFoundException {
//        HashMap<String, Vartotojas> vartotojuSarasas = delVartotojuArchyvuotojas.istraukti(failoAdresas);
//        Set set = vartotojuSarasas.entrySet();
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry mentry = (Map.Entry) iterator.next();
//            System.out.print("Raktas: " + mentry.getKey() + ", reikšmė: ");
//            System.out.println(mentry.getValue());
//        }
//    }
//
//
//}
