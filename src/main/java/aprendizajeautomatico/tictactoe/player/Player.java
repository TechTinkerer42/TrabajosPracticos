package aprendizajeautomatico.tictactoe.player;

import aprendizajeautomatico.tictactoe.Game;
import aprendizajeautomatico.tictactoe.Mark;
import aprendizajeautomatico.tictactoe.PlayerStats;

public interface Player {
	
	void makeMove(Game game);
	
	void notifyEndOfgame(Game game);
	
	PlayerStats getStats();

	String getName();
	
	void restart();
	
	Mark getMark();
}
