package grader;

import java.util.*;

public class CodePostGrader implements AdminGradingAPI {

    private static List<Assignment> assignmentList = new ArrayList<>();
    private final List<Assistant> assistantList = new ArrayList<>();

    private final Object lock;
    private int submittedAssigmentCount;

    private boolean noMoreGrading;

    public CodePostGrader(int numberOfAssistants) {
        this.lock = new Object();
        for (int i = 0; i < numberOfAssistants; i++) {
            Assistant assistant = new Assistant(String.valueOf(i + 1), this);
            assistantList.add(assistant);
            new Thread(assistant).start();
        }
    }

    private boolean isThereForGrade() {
        for (Assignment assignment : getAssignmentList()) {
            if (!assignment.isGraded()) {
                return true;
            }
        }

        return false;
    }

    public void finishGrading() {
        synchronized (lock) {
            noMoreGrading = true;
            lock.notifyAll();
        }
    }

    @Override
    public Assignment getAssignment() {
        synchronized (lock) {
            while (!isThereForGrade() && !noMoreGrading) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Assistant thread got interrupted!");
                    e.printStackTrace();
                }
            }

            for (Assignment assignment : getAssignmentList()) {
                if (!assignment.isGraded()) {
                    assignmentList.remove(assignment);
                    return assignment;
                }
            }

            return null;
        }
    }

    @Override
    public int getSubmittedAssignmentsCount() {
        return submittedAssigmentCount;
    }

    @Override
    public List<Assistant> getAssistants() {
        return assistantList;
    }

    @Override
    public void submitAssignment(Assignment assignment) {
        synchronized (lock) {
            assignmentList.add(assignment);
            submittedAssigmentCount++;
            lock.notifyAll();
        }
    }

    public static List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public static void setAssignmentList(List<Assignment> assignmentList) {
        CodePostGrader.assignmentList = assignmentList;
    }
}
