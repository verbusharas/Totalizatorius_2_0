package backEnd;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Vartotojas implements Serializable {

    private String username;
    private String password;
    private String vardas;
    private String pavarde;
    private static int sukurtuVartotojuSkaicius;
    private ArrayList<LocalDateTime> prisijungimoLaikai = new ArrayList<>();
    private ArrayList<LocalDateTime> atsijungimoLaikai = new ArrayList<>();

    private LocalDateTime profilioSukurimoLaikas;

    public Vartotojas() {

    }

    public Vartotojas(String username, String password, String vardas, String pavarde) {
        this.username = username;
        this.password = password;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.profilioSukurimoLaikas = LocalDateTime.now();
        sukurtuVartotojuSkaicius++;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVardas() {
        return vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public static int getSukurtuVartotojuSkaicius() {
        return sukurtuVartotojuSkaicius;
    }

    public void naujasPrisijungimas() {
        prisijungimoLaikai.add(LocalDateTime.now());
    }

    public void naujasAtsijungimas() {
        atsijungimoLaikai.add(LocalDateTime.now());
    }

      public String getProfilioSukurimoLaikas() {
        DateTimeFormatter laikoFormatas = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return profilioSukurimoLaikas.format(laikoFormatas);
    }

  public String gautiPrisijungimuIsklotine() {
        String isklotine = "";
        DateTimeFormatter laikoFormatas = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        try {
            for (int i = 0; i < prisijungimoLaikai.size() - 1; i++) {
                isklotine += (i+1) + ". Prisijungta: " + prisijungimoLaikai.get(i).format(laikoFormatas) + ". ";
                isklotine += ". Atsijungta: " +  atsijungimoLaikai.get(i).format(laikoFormatas) + "\n";
            }
        } catch (IndexOutOfBoundsException ex) {
            isklotine += 0 + prisijungimoLaikai.get(0).toLocalDate().toString() + " " + prisijungimoLaikai.get(0).toLocalTime().toString() + "\n";
            isklotine += 0 + ". Atsijungta: dar nebuvo.\n";
        }
      System.out.println(isklotine);
        return isklotine;
  }

    public String getPaskutinisPrisijungimas() {
        String paskutinisPrisijungimas = "";
        DateTimeFormatter laikoFormatas = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        try {
            paskutinisPrisijungimas += prisijungimoLaikai.get(prisijungimoLaikai.size() - 1).format(laikoFormatas) + "\n";
        } catch (IndexOutOfBoundsException ex) {
            paskutinisPrisijungimas += "Dar prisijungta nebuvo.";
        }
        return paskutinisPrisijungimas;
    }

    public String getPaskutinisAtsijungimas() {
        String paskutinisAtsijungimas = "";
        DateTimeFormatter laikoFormatas = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        try {
            paskutinisAtsijungimas += atsijungimoLaikai.get(atsijungimoLaikai.size() - 1).format(laikoFormatas);
        } catch (IndexOutOfBoundsException ex) {
            paskutinisAtsijungimas += "Dar atsijungta nebuvo.";
        }
        return paskutinisAtsijungimas;
    }


  public void valytiPrisijungimuIsklotine() {
        prisijungimoLaikai.clear();
        atsijungimoLaikai.clear();
  }

    @Override

    public String toString() {
        return "Vardas: " + vardas + ", pavardė: " + pavarde + ", prisijungimo vardas: " + username + ", slaptažodis: " + password + ", sukūrimo data: " + profilioSukurimoLaikas;
    }

}
