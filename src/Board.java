import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends MouseAdapter {

	private Tile[][] tiles;
	private Difficulty difficulty;
	private int mines;
	private int row;
	private int col;
	private int flagsLeft;
	private JLabel flags;
	private int totalTiles;
	private boolean firstClicked;
	private JFrame frame;
	private JPanel minePanel;
	private JButton restart;

	public Board(Difficulty difficulty) {
		this.difficulty = difficulty;
		start();
	}

	public void start() {
		firstClicked = false;
		frame = new JFrame("Minesweeper");
		minePanel = new JPanel();

		switch (difficulty) {
			case beginner:
				row = 8;
				col = 8;
				mines = 10;
				frame.setSize(650, 750);
				break;
			case intermediate:
				row = 16;
				col = 16;
				mines = 40;
				frame.setSize(1000, 1070);
				break;
			case expert:
				row = 16;
				col = 30;
				mines = 99;
				frame.setSize(1700, 1070);
				break;
		}

		tiles = new Tile[row][col];
		flagsLeft = mines;
		totalTiles = row * col;

		restart = new JButton("RESTART");
		restart.addMouseListener(this);
		restart.setPreferredSize(new Dimension(160, 50));
		JPanel restartPanel = new JPanel(new GridBagLayout());
		restartPanel.add(restart);
		restartPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

		flags = new JLabel("Flags left: " + flagsLeft);
		flags.setFont(new Font("", Font.PLAIN, 20));
		flags.setIcon(new ImageIcon("flag.png"));
		flags.setBorder(BorderFactory.createEtchedBorder(1));
		flags.setPreferredSize(new Dimension(160, 50));
		JPanel flagPanel = new JPanel(new GridBagLayout());
		flagPanel.add(flags);
		flagPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

		minePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		minePanel.setLayout(new GridLayout(row, col));

		frame.setIconImage(new ImageIcon("mine.png").getImage());
		frame.setLayout(new BorderLayout());
		frame.add(minePanel, BorderLayout.CENTER);
		frame.add(flagPanel, BorderLayout.NORTH);
		frame.add(restartPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				Tile tile = new Tile(i,j);
				tile.addMouseListener(this);
				tiles[i][j] = tile;
			}
		}

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

			while (tiles[rnRow][rnCol].getMine() || tiles[rnRow][rnCol].getFirstClickedTile()) {
				rnRow = (int) (Math.random() * row - 1);
				rnCol = (int) (Math.random() * col - 1);
			}

			tiles[rnRow][rnCol].setMine(true);
		}
	}

	public void surroundingMines() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (!(tiles[i][j].getMine())) {
					int count = 0;

					for (int p = i - 1; p <= i + 1; p++) {
						for (int q = j - 1; q <= j + 1; q++) {
							if (0 <= p && p < tiles.length && 0 <= q && q < tiles[0].length) {
								if (tiles[p][q].getMine()) {
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
					if (!(tiles[p][q].getMine())) {
						if ((tiles[p][q]).getHidden()) {
							tiles[p][q].revealTile();

							if (tiles[p][q].getSurMines() == 0) {
								zeroReveal(p,q);
							}
						}
					}
				}
			}
		}
	}

	public static void revealAll() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {

				tiles[i][j].revealTile();
				tiles[i][j].setStop(true);
			}
		}
	}

	public static void gameOver() {
		revealAll();
		JOptionPane.showMessageDialog(null, "You lost, better luck next time!");
	}

	public static void DecreaseFlags() {
		flagsLeft--;
		flags.setText("Flags left: " + flagsLeft);

	}

	public static void IncreaseFlags() {
		flagsLeft++;
		flags.setText("Flags left: " + flagsLeft);
	}

	public static void victory() {
		int minesLeft = mines;
		int tilesLeft = totalTiles - mines;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].getMine() && tiles[i][j].getFlagged()) {
					minesLeft--;
				} else if ((!(tiles[i][j].getMine())) && !tiles[i][j].getHidden()) {
					tilesLeft--;
				}
			}
		}

		if (minesLeft == 0 && tilesLeft == 0) {
			revealAll();
			JOptionPane.showMessageDialog(null, "Congratulations, you won!");
		}
	}

	public void restart() {
		// TODO - implement Board.restart
		throw new UnsupportedOperationException();
	}

	public void solution() {
		int oneRow = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {

				if (oneRow%8 == 0) {
					System.out.println();
				}
				if ((tiles[i][j] instanceof Mine)) {
					System.out.print("X");
				} else {
					System.out.print("O");
				}
				oneRow++;
			}
		}
	}
}