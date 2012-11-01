package aprendizajeautomatico.tictactoe.ai;

import java.io.Serializable;
import java.util.List;

import aprendizajeautomatico.tictactoe.Board;
import aprendizajeautomatico.tictactoe.ai.LMSTrainer.BoardWithEvaluation;
import aprendizajeautomatico.tictactoe.ai.lmsalgorithm.implementation.DefensiveSelectionStrategy;
import aprendizajeautomatico.tictactoe.ai.lmsalgorithm.implementation.LearnStartegy;
import aprendizajeautomatico.util.Logger;

public class LMSAlgorithm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LearnStartegy strategy;
	private float nu;
	
	public LMSAlgorithm() {
		this(new DefensiveSelectionStrategy(), 0.1f);
	}
	
	public LMSAlgorithm(LearnStartegy strategy, float nu) {
		this.nu  = nu;
		setStrategy(strategy);
		Logger.log("Intelligent Player", "Using strategy " + strategy.getName(), Logger.LEVEL_TRACE);		
	}

	public void train(List<BoardWithEvaluation> traningSet) {
		// Calculate all output values
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
	
	public void setStrategy(LearnStartegy strategy) {
		this.strategy = strategy;
	}
	
	public LearnStartegy getStrategy() {
		return strategy;
	}
	
	@Override
	public String toString() {
		String s = "LMSAlgorithm {";
		s += "nu: " + nu + ",\n";
		s += "strategy: " + strategy + "\n";
		return s + "}";
	}
}
