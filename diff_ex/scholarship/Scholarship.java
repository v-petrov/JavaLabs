package scholarship;

public class Scholarship {

    private String name;
    private double semesterGrade;
    private double parentIncome;
    private String facultyNumber;

    private int kindOfScholarship;

    public Scholarship(String name, double semesterGrade, double parentIncome, String facultyNumber, int kindOfScholarship) {
        this.name = name;
        this.semesterGrade = semesterGrade;
        this.parentIncome = parentIncome;
        this.facultyNumber = facultyNumber;
        this.kindOfScholarship = kindOfScholarship;
    }

    public String getName() {
        return name;
    }

    public double getSemesterGrade() {
        return semesterGrade;
    }

    public double getParentIncome() {
        return parentIncome;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public int getKindOfScholarship() {
        return kindOfScholarship;
    }
}
