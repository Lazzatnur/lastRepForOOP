package gametable;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import control.Keyboard;

public class Table {

	public static int width = 280, height = 504;
	JFrame jf;
	
	public static Font pixelfont;
		
	public void create() throws IOException {

		jf = new JFrame("Tetris");
		jf.setSize(width+ 16, height + 39); 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setLayout(null);
		jf.addKeyListener(new Keyboard());
		jf.requestFocus();
		


		Menu dm = new Menu();
		setupDraw(dm, 0, 0, width, height);
		
		GameStyle dg = new GameStyle();
		setupDraw(dg, 0, 1, width + 1, height + 1);

		
		
		

		jf.setVisible(true);
	

	}
		
	private void setupDraw(JLabel draw, int x, int y, int width, int height) {
		draw.setBounds(x, y, width, height);
		draw.setVisible(true);
		jf.add(draw);
	}

}
