import java.util.Scanner;

public class Manager {
    private static Scanner scanner = new Scanner(System.in);
    private static EVL<Account> list = new EVL<>();

    public static void main(String[] args) {

        System.out.println("Welcome to the Password-Manager!");
        while (true) {
            System.out.println("Would you like to (a)dd, (d)elete a password, (v)iew your passwords, (s)earch or (e)xit?");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equalsIgnoreCase("e")) {
                break;
            }

            switch (answer) {
                case "a":
                    ManagerUtil.addAccount(list);
                    break;
                case "d":
                    ManagerUtil.deleteAccount(list);
                    break;
                case "v":
                    ManagerUtil.viewPasswords(list);
                    break;
                case "s":
                    ManagerUtil.search(list);
                    break;
                default:
                    System.out.println("Invalid value");
            }
        }
    }
}
