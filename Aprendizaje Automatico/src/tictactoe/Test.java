package tictactoe;

import tictactoe.ai.HumanPlayer;
import tictactoe.ai.Player;
import tictactoe.ai.lmsalgorithm.LMSTrainer;
import util.Logger;

public class Test {

	public static void main(String[] args) {
		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		Engine ticTacToe = new Engine();
		Player p1 = getP1();
		Player p2 = getP2();
		while(true) {	// play for ever =)
			ticTacToe.startGame(p1, p2);
			Player temp = p1;
			p1 = p2;
			p2 = temp;
			System.out.println("***** Players have switched turns! *****");
		}
	}
	
	private static Player getP1() {
		return new HumanPlayer(Mark.X);
	}
	
	private static Player getP2() {
		LMSTrainer trainer = new LMSTrainer();
		return trainer.createTrainedPlayer(Mark.O);
	}
}
