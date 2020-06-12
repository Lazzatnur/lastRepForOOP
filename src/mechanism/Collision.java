package mechanism;

import game.BlockType; 
import game.Logic;
import game.Sequence;


public class Collision {

	public static boolean collideWithBlock(BlockType b, int direction) {
		

		switch (direction) {
		case -1:
			if (b.getY() > 0) {
				if (b.getX() > 0) {
					for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
						for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
							if (b.getBounds()[b.getRotation()][i][j] == 1) {
								if (Logic.map[b.getX() + i - 1][b.getY() + j] >= 1) {

									return true;
								}
							}
						}
					}

				}
			}
			break;
		case 0:
			if (b.getY() + b.getSize() > 1) {
				if (b.getY() - b.getSize() < 17) {
					try {
						for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
							for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
								if (b.getBounds()[b.getRotation()][i][j] == 1) {

									if (Logic.map[b.getX() + i][b.getY() + j + 1] >= 1) {

										Logic.spawnNewBlock = true;
										fillBlock(b);

										return true;
									}

								}
							}
						} 
					} catch (Exception e) {
						return false;
					}
				}
			}

			break;
		case 1:
			if (b.getY() > 0) {
				if (b.getX() < 10) {
					for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
						for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
							if (b.getBounds()[b.getRotation()][i][j] == 1) {
								if (Logic.map[b.getX() + i + 1][b.getY() + j] >= 1) {

									return true;
								}
							}
						}
					}

				}
			}
			break;
		}

		return false;
	}

	public static boolean collideInRotation(BlockType b) {
		int rot = b.getRotation() + 1;
		if (rot == 4) {
			rot = 0;
		}
		
		BlockType block = new BlockType();
		block.setRotation(rot);
		block.setBounds(b.getBounds());
		block.setSize(b.getSize());
		block.setX(b.getX()-1);
		block.setY(b.getY());
		
		if(collideWithWall(block, 1)) {
			return true;
		}
		block.setX(b.getX()+2);
		if(collideWithWall(block, -1)) {
			return true;
		}

		if (b.getY() > 0) {
			for (int i = 0; i < b.getBounds()[rot].length; i++) {
				for (int j = 0; j < b.getBounds()[rot][i].length; j++) {
					if (b.getBounds()[rot][i][j] == 1) {
						try {
							if (Logic.map[b.getX() + i][b.getY() + j] >= 1) {

								return true;
							}
						} catch (Exception e) {
							return true;
						}
					}
				}

			}
		}

		return false;
	}

	public static boolean collideWithWall(BlockType b, int direction) {
		switch (direction) {
		case -1:
			for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
				for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
					if (b.getBounds()[b.getRotation()][i][j] == 1) {
						if (b.getX() + i == 0) {
							return true;
						}
					}
				}
			}
			break;
		case 0:
			for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
				for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
					if (b.getBounds()[b.getRotation()][i][j] == 1) {
						if (b.getY() + j == 17) {
							Logic.spawnNewBlock = true;
							fillBlock(b);

							return true;
						}
					}
				}
			}
			break;
		case 1:
			for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
				for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
					if (b.getBounds()[b.getRotation()][i][j] == 1) {
						if (b.getX() + (i + 2) >= 11) {
							return true;
						}
					}
				}
			}
			break;
		}

		return false;
	}

	private static void fillBlock(BlockType b) {
		try {
			for (int i = 0; i < b.getBounds()[b.getRotation()].length; i++) {
				for (int j = 0; j < b.getBounds()[b.getRotation()][i].length; j++) {
					if (b.getBounds()[b.getRotation()][i][j] == 1) {
						Logic.map[b.getX() + i][b.getY() + j] = b.getTypeValue();

					}

				}
			}
		} catch (Exception e) {

		}
		checkLoose();
	}

	public static void checkFullRow(int multiplier) {

		int blocksInRow = 0;

		for (int y = Logic.map[0].length - 1; y > 0; y--) {
			for (int x = 0; x < Logic.map.length; x++) {

				if (Logic.map[x][y] > 0) {
					blocksInRow++;
				}
			}
			if (blocksInRow == 10) {
				Logic.scoreToAdd += (10 * multiplier);
				delRow(y, multiplier);
				break;
			} else {
				blocksInRow = 0;
			}

		}

		Logic.score += Logic.scoreToAdd;
		Logic.scoreToAdd = 0;

		if (Logic.score > Logic.highscore) {
			Logic.highscore = Logic.score;
			
		}
	}



	private static void delRow(int row, int multiplier) {

		for (int i = 0; i < Logic.map.length; i++) {
			Logic.map[i][row] = 0;
		}

		for (int y = row; y > 1; y--) {
			for (int x = 0; x < Logic.map.length; x++) {
				Logic.map[x][y] = Logic.map[x][y - 1];
			}

		}
		checkFullRow(multiplier + 1);
	}

	private static void checkLoose() {
		for (int x = 0; x < Logic.map.length; x++) {

			if (Logic.map[x][0] > 0) {
				Logic.gamestate = Sequence.gameover;
			}

		}
	}

}
