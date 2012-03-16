package tictactoe.ai;

import java.util.Random;

import tictactoe.Game;
import tictactoe.Mark;

public class RandomPlayer implements Player {

	private Random random = new Random();
	private Mark mark;
	
	public RandomPlayer(Mark mark) {
		this.mark = mark;
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
	
	public Mark getMark() {
		return mark;
	}

	@Override
	public String toString() {
		return "Player: " + mark;
	}
}
