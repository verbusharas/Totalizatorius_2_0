package backEnd;

import java.io.IOException;

public class Nustatymai extends NustatymuManager {

    private final String NUSTATYMU_FAILO_ADRESAS = "src/files/TotalizatoriusNustatymai.dat";

    public void Nustatymai(){
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
    }
    @Override
    public int getTaskaiUzTiksluSpejima() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiUzTiksluSpejima();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiUzTiksluSpejima(int taskaiUzTiksluSpejima){
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = archyvuotojas.istraukti();
        try {
            nustatymai.setTaskaiUzTiksluSpejima(taskaiUzTiksluSpejima);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        archyvuotojas.irasyti(nustatymai);
    }

    @Override
    public String getVartotojuFailoAdresas(){
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getVartotojuFailoAdresas();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setVartotojuFailoAdresas(String vartotojuFailoAdresas) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setVartotojuFailoAdresas(vartotojuFailoAdresas);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getKomanduFailoAdresas(){
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getKomanduFailoAdresas();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setKomanduFailoAdresas(String komanduFailoAdresas) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setKomanduFailoAdresas(komanduFailoAdresas);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRungtyniuFailoAdresas(){
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getRungtyniuFailoAdresas();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setRungtyniuFailoAdresas(String rungtyniuFailoAdresas) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setRungtyniuFailoAdresas(rungtyniuFailoAdresas);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getTaskaiKompensaciniai() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiKompensaciniai();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiKompensaciniai(int taskaiKompensaciniai) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setTaskaiKompensaciniai(taskaiKompensaciniai);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTaskaiUzNugaletoja() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiUzNugaletoja();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiUzNugaletoja(int taskaiUzNugaletoja) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setTaskaiUzNugaletoja(taskaiUzNugaletoja);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTaskaiUzIvarciuSkirtuma() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiUzIvarciuSkirtuma();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiUzIvarciuSkirtuma(int taskaiUzIvarciuSkirtuma) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;

        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setTaskaiUzIvarciuSkirtuma(taskaiUzIvarciuSkirtuma);
            archyvuotojas.irasyti(nustatymai);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTaskaiKompensaciniaiMAX() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiKompensaciniaiMAX();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiKompensaciniaiMAX(int taskaiUzTiksluSpejimaMAX) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setTaskaiKompensaciniaiMAX(taskaiUzTiksluSpejimaMAX);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getTaskaiKompensaciniaiKOEF() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiKompensaciniaiKOEF();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiKompensaciniaiKOEF(int taskaiUzTiksluSpejimaKOEF) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setTaskaiKompensaciniaiKOEF(taskaiUzTiksluSpejimaKOEF);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getTaskaiKompensaciniaiAPSAUGA() {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            return nustatymai.getTaskaiKompensaciniaiAPSAUGA();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setTaskaiKompensaciniaiAPSAUGA(int taskaiUzTiksluSpejimaAPSAUGA) {
        Archyvuotojas<NustatymuManager> archyvuotojas = new Archyvuotojas<NustatymuManager>(NUSTATYMU_FAILO_ADRESAS);
        NustatymuManager nustatymai = null;
        try {
            nustatymai = archyvuotojas.istraukti();
            nustatymai.setTaskaiKompensaciniaiAPSAUGA(taskaiUzTiksluSpejimaAPSAUGA);
            archyvuotojas.irasyti(nustatymai);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    public String getVisiNustatymai() throws IOException, ClassNotFoundException {
        return
                        "Vartotojų failo adresas: " + this.getVartotojuFailoAdresas() + "\n"
                        + "Komandų failo adresas: " + this.getKomanduFailoAdresas() + "\n"
                        + "Rungtynių failo adresas: " + this.getRungtyniuFailoAdresas() + "\n"
                        + "Kompensaciniai taškai: " + this.getTaskaiKompensaciniai() + "\n"
                        + "Taškai už nugalėtoją: " + this.getTaskaiUzNugaletoja() + "\n"
                        + "Taškai už įvarčių skirtumą: " + this.getTaskaiUzIvarciuSkirtuma() + "\n"
                        + "Taškai kompensaciniai MAX: " + this.getTaskaiKompensaciniaiMAX() + "\n"
                        + "Taškai kompensaciniai KOEF: " + this.getTaskaiKompensaciniaiKOEF() + "\n"
                        + "Taškai kompensaciniai APSAUGA: " + this.getTaskaiKompensaciniaiAPSAUGA() + "\n";
    }


}
