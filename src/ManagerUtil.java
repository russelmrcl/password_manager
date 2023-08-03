import java.io.*;
import java.util.Scanner;

public class ManagerUtil {

    private static Scanner scanner = new Scanner(System.in);
    private static EVL<String> newData = new EVL<>();
    // Choose your own file path!
    private static String filePath = "";
    private static boolean usedView = true;

    public static void addPassword(EVL<Account> list) {
        try {
            while (true) {
                System.out.println("[A]dd new password or [E]xit:");
                String answer = scanner.nextLine().toLowerCase();
                if (answer.equalsIgnoreCase("a")) {
                    // Prompt the user to enter platform, username, and password for the new account
                    System.out.println("Platform:");
                    String platform = scanner.nextLine();
                    System.out.println("Username:");
                    String username = scanner.nextLine();
                    System.out.println("Password:");
                    String password = scanner.nextLine();
                    // Create a new Account object and add it to the list
                    list.push(new Account(platform, username, password));
                    System.out.println("Password successfully added!");
                    System.out.println();
                }
                break;
            }

            // Write the account data to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Account a : list) {
                writer.write(a.toString());
                writer.newLine();
            }
            // If data was previously viewed, clear the temporary storage and set usedView to true
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
        // If the data was previously viewed, load it from the file into newData
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        try {
            while (true) {
                System.out.println("[D]elete password or [E]xit:");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("d")) {
                    // Prompt the user to enter the platform for the password to delete
                    System.out.println("Enter the platform to delete the password: ");
                    String platformDelete = scanner.nextLine();
                    boolean accountExists = false;
                    // Search for the account in newData and delete it if found
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

            // Write the remaining data in newData to the file
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
        // If the data was previously viewed, load it from the file into newData
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        while (true) {
            System.out.println("[S]earch password or [E]xit:");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("s")) {
                // Prompt the user to enter the platform for the password to search
                System.out.println("Enter the platform to search the password:");
                String platformSearch = scanner.nextLine();
                boolean accountExists = false;
                // Search for the account in newData and display it if found
                for (String s : newData) {
                    if (s.contains(platformSearch)) {
                        System.out.println(newData.search(s));
                        System.out.println();
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
    }

    public static void viewPasswords(EVL<Account> list) {
        // If the data was previously viewed, load it from the file into newData
        if (usedView) {
            passwordReader(list);
            usedView = false;
        }
        // Display all accounts stored in newData
        for (String s : newData) {
            System.out.println(s);
        }
        System.out.println();
    }

    private static void passwordReader(EVL<Account> list) {
        try {
            // Read the data from the file and push it into newData
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
