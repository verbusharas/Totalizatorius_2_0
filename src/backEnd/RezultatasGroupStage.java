package backEnd;

import java.io.Serializable;

public class RezultatasGroupStage implements Rezultatas, Serializable {

    protected int ivarciaiKomanda1 = 0;
    protected int ivarciaiKomanda2 = 0;

    RezultatasGroupStage(){
    }

    public RezultatasGroupStage(int ivarciaiKomanda1, int ivarciaiKomanda2) {
        this.ivarciaiKomanda1 = ivarciaiKomanda1;
        this.ivarciaiKomanda2 = ivarciaiKomanda2;
    }

    @Override
    public int getTotalIvarciaiKomanda1() {
        return ivarciaiKomanda1;
    }

    @Override
    public int getTotalIvarciaiKomanda2() {
        return ivarciaiKomanda2;
    }

    @Override
    public void setGalutinisRezultatas(int ivarciaiKomanda1, int ivarciaiKomanda2) {
    this.ivarciaiKomanda1 = ivarciaiKomanda1;
    this.ivarciaiKomanda2 = ivarciaiKomanda2;
    }

    @Override
    public String toString() {
        return "" + ivarciaiKomanda1 + " : " + ivarciaiKomanda2;
    }
}
