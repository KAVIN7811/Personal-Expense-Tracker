import java.util.*;

class Expense {
    private int id;
    private String date;
    private String category;
    private double amount;
    private String paymentMode;
    private String note;

    public Expense(int id, String date, String category, double amount, String paymentMode, String note) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.note = note;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getPaymentMode() { return paymentMode; }
    public String getNote() { return note; }

    public void setDate(String date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public void setNote(String note) { this.note = note; }

    public void displayDetails() {
        System.out.println("------------------------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Date: " + date);
        System.out.println("Category: " + category);
        System.out.println("Amount: " + amount);
        System.out.println("Payment Mode: " + paymentMode);
        System.out.println("Note: " + note);
        System.out.println("------------------------------------------------");
    }
}

class MonthlyExpense extends Expense {
    public MonthlyExpense(int id, String date, String category, double amount, String paymentMode, String note) {
        super(id, date, category, amount, paymentMode, note);
    }

    @Override
    public void displayDetails() {
        System.out.println("Monthly Expense Details");
        super.displayDetails();
    }
}

class ExpenseManager {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private int nextId = 1;

    public void addExpense(String date, String category, double amount, String paymentMode, String note) {
        Expense e = new MonthlyExpense(nextId++, date, category, amount, paymentMode, note);
        expenses.add(e);
        System.out.println("Expense added successfully");
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found");
            return;
        }
        for (Expense e : expenses) {
            e.displayDetails();
        }
    }

    public void updateExpense(int id, Scanner sc) {
        for (Expense e : expenses) {
            if (e.getId() == id) {
                System.out.print("Enter new Category: ");
                e.setCategory(sc.nextLine());
                System.out.print("Enter new Amount: ");
                e.setAmount(Double.parseDouble(sc.nextLine()));
                System.out.print("Enter new Payment Mode: ");
                e.setPaymentMode(sc.nextLine());
                System.out.print("Enter new Note: ");
                e.setNote(sc.nextLine());
                System.out.println("Expense updated successfully");
                return;
            }
        }
        System.out.println("Expense not found");
    }

    public void deleteExpense(int id) {
        Iterator<Expense> iterator = expenses.iterator();
        while (iterator.hasNext()) {
            Expense e = iterator.next();
            if (e.getId()==id) {
                iterator.remove();
                System.out.println("Expense deleted successfully");
                return;
            }
        }
        System.out.println("Expense not found");
    }

    public void calculateTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total Expense: " + total);
    }
}

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        int choice;

        do {
            System.out.println("===== Personal Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Calculate Total Expense");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Date (DD/MM/YYYY): ");
                    String date = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = Double.parseDouble(sc.nextLine());
                    System.out.print("Enter Payment Mode: ");
                    String mode = sc.nextLine();
                    System.out.print("Enter Note: ");
                    String note = sc.nextLine();
                    manager.addExpense(date, category, amount, mode, note);
                    break;

                case 2:
                    manager.viewAllExpenses();
                    break;

                case 3:
                    System.out.print("Enter Expense ID to Update: ");
                    int idUpdate = Integer.parseInt(sc.nextLine());
                    manager.updateExpense(idUpdate, sc);
                    break;

                case 4:
                    System.out.print("Enter Expense ID to Delete: ");
                    int idDelete = Integer.parseInt(sc.nextLine());
                    manager.deleteExpense(idDelete);
                    break;

                case 5:
                    manager.calculateTotal();
                    break;

                case 6:
                    System.out.println("Exiting program");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);

        sc.close();
    }
}
