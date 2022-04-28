package edu.citadel.satm;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class Stepdefs {
    CustomerAccount defaultCustomer = new CustomerAccount("123456789", "1234", "Cool", "Guy");
    CustomerAccount[] accounts = {defaultCustomer};
    ATM atm = new ATM(accounts, 50000.0);

    @Given("I have {double} in my account")
    public void IHaveBalanceInMyAccount(double bal) {
        defaultCustomer.deposit(bal);
    }

    @When("I perform a balance inquiry")
    public void iPerformABalanceInquiry() {
    }

    @When("I make a deposit of {double}")
    public void IMakeADeposit(double dpAmt) {
        defaultCustomer.deposit(dpAmt);
    }

    @When("I request to withdrawal {double}")
    public void IRequestToWithdrawalAmount(double wdAmt) {
        defaultCustomer.withdrawal(wdAmt);
        atm.withdrawal(wdAmt);
    }

    @Then("I should have {double}")
    public void iShouldHaveBalance(double bal) {
        assertEquals(bal, defaultCustomer.getBalance(), 0.01);
    }

    @And("The ATM has {double} stored")
    public void theATMHasCurrencyStored(double curr) {
        atm.setCurrency(curr);
    }

    @And("The ATM should have {double} leftover")
    public void theATMShouldHaveCurrencyLeftover(double curr) {
        assertEquals(curr, atm.getCurrency(), 0.01);
    }

}
