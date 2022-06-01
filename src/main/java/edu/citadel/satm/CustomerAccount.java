package edu.citadel.satm;

public class CustomerAccount {
    String pan, pin;
    String FName, LName;
    double balance;

    public CustomerAccount(String pan, String pin, String f_name, String l_name, double balance) {
        this.pan = pan;
        this.pin = pin;
        this.FName = f_name; this.LName = l_name;
        this.balance = balance;
    }

    public CustomerAccount(String pan, String pin, String f_name, String l_name) {
        this.pan = pan;
        this.pin = pin;
        this.FName = f_name; this.LName = l_name;
        this.balance = 0.0;
    }

    public String getPan() { return pan; }
    public String getPin() { return pin; }
    public String getFName() { return FName; }
    public String getLName() { return LName; }
    public double getBalance() { return balance; }


    public boolean validPIN(String pin) {
        return this.pin.compareTo(pin) == 0;
    }

    public void deposit(double dpAmt) {
        if (dpAmt < 0.0) { throw new IllegalArgumentException(); }
        this.balance += dpAmt;
    }

    public void withdrawal(double wdAmt) {
        if (wdAmt > this.balance) { throw new ArithmeticException(); }
        this.balance -= wdAmt;
    }
}
