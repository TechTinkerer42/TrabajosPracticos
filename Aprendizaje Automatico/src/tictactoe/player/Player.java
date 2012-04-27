package tictactoe.player;

import tictactoe.Game;
import tictactoe.Mark;
import tictactoe.PlayerStats;

public interface Player {
	
	void makeMove(Game game);
	
	void notifyEndOfgame(Game game);
	
	PlayerStats getStats();

	String getName();
	
	void restart();
	
	Mark getMark();
}
