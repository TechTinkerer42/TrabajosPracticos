package tictactoe;

import tictactoe.ai.HumanPlayer;
import tictactoe.ai.Player;
import tictactoe.ai.RandomPlayer;

public class Test {

	public static void main(String[] args) {
		Player p1 = new HumanPlayer(Mark.X);
		Player p2 = new RandomPlayer(Mark.O);
		new Engine().startGame(p1, p2);
	}
	
}
