package grader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Assistant extends Thread {

    private final String name;
    private final AdminGradingAPI grader;
    private final static AtomicInteger gradedAssignments = new AtomicInteger(0);

    private int personalGraded;

    public Assistant(String name, AdminGradingAPI grader) {
        this.name = name;
        this.grader = grader;
        this.personalGraded = 0;
    }

    @Override
    public void run() {
        while (true) {
            Assignment assignment = grader.getAssignment();
            if (assignment == null) {
                System.out.println("Assistant: " + getAssistantName() + ", " + "graded: " + personalGraded + " task/s!");
                return;
            }
            try {
                Thread.sleep(assignment.type().getGradingTime());

                List<Assignment> assignments = CodePostGrader.getAssignmentList();
                assignments.add(new Assignment(assignment.studentFn(), assignment.studentName(), assignment.type(), true));
                CodePostGrader.setAssignmentList(assignments);

                gradedAssignments.incrementAndGet();
                personalGraded++;
            } catch (InterruptedException e) {
                System.out.println("Assistant thread got interrupted!");
                e.printStackTrace();
            }
        }
    }

    public static int getNumberOfGradedAssignments() {
        return gradedAssignments.get();
    }

    public String getAssistantName() {
        return name;
    }
}
