package tictactoe;

import tictactoe.player.Player;
import util.Logger;


public class Engine {
	
	private static final int FOR_EVER = -1;
	
	private int playedTurns;
	private Game game;
	private Player current, p1, p2;
	private boolean printStatus;
	
	public Engine(Player p1, Player p2) {
		game = new Game();
		printStatus = true;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void setPrintStatus(boolean printStatus) {
		this.printStatus = printStatus;
	}
	
	public void run() {
		run(FOR_EVER);
	}
	
	public void run(int nGames) {
		boolean endOfgame = false;
		do {
			playOneGame();
			switchPlayers();
			if (nGames != FOR_EVER) {
				nGames--;
				endOfgame = nGames < 0;
			}
			if (printStatus) {
				System.out.println("***** Game stats ******");
				System.out.println(p1);
				System.out.println(p2);
			}
		} while(!endOfgame);
	}
	
	private void switchPlayers() {
		Player temp = p1;
		p1 = p2;
		p2 = temp;
		Logger.log("End of Turn", "***** Players have switched turns! *****", Logger.LEVEL_TRACE);
	}
		
	private void playOneGame() {
		restart();
		while(!game.isGameFinished()) {
			printBoardStatus();
			current.makeMove(game);
			playedTurns++;
			current = (current == p2) ? p1 : p2;
		}
		printBoardStatus();
		p1.notifyEndOfgame(game);
		p2.notifyEndOfgame(game);
	}
	
	private void restart() {
		playedTurns = 0;
		game.restart();
		current = p1;
	}
	
	private void printBoardStatus() {
		if (!printStatus) {
			return;
		}
		if (!game.isGameFinished()) {
			System.out.println("------- Turn Count: " + playedTurns + "-------");
			System.out.println("Plays " + current.getName());
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
