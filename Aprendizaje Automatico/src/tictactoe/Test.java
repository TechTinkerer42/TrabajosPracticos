package tictactoe;

import tictactoe.ai.HumanPlayer;
import tictactoe.ai.Player;
import tictactoe.ai.lmsalgorithm.LMSTrainer;
import util.Logger;

public class Test {

	public static void main(String[] args) {
		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_DEBUG;
		Engine ticTacToe = new Engine();
		ticTacToe.startGame(getP1(), getP2());
	}
	
	private static Player getP1() {
		return new HumanPlayer(Mark.X);
	}
	
	private static Player getP2() {
		LMSTrainer trainer = new LMSTrainer();
		return trainer.createTrainedPlayer(Mark.O);
	}
}
