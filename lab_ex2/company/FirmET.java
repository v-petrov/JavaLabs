package lab_ex2.company;

import lab_ex2.company.Company;

public class FirmET extends Company {

    private String ownerName;
    private double startingCapital;
    private double currentCapital;

    public FirmET(String companyName, String dateOfCreation, String uniqueID, String ownerName, double startingCapital, double currentCapital) {
        super(companyName, dateOfCreation, uniqueID);
        this.ownerName = ownerName;
        this.startingCapital = startingCapital;
        this.currentCapital = currentCapital;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getStartingCapital() {
        return startingCapital;
    }

    public void setStartingCapital(double startingCapital) {
        this.startingCapital = startingCapital;
    }

    public double getCurrentCapital() {
        return currentCapital;
    }

    public void setCurrentCapital(double currentCapital) {
        this.currentCapital = currentCapital;
    }

    public double winnings() {
        return currentCapital - startingCapital;
    }
}
