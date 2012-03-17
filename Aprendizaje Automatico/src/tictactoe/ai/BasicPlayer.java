package tictactoe.ai;

import java.util.Random;

import tictactoe.Mark;

public abstract class BasicPlayer implements Player {

	protected Random random = new Random();
	protected Mark mark;

	
	public BasicPlayer(Mark mark) {
		this.mark = mark;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public String getName() {
		return "Basic Player";
	}

	@Override
	public String toString() {
		return getName() + ": " + mark;
	}
}
