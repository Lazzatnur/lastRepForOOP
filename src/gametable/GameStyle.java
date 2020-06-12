package gametable;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import game.Logic;
import mechanism.Conversion;

public class GameStyle extends JLabel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		g.setColor(Logic.currentBlock.getColor());
		for (int j = 0; j < Logic.currentBlock.getBounds()[Logic.currentBlock.getRotation()].length; j++) {
			for (int k = 0; k < Logic.currentBlock.getBounds()[Logic.currentBlock.getRotation()][j].length; k++) {

				if (Logic.currentBlock.getBounds()[Logic.currentBlock.getRotation()][j][k] == 1) {
					g.fillRect(Conversion.cellToCoord(Logic.currentBlock.getX() + j),
							Conversion.cellToCoord(Logic.currentBlock.getY() + k), 28, 28);
				}
			}
		}

		for (int i = 0; i < Logic.map.length; i++) {
			for (int j = 0; j < Logic.map[i].length; j++) {
				if (Logic.map[i][j] > 0) {
					switch (Logic.map[i][j]) {
					case 1:
						g.setColor(Color.GREEN);						
						break;
					case 2:
						g.setColor(Color.ORANGE);
						break;
					case 3:
						g.setColor(Color.CYAN);
						break;
					case 4:
						g.setColor(Color.BLUE);
						break;
					case 5:
						g.setColor(Color.MAGENTA);
						break;
					case 6:
						g.setColor(Color.YELLOW);
						break;
					case 7:
						g.setColor(Color.RED);
						break;
					}
					g.fillRect(Conversion.cellToCoord(i), Conversion.cellToCoord(j), 28, 28);
				}
			}
		}

		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 18; j++) {
				g.drawRect(i * 28, j * 28, 28, 28);
			}
		}


		g.setColor(Color.BLACK);
		g.drawRect(0, 0, Table.width, Table.height);

		repaint();
	}

}
