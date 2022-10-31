package lab_ex2.company;

public class Company {

    private String companyName;
    private String dateOfCreation;
    private String uniqueID;

    public Company(String companyName, String dateOfCreation, String uniqueID) {
        this.companyName = companyName;
        this.dateOfCreation = dateOfCreation;
        setUniqueID(uniqueID);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        if (uniqueID.length() == 10) {
            this.uniqueID = uniqueID;
        } else {
            System.out.println("The length of the uniqueID must be 10");
        }
    }
}
