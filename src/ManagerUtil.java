import java.io.*;
import java.util.Scanner;

public class ManagerUtil {

    private static Scanner scanner = new Scanner(System.in);
    private static EVL<String> newData = new EVL<>();
    //Choose your own file path!!
    private static String filePath = "";
    private static boolean usedView = true;

    public static void addAccount(EVL<Account> list) {
        try {
            while (true) {
                System.out.println("(a)dd new Password or (e)xit:");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("a")) {
                    System.out.println("Website:");
                    String website = scanner.nextLine();
                    System.out.println("Username:");
                    String username = scanner.nextLine();
                    System.out.println("Password:");
                    String password = scanner.nextLine();
                    list.push(new Account(website, username, password));
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

    public static void deleteAccount(EVL<Account> list) {
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        try {
            while (true) {
                System.out.println("(d)elete Account or (e)xit:");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("d")) {
                    System.out.println("Enter the website to delete the account:");
                    String websiteDelete = scanner.nextLine();
                    boolean accountExists = false;
                    for (String s : newData) {
                        if (s.contains(websiteDelete)) {
                            newData.searchAndDelete(s);
                            System.out.println("Account successfully deleted!");
                            accountExists = true;
                            break;
                        }
                    }
                    if (!accountExists) {
                        System.out.println("Account doesn't exist");
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

    public static void search(EVL<Account> list) {
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        while (true) {
            System.out.println("(s)earch Account or (e)xit:");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("s")) {
                System.out.println("Enter the website to search the account:");
                String websiteSearch = scanner.nextLine();
                boolean accountExists = false;
                for (String s : newData) {
                    if (s.contains(websiteSearch)) {
                        System.out.println(newData.search(s));
                        accountExists = true;
                        break;
                    }
                }
                if (!accountExists) {
                    System.out.println("Account doesn't exist");
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
