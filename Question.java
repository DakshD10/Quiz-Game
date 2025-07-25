import java.util.ArrayList;

public class Question {
    String questionText;
    String correctAnswer;
    ArrayList<String> options;

    // Constructor to set values
    public Question(String questionText, String correctAnswer, ArrayList<String> options) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }
}

