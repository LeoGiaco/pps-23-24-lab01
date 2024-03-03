import static example.model.SimpleBankAccountWithATM.FEE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccountWithATM;

class SimpleBankAccountWithATMTest {

    private static final int AMOUNT = 100;
    private static final int WITHDRAW_AMOUNT = 50;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;


    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccountWithATM(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }
    
    @Test
    void testDepositWithFee() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT + FEE);
        assertEquals(AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT + FEE);
        bankAccount.deposit(2, WITHDRAW_AMOUNT);
        assertEquals(AMOUNT, bankAccount.getBalance());
    }
    
    
    @Test
    void testWithdrawWithFee() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT + FEE);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT - FEE);
        assertEquals(AMOUNT - WITHDRAW_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT + FEE);
        bankAccount.withdraw(2, WITHDRAW_AMOUNT);
        assertEquals(AMOUNT, bankAccount.getBalance());
    }
}
