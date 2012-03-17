package tictactoe.ai.lmsalgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.IntelligentPlayer;
import util.Logger;

public class LMSTrainer {

	private static final float PRICE_WINNING = 100f;
	private static final float PRICE_LOOSING = -100f;
	private static final float PRICE_TIE = 10f;
	
	public static void train(Mark winner, IntelligentPlayer p, List<Board> completeGame) {
		Map<Board, Float> traningSet = new HashMap<Board, Float>();
		Board suc = completeGame.remove(0);
		if (winner == null) {
			traningSet.put(suc, PRICE_TIE);
		} else if (winner == p.getMark()) {
			traningSet.put(suc, PRICE_WINNING);
		} else {
			traningSet.put(suc, PRICE_LOOSING);
		}
		LMSAlgorithm experience = p.getExperience();
		for (Board b: completeGame) {
			traningSet.put(b, experience.evaluate(suc, p.getMark()));
			suc = b;
		}
		String logChanges = "\nprevious w: " + Arrays.toString(experience.getW());
		p.train(traningSet);
		logChanges += "\nnew w: " + Arrays.toString(experience.getW());
		Logger.log("LMS Trainter", logChanges, Logger.LEVEL_TRACE);
	}
}
