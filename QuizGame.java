import java.util.ArrayList;
import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        QuizService quizService = new QuizService();
        ArrayList<Question> questions = quizService.fetchQuestions();

        if (questions.isEmpty()) {
            System.out.println("No questions found. Please try again later.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.questionText);

            for (int j = 0; j < q.options.size(); j++) {
                System.out.println((j + 1) + ". " + q.options.get(j));
            }

            System.out.print("Your answer (1-" + q.options.size() + "): ");
            int userChoice = scanner.nextInt();
            String userAnswer = q.options.get(userChoice - 1);

            if (userAnswer.equals(q.correctAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + q.correctAnswer);
            }
        }

        System.out.println("\nQuiz Over! You scored " + score + "/" + questions.size());
        scanner.close();
    }
}