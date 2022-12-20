package grader;

import java.util.Random;

public class Student implements Runnable {

    private final int fn;
    private final String name;
    private final StudentGradingAPI studentGradingAPI;

    private final static Random random = new Random();

    private final int workingTime;

    public Student(int fn, String name, StudentGradingAPI studentGradingAPI) {
        this.fn = fn;
        this.name = name;
        this.studentGradingAPI = studentGradingAPI;
        this.workingTime = random.nextInt(1001);
    }

    private AssignmentType getAssignmentType(int type) {
        return switch (type) {
            case 0 -> AssignmentType.LAB;
            case 1 -> AssignmentType.PLAYGROUND;
            case 2 -> AssignmentType.HOMEWORK;
            case 3 -> AssignmentType.PROJECT;
            default -> null;
        };
    }

    @Override
    public void run() {
        int type = random.nextInt(4);
        try {
            Thread.sleep(workingTime);
            Assignment assignment = new Assignment(fn, name, getAssignmentType(type), false);
            studentGradingAPI.submitAssignment(assignment);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with the tread!");
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }
}
