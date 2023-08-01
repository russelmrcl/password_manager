public class Account {

    private String website;
    private String username;
    private String password;

    public Account(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public Account(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return website + " = [" + "username: " + username + ", " + "password: " + password + "]";
    }
}
