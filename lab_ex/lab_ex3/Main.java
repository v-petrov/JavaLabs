package lab_ex.lab_ex3;

public class Main {

    public static void main(String[] args) {
        Playable[] playable = new Playable[2];

        playable[0] = new DebitAccount(100, 10);
        playable[1] = new CreditAccount(100, 10, 2);

        double amount;
        for (int i = 0; i < 2; i++) {
            amount = playable[i].pay();
            System.out.println(amount);
        }
    }
}
