package tictactoe.ai;

import tictactoe.Game;
import tictactoe.PlayerStats;

public interface Player {
	
	void makeMove(Game game);
	
	void notifyEndOfgame(Game game);
	
	PlayerStats getStats();

	String getName();
	
	void restart();
	
}
