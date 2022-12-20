package grader;

import java.util.List;

public interface AdminGradingAPI extends StudentGradingAPI {

    Assignment getAssignment();

    int getSubmittedAssignmentsCount();

    List<Assistant> getAssistants();
}
