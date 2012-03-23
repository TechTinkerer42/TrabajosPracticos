package tictactoe.ai.lmsalgorithm;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.lmsalgorithm.implementation.DefensiveSelectionStrategy;
import tictactoe.ai.lmsalgorithm.implementation.LearnStartegy;
import util.Logger;

public class LMSAlgorithm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LearnStartegy strategy;
	private float nu;
	
	public LMSAlgorithm() {
		this(new DefensiveSelectionStrategy());
	}
	
	public LMSAlgorithm(LearnStartegy strategy) {
		this.strategy = strategy;
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
	
	public void restart() {
		strategy.restart();
	}
}
