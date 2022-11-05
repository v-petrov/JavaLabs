package scholarship;

import java.util.ArrayList;
import java.util.List;

public class Secretary implements Runnable {
    private final Scholarship scholarship;

    private static final List<Scholarship> approvedScholarship = new ArrayList<>();
    private static final List<Scholarship> disapprovedScholarship = new ArrayList<>();

    public Secretary(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    @Override
    public void run() {
        boolean isCorrect = isScholarshipCorrect(scholarship);

        if (isCorrect) {
            approvedScholarship.add(scholarship);
        } else {
            disapprovedScholarship.add(scholarship);
        }
    }

    private boolean isScholarshipCorrect(Scholarship scholarship) {
        if (scholarship.getKindOfScholarship() == 1) {
            return scholarship.getSemesterGrade() > 5.50 && scholarship.getParentIncome() < 500;
        } else {
            return scholarship.getSemesterGrade() > 5.30 && scholarship.getParentIncome() < 300;
        }
    }

    public static List<Scholarship> getApprovedScholarship() {
        return approvedScholarship;
    }

    public static List<Scholarship> getDisapprovedScholarship() {
        return disapprovedScholarship;
    }
}
