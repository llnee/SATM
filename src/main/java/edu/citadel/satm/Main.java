package edu.citadel.satm;

import java.util.Random;
import javax.swing.*;

public class Main {

    public static CustomerAccount[] createAccts(int numAccts) {
        CustomerAccount[] accts = new CustomerAccount[numAccts];
        Random r = new Random();
        for (int i = 0; i < numAccts; i++) {
            String pan = String.valueOf(r.nextInt(100000000, 999999999));  // 9-digit pos int
            String pin = String.valueOf(r.nextInt(1000, 9999));  // 4-digit pos int
            String f_name = ""; String l_name = "";
            double bal = r.nextDouble();
            accts[i] = new CustomerAccount(pan, pin, f_name, l_name, bal);
        }
        return accts;
    }

    public static void main(String[] args){

        CustomerAccount[] accts = createAccts(5);
        ATM atm = new ATM(accts, 10000.0);

        View v = new View("MyATM");

        Controller c = new Controller(atm, v);
        c.initController();

    }
}
