package gametable;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import game.Logic;
import game.Sequence;

public class Menu extends JLabel {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Logic l= new Logic();
		
		String score = String.valueOf(l.getScore());
		String highscore = String.valueOf(l.getHighscore());
		if(Logic.gamestate == Sequence.gameover){
			g.setColor(new Color(255, 48, 2));
			g.fillRect(0, Table.height / 2 - 50, Table.width+200, 100);
			g.setColor(Color.WHITE);
			g.drawString("your score: "+score, Table.width/3, Table.height / 2);
			g.drawString("highscore: "+highscore, Table.width/3, Table.height / 2 +12);
			
		}

		repaint();
		
	}
}
