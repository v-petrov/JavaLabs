package grader;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CodePostGrader codePostGrader = new CodePostGrader(5);
        for (int i = 0; i < 30; i++) {
            Student st = new Student(i + 1, String.valueOf(i + 1), codePostGrader);
            new Thread(st).start();
        }

        Thread.sleep(5000);

        codePostGrader.finishGrading();

        Thread.sleep(1000);

        System.out.println(codePostGrader.getSubmittedAssignmentsCount());

        System.out.println(Assistant.getNumberOfGradedAssignments());
    }
}
