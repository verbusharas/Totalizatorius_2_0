package backEnd;

public class Raktazodziai {


    //Čia nustatomos unikalios HTML source kodo iškarpos,
    //pagal kurias galima rasti ieškomos informacijos puslapyje pradžią ir pabaigą

    //VALSTYBIU PAVADINIMAI
    private static String pavadinimasPrefix1 = "fi-t__nText \">";
    private static String pavadinimasPrefix2 = "fi-t__nText slonger\">";
    private static String pavadinimasSuffix = "<";

    //VALSTYBIU TRUMPINIAI
    private static String isoPrefix = "fi-t__nTri\">";
    private static String isoSuffix = "<";

    //VALSTYBIU ID
    private static String iDPrefix = "<tr data-team-id=\"";
    private static String iDSuffix = "\"";

    //VALSTYBIU REITINGAVIMO TASKAI
    private static String taskaiPrefix = "<td class=\"fi-table__td fi-table__points\"><span class=\"text\">";
    private static String taskaiSuffix = "<";

    //VALSTYBIU VELIAVU *.PNG URL
    private static String veliavaPrefix1 = "<div class=\"fi-t__i \">";
    private static String veliavaPrefix2 = "<img src=\"";
    private static String veliavaSuffix = "\"";

    //VALSTYBIU KOMANDU FEDERACIJU ASOCIACIJOS
    private static String federacijuPrefix = "<span class=\"text\">#";
    private static String federacijuSuffix = "#";




    public static String getPavadinimasPrefix1() {
        return pavadinimasPrefix1;
    }

    public static String getPavadinimasPrefix2() {
        return pavadinimasPrefix2;
    }

    public static String getPavadinimasSuffix() {
        return pavadinimasSuffix;
    }

    public static String getIsoPrefix() {
        return isoPrefix;
    }

    public static String getIsoSuffix() {
        return isoSuffix;
    }

    public static String getiDPrefix() {
        return iDPrefix;
    }

    public static String getiDSuffix() {
        return iDSuffix;
    }

    public static String getTaskaiPrefix() {
        return taskaiPrefix;
    }

    public static String getTaskaiSuffix() {
        return taskaiSuffix;
    }

    public static String getVeliavaPrefix1() {
        return veliavaPrefix1;
    }

    public static String getVeliavaPrefix2() {
        return veliavaPrefix2;
    }

    public static String getVeliavaSuffix() {
        return veliavaSuffix;
    }

    public static String getFederacijuPrefix() {
        return federacijuPrefix;
    }

    public static String getFederacijuSuffix() {
        return federacijuSuffix;
    }
}
