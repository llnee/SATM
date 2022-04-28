package edu.citadel.satm;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerTest {
    private Controller c;
    private ATM atm;
    private View view;
    private CustomerAccount currCustomer;

    @Before
    public void setUp() {
        currCustomer = new CustomerAccount("123456789", "1234", "Cool", "Guy", 566.0);
        atm = new ATM( new CustomerAccount[]{currCustomer}, 50000.0);
        view = new View("ControllerTest");
        c = new Controller(atm, view);
        c.initController();
    }

    @Test
    public void dispenseFundsTest() {
        c.dispenseFunds();
        assertTrue(atm.getDispenserClear());
        assertEquals("Withdrawal complete.", view.getTopMsg().getText());
    }

    @Test
    public void acceptDPTest() {
        c.acceptDP();
        assertTrue(atm.getDpSlotClear());
        assertEquals("Deposit accepted.", view.getTopMsg().getText());
    }

    @Test
    public void enterFieldValidPANTest() {
        view.setCurrEntryOp(0);
        view.getEntry().setText("123456789");
        c.enterField();

    }

    @Test
    public void enterFieldValidPINTest() {
        view.setCurrEntryOp(1);
        view.getEntry().setText("1234");
        c.enterField();
    }

    @Test
    public void enterFieldValidWdTest() {
        view.setCurrEntryOp(2);
        view.getEntry().setText("10.0");
        double newBalance = currCustomer.getBalance() + Double.parseDouble(view.getEntry().getText());
        c.enterField();

        assertEquals(newBalance, currCustomer.getBalance(), 0.01);
        assertFalse(atm.getDispenserClear());

    }

    @Test
    public void enterFieldValidDpTest() {
        view.setCurrEntryOp(3);
        view.getEntry().setText("50.0");
        double newBalance = currCustomer.getBalance() + Double.parseDouble(view.getEntry().getText());
        c.enterField();

        assertEquals(newBalance, currCustomer.getBalance(), 0.01);
        assertFalse(atm.getDpSlotClear());
    }

}
