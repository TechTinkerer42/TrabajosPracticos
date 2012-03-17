package tictactoe.ai.lmsalgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.IntelligentPlayer;
import tictactoe.ai.Player;
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
	
	public Player createTrainedPlayer(Mark mark) {
		IntelligentPlayer p = new IntelligentPlayer(mark);
		trainAsSecondPlayer(p);
		return p;
	}

	private void trainAsSecondPlayer(IntelligentPlayer p) {
		p.train(createTrainSet());
	}
	
	private Map<Board, Float> createTrainSet() {
		Map<Board, Float> trainSet = new HashMap<Board, Float>();
		Board b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 1, 1);
		trainSet.put(b1, 50f);
		
		b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 0, 1);
		b1.set(Mark.X, 1, 0);
		b1.set(Mark.O, 2, 0);
		trainSet.put(b1, 100f);
		
		b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 0, 1);
		b1.set(Mark.X, 1, 1);
		b1.set(Mark.O, 2, 2);
		trainSet.put(b1, 100f);
		
		b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 1, 1);
		b1.set(Mark.X, 0, 1);
		b1.set(Mark.O, 0, 2);
		b1.set(Mark.X, 2, 2);
		b1.set(Mark.O, 2, 0);
		trainSet.put(b1, 150f);
		
		b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 0, 1);
		b1.set(Mark.X, 0, 2);
		b1.set(Mark.O, 1, 1);
		b1.set(Mark.X, 2, 2);
		b1.set(Mark.O, 2, 1);
		trainSet.put(b1, 200f);
		
		return trainSet;
	}
}
