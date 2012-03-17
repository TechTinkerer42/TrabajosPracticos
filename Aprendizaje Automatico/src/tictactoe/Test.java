package tictactoe;

import java.io.IOException;

import tictactoe.ai.HumanPlayer;
import tictactoe.ai.IntelligentPlayer;
import tictactoe.ai.Player;
import tictactoe.ai.RandomPlayer;
import tictactoe.ai.lmsalgorithm.LMSAlgorithm;
import tictactoe.ai.lmsalgorithm.PlayerIO;
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
	
	public void run() {
		run(FOR_EVER);
	}
	
	public void run(int nGames) {
		boolean enfOfgame = false;
		do {	// play for ever =)
			ticTacToe.startGame(p1, p2);
			Player temp = p1;
			p1 = p2;
			p2 = temp;
			System.out.println("***** Players have switched turns! *****");
			if (nGames != FOR_EVER) {
				nGames--;
				enfOfgame = nGames < 0;
			}
		} while(!enfOfgame);
	}
	
	
	
	public static void main(String[] args) {
		args = new String[] {"train", "100"};
		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		Test test = new Test();
		Player p1, p2;
		if (args.length == 2) {
			p1 = new HumanPlayer(Mark.X);
			if ("trained".equals(args[0])) {
				p2 = loadPlayer(Mark.O);
			} else if ("train".equals(args[0])) {
				int times = Integer.parseInt(args[1]);
				Logger.LOG_LEVEL = Logger.LEVEL_OFF;
				p2 = trainIntelligentPlayer(test, times, Mark.O);
				Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
			} else {
				p2 = new IntelligentPlayer(Mark.O);
			}
		} else {
			p1 = new HumanPlayer(Mark.X);
			p2 = new IntelligentPlayer(Mark.O);
		}
		test.setP1(p1);
		test.setP2(p2);
		test.run();
	}
	
	private static Player loadPlayer(Mark mark) {
		LMSAlgorithm algorithm;
		try {
			algorithm = PlayerIO.load();
			if (algorithm == null) {
				algorithm = new LMSAlgorithm();
			}
			return new IntelligentPlayer(algorithm, mark);
		} catch (IOException e) {
			Logger.log("Warning", "Could not load player from file." + e.getMessage(), Logger.LEVEL_WARNING);
			return new IntelligentPlayer(mark);
		}
		
	}

	private static Player trainIntelligentPlayer(Test test, int nGames, Mark mark) {
		Player intelligent = new IntelligentPlayer(mark);
		Player random = new RandomPlayer((mark == Mark.X) ? Mark.O : Mark.X);
		test.setP1(random);
		test.setP2(intelligent);
		test.run(nGames);
		return intelligent;
	}
}
