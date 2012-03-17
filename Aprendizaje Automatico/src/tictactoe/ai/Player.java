package tictactoe.ai;

import tictactoe.Game;

public interface Player {
	
	void makeMove(Game game);
	
	void notifyEndOfgame(Game game);
	
}
