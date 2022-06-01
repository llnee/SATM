package edu.citadel.satm;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerTest {
    private Controller c;
    private ATM atm;
    private View v;

    @Before
    public void setUp() {
        CustomerAccount[] accts = {};
        atm = new ATM(accts, 10000.0);
        v = new View("MyATM");
        c = new Controller(atm, v);
        c.initController();
    }

    @Test
    public void dispenseFundsTest() {
        c.dispenseFunds();
        assertTrue(atm.getDispenserClear());
//        assertEquals("Withdrawal complete.", view.getTopMsg().getText());
    }

    @Test
    public void acceptDPTest() {
        c.acceptDP();
        assertEquals("Deposit accepted.", v.getTopMsg().getText());
    }

    @Test
    public void enterFieldValidPANTest() {
        v.setCurrEntryOp(0);
        v.getEntry().setText("123456789");
        c.enterField();

    }

    @Test
    public void enterFieldValidPINTest() {
        v.setCurrEntryOp(1);
        v.getEntry().setText("1234");
        c.enterField();
    }

    @Test
    public void enterFieldValidWdTest() {
        atm.setDispenserClear(true);
        v.setCurrEntryOp(2);
        String wd_amt = "10.0";
        v.getEntry().setText(wd_amt);
        double newBalance = c.getCurrCustomer().getBalance() - Double.parseDouble(wd_amt);
        c.enterField();

        assertEquals(newBalance, c.getCurrCustomer().getBalance(), 0.01);
        assertFalse(atm.getDispenserClear());
    }

    @Test
    public void atmDispenserBlockedTest() {
        atm.setDispenserClear(false);
        v.setCurrEntryOp(2);
        String wd_amt = "10.0";
        v.getEntry().setText(wd_amt);
        c.enterField();

        assertEquals("Please clear dispenser by clicking it.", v.getBtmMsg().getText());
    }

    @Test
    public void wdNegTest() {
        atm.setDispenserClear(true);
        v.setCurrEntryOp(2);
        String wd_amt = "-10.0";
        v.getEntry().setText(wd_amt);
        c.enterField();

        assertEquals("Cannot be negative. Enter new withdrawal amount.", v.getBtmMsg().getText());
    }

    @Test
    public void wdGreaterThanBalTest() {
        atm.setDispenserClear(true);
        v.setCurrEntryOp(2);
        double originalBal = c.getCurrCustomer().getBalance();
        double originalAtmCurr = atm.getCurrency();
        double multOfTen = 10 - (originalBal % 10.0);
        String wd_amt = Double.toString(originalBal + multOfTen);
        v.getEntry().setText(wd_amt);
        c.enterField();

        assertEquals("Insufficient funds. ", v.getTopMsg().getText());
        assertEquals("Enter new withdrawal amount.", v.getBtmMsg().getText());
        assertEquals(c.getCurrCustomer().getBalance(), originalBal, 0.01);
        assertEquals(c.getAtm().getCurrency(), originalAtmCurr, 0.01);
    }

    @Test
    public void wdExceedsATMCurrencyTest() {
        atm.setDispenserClear(true);
        v.setCurrEntryOp(2);
        double originalBal = c.getCurrCustomer().getBalance();
        atm.setCurrency(originalBal - 10.0); // atm.currency must be < customerBal. Otherwise, test fails at wd > bal
        double originalAtmCurr = atm.getCurrency();
        String wd_amt = Double.toString(originalBal - 5.0);
        v.getEntry().setText(wd_amt);
        c.enterField();

        assertEquals("Cannot currently process withdrawals that large. ", v.getTopMsg().getText());
        assertEquals("Enter new withdrawal amount.", v.getBtmMsg().getText());
        assertEquals(c.getCurrCustomer().getBalance(), originalBal, 0.01);
        assertEquals(c.getAtm().getCurrency(), originalAtmCurr, 0.01);
    }

    @Test
    public void wdNotMultiple10Test() {
        atm.setDispenserClear(true);
        v.setCurrEntryOp(2);
        double originalBal = c.getCurrCustomer().getBalance();
        double originalAtmCurr = atm.getCurrency();
        String wd_amt = Double.toString(2.0);
        v.getEntry().setText(wd_amt);
        c.enterField();

        assertEquals("Machine can only dispense $10 notes. ", v.getTopMsg().getText());
        assertEquals("Enter new withdrawal amount.", v.getBtmMsg().getText());
        assertEquals(c.getCurrCustomer().getBalance(), originalBal, 0.01);
        assertEquals(c.getAtm().getCurrency(), originalAtmCurr, 0.01);
    }


    @Test
    public void enterFieldValidDpTest() {
        v.setCurrEntryOp(3);
        v.getEntry().setText("50.0");
        double newBalance = c.getCurrCustomer().getBalance() + Double.parseDouble(v.getEntry().getText());
        c.enterField();

        assertEquals(newBalance, c.getCurrCustomer().getBalance(), 0.01);
    }

}
