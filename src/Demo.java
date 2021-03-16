import javax.swing.*;

public class Demo {
    public static void main(String[] args) {
        String[] difficulties = {"beginner", "intermediate", "expert"};
        Difficulty difficulty = Difficulty.valueOf(
                (String) JOptionPane.showInputDialog(null, "Choose difficulty", "Difficulty",
                                                JOptionPane.QUESTION_MESSAGE, new ImageIcon("mine.png"), difficulties, difficulties[0]));
        Board minesweeper = new Board(difficulty);
    }
}
