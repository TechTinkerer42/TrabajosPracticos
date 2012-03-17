package tictactoe.ai;

import tictactoe.Game;
import tictactoe.Mark;

public interface Player {
	
	void makeMove(Game game);
	
	void notifyWinner(Mark winner);
}
