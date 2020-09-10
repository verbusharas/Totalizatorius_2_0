package backEnd;

public class PasswordException extends Exception {
    private String priezastis;

    public PasswordException(String priezastis) {
        super(priezastis);
        this.priezastis = priezastis;
    }

    public String getPriezastis() {
        return priezastis;
    }

    public void setPriezastis(String priezastis) {
        this.priezastis = priezastis;
    }

}