import java.util.Scanner;

public class Manager {
    private static Scanner scanner = new Scanner(System.in);
    private static EVL<Account> list = new EVL<>();

    public static void main(String[] args) {

        System.out.println("Welcome! How would you like to manage your passwords today? " +
                "You can choose from the following options:");
        while (true) {
            System.out.println("[A]dd a new password, [D]elete an existing password");
            System.out.println("[V]iew your saved passwords, [S]earch for a specific password");
            System.out.println("[e]xit - Exit the password manager");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equalsIgnoreCase("e")) {
                break;
            }

            switch (answer) {
                case "a":
                    ManagerUtil.addPassword(list);
                    break;
                case "d":
                    ManagerUtil.deletePassword(list);
                    break;
                case "v":
                    ManagerUtil.viewPasswords(list);
                    break;
                case "s":
                    ManagerUtil.searchPasswords(list);
                    break;
                default:
                    System.out.println("Invalid value");
            }
        }
    }
}
