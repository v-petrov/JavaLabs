package lab_ex.lab_ex3;

public class DebitAccount extends BankAccount {

    public DebitAccount(double amount, double interest) {
        super(amount, interest);
    }

    @Override
    public double pay() {
        return amount += amount * (interest / 100);
    }
}
