import javax.swing.*;

public class Demo {
    public static void main(String[] args) {
        String[] difficulties = {"beginner", "intermediate", "expert"};
        String input = (String) JOptionPane.showInputDialog(null, "Choose difficulty", "Difficulty",
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("mine.png"), difficulties, difficulties[0]);

        if (!(input == null)) {
            Difficulty difficulty = Difficulty.valueOf(input);
            Board minesweeper = new Board(difficulty);
        }
    }
}
