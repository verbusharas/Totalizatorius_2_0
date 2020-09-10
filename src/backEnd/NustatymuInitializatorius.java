package backEnd;

import java.io.IOException;

public class NustatymuInitializatorius {
    private static final String NUSTATYMU_FAILO_ADRESAS = "src/files/TotalizatoriusNustatymai.dat";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        ATKOMENTUOTI NUSTATYMO NAUJO INITIALIZAVIMO ATVEJU:
//        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<>(NUSTATYMU_FAILO_ADRESAS);
//        NustatymuManager nustatymai = new NustatymuManager();
//        archyvuotojas.irasyti(nustatymai);

// KITAIS ATVEJAIS KREIPTIS i "NUSTATYMAI":

        Nustatymai nustatymai = new Nustatymai();
        System.out.println(nustatymai.getVisiNustatymai());
        nustatymai.setVartotojuFailoAdresas("src/files/TotalizatoriusRegistruotiVartotojai.dat");
        nustatymai.setRungtyniuFailoAdresas("src/files/TotalizatoriusRungtynes.dat");
        nustatymai.setKomanduFailoAdresas("src/files/TotalizatoriusKomandos.dat");
        nustatymai.setTaskaiKompensaciniai(25);
        nustatymai.setTaskaiUzNugaletoja(25);
        nustatymai.setTaskaiKompensaciniaiAPSAUGA(6);
        nustatymai.setTaskaiKompensaciniaiKOEF(5);
        nustatymai.setTaskaiKompensaciniaiMAX(25);
        nustatymai.setTaskaiUzIvarciuSkirtuma(50);
        nustatymai.setTaskaiUzTiksluSpejima(50);
        System.out.println(nustatymai.getVisiNustatymai());


    }

}
