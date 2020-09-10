package backEnd;

public class UsernameException extends Exception {
    private String priezastis;

    public UsernameException(String priezastis) {
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