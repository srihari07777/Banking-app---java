package banking;

import java.sql.SQLException;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nBanking Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Update Account Balance");
            System.out.println("4. Delete Account");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    viewAccount(scanner);
                    break;
                case 3:
                    updateAccountBalance(scanner);
                    break;
                case 4:
                    deleteAccount(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        try {
            BankAccount account = new BankAccount(accountNumber, name, balance);
            account.saveAccount();  // Save account to the database
            System.out.println("Account created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    private static void viewAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        try {
            BankAccount account = BankAccount.getAccount(accountNumber);
            if (account != null) {
                System.out.println("Account Holder: " + account.getHolderName());
                System.out.println("Balance: " + account.getBalance());
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account: " + e.getMessage());
        }
    }

    private static void updateAccountBalance(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter New Balance: ");
        double newBalance = scanner.nextDouble();

        try {
            BankAccount account = BankAccount.getAccount(accountNumber);
            if (account != null) {
                account.updateBalance(newBalance);
                System.out.println("Balance updated successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    private static void deleteAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        try {
            BankAccount.deleteAccount(accountNumber);
            System.out.println("Account deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }
}
