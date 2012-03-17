package tictactoe.ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tictactoe.Board;
import tictactoe.Game;
import tictactoe.Mark;

public class HumanPlayer extends BasicPlayer {

	private BufferedReader input;
	
	public HumanPlayer(Mark mark) {
		super(mark);
		InputStreamReader converter = new InputStreamReader(System.in);
		input = new BufferedReader(converter);
	}

	@Override
	public void makeMove(Game game) {
		boolean validMovement = false;
		while(!validMovement) {
			Movement movement = readMovement();
			if (validMovement(movement)) {
				if (!game.isSet(movement.row, movement.column)) {
					game.put(mark, movement.row, movement.column);
					validMovement = true;
				} else {
					System.out.println("Cell is already set!");
				}
			} else {
				System.out.println("Invalid selection!");
			}
		}
	}
	
	private boolean validMovement(Movement movement) {
		return 0 <= movement.row && movement.row < Board.SIZE && 
			0 <= movement.column && movement.column < Board.SIZE;
	}
	
	private Movement readMovement() {
		boolean validValues = false;
		Movement movement = new Movement();
		String line;
		while (!validValues) {
			try {
				System.out.println("Insert command:");
				line = input.readLine();
				String[] values = line.split(",");
				if (values != null && values.length == 2) {
					movement.row = Integer.parseInt(values[0]);
					movement.column = Integer.parseInt(values[1]);
					validValues = true;
				} else {
					System.out.println("Command format: row, column");
				}
			} catch (IOException e) {
				System.out.println("Could not grab user input!");
			} catch (NumberFormatException e) {
				System.out.println("row and columns must be separated by a comma and be positive integers between 0 and 2 inclusive");
			}
		}
		return movement;
	}

	@Override
	public String getName() {
		return "Human Player";
	}
	
	private static class Movement {
		int row;
		int column;
	}

}
