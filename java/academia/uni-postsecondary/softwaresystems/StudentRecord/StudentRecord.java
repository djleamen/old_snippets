package softwaresystems.StudentRecord;

public class StudentRecord {
    private static final float ASSIGNMENTS_WEIGHT = 0.2f;
    private static final float MIDTERM_WEIGHT = 0.3f;
    private static final float EXAM_WEIGHT = 0.5f;

    private static final int A_MIN = 80;
    private static final int B_MIN = 70;
    private static final int C_MIN = 60;
    private static final int D_MIN = 50;

    private String studId;
    private float midtermGrade;
    private float assignGrade;
    private float examGrade;
    private float finalGrade;
    private char letterGrade;

    public StudentRecord(String studId, float assignGrade, float midtermGrade, float examGrade) {
        this.studId = studId;
        this.midtermGrade = midtermGrade;
        this.assignGrade = assignGrade;
        this.examGrade = examGrade;
        setFinalGrade();
        setLetterGrade();
    }

    public String getStudentId() {
        return studId;
    }

    public void setStudentId(String studId) {
        this.studId = studId;
    }

    public float getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(float midtermGrade) {
        this.midtermGrade = midtermGrade;
        setFinalGrade();
        setLetterGrade();
    }

    public float getAssignmentGrade() {
        return assignGrade;
    }

    public void setAssignmentGrade(float assignGrade) {
        this.assignGrade = assignGrade;
        setFinalGrade();
        setLetterGrade();
    }

    public float getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(float examGrade) {
        this.examGrade = examGrade;
        setFinalGrade();
        setLetterGrade();
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    private void setFinalGrade() {
        finalGrade = (midtermGrade*MIDTERM_WEIGHT) + (assignGrade*ASSIGNMENTS_WEIGHT) + (examGrade* EXAM_WEIGHT);
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    private void setLetterGrade() {
        if (finalGrade >= A_MIN) {
            letterGrade = 'A';
        } else if (finalGrade < A_MIN && finalGrade >= B_MIN) {
            letterGrade = 'B';
        } else if (finalGrade < B_MIN && finalGrade >= C_MIN) {
            letterGrade = 'C';
        } else if (finalGrade < C_MIN && finalGrade >= D_MIN) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
    }
}
