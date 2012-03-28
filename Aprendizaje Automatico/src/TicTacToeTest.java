import tictactoe.Engine;
import tictactoe.Mark;
import tictactoe.player.HumanPlayer;
import tictactoe.player.IntelligentPlayer;
import tictactoe.player.Player;
import tictactoe.player.RandomPlayer;
import util.Logger;


public class TicTacToeTest {

	public static void main(String[] args) {
		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		Player p1 = new HumanPlayer(Mark.X);
//		Player p2 = new IntelligentPlayer(Mark.O);
		Player p2 = trainIntelligentPlayer(2000, Mark.O);
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
		System.out.println(intelligent);
		((IntelligentPlayer) intelligent).flushIO();
		return intelligent;
	}
}
