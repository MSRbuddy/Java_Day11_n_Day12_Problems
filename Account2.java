class Account {
    private double balance;

    public Account(double initialBalance)
    {
        if (initialBalance >= 0)
        {
            balance = initialBalance;
        }
        else
        {
            balance = 0.0;
        }
    }

    public double getBalance()
    {
        return balance;
    }

    public void debit(double amount)
    {
        if (amount <= balance)
        {
            balance -= amount;
            System.out.println("Debit of $" + amount + " successful.");
        } else {
            System.out.println("Debit amount exceeded account balance.");
        }
    }
}

public class Account2
{
    public static void main(String[] args)
    {
        Account account = new Account(1000.0);

        System.out.println("Initial balance: $" + account.getBalance());

        // Test debit method
        double debitAmount = 500.0;
        System.out.println("\nDebiting $" + debitAmount + " from the account...");
        account.debit(debitAmount);
        System.out.println("Current balance: $" + account.getBalance());

        debitAmount = 1500.0;
        System.out.println("\nDebiting $" + debitAmount + " from the account...");
        account.debit(debitAmount);
        System.out.println("Current balance: $" + account.getBalance());
    }
}