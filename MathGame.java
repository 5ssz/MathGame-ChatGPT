import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MathGame {
    private JFrame frame;
    private JLabel questionLabel;
    private JLabel scoreLabel;
    private JTextField answerField;
    private JPanel scorePanel;

    private int score;

    private int num1, num2, answer;

    public MathGame() {
        score = 0;
        frame = new JFrame("Math Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(questionLabel, BorderLayout.CENTER);

        answerField = new JTextField(20);
        answerField.addActionListener(new AnswerListener());
        frame.add(answerField, BorderLayout.SOUTH);

        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scorePanel.add(scoreLabel);
        frame.add(scorePanel, BorderLayout.NORTH);

        generateQuestion();

        frame.setVisible(true);
    }

    public void generateQuestion() {
        num1 = (int) (Math.random() * 10) + 1;
        num2 = (int) (Math.random() * 10) + 1;
        answer = num1 * num2;

        questionLabel.setText(num1 + " * " + num2 + " = ");
        answerField.setText("");
        answerField.requestFocus();
    }

    private class AnswerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                int userAnswer = Integer.parseInt(answerField.getText());
                if (userAnswer == answer) {
                    score += 5;
                    scoreLabel.setText("Score: " + score);
                    scorePanel.setBackground(Color.GREEN);
                    Timer timer = new Timer(500, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            scorePanel.setBackground(Color.BLACK);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    score--;
                    scoreLabel.setText("Score: " + score);
                    scorePanel.setBackground(Color.RED);
                    Timer timer = new Timer(500, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            scorePanel.setBackground(Color.BLACK);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
                generateQuestion();
            } catch (NumberFormatException e) {
                answerField.setText("");
                answerField.requestFocus();
            }
        }
    }

    public static void main(String[] args) {
        new MathGame();
    }
}
