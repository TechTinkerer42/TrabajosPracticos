package tictactoe;

import tictactoe.ai.Player;
import util.Logger;

public class Test {
	private static final int FOR_EVER = -1;
	
	private Engine ticTacToe;
	private Player p1, p2;

	public Test() {
		this(null, null);
	}
	
	public Test(Player p1, Player p2) {
		ticTacToe = new Engine();
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	
	public void setP2(Player p2) {
		this.p2 = p2;
	}
	
	public Engine getEngine() {
		return ticTacToe;
	}
	
	public void run() {
		run(FOR_EVER);
	}
	
	public void run(int nGames) {
		boolean enfOfgame = false;
		do {
			ticTacToe.startGame(p1, p2);
			Player temp = p1;
			p1 = p2;
			p2 = temp;
			Logger.log("End of Turn", "***** Players have switched turns! *****", Logger.LEVEL_TRACE);
			if (nGames != FOR_EVER) {
				nGames--;
				enfOfgame = nGames < 0;
			}
		} while(!enfOfgame);
	}
}
