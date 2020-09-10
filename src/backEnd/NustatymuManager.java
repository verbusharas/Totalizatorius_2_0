package backEnd;

import java.io.IOException;
import java.io.Serializable;

public class NustatymuManager implements Serializable {


    protected String vartotojuFailoAdresas; // = "Bandymas5.dat";
    protected String komanduFailoAdresas; // = "kol kas nenustatyta";
    protected String rungtyniuFailoAdresas; // = "RungtyniuSarasas.dat";

    protected int taskaiUzTiksluSpejima;
    protected int taskaiKompensaciniai;
    protected int taskaiUzNugaletoja;
    protected int taskaiUzIvarciuSkirtuma;

    //Artimo spėjimo kompensacinis bonusas, pvz rezultatui esant 5:3, spejimas 5:5 traktuojamas kaip artimesnis nei 0:0;
    //"if" sąlyga užtikrina, kad per didelis prašovimas taškų neatims
    //kompensac.taškai. MAX - KOEF * APSAUGA > 0
    protected int taskaiKompensaciniaiMAX;
    protected int taskaiKompensaciniaiKOEF;
    protected int taskaiKompensaciniaiAPSAUGA; //APSAUGA = (MAX/KOEF)++

    public NustatymuManager() {
        vartotojuFailoAdresas = ".dat";
        komanduFailoAdresas = ".dat";
        rungtyniuFailoAdresas = ".dat";
        taskaiKompensaciniai = 0;
        taskaiUzTiksluSpejima = 0;
        taskaiUzNugaletoja = 0;
        taskaiUzIvarciuSkirtuma = 0;
        taskaiKompensaciniaiMAX = 0;
        taskaiKompensaciniaiKOEF = 0;
        taskaiKompensaciniaiAPSAUGA = 0;

    }

    public int getTaskaiUzTiksluSpejima() throws IOException, ClassNotFoundException {
        return taskaiUzTiksluSpejima;
    }

    public void setTaskaiUzTiksluSpejima(int taskaiUzTiksluSpejima) throws IOException, ClassNotFoundException {
        this.taskaiUzTiksluSpejima = taskaiUzTiksluSpejima;
    }

    public String getVartotojuFailoAdresas() throws IOException, ClassNotFoundException {

        return vartotojuFailoAdresas;
    }

    public void setVartotojuFailoAdresas(String vartotojuFailoAdresas) throws IOException, ClassNotFoundException {
        this.vartotojuFailoAdresas = vartotojuFailoAdresas;
    }

    public String getKomanduFailoAdresas() throws IOException, ClassNotFoundException {
        return komanduFailoAdresas;
    }

    public void setKomanduFailoAdresas(String komanduFailoAdresas) throws IOException, ClassNotFoundException {
        this.komanduFailoAdresas = komanduFailoAdresas;
    }

    public String getRungtyniuFailoAdresas() throws IOException, ClassNotFoundException {
        return rungtyniuFailoAdresas;
    }

    public void setRungtyniuFailoAdresas(String rungtyniuFailoAdresas) throws IOException, ClassNotFoundException {
        this.rungtyniuFailoAdresas = rungtyniuFailoAdresas;
    }

    public int getTaskaiKompensaciniai() throws IOException, ClassNotFoundException {
        return taskaiKompensaciniai;
    }

    public void setTaskaiKompensaciniai(int taskaiKompensaciniai) throws IOException, ClassNotFoundException {
        this.taskaiKompensaciniai = taskaiKompensaciniai;
    }

    public int getTaskaiUzNugaletoja() throws IOException, ClassNotFoundException {
        return taskaiUzNugaletoja;
    }

    public void setTaskaiUzNugaletoja(int taskaiUzNugaletoja) throws IOException, ClassNotFoundException {
        this.taskaiUzNugaletoja = taskaiUzNugaletoja;
    }

    public int getTaskaiUzIvarciuSkirtuma() throws IOException, ClassNotFoundException {
        return taskaiUzIvarciuSkirtuma;
    }

    public void setTaskaiUzIvarciuSkirtuma(int taskaiUzIvarciuSkirtuma) throws IOException, ClassNotFoundException {
        this.taskaiUzIvarciuSkirtuma = taskaiUzIvarciuSkirtuma;
    }

    public int getTaskaiKompensaciniaiMAX() throws IOException, ClassNotFoundException {
        return taskaiKompensaciniaiMAX;
    }

    public void setTaskaiKompensaciniaiMAX(int taskaiUzTiksluSpejimaMAX) throws IOException, ClassNotFoundException {
        this.taskaiKompensaciniaiMAX = taskaiUzTiksluSpejimaMAX;
    }

    public int getTaskaiKompensaciniaiKOEF() throws IOException, ClassNotFoundException {
        return taskaiKompensaciniaiKOEF;
    }

    public void setTaskaiKompensaciniaiKOEF(int taskaiUzTiksluSpejimaKOEF) throws IOException, ClassNotFoundException {
        this.taskaiKompensaciniaiKOEF = taskaiUzTiksluSpejimaKOEF;
    }

    public int getTaskaiKompensaciniaiAPSAUGA() throws IOException, ClassNotFoundException {
        return taskaiKompensaciniaiAPSAUGA;
    }

    public void setTaskaiKompensaciniaiAPSAUGA(int taskaiUzTiksluSpejimaAPSAUGA) throws IOException, ClassNotFoundException {
        this.taskaiKompensaciniaiAPSAUGA = taskaiUzTiksluSpejimaAPSAUGA;
    }
}
