package lab_ex.lab_ex3;

public class CreditAccount extends BankAccount {

    private int months;

    public CreditAccount(double amount, double interest, int months) {
        super(amount, interest);
        this.months = months;
    }

    @Override
    public double pay() {
        for (int i = 0; i < months; i++) {
            amount += amount * (interest / 100);
        }

        return amount;
    }
}
