import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private static final int AMOUNT = 100;
    private static final int WITHDRAW_AMOUNT = 70;
    private static final int NEGATIVE_AMOUNT = -10;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT);
        assertEquals(AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testNegativeDepositThrows() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(accountHolder.getId(), NEGATIVE_AMOUNT));
    }

    @Test
    void testWrongDeposit() {
        int amount2 = 50;
        bankAccount.deposit(accountHolder.getId(), AMOUNT);
        bankAccount.deposit(2, amount2);
        assertEquals(AMOUNT, bankAccount.getBalance());
    }
    
    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(AMOUNT - WITHDRAW_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testNegativeWithdrawThrows() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(accountHolder.getId(), NEGATIVE_AMOUNT));
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), AMOUNT);
        bankAccount.withdraw(2, WITHDRAW_AMOUNT);
        assertEquals(AMOUNT, bankAccount.getBalance());
    }
}
