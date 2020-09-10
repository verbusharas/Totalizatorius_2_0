package backEnd;

public class Taskai {


    int skiriamiTaskai;
    Nustatymai nustatymai = new Nustatymai();
    protected int taskaiUzTiksluSpejima = nustatymai.getTaskaiUzTiksluSpejima();
    protected int taskaiKompensaciniai = nustatymai.getTaskaiKompensaciniai();
    protected int taskaiUzNugaletoja = nustatymai.getTaskaiUzNugaletoja();
    protected int taskaiUzIvarciuSkirtuma = nustatymai.getTaskaiUzIvarciuSkirtuma();
    protected int taskaiKompensaciniaiMAX = nustatymai.getTaskaiKompensaciniaiMAX();
    protected int taskaiKompensaciniaiKOEF = nustatymai.getTaskaiKompensaciniaiKOEF();
    protected int taskaiKompensaciniaiAPSAUGA = nustatymai.getTaskaiKompensaciniaiAPSAUGA();
    protected int rez1 = 0;
    protected int rez2 = 0;
    protected int spej1 = 0;
    protected int spej2 = 0;
    protected String taskuSandara = "";

    public Taskai(Rungtynes rungtynes, Rezultatas spejimas) {
        if (rungtynes.arIvyko()) {
            this.rez1 = rungtynes.getRezultatas().getTotalIvarciaiKomanda1();
            this.rez2 = rungtynes.getRezultatas().getTotalIvarciaiKomanda2();
            this.spej1 = spejimas.getTotalIvarciaiKomanda1();
            this.spej2 = spejimas.getTotalIvarciaiKomanda2();
            skiriamiTaskai = skaiciuoti();
        }
    }

    public Taskai(Rezultatas rezultatas, Rezultatas spejimas) {
        this.rez1 = rezultatas.getTotalIvarciaiKomanda1();
        this.rez2 = rezultatas.getTotalIvarciaiKomanda2();
        this.spej1 = spejimas.getTotalIvarciaiKomanda1();
        this.spej2 = spejimas.getTotalIvarciaiKomanda2();
        skiriamiTaskai = skaiciuoti();
    }

    public Taskai() {
        skiriamiTaskai = skaiciuoti();
    }

    public int skaiciuoti(Rungtynes rungtynes, Rezultatas spejimas) {
        skiriamiTaskai = 0;
        taskuSandara = "";

        if (rungtynes.arIvyko()) {
            this.rez1 = rungtynes.getRezultatas().getTotalIvarciaiKomanda1();
            this.rez2 = rungtynes.getRezultatas().getTotalIvarciaiKomanda2();
            this.spej1 = spejimas.getTotalIvarciaiKomanda1();
            this.spej2 = spejimas.getTotalIvarciaiKomanda2();
            //Tikslus spejimas:
            if (rez1 == spej1 && rez2 == spej2) {
                skiriamiTaskai += taskaiUzTiksluSpejima;
                taskuSandara += "+" + taskaiUzTiksluSpejima;
            }

            //Tikslus nugaletojas ir ivarciu skirtumas:
            if (rez1 - rez2 == spej1 - spej2) {
                skiriamiTaskai += taskaiUzIvarciuSkirtuma;
                taskuSandara += "+" + taskaiUzIvarciuSkirtuma;
            }

            //Tikslus nugaletojas:
            if ((rez1 > rez2 && spej1 > spej2) || (rez1 < rez2 && spej1 < spej2) || rez1 == rez2 && spej1 == spej2) {
                skiriamiTaskai += taskaiUzNugaletoja;
                taskuSandara += "+" + taskaiUzNugaletoja;
            }

//            System.out.println("rez1="+rez1 + ", rez2="+rez2 + ", spej1="+spej1 + ", spej2="+spej2);
            //Artimo spėjimo kompensacinis bonusas, pvz rezultatui esant 5:3, spejimas 5:5 traktuojamas kaip artimesnis nei 0:0;
            //"if" sąlyga užtikrina, kad per didelis prašovimas taškų neatims
            if ((Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) < taskaiKompensaciniaiAPSAUGA) {
                skiriamiTaskai += taskaiKompensaciniaiMAX - (Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) * taskaiKompensaciniaiKOEF;
                taskuSandara += "+(" + (taskaiKompensaciniaiMAX - (Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) * taskaiKompensaciniaiKOEF + ")");
            }


        }
        return skiriamiTaskai;
    }

    public int skaiciuoti(Rezultatas rezultatas, Rezultatas spejimas) {

        skiriamiTaskai = 0;
        taskuSandara = "";
        //Tikslus spejimas:
        if (rez1 == spej1 && rez2 == spej2) {
            skiriamiTaskai += taskaiUzTiksluSpejima;
            taskuSandara += "+" + taskaiUzTiksluSpejima;
        }

        //Tikslus nugaletojas ir ivarciu skirtumas:
        if (rez1 - rez2 == spej1 - spej2) {
            skiriamiTaskai += taskaiUzIvarciuSkirtuma;
            taskuSandara += "+" + taskaiUzIvarciuSkirtuma;
        }
        //Tikslus nugaletojas:
        if ((rez1 > rez2 && spej1 > spej2) || (rez1 < rez2 && spej1 < spej2) || rez1 == rez2 && spej1 == spej2) {
            skiriamiTaskai += taskaiUzNugaletoja;
            taskuSandara += "+" + taskaiUzNugaletoja;
        }

        //Artimo spėjimo kompensacinis bonusas, pvz rezultatui esant 5:3, spejimas 5:5 traktuojamas kaip artimesnis nei 0:0;
        //"if" sąlyga užtikrina, kad per didelis prašovimas taškų neatims
        if ((Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) < taskaiKompensaciniaiAPSAUGA) {
            skiriamiTaskai += taskaiKompensaciniaiMAX - (Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) * taskaiKompensaciniaiKOEF;
            taskuSandara += "+(" + (taskaiKompensaciniaiMAX - (Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) * taskaiKompensaciniaiKOEF + ")");
        }

        return skiriamiTaskai;
    }

    public int skaiciuoti() {
        skiriamiTaskai = 0;
        taskuSandara = "";
        //Tikslus spejimas:
        if (rez1 == spej1 && rez2 == spej2) {
            skiriamiTaskai += taskaiUzTiksluSpejima;
            taskuSandara += "+" + taskaiUzTiksluSpejima;
        }

        //Tikslus nugaletojas ir ivarciu skirtumas:
        if (rez1 - rez2 == spej1 - spej2) {
            skiriamiTaskai += taskaiUzIvarciuSkirtuma;
            taskuSandara += "+" + taskaiUzIvarciuSkirtuma;
        }

        //Tikslus nugaletojas:
        if ((rez1 > rez2 && spej1 > spej2) || (rez1 < rez2 && spej1 < spej2) || rez1 == rez2 && spej1 == spej2) {
            skiriamiTaskai += taskaiUzNugaletoja;
            taskuSandara += "+" + taskaiUzNugaletoja;
        }

        //Artimo spėjimo kompensacinis bonusas, pvz rezultatui esant 5:3, spejimas 5:5 traktuojamas kaip artimesnis nei 0:0;
        //"if" sąlyga užtikrina, kad per didelis prašovimas taškų neatims
        if ((Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) < taskaiKompensaciniaiAPSAUGA) {
            skiriamiTaskai += taskaiKompensaciniaiMAX - (Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) * taskaiKompensaciniaiKOEF;
            taskuSandara += "+(" + (taskaiKompensaciniaiMAX - (Math.abs(rez1 - spej1) + Math.abs(rez2 - spej2)) * taskaiKompensaciniaiKOEF + ")");
        }

        return skiriamiTaskai;
    }


    public int getRez1() {
        return rez1;
    }

    public int getRez2() {
        return rez2;
    }

    public int getSpej1() {
        return spej1;
    }

    public int getSpej2() {
        return spej2;
    }

    public int getSkiriamiTaskai() {
        return skiriamiTaskai;
    }

    @Override
    public String toString() {
        return spej1 + "   :   " + spej2 + "    +" + skiriamiTaskai + " tašk." + " " + getTaskuSandara();
    }

    public String getTaskuSandara() {
        int ilgis = taskuSandara.length();
        if (ilgis > 0)
            //grąžinama be pirmo pliuso
            return taskuSandara.substring(1, ilgis);
        else return "";
    }
}
