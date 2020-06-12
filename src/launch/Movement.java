package launch;

import game.BlockType;
import game.Logic;
import game.Sequence;
import mechanism.Collision;

public class Movement extends Thread {
	private boolean running = true;

	@Override
	public void run() {
		while (running) {
			try {
				if (Logic.gamestate == Sequence.ingame) {
					
					if (!Collision.collideWithWall(Logic.currentBlock, 0)
							&& !Collision.collideWithBlock(Logic.currentBlock, 0)) {
						Logic.currentBlock.setY(Logic.currentBlock.getY() + 1);
						Collision.collideWithWall(Logic.currentBlock, 0);

					}

					if (Logic.spawnNewBlock) {
						Collision.checkFullRow(1);
						Logic.blocks.add(Logic.nextBlock);
						Logic.currentBlock = Logic.nextBlock;
						Logic.nextBlock = new BlockType();
						Logic.spawnNewBlock = false;
					}
				}
				if (!Logic.speedup) {
					sleep(600);
				} else {
					sleep(60);
				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
