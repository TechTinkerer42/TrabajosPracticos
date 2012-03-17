package tictactoe.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import tictactoe.Board;
import tictactoe.Game;
import tictactoe.Mark;
import tictactoe.Movement;
import tictactoe.ai.lmsalgorithm.LMSAlgorithm;
import util.Logger;

public class IntelligentPlayer extends BasicPlayer {

	private LMSAlgorithm experience;
	
	public IntelligentPlayer(Mark mark) {
		super(mark);
		experience = new LMSAlgorithm();
	}

	public void train(Map<Board, Float> traningSet) {
		experience.train(traningSet, mark);
	}
	
	@Override
	public void makeMove(Game game) {
		Movement move = selectMovement(game.getBoard());
		game.put(mark, move.row, move.column);
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
			float value = experience.evaluate(temp, mark);
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
	
}
