import java.io.*;
import java.util.Scanner;

public class ManagerUtil {

    private static Scanner scanner = new Scanner(System.in);
    private static EVL<String> newData = new EVL<>();
    //Choose your own file path!!
    private static String filePath = "";
    private static boolean usedView = true;

    public static void addPassword(EVL<Account> list) {
        try {
            while (true) {
                System.out.println("[A]dd new password or [E]xit:");
                String answer = scanner.nextLine().toLowerCase();
                if (answer.equalsIgnoreCase("a")) {
                    System.out.println("Platform:");
                    String platform = scanner.nextLine();
                    System.out.println("Username:");
                    String username = scanner.nextLine();
                    System.out.println("Password:");
                    String password = scanner.nextLine();
                    list.push(new Account(platform, username, password));
                }
                break;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Account a : list) {
                writer.write(a.toString());
                writer.newLine();
            }
            if (!usedView) {
                usedView = true;
                newData.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deletePassword(EVL<Account> list) {
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        try {
            while (true) {
                System.out.println("[D]elete password or [E]xit:");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("d")) {
                    System.out.println("Enter the platform to delete the password: ");
                    String platformDelete = scanner.nextLine();
                    boolean accountExists = false;
                    for (String s : newData) {
                        if (s.contains(platformDelete)) {
                            newData.searchAndDelete(s);
                            System.out.println("Password successfully deleted!");
                            accountExists = true;
                            break;
                        }
                    }
                    if (!accountExists) {
                        System.out.println("Password doesn't exist");
                    }
                }
                break;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String a : newData) {
                writer.write(a);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchPasswords(EVL<Account> list) {
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        while (true) {
            System.out.println("[S]earch password or [E]xit:");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("s")) {
                System.out.println("Enter the platform to search the password:");
                String platformSearch = scanner.nextLine();
                boolean accountExists = false;
                for (String s : newData) {
                    if (s.contains(platformSearch)) {
                        System.out.println(newData.search(s));
                        System.out.println();
                        accountExists = true;
                        break;
                    }
                }
                if (!accountExists) {
                    System.out.println("password doesn't exist");
                }
            }
            break;
        }
    }

    public static void viewPasswords(EVL<Account> list) {
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        for (String s : newData) {
            System.out.println(s);
        }
        System.out.println();
    }

    private static void passwordReader(EVL<Account> list) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                newData.push(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
