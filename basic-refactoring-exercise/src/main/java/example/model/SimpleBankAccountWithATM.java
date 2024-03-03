package example.model;

public class SimpleBankAccountWithATM extends SimpleBankAccount {
    public static final double FEE = 1.0;

    public SimpleBankAccountWithATM(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void deposit(final int userID, final double amount) {
        super.deposit(userID, amount - FEE);
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        super.withdraw(userID, amount + FEE);
    }
}
