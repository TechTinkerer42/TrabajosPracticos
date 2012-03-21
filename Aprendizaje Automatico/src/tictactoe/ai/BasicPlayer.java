package tictactoe.ai;

import java.util.Random;

import tictactoe.Game;
import tictactoe.Mark;
import tictactoe.PlayerStats;

public abstract class BasicPlayer implements Player {

	protected Random random = new Random();
	protected Mark mark;
	protected PlayerStats stats;

	
	public BasicPlayer(Mark mark) {
		this.mark = mark;
		stats = new PlayerStats();
	}
	
	public Mark getMark() {
		return mark;
	}
	
	@Override
	public String getName() {
		return "Basic Player";
	}

	@Override
	public String toString() {
		String s = getName() + ": " + mark + "\n";
		s += stats.toString() + "\n";
		return s;
	}
	
	@Override
	public void notifyEndOfgame(Game game) {
		Mark winner = game.getWinner();
		if (winner == mark) {
			stats.notifyStatus(PlayerStats.STATUS_WIN);
		} else if (winner == Mark.NONE) {
			stats.notifyStatus(PlayerStats.STATUS_TIE);
		} else {
			stats.notifyStatus(PlayerStats.STATUS_LOOSE);
		}
	}
	
	@Override
	public PlayerStats getStats() {
		return stats;
	}
}
