package tictactoe.ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tictactoe.Board;
import tictactoe.Game;
import tictactoe.Mark;
import tictactoe.Movement;
import tictactoe.ai.lmsalgorithm.PlayerIO;
import tictactoe.ai.lmsalgorithm.LMSAlgorithm;
import tictactoe.ai.lmsalgorithm.LMSTrainer;
import util.Logger;

public class IntelligentPlayer extends BasicPlayer {

	private LMSAlgorithm algorithm;
	private List<Board> boards;
	
	public IntelligentPlayer(Mark mark) {
		this(new LMSAlgorithm(), mark);
	}
	
	public IntelligentPlayer(LMSAlgorithm algorithm, Mark mark) {
		super(mark);
		boards = new LinkedList<Board>();
		this.algorithm = algorithm;
	}

	public void train(Map<Board, Float> traningSet) {
		algorithm.train(traningSet, mark);
	}
	
	@Override
	public void makeMove(Game game) {
		Movement move = selectMovement(game.getBoard());
		game.put(mark, move.row, move.column);
		((LinkedList<Board>) boards).addFirst(game.getBoard().clone());
	}

	@Override
	public void notifyEndOfgame(Game game) {
		super.notifyEndOfgame(game);
		Logger.log("Intelligent player", "Using this last game to train my self.\n", Logger.LEVEL_TRACE);
		LMSTrainer.train(game.getWinner(), this, boards);
		try {
			PlayerIO.save(algorithm);
		} catch (IOException e) {
			Logger.log("Error", "Could not save player experience", Logger.LEVEL_ERROR);
		}
	}
	
	/**
	 * Input: Cualquier tablero desntro del conjunto de tableros validos.
	 * Output: Cualquier movimiento dentro del conjunto de movimientos validos.
	 */
	private Movement selectMovement(Board board) {
		Board temp = board.clone();
		Movement best = null;
		float bestValue = 0;
		for(Movement movement: calcMovements(board)) {
			temp.set(mark, movement.row, movement.column);
			float value = algorithm.evaluate(temp, mark);
			Logger.log("Player " + mark, "Movement " + movement + " evaluated as: " + value, Logger.LEVEL_DEBUG);
			if (best == null || bestValue < value) {
				best = movement;
				bestValue = value;
			}
			temp.set(Mark.NONE, movement.row, movement.column);
		}
		Logger.log("Player " + mark, "Best Movement: " + best + "=> eval: " + bestValue, Logger.LEVEL_DEBUG);
		return best;
	}

	private Collection<Movement> calcMovements(Board board) {
		Collection<Movement> possibleMovemens = new ArrayList<Movement>();
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				if (!board.isSet(i, j)) {
					possibleMovemens.add(new Movement(i, j));
				}
			}
		}
		return possibleMovemens;
	}
	
	public LMSAlgorithm getExperience() {
		return algorithm;
	}
	
	@Override
	public String getName() {
		return "Intelligent Player";
	}
}
