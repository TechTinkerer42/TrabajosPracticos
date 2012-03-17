package tictactoe.ai.lmsalgorithm;

import java.util.Map;
import java.util.Map.Entry;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.lmsalgorithm.implementation.DefensiveSelectionStrategy;
import tictactoe.ai.lmsalgorithm.implementation.LearnStartegy;
import util.Logger;

public class LMSAlgorithm {
	
	private LearnStartegy strategy;
	private float nu;
	
	public LMSAlgorithm() {
//		strategy = new ClosestSelectionBoardAnalizer();
		strategy = new DefensiveSelectionStrategy();
		nu  = 0.1f;
		Logger.log("Intelligent Player", "Using strategy " + strategy.getName(), Logger.LEVEL_TRACE);
	}

	public void train(Map<Board, Float> traningSet, Mark toEval) {
		for (Entry<Board, Float> entry: traningSet.entrySet()) {
			float vAprox = strategy.calcVAprox(entry.getKey(), toEval);
			strategy.updateW(nu, entry.getValue(), vAprox);
		}
	}
	
	public float evaluate(Board board, Mark toEval) {
		return strategy.calcVAprox(board, toEval);
	}

	public float[] getW() {
		return strategy.getW();
	}
}
