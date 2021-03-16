import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Mine extends Tile {

	public Mine(int r, int c) {
		super(r,c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		if ((e.getButton() == 1) && (getFlagged() == false) && (getHidden() == true)) {
			Board.gameOver();
		}
	}

	public void revealMine() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setIcon(new ImageIcon("mine.png"));
		this.setHidden(false);
	}
}