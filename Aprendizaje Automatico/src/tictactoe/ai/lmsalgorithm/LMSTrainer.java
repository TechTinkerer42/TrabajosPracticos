package tictactoe.ai.lmsalgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.IntelligentPlayer;
import util.Logger;

public class LMSTrainer {

	private static final float PRICE_WINNING = 100f;
	private static final float PRICE_LOOSING = -100f;
	private static final float PRICE_TIE = 0f;
	
	public static void train(Mark winner, IntelligentPlayer p, List<Board> completeGame) {
		Map<Board, Float> traningSet = new HashMap<Board, Float>();
		Board suc = completeGame.remove(0);
		float value;
		if (winner == null) {
			value = PRICE_TIE;
		} else if (winner == p.getMark()) {
			value = PRICE_WINNING;
		} else {
			value = PRICE_LOOSING;
		}
		traningSet.put(suc, value);
		for (Board b: completeGame) {
			value *= 0.8f;
			traningSet.put(b, value);
			suc = b;
		}
		LMSAlgorithm experience = p.getExperience();
		String logChanges = "\nprevious w: " + Arrays.toString(experience.getW());
		p.train(traningSet);
		logChanges += "\nnew w: " + Arrays.toString(experience.getW());
		Logger.log("LMS Trainter", logChanges, Logger.LEVEL_TRACE);
	}
	
	public static void train() {
		IntelligentPlayer p = new IntelligentPlayer(Mark.O);
		List<Board> boards = new LinkedList<Board>();
		
		Board b = new Board();
		
		b.set(Mark.X, 0, 0);
		boards.add(b.clone());
		
		b.set(Mark.O, 1, 1);
		boards.add(b.clone());
		
		b.set(Mark.X, 0, 1);
		boards.add(b.clone());
		
		b.set(Mark.O, 1, 2);
		boards.add(b.clone());
		
		b.set(Mark.X, 0, 2);
		boards.add(b.clone());
		//revert
		LinkedList<Board> reverted = new LinkedList<Board>();
		for (Board bAux: boards) {
			reverted.addFirst(bAux);
		}
		train(Mark.X, p, reverted);
	}
}
