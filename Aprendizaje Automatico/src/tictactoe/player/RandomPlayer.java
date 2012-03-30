package tictactoe.player;

import java.util.LinkedList;
import java.util.List;

import tictactoe.Game;
import tictactoe.Mark;
import tictactoe.ai.Position;

public class RandomPlayer extends BasicPlayer {

	public RandomPlayer(Mark mark) {
		super(mark);
	}

	@Override
	public void makeMove(Game game) {
		List<Position> movements = new LinkedList<Position>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!game.getBoard().isSet(i, j)) {
					movements.add(new Position(i, j));
				}
			}
		}
		if (movements.isEmpty()) {
			throw new IllegalStateException("No movements available!");
		}
		Position mov = movements.get(random.nextInt(movements.size()));
		game.put(mark, mov.row, mov.column);
	}

	@Override
	public String getName() {
		return "Random Player";
	}
}
