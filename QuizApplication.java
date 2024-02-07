import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class QuizQuestion {
    private String questionText;
    private ArrayList<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String questionText, ArrayList<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class QuizManager {
    private ArrayList<QuizQuestion> questions;

    public QuizManager() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (QuizQuestion question : questions) {
            System.out.println(question.getQuestionText());
            ArrayList<String> options = question.getOptions();

            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int userAnswer = getUserAnswer(options.size());

            if (userAnswer == question.getCorrectAnswerIndex() + 1) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " +
                        (question.getCorrectAnswerIndex() + 1) + ". " +
                        options.get(question.getCorrectAnswerIndex()) + "\n");
            }
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }

    private int getUserAnswer(int maxOptions) {
        Scanner scanner = new Scanner(System.in);
        int userAnswer = -1;

        while (userAnswer < 1 || userAnswer > maxOptions) {
            try {
                System.out.print("Enter your answer (1-" + maxOptions + "): ");
                userAnswer = scanner.nextInt();
                if (userAnswer < 1 || userAnswer > maxOptions) {
                    System.out.println("Invalid input. Please enter a number between 1 and " + maxOptions + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the scanner buffer
            }
        }

        return userAnswer;
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        // Sample Quiz Setup
        QuizQuestion question1 = new QuizQuestion("What is the capital of France?",
                new ArrayList<>(List.of("Berlin", "Paris", "London", "Rome")), 1);

        QuizQuestion question2 = new QuizQuestion("Which planet is known as the Red Planet?",
                new ArrayList<>(List.of("Earth", "Mars", "Venus", "Jupiter")), 2);

        QuizQuestion question3 = new QuizQuestion("What is the largest mammal?",
                new ArrayList<>(List.of("Elephant", "Blue Whale", "Giraffe", "Dolphin")), 1);

        QuizManager quizManager = new QuizManager();
        quizManager.addQuestion(question1);
        quizManager.addQuestion(question2);
        quizManager.addQuestion(question3);

        // Start the Quiz
        quizManager.startQuiz();
    }
}
