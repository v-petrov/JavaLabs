package lab_ex2.company;

import lab_ex2.company.FirmET;

public class Main {

    public static void main(String[] args) {
        FirmET firm = new FirmET("Vasil-OOD", "26/10/2022", "0123456789", "Vasil", 50_000, 1_000_000);
        System.out.println(firm.winnings());
    }
}
