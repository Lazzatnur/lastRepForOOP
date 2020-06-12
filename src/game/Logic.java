package game;

import java.util.ArrayList;
public class Logic {
	public static int score = 0, highscore = 0, scoreToAdd = 0;
	
	public static boolean spawnNewBlock = false, speedup = false;
	
	public static ArrayList<BlockType> blocks = new ArrayList<BlockType>();
	public static BlockType currentBlock, nextBlock;
	
	public static int[][] map = new int[10][18];
	
	public static Sequence gamestate = Sequence.start;
	
	public static void clear() {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				map[x][y] = 0;
			}
		}
	}
	public int getScore(){
		return score;
	}
	public int getHighscore(){
		return highscore;
	}

}
