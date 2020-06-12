package game;

import java.util.concurrent.ThreadLocalRandom;

public enum BlockSequence {
	I,O,T,L,J,Z,S;
	
	public static BlockSequence random() {
		
		return values()[ThreadLocalRandom.current().nextInt(0,values().length)];
	}
}
