package tictactoe.ai;

import java.io.Serializable;
import java.util.List;

import tictactoe.Board;
import tictactoe.ai.LMSTrainer.BoardWithEvaluation;
import tictactoe.ai.lmsalgorithm.implementation.DefensiveSelectionStrategy;
import tictactoe.ai.lmsalgorithm.implementation.LearnStartegy;
import util.Logger;

public class LMSAlgorithm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LearnStartegy strategy;
	private float nu;
	
	public LMSAlgorithm() {
//		this(new MitchelsStrategy(), 0.1f);
		this(new DefensiveSelectionStrategy(), 0.1f);
	}
	
	public LMSAlgorithm(LearnStartegy strategy, float nu) {
		this.strategy = strategy;
		this.nu  = nu;
		Logger.log("Intelligent Player", "Using strategy " + strategy.getName(), Logger.LEVEL_TRACE);		
	}

	public void train(List<BoardWithEvaluation> traningSet) {
		for (BoardWithEvaluation boardWithEval: traningSet) {
			float vAprox = strategy.calcVAprox(boardWithEval.board);
			strategy.updateW(nu, boardWithEval.value, vAprox);
		}
	}
	
	public float evaluate(Board board) {
		return strategy.calcVAprox(board);
	}

	public float[] getW() {
		return strategy.getW();
	}
	
	public void restart() {
		strategy.restart();
	}
}
