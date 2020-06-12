package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Logic;
import game.Sequence;
import mechanism.Collision;

public class Keyboard implements KeyListener { // KeyLiatener is special class to get values from Keyboard

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Logic.gamestate == Sequence.start) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) { // click enter=> start a game
				Logic.gamestate = Sequence.ingame;

			}
		}

		if (Logic.gamestate == Sequence.ingame) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) { // space=>rotate the blcok
				try {
					if (!Collision.collideInRotation(Logic.currentBlock)) {
						Logic.currentBlock.rotate();
					}
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_S) { // click s=> speed of the object will be faster
				Logic.speedup = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_D) { // click d=> to the right

				try {
					if (!Collision.collideWithWall(Logic.currentBlock, 1)
							&& !Collision.collideWithBlock(Logic.currentBlock, 1)) {
						Logic.currentBlock.setX(Logic.currentBlock.getX() + 1);
					} 
				} catch (Exception e2) {
					e2.printStackTrace();
					
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A) { // click a=> to the left
				try {
					if (!Collision.collideWithWall(Logic.currentBlock, -1)
							&& !Collision.collideWithBlock(Logic.currentBlock, -1)) {
						Logic.currentBlock.setX(Logic.currentBlock.getX() - 1);
					} 
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // click esc=> to pause the game
				Logic.gamestate = Sequence.pause;

			}
		} else if (Logic.gamestate == Sequence.pause) { // click esc=> to continue the game, if game paused
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Logic.gamestate = Sequence.ingame;
			}

		} else if (Logic.gamestate == Sequence.gameover) { // click enter after the end=> start a new game
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Logic.gamestate = Sequence.ingame;
				Logic.clear();  // everything is clear and no blocks
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Logic.gamestate == Sequence.ingame) {
			if (e.getKeyCode() == KeyEvent.VK_S) {
				Logic.speedup = false;
			}
		}

	}

}
