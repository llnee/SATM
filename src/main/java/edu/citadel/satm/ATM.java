package edu.citadel.satm;

import java.util.HashMap;

public class ATM {
    CustomerAccount defaultCustomer = new CustomerAccount("123456789", "1234", "Cool", "Guy", 5880.05);
    HashMap<String, CustomerAccount> accounts = new HashMap<>();
    double currency;
    boolean dispenserClear = true;
    boolean dpSlotClear = true;

    public ATM(CustomerAccount[] accounts, double currency) {
        addAccount(defaultCustomer);
        for (CustomerAccount acct : accounts) {
            addAccount(acct);
        }
        if (currency > 0.0 && currency % 10.0 == 0.0) {
            this.currency = currency;
        } else {
            this.currency = 0.0;
        }
    }

    /*Should only add acct iff acct not in accounts, else throw an exception.*/
    private void addAccount(CustomerAccount acct) {
        this.accounts.put(acct.getPan(), acct);
    }

    public boolean validPAN(String pan) {
        try {
            this.accounts.get(pan);
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public CustomerAccount getCurrentCustomer(String pan) {
        if (validPAN(pan)) {
            return this.accounts.get(pan);
        }
        return null;
    }

    public void setCurrency(double curr) { this.currency = curr; }
    public double getCurrency() {
        return currency;
    }

    public boolean getDispenserClear() { return dispenserClear; }
    public boolean getDpSlotClear() { return dpSlotClear; }

    private boolean divisibleByTen(double amt) { // if ones digit is a zero, it's divisible by 10
        String amtStr = ""+amt;
        int decimalIdx = amtStr.indexOf(".");
        String onesDigit = String.valueOf(amtStr.charAt(decimalIdx-1));
        return onesDigit.equals("0");
    }

    public void withdrawal(double wdAmt) {
        if (wdAmt > this.currency) { throw new IllegalArgumentException(); }
        if (!divisibleByTen(wdAmt)) { throw new UnsupportedOperationException(); }
        this.currency -= wdAmt;
    }

    public void setDispenserClear(boolean dispenserClear) {
        this.dispenserClear = dispenserClear;
    }

    public void setDpSlotClear(boolean dpSlotClear) {
        this.dpSlotClear = dpSlotClear;
    }

}
