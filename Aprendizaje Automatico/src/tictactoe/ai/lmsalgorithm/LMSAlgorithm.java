package tictactoe.ai.lmsalgorithm;

import java.util.Map;
import java.util.Map.Entry;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.lmsalgorithm.implementation.ClosestSelectionBoardAnalizer;
import tictactoe.ai.lmsalgorithm.implementation.LearnStartegy;

public class LMSAlgorithm {
	
	private LearnStartegy strategy;
	private float nu;
	
	public LMSAlgorithm() {
		strategy = new ClosestSelectionBoardAnalizer();
		nu  = 0.1f;
	}
	
	public float evaluate(Board board, Mark toEval) {
		return strategy.evauate(board, toEval);
	}
	
	public void train(Mark toEval, Map<Board, Float> traningSet) {
		int a;
		for (Entry<Board, Float> entry: traningSet.entrySet()) {
			strategy.calcX(entry.getKey(), toEval);
			float vAprox = strategy.calcVAprox(entry.getKey(), toEval);
			strategy.updateW(nu, entry.getValue(), vAprox);
		}
	}
	
}
