package edu.citadel.satm;

import java.awt.*;
import java.util.Objects;

public class Controller {

    private final ATM atm;
    private final View view;
    private CustomerAccount currCustomer;

    // getters for testing only
    public ATM getAtm() { return atm; }
    public View getView() { return view; }
    public CustomerAccount getCurrCustomer(){ return currCustomer; }

    public Controller(ATM atm, View v) {
        this.atm = atm;
        view = v;
        currCustomer = new CustomerAccount("123456789", "1234", "Cool", "Guy", 566.0);
    }

    public void initController() {
        view.getDispenserButton().addActionListener(e -> dispenseFunds());
        view.getClearButton().addActionListener(e -> clearField());
        view.getEnterButton().addActionListener(e -> enterField());
        view.getCancelButton().addActionListener(e -> cancelAction());
        view.getViewBalButton().addActionListener(e -> viewBalAction());
        view.getWdButton().addActionListener(e -> wdAction());
        view.getDpButton().addActionListener(e -> dpAction());
        view.getYesButton().addActionListener(e -> yesAction());
        view.getNoButton().addActionListener(e -> noAction());
    }

    public void dispenseFunds() {
        atm.setDispenserClear(true);
        view.getDispenserButton().setBackground(view.getEnterButton().getBackground());
        view.getDispenserButton().setForeground(view.getEnterButton().getForeground());
    }

    public void acceptDP() {
        view.setTopMsg("Deposit accepted.");
    }

    private void clearField() {
        view.setEntry("");
    }

    private void enteredPAN(String pan) {
        view.setTopMsg("");
        if (!atm.validPAN(pan)) {
            view.setBtmMsg("   Invalid ATM card. Card retained.                 ");
            view.clearView();
        } else {
            this.currCustomer = atm.getCurrentCustomer(pan);
            view.setBtmMsg("  Please enter your PIN number.    ");
            view.setCurrEntryOp(1);
        }
    }

    private void enteredPIN(String pin) {
        view.setTopMsg("");
        if (!currCustomer.validPIN(pin)) {
            view.setBtmMsg("             Invalid pin. Card retained.                 ");
            view.clearView();
        } else {
            view.hideEntry();
            view.setBtmMsg("            Select transaction below:                         ");
            view.viewTransactionSelection();
        }
    }

    private void enteredWdAmt(double wdAmt) {
        if (atm.dispenserClear) {
            if (wdAmt >= 0.0) {
                try {
                    currCustomer.withdrawal(wdAmt);
                    atm.withdrawal(wdAmt);
                    atm.setDispenserClear(false);
                    view.setBtmMsg("Click dispenser to retrieve funds.");
                    view.getDispenserButton().setBackground(Color.GREEN);
                    view.getDispenserButton().setBackground(Color.GREEN);
                    viewBalAction();
                } catch (ArithmeticException e) {
                    view.setTopMsg("Insufficient funds. ");
                    view.setBtmMsg("Enter new withdrawal amount.");
                    currCustomer.deposit(wdAmt); // returns withdrawn funds to customer
                } catch (IllegalArgumentException e) {
                    view.setTopMsg("Cannot currently process withdrawals that large. ");
                    view.setBtmMsg("Enter new withdrawal amount.");
                    currCustomer.deposit(wdAmt); // """
                } catch (UnsupportedOperationException e) {
                    view.setTopMsg("Machine can only dispense $10 notes. ");
                    view.setBtmMsg("Enter new withdrawal amount.");
                    currCustomer.deposit(wdAmt); // """
                }
            } else {
                view.setBtmMsg("Cannot be negative. Enter new withdrawal amount.");
            }
        } else {
            view.hideEntry();
            view.setBtmMsg("Please clear dispenser by clicking it.");
        }
    }

    private void enteredDpAmt(double dpAmt) {
        try {
            currCustomer.deposit(dpAmt);
            viewBalAction();
        } catch (IllegalArgumentException e) {
            view.setBtmMsg("Cannot be negative. Enter new deposit amount.");
        }
    }


    public void enterField() {
        // can enter pan, pin, wd_amt, dp_amt
        /*FIX: not tested or handled for any enterField, enter text or chars*/
        String input = view.getEntry().getText();
        String currEntryOp = view.getCurrEntryOp().getText();

        view.getEntry().setText("");
        if (Objects.equals(currEntryOp, "Enter PAN:")) {
            enteredPAN(input);
        } else if (Objects.equals(currEntryOp, "Enter PIN:")) {
            enteredPIN(input);
        } else if (Objects.equals(currEntryOp, "Enter withdrawal amount:")) {
            enteredWdAmt(Double.parseDouble(input));
        } else if (Objects.equals(currEntryOp, "Enter deposit amount:")) {
            enteredDpAmt(Double.parseDouble(input));
        }
    }

    public void cancelAction() {
        clearField();
    }

    public void viewBalAction() {
        view.hideEntry();
        view.hideTransactionSelection();
        view.setTopMsg("            Balance: $" + currCustomer.getBalance());
        view.setBtmMsg("");
        view.setBtmMsg("        Another transaction?");
        view.viewYesNo();
    }

    private void wdAction() {
        view.hideYesNo();
        view.hideTransactionSelection();
        view.viewEntry();
        view.setTopMsg(""); view.setBtmMsg("");
        view.getCurrEntryOp().setText("Enter withdrawal amount:");
    }

    private void dpAction() {
        view.hideYesNo();
        view.hideTransactionSelection();
        view.viewEntry();
        view.setTopMsg(""); view.setBtmMsg("");
        view.getCurrEntryOp().setText("Enter deposit amount:");
    }

    private void yesAction() {
        view.hideYesNo();
        view.viewTransactionSelection();
        enteredPIN(currCustomer.getPin());
    }

    private void noAction() {
        view.setTopMsg("     Thank you.     ");
        view.setBtmMsg("Your atm card has been returned to you.");
        view.clearView();
    }
}
