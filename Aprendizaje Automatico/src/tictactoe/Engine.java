package tictactoe;

import tictactoe.ai.Player;


public class Engine {
	
	private int playedTurns;
	private Game game;
	private Player current, p1, p2;
	
	public Engine() {
		game = new Game();
	}
	
	public void startGame(Player p1, Player p2) {
		restart();
		this.p1 = p1;
		this.p2 = p2;
		current = p1;
		play();
		p1.notifyEndOfgame(game);
		p2.notifyEndOfgame(game);
	}
	
	private void restart() {
		playedTurns = 0;
		game.start();
		current = null;
	}
	
	private void play() {
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
			String winner = (game.getWinner() == null) ? "TIE" : game.getWinner().toString();
			System.out.println("Winner: " + winner + "\n");
		}
		System.out.print("\n    ");
		for(int i = 0; i < Board.SIZE; i++) {
			System.out.print(i + " ");
		}
		System.out.println("\n   ------");
		int rowNumber = 0;
		for (Mark[] row: game.getBoard().getMarks()) {
			System.out.print(rowNumber++ + " | ");
			for(Mark value: row) {
				String mark = (value == Mark.O) ? "0" : (value == Mark.X ? "X" : "-"); 
				System.out.print(mark + " ");
			}
			System.out.println();
		}
	}
}
