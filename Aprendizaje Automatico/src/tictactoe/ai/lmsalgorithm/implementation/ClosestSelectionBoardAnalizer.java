package tictactoe.ai.lmsalgorithm.implementation;

import tictactoe.Board;
import tictactoe.Mark;

public class ClosestSelectionBoardAnalizer extends LearnStartegy {
			
	public ClosestSelectionBoardAnalizer() {
		super(3);
	}

	@Override
	public void calcX(Board b, Mark toEval) {
		x[0] = 1;	// always has to be 1
		x[1] = evalPairs(b, toEval, true);	// toEval adyacent equal pairs
		x[2] = evalPairs(b, toEval, false);	// opposite of toEval adyancent pairs
	}

	private float evalPairs(Board b, Mark toEval, boolean compareEquals) {
		temp.setTo(b);
		int pairs = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				Mark mark = b.get(i, j);
				if (mark == Mark.NONE) {
					continue;
				}
				if ((compareEquals && mark == toEval) || (!compareEquals && mark != toEval)) {
					pairs += countAdyanetPairs(toEval, i, j);
					temp.set(Mark.NONE, i, j);
				}
			}
		}
		return pairs;
	}

	private int countAdyanetPairs(Mark toEval, int row, int col) {
		if (isBorder(row, col)) {
			return countAdyacentPairs(toEval, row, col, SIDE_NON_DIAGONAL);
		} else {
			return countAdyacentPairs(toEval, row, col, SIDE_ALL);
		}
	}
	
	private boolean isBorder(int row, int col) {
		return row == 0 || col == 0 || row == Board.SIZE - 1 || col == Board.SIZE - 1;
	}

	private int countAdyacentPairs(Mark toEval, int row, int col, int[][] sides) {
		int pairs = 0;
		for (int i = 0; i < sides.length; i++) {
			int evalRow = row + sides[i][0];
			int evalCol = col + sides[i][1];
			Mark mark = temp.get(evalRow, evalCol);
			if (mark == null) {
				continue;
			}
			if (mark == toEval) {
				pairs++;
			}
		}
		return pairs;
	}
}
