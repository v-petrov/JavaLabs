package lab_ex.lab_ex3;

public abstract class BankAccount implements Playable {

    protected double amount;
    protected double interest;

    protected BankAccount(double amount, double interest) {
        this.amount = amount;
        this.interest = interest;
    }


}
