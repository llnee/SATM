package edu.citadel.satm;

import javax.swing.*;
import java.util.Objects;

public class Controller {

    private ATM atm;
    private View view;
    private CustomerAccount currCustomer;

    public Controller(ATM atm, View v) {
        this.atm = atm;
        view = v;
        currCustomer = new CustomerAccount("123456789", "1234", "Cool", "Guy", 566.0);
    }

    public void initController() {
        view.getDispenserButton().addActionListener(e -> dispenseFunds());
        view.getDPSlotButton().addActionListener(e -> acceptDP());
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
        view.setTopMsg("Withdrawal complete.");
    }

    public void acceptDP() {
        atm.setDpSlotClear(true);
        view.setTopMsg("Deposit accepted.");
    }

    private void clearField() {
        view.setEntry("");
    }

    private void enteredPAN(String pan) {
        view.setTopMsg("                   ");
        if (!atm.validPAN(pan)) {
            view.setBtmMsg("     Invalid ATM card. Card retained.                 ");
            view.clearView();
        } else {
            this.currCustomer = atm.getCurrentCustomer(pan);
            view.setBtmMsg("               Please enter your PIN number.                    ");
            view.setCurrEntryOp(1);
        }
    }

    private void enteredPIN(String pin) {
        view.setTopMsg("                   ");
        if (!currCustomer.validPIN(pin)) {
            view.setBtmMsg("               Invalid pin. Card retained.                 ");
            view.clearView();
        } else {
//            view.setTopMsg("");
            view.setBtmMsg("              Select transaction below:                         ");
            view.viewTransactionSelection();
            view.getEntry().setEnabled(false); //disables entry until wd/dp selected
            view.getCurrEntryOp().setText("                              ");
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
                    viewBalAction();
                } catch (ArithmeticException e) {
                    view.setBtmMsg("Insufficient funds. Enter new withdrawal amount.");
                    currCustomer.deposit(wdAmt); // returns withdrawn funds to customer
                } catch (IllegalArgumentException e) {
                    view.setBtmMsg("Cannot currently process withdrawals that large. Enter new withdrawal amount.");
                    currCustomer.deposit(wdAmt); // returns withdrawn funds to customer
                } catch (UnsupportedOperationException e) {
                    view.setBtmMsg("Machine can only dispense $10 notes. Enter new withdrawal amount     .");
                    currCustomer.deposit(wdAmt); // returns withdrawn funds to customer
                }
            } else {
                view.setBtmMsg("Cannot be negative. Enter new withdrawal amount.");
            }
        } else {
            view.setBtmMsg("Please clear dispenser by clicking it.");
        }
    }

    private void enteredDpAmt(double dpAmt) {
        if (atm.dpSlotClear) {
            try {
                currCustomer.deposit(dpAmt);
                atm.setDpSlotClear(false);
                view.setBtmMsg("Click DP Slot to insert funds.");
                viewBalAction();
            } catch (IllegalArgumentException e) {
                view.setBtmMsg("Cannot be negative. Enter new deposit amount.");
            }
        } else {
            view.setBtmMsg("Please clear dp slot by clicking it.");
        }
    }


    public void enterField() {
        // can enter pan, pin, wd_amt, dp_amt
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
        view.getEntry().setEnabled(false);
        view.hideTransactionSelection();
        view.setTopMsg("                    Balance:          $" + currCustomer.getBalance());
        view.setBtmMsg("");
        view.getCurrEntryOp().setText("                                   ");
        view.setBtmMsg("          Another transaction?");
        view.viewYesNo();
    }

    private void wdAction() {
        view.hideYesNo();
        view.getEntry().setEnabled(true); //enable entry again
        view.hideTransactionSelection();
        view.getCurrEntryOp().setText("Enter withdrawal amount:");
    }

    private void dpAction() {
        view.hideYesNo();
        view.getEntry().setEnabled(true); //enable entry again
        view.hideTransactionSelection();
        view.getCurrEntryOp().setText("Enter deposit amount:");
    }

    private void yesAction() {
        view.hideYesNo();
        view.viewTransactionSelection();
        enteredPIN(currCustomer.getPin());
    }

    private void noAction() {
        view.setTopMsg("     Thank you.     ");
        view.setBtmMsg("Please take your atm card.     ");
        view.clearView();
    }
}
