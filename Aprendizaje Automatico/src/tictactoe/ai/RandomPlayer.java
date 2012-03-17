package tictactoe.ai;

import tictactoe.Game;
import tictactoe.Mark;

public class RandomPlayer extends BasicPlayer {

	public RandomPlayer(Mark mark) {
		super(mark);
	}
	
	@Override
	public void makeMove(Game game) {
		boolean moved = false;
		while (!moved) {
			int row = random.nextInt(3);
			int col = random.nextInt(3);
			if (!game.isSet(row, col)) {
				game.put(mark, row, col);
				moved = true;
				System.out.println("\n" + this + " => Set: (" + row + ", " + col + ")");
			}
		}
	}
	
	@Override
	public String getName() {
		return "Random Player";
	}
}
