package test;

public class testZodziuKarpymas {
    public static void main(String[] args) {
        String valstybe = "Vienas du trys keturi penki";


        String[] zodziai = valstybe.split(" ");

        if (zodziai.length == 2) {
            System.out.println(zodziai[0]);
            System.out.println(zodziai[1]);
        } else if (zodziai.length>2){
            System.out.println(zodziai[0] + " " + zodziai[1]);
            for (int i = 2; i<zodziai.length; i++) {
                System.out.print(zodziai[i]+ " ");
            }
        }
    }
}
