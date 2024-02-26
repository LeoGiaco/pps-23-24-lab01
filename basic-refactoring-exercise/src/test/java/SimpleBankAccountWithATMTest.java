import static example.model.SimpleBankAccountWithATM.FEE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccountWithATM;

class SimpleBankAccountWithATMTest {

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
        bankAccount.deposit(accountHolder.getId(), 100 + FEE);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), 100 + FEE);
        bankAccount.deposit(2, 50);
        assertEquals(100, bankAccount.getBalance());
    }


    @Test
    void testWithdrawWithFee() {
        bankAccount.deposit(accountHolder.getId(), 100 + FEE);
        bankAccount.withdraw(accountHolder.getId(), 50 - FEE);
        assertEquals(50, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 100 + FEE);
        bankAccount.withdraw(2, 70);
        assertEquals(100, bankAccount.getBalance());
    }
}
