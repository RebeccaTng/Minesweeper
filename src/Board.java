import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

	private static Tile[][] tiles;
	private Difficulty difficulty;
	private int mines;
	private int row;
	private int col;

	public Board(Difficulty difficulty) {
		this.difficulty = difficulty;
		start();
	}

	public void start() {
		JFrame frame = new JFrame("Minesweeper");
		JPanel minePanel = new JPanel();

		switch (difficulty) {
			case beginner:
				row = 8;
				col = 8;
				mines = 10;
				frame.setSize(700, 700);
				minePanel.setPreferredSize(new Dimension(600,600));
				break;
			case intermediate:
				row = 16;
				col = 16;
				mines = 40;
				frame.setSize(1000, 1000);
				minePanel.setPreferredSize(new Dimension(900,900));
				break;
			case expert:
				row = 16;
				col = 30;
				mines = 99;
				frame.setSize(1700, 1000);
				minePanel.setPreferredSize(new Dimension(1600,900));
				break;
		}

		tiles = new Tile[row][col];

		minePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		minePanel.setLayout(new GridLayout(row, col));

		frame.setLayout(new BorderLayout(/*20,20 -> voor afstand tss componenten maar met createEmptyBorder is genoeg*/));
		frame.add(minePanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				Tile e = new Tile(i, j);
				tiles[i][j] = e;
			}
		}

		populateMines();
		surroundingMines();

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				minePanel.add(tiles[x][y]);
			}
		}

		frame.setVisible(true);
	}

	public void populateMines() {
		for (int i = 0; i < mines; i++) {
			int rnRow = (int) (Math.random() * row - 1);
			int rnCol = (int) (Math.random() * col - 1);

			while (tiles[rnRow][rnCol] instanceof Mine) {
				rnRow = (int) (Math.random() * row - 1);
				rnCol = (int) (Math.random() * col - 1);
			}

			tiles[rnRow][rnCol] = new Mine(rnRow, rnCol);
		}
	}

	public void surroundingMines() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (!(tiles[i][j] instanceof Mine)) {
					int count = 0;

					for (int p = i - 1; p <= i + 1; p++) {
						for (int q = j - 1; q <= j + 1; q++) {
							if (0 <= p && p < tiles.length && 0 <= q && q < tiles[0].length) {
								if (tiles[p][q] instanceof Mine) {
									count++;
								}
							}
						}
					}

					tiles[i][j].setSurMines(count);
				}
			}
		}
	}

	public static void zeroReveal(int r, int c) {
		for (int p = r - 1; p <= r + 1; p++) {
			for (int q = c - 1; q <= c + 1; q++) {
				if (0 <= p && p < tiles.length && 0 <= q && q < tiles[0].length) {
					if (!(tiles[p][q] instanceof Mine)) {
						int count = tiles[p][q].getSurMines();

						if ((tiles[p][q]).getHidden() == true) {

							if (!(count == 0)) {
								tiles[p][q].revealTile();
							}

							if (count == 0) {
								tiles[p][q].revealZero();
								zeroReveal(p,q);
							}
						}
					}
				}
			}
		}
	}

	public static void gameOver() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {

				if (tiles[i][j] instanceof Mine) {
					((Mine) tiles[i][j]).revealMine();
				}

				tiles[i][j].setStop(true);
			}
		}
	}

	public void displayMinesLeft() {
		// TODO - implement Board.displayMinesLeft
		throw new UnsupportedOperationException();
	}

	public void leftClick(int r, int c) {
		// TODO - implement Board.leftClick
		throw new UnsupportedOperationException();
	}

	public void rightClick(int r, int c) {
		// TODO - implement Board.rightClick
		throw new UnsupportedOperationException();
	}

	public void restart() {
		// TODO - implement Board.restart
		throw new UnsupportedOperationException();
	}
}