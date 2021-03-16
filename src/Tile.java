import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton implements MouseListener {

	private int row;
	private int column;
	private boolean hidden;
	private boolean flagged;
	private ImageIcon flag;
	private int surMines;
	private boolean stop;

	public Tile(int r, int c) {
		row = r;
		column = c;
		hidden = true;
		flagged = false;
		this.addMouseListener(this);
		stop = false;
	}

	public boolean getHidden() {
		return this.hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean getFlagged() {
		return this.flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
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

	public void revealTile() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setIcon(null);
		this.setText(surMines + "");
		this.hidden = false;
	}

	public void revealZero() {
		this.setBackground(Color.LIGHT_GRAY);
		this.hidden = false;
		this.setIcon(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getButton() == 1) && (flagged == false) && (hidden == true) && (stop == false)) {

			if (!(surMines == 0)) {
				revealTile();
			}

			if (surMines == 0 && !(this instanceof Mine)) {
				Board.zeroReveal(row, column);
			}

		} else if (e.getButton() == 3 && (hidden == true) && (stop == false)) {
			if (flagged == true) {
					this.setIcon(null);
					this.flagged = false;
			} else {
					this.setIcon(new ImageIcon("flag.png"));
					this.flagged = true;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

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