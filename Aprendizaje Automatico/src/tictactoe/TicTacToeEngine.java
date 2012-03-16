package tictactoe;

import tictactoe.ai.Player;
import tictactoe.ai.RandomPlayer;


public class TicTacToeEngine {
	
	private int playedTurns;
	private Game game;
	private Player current, p1, p2;
	
	public TicTacToeEngine() {
		game = new Game();
		playedTurns = 0;
	}
	
	public void random() {
		p1 = new RandomPlayer(Mark.X);
		p2 = new RandomPlayer(Mark.O);
		current = p1;
		while(!game.isGameFinished()) {
			printBoardStatus();
			current.makeMove(game);
			playedTurns++;
			nextPlayer();
		}
		printBoardStatus();
	}
	
	private Player nextPlayer() {
		if (current == p2 || current == null) {
			current = p1;
		} else {
			current = p2;
		}
		return current;
	}
	
	private void printBoardStatus() {
		if (!game.isGameFinished()) {
			System.out.println("------- Turn Count: " + playedTurns + "-------");
			System.out.println("Plays " + current);
		} else {
			System.out.println("*******************************************");
			System.out.println("Game Finished! - Final board:");
			System.out.println("Winner: " + game.getWinner() + "\n");
		}
		for (Mark[] row: game.getBoard().getMarks()) {
			for(Mark value: row) {
				String mark = (value == Mark.O) ? "0" : (value == Mark.X ? "X" : "-"); 
				System.out.print(mark + " ");
			}
			System.out.println();
		}
	}
}
