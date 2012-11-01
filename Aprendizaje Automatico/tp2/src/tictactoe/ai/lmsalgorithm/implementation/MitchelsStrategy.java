package tictactoe.ai.lmsalgorithm.implementation;

import tictactoe.Board;
import tictactoe.Mark;

public class MitchelsStrategy extends LearnStartegy {

	private static final long serialVersionUID = 1L;

	public MitchelsStrategy() {
		super(6);
	}

	@Override
	public void calcX(Board board) {
		x[0] = count(board, Mark.O);
		x[1] = count(board, Mark.X);
		x[2] = countNumberInLine(board, Mark.O, 2);
		x[3] = countNumberInLine(board, Mark.X, 2);
		x[4] = countNumberInLine(board, Mark.O, 3);
		x[5] = countNumberInLine(board, Mark.X, 3);
	}

	public int count(Board board, Mark mark) {
		int count = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				if (board.get(i, j) == mark) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public String getName() {
		return "Mitchel's Strategy";
	}

	private int countNumberInLine(Board board, Mark toEval, int expected) {
		return countNumberInRow(board, toEval, expected) +
			countNumberInColumn(board, toEval, expected) +
			countNumberInDiagonals(board, toEval, expected); 
	}
	
	private int countNumberInRow(Board board, Mark toEval, int expected) {
		int count = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			int marksInRow = 0;
			for (int j = 0; j < Board.SIZE; j++) {
				if (board.get(i, j) == toEval) {
					marksInRow++;
				}
			}
			if (marksInRow == expected) {
				count++;
			}
		}
		return count;
	}

	private int countNumberInColumn(Board board, Mark toEval, int expected) {
		int count = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			int marksInColumn = 0;
			for (int j = 0; j < Board.SIZE; j++) {
				if (board.get(j, i) == toEval) { 
					marksInColumn++;
				}
			}
			if (marksInColumn == expected) {
				count++;
			}
		}
		return count;
	}
	
	private int countNumberInDiagonals(Board board, Mark toEval, int expected) {
		int[][][] diagonals = {{{0,0} , {1,1}, {2,2}}, {{0,2} , {1,1}, {2,0}}};
		int count = 0;
		for(int[][] diagonal: diagonals) {
			int marksInDiagonal = 0;
			for (int i = 0; i < diagonal.length; i++) {
				Mark mark = board.get(diagonal[i][0], diagonal[i][1]); 
				if (mark == toEval) {
					marksInDiagonal++;
				}
			}
			if (marksInDiagonal == expected) {
				count++;
			}
		}
		return count;
	}
}
