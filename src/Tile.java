import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton implements MouseListener {

	private int row;
	private int column;
	private boolean hidden;
	private boolean flagged;
	private int surMines;
	private boolean stop;

	public Tile(int r, int c) {
		row = r;
		column = c;
		hidden = true;
		flagged = false;
		this.addMouseListener(this);
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
		this.setBackground(Color.LIGHT_GRAY);
		this.setIcon(null);
		if (surMines != 0) {
			this.setText(surMines + "");
		}
		this.hidden = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getButton() == 1) && (!flagged) && (hidden)) {
			if (!(surMines == 0)) {
				revealTile();
			}

			if (surMines == 0 && !(this instanceof Mine)) {
				Board.zeroReveal(row, column);
			}

		} else if (e.getButton() == 3 && (hidden)) {
			if (flagged) {
					this.setIcon(null);
					this.flagged = false;
					Board.IncreaseFlags();
			} else {
					this.setIcon(new ImageIcon("flag.png"));
					this.flagged = true;
					Board.DecreaseFlags();
			}

		}
		if (!stop) Board.victory();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}