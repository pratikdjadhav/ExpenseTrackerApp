
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String file = "transactions.txt";

        List<Transaction> transactions = loadTransactions(file);

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {

                System.out.println("\n\n\t\t--- Expense Tracker Menu ---");
                System.out.println("1.Add a transaction");
                System.out.println("2.View all transactions");
                System.out.println("3.View balance summary");
                System.out.println("4.Delete a transaction");
                System.out.println("5.Exit and save");

                System.out.print("Enter your choice : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {

                        System.out.println("-----------Add transaction-----------\n ");

                        int id = transactions.isEmpty() ? 1 : transactions.get(transactions.size() - 1).getId() + 1;

                        String date = LocalDate.now().toString();

                        System.out.print("Enter type : ");
                        String type = scanner.nextLine();

                        System.out.print("Enter amount : ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Enter category : ");
                        String category = scanner.nextLine();

                        System.out.print("Enter note : ");
                        String note = scanner.nextLine();

                        transactions.add(new Transaction(id, date, type, amount, category, note));

                    }
                    case 2 -> {

                        System.out.println("-----------All transactions-----------\n ");

                        if (transactions.isEmpty()) {
                            System.out.println("No Transactions");
                        } else {

                            for (Transaction txn : transactions) {
                                System.out.println(txn.toString());
                            }

                        }

                    }
                    case 3 -> {

                        System.out.println("-----------Balance Summary-----------\n ");

                        if (!transactions.isEmpty()) {

                            double totalIncome = 0;
                            double totalExpense = 0;
                            for (Transaction txn : transactions) {

                                if (txn.getType().trim().equalsIgnoreCase("income")) {
                                    totalIncome += txn.getAmount();
                                } else if (txn.getType().trim().equalsIgnoreCase("expense")) {
                                    totalExpense += txn.getAmount();
                                }
                            }

                            double balance = totalIncome - totalExpense;

                            System.out.println("Total Income : " + totalIncome);
                            System.out.println("Total Expense : " + totalExpense);
                            System.out.println("Current Balance : " + balance);

                        } else {
                            System.out.println("No Transactions Found!");
                        }

                    }
                    case 4 -> {

                        System.out.println("-----------Delete transaction-----------\n ");

                        if (!transactions.isEmpty()) {

                            System.out.print("Enter transaction id : ");
                            int id = scanner.nextInt();
                            scanner.nextLine();

                            boolean flag = false;

                            for (int i = 0; i < transactions.size(); i++) {
                                if (transactions.get(i).getId() == id) {
                                    transactions.remove(i);
                                    flag = true;
                                    System.out.println("Transaction removed successfully!");
                                    break;
                                }
                            }

                            if (!flag) {
                                System.out.println("ID not found!");
                            }
                        } else {
                            System.out.println("No Transactions Found!");
                        }

                    }
                    case 5 -> {

                        saveTransactions(transactions, file);
                        System.exit(0);

                    }
                    default -> {
                        System.out.println("Invalid Option\nSelect from choice");
                    }
                }

            }

        }

    }

    static void saveTransactions(List<Transaction> transactions, String file) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Transaction txn : transactions) {
                String line = txn.getId() + " | " + txn.getDate() + " | "
                        + txn.getType() + " | " + txn.getAmount() + " | " + txn.getCategory() + " | " + txn.getNote();
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException error) {
            System.out.println("Error : " + error.getMessage());
        }

    }

    static List<Transaction> loadTransactions(String file) {

        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\s*\\|\\s*");

                int id = Integer.parseInt(parts[0]);
                String date = parts[1];
                String type = parts[2];
                double amount = Double.parseDouble(parts[3]);
                String category = parts[4];
                String note = parts[5];

                Transaction txn = new Transaction(id, date, type, amount, category, note);
                transactions.add(txn);

            }

        } catch (IOException error) {
            System.out.println("Error : " + error.getMessage());
        }

        return transactions;

    }
}
