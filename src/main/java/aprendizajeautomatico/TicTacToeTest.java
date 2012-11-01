package aprendizajeautomatico;

import aprendizajeautomatico.tictactoe.Engine;
import aprendizajeautomatico.tictactoe.Mark;
import aprendizajeautomatico.tictactoe.player.HumanPlayer;
import aprendizajeautomatico.tictactoe.player.IntelligentPlayer;
import aprendizajeautomatico.tictactoe.player.Player;
import aprendizajeautomatico.tictactoe.player.RandomPlayer;
import aprendizajeautomatico.util.Logger;

public class TicTacToeTest {
	
	public static void main(String[] args) {
		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		Player p1 = new HumanPlayer(Mark.X);
		Player p2 = null;
		if (args != null && args.length == 2) {
			if ("trained".equals(args[0])) {
				try {
					int  ngames = Integer.parseInt(args[1]);
					p2 = trainIntelligentPlayer(ngames, Mark.O);
				} catch(NumberFormatException e) {
					System.out.println("Invalid total number of games!");
					return;
				}
			}
		}
		if (p2 == null) {
			p2 = new IntelligentPlayer(Mark.O);	
		}
		new Engine(p1, p2).run();
	}

	private static Player trainIntelligentPlayer(int nGames, Mark mark) {
		Player intelligent = new IntelligentPlayer(mark);
		Player random = new RandomPlayer((mark == Mark.X) ? Mark.O : Mark.X);
		Engine engine = new Engine(random, intelligent);
		engine.setPrintStatus(false);
		System.out.println("Training the player....");
		engine.run(nGames);
		System.out.println(">>>>>>>>>>>> Intelligent player final stats <<<<<<<<<<<");
		((IntelligentPlayer) intelligent).flushIO();
		System.out.println(intelligent);
		return intelligent;
	}
}
