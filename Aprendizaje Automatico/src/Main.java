import java.io.IOException;

import tictactoe.Mark;
import tictactoe.Test;
import tictactoe.ai.HumanPlayer;
import tictactoe.ai.IntelligentPlayer;
import tictactoe.ai.Player;
import tictactoe.ai.RandomPlayer;
import tictactoe.ai.lmsalgorithm.LMSAlgorithm;
import tictactoe.ai.lmsalgorithm.PlayerIO;
import util.Logger;


public class Main {

	public static void main(String[] args) {
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
		test.getEngine().setPrintStatus(true);
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
		test.setPrintStatus(false);
		System.out.println("Training the player....");
		test.run(nGames);
		System.out.println(">>>>>>>>>>>> Intelligent player final stats <<<<<<<<<<<");
		System.out.println(intelligent);
		return intelligent;
	}
}
