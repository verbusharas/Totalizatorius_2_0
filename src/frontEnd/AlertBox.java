package frontEnd;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox {

    public static void rodyti(String pranesimoTekstas, String mygtukoTekstas) {
        Stage langas = new Stage(StageStyle.UTILITY);

        VBox registracijosMaketas = new VBox();
        registracijosMaketas.setPadding(new Insets(10,10,10,10));
        registracijosMaketas.setSpacing(2);
        registracijosMaketas.setMinWidth(200);

        langas.initModality(Modality.APPLICATION_MODAL);
        langas.setMinWidth(200);
        langas.setTitle("Naujo vartotojo registracija");
        langas.setScene(new Scene(registracijosMaketas));
        langas.show();
    }

}
