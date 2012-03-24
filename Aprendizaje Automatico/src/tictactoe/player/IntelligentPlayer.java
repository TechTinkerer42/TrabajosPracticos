package tictactoe.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import tictactoe.Board;
import tictactoe.Game;
import tictactoe.Mark;
import tictactoe.ai.LMSAlgorithm;
import tictactoe.ai.LMSTrainer;
import tictactoe.ai.LMSTrainer.BoardWithEvaluation;
import tictactoe.ai.Position;
import util.Logger;

public class IntelligentPlayer extends BasicPlayer {

	private LMSAlgorithm algorithm;
	private List<Board> boards;
	private boolean trainingMode;
	
	public IntelligentPlayer(Mark mark) {
		this(new LMSAlgorithm(), mark);
	}
	
	public IntelligentPlayer(LMSAlgorithm algorithm, Mark mark) {
		super(mark);
		boards = new LinkedList<Board>();
		this.algorithm = algorithm;
		trainingMode = true;
	}

	public void train(List<BoardWithEvaluation> traningSet) {
		algorithm.train(traningSet);
	}
	
	@Override
	public void makeMove(Game game) {
		Position move = selectMovement(game.getBoard());
		game.put(mark, move.row, move.column);
		if (trainingMode) {
			((LinkedList<Board>) boards).addFirst(game.getBoard().clone());
		}
	}

	@Override
	public void notifyEndOfgame(Game game) {
		super.notifyEndOfgame(game);
		if (trainingMode) {
			Logger.log("Intelligent player", "Using this last game to train my self.\n", Logger.LEVEL_TRACE);
			LMSTrainer.train(game.getWinner(), this, boards);
		}
		boards.clear();
	}
	
	/**
	 * Input: Cualquier tablero desntro del conjunto de tableros validos.
	 * Output: Cualquier movimiento dentro del conjunto de movimientos validos.
	 */
	private Position selectMovement(Board board) {
		Board temp = board.clone();
		Position best = null;
		float bestValue = 0;
		for(Position pos: calcMovements(board)) {
			temp.set(mark, pos.row, pos.column);
			float value = algorithm.evaluate(temp);
			Logger.log("Player " + mark, "Movement " + pos + " evaluated as: " + value, Logger.LEVEL_DEBUG);
			if (best == null || bestValue < value) {
				best = pos;
				bestValue = value;
			}
			temp.set(Mark.NONE, pos.row, pos.column);
		}
		Logger.log("Player " + mark, "Best Movement: " + best + "=> eval: " + bestValue, Logger.LEVEL_DEBUG);
		return best;
	}

	private Collection<Position> calcMovements(Board board) {
		Collection<Position> possibleMovemens = new ArrayList<Position>();
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				if (!board.isSet(i, j)) {
					possibleMovemens.add(new Position(i, j));
				}
			}
		}
		return possibleMovemens;
	}
	
	public LMSAlgorithm getAlgorithm() {
		return algorithm;
	}
	
	@Override
	public String getName() {
		return "Intelligent Player";
	}
	
	public void setTrainingMode(boolean trainingMode) {
		this.trainingMode = trainingMode;
	}
	
	@Override
	public void restart() {
		super.restart();
		algorithm.restart();
		boards.clear();
		trainingMode = true;
	}
}
