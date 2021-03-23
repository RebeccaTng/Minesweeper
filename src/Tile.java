import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

	private int row;
	private int column;
	private boolean hidden;
	private boolean flagged;
	private int surMines;
	private boolean stop;
	private boolean isMine;
	private boolean firstClickedTile;

	public Tile(int r, int c) {
		row = r;
		column = c;
		hidden = true;
		flagged = false;
		stop = false;
		isMine = false;
		firstClickedTile = false;
	}

	public boolean getFirstClickedTile() {
		return firstClickedTile;
	}

	public void setFirstClickedTile(boolean firstClickedTile) {
		this.firstClickedTile = firstClickedTile;
	}

	public boolean getMine() {
		return isMine;
	}

	public void setMine(boolean mine) {
		isMine = mine;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean getHidden() {
		return this.hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public boolean getFlagged() {
		return this.flagged;
	}

	public void setSurMines(int count) {
		surMines = count;
	}

	public int getSurMines() {
		return surMines;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isStop() {
		return stop;
	}

	public void revealTile() {
		if (!isMine) {
			this.setBackground(Color.LIGHT_GRAY);
			this.setIcon(null);
			if (surMines != 0) {
				this.setText(surMines + "");
			}
		}
		else {
			this.setBackground(Color.LIGHT_GRAY);
			this.setIcon(new ImageIcon("mine.png"));
		}
		this.hidden = false;
	}
}