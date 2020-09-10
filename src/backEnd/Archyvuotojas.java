package backEnd;

import java.io.*;
import java.util.HashMap;

public class Archyvuotojas<T> implements Serializable {

    private String failoAdresas;


    public Archyvuotojas(String failoAdresas) {
        this.failoAdresas = failoAdresas;
    }

    //GENERIC ARCHYVUOTOJAS
    //kviečiant metodą įrašyti reikia nurodyti HashMap indeksą, patį objektą ir failo adresą
    //Komandoms indeksas yra ISO trumpinys
    //Rungtynėms indeksas yra unikalus rungtynių kodas
    //Vartotojams indeksas yra username

    public <T> void irasyti(String indeksas, T objektas, String failoAdresas) throws IOException, ClassNotFoundException {
        HashMap<String, T> sarasas = istrauktiSarasa(failoAdresas);
        sarasas.put(indeksas, objektas);
        issaugotiFaile(sarasas, failoAdresas);
    }

    public <T> void irasyti(String indeksas, T objektas) {
        HashMap<String, T> sarasas = null;
        try {
            sarasas = istrauktiSarasa(failoAdresas);
            sarasas.put(indeksas, objektas);
            issaugotiFaile(sarasas, failoAdresas);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public <T> void irasyti(T objektas) {
        try {
            issaugotiFaile(objektas, failoAdresas);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public <T> HashMap<String, T> istrauktiSarasa(String failoAdresas) throws ClassNotFoundException, IOException {

        HashMap<String, T> objektuSarasas = new HashMap<>();

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(failoAdresas));
        ) {
            objektuSarasas = (HashMap<String, T>) (input.readObject());
            input.close();
        } catch (FileNotFoundException e) {
            return objektuSarasas;
        }

        return objektuSarasas;
    }

    //OVERLOAD - kai konstruktoriuje nurodomas failo adresas
    public <T> HashMap<String, T> istrauktiSarasa(){

        HashMap<String, T> objektuSarasas = new HashMap<>();

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(failoAdresas));
        ) {
            objektuSarasas = (HashMap<String, T>) (input.readObject());
            input.close();
        } catch (FileNotFoundException e) {
            return objektuSarasas;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objektuSarasas;
    }

    //kai istraukiamas tik vienas objektas
    public <T> T istraukti()  {

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(failoAdresas));
        ) {
            return (T) (input.readObject());
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } return null;

    }


    public <T> void valytiArchyva(String failoAdresas) throws IOException, ClassNotFoundException {
        HashMap<String, T> objektuSarasas = istrauktiSarasa(failoAdresas);
        objektuSarasas.clear();
        issaugotiFaile(objektuSarasas, failoAdresas);
    }

    //OVERLOAD - kai konstruktoriuje nurodomas failo adresas
    public <T> void valytiArchyva() throws IOException, ClassNotFoundException {
        HashMap<String, T> objektuSarasas = istrauktiSarasa(failoAdresas);
        objektuSarasas.clear();
        issaugotiFaile(objektuSarasas, failoAdresas);
    }


    private <T> void issaugotiFaile(HashMap<String, T> sarasas, String failoAdresas) throws IOException, ClassNotFoundException {

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(failoAdresas));
        ) {
            output.writeObject(sarasas);
            output.close();
        }
    }

    //OVERLOAD - kai saugojamas tik vienas objektas
    private <T> void issaugotiFaile(T objektas, String failoAdresas) throws IOException, ClassNotFoundException {

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(failoAdresas));
        ) {
            output.writeObject(objektas);
            output.close();
        }
    }


}
