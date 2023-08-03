public class Account {

    private String platform;
    private String username;
    private String password;

    public Account(String platform, String username, String password) {
        this.platform = platform;
        this.username = username;
        this.password = password;
    }

    public Account(String platform) {
        this.platform = platform;
    }

    public String getWebsite() {
        return platform;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return platform + " = [" + "username: " + username + ", " + "password: " + password + "]";
    }
}
