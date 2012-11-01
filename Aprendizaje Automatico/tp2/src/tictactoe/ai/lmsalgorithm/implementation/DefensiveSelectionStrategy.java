package tictactoe.ai.lmsalgorithm.implementation;

import java.util.Arrays;

import tictactoe.Board;
import tictactoe.Mark;
import util.Logger;

public class DefensiveSelectionStrategy extends LearnStartegy {

	private static final long serialVersionUID = 1L;

	public DefensiveSelectionStrategy() {
		super(3);
	}
	
	@Override
	public String getName() {
		return "Defensive Strategy";
	}

	@Override
	public void calcX(Board b) {
		x[0] = countFilledLines(b, Mark.O);
//		x[0] = countFilledLines(b, Mark.X);
		x[1] = countBlocks(b, Mark.O);
//		x[1] = countBlocks(b, Mark.X);
		x[2] = (b.get(1, 1) == Mark.O) ? 1 : 0;
//		x[2] = (b.get(1, 1) == Mark.X) ? 1 : 0;
		Logger.log("x values", Arrays.toString(x), Logger.LEVEL_DEBUG);
	}

	private float countFilledLines(Board b, Mark toEval) {
		return countRows(b, toEval) + countColumns(b, toEval) + countDiagonals(b, toEval);
	}
	
	// x1
	
	private int countRows(Board b, Mark toEval) {
		int lines = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			boolean isLine = true;
			for (int j = 0; j < Board.SIZE && isLine; j++) {
				if (b.get(i, j) != toEval) {
					isLine = false;
				}
			}
			if (isLine) {
				lines++;
			}
		}
		return lines;
	}
	
	private int countColumns(Board b, Mark toEval) {
		int columns = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			boolean isColumn = true;
			for (int j = 0; j < Board.SIZE && isColumn; j++) {
				if (b.get(j, i) != toEval) {
					isColumn = false;
				}
			}
			if (isColumn) {
				columns++;
			}
		}
		return columns;
	}

	private int countDiagonals(Board b, Mark toEval) {
		int diagonals = 0;
		if (b.get(0, 0) == toEval && b.get(0, 0) == b.get(1, 1) && b.get(1, 1) == b.get(2, 2)) {
			diagonals++;
		}
		if (b.get(0, 2) == toEval && b.get(0, 2) == b.get(1, 1) && b.get(1, 1) == b.get(2, 0)) {
			diagonals++;
		}
		return diagonals;
	}

	
	// x2
	
	private float countBlocks(Board b, Mark toEval) {
		return countBlocksRows(b, toEval) + countBlocksColumns(b, toEval) + countBlockDiagonals(b, toEval);
	}

	private float countBlocksRows(Board b, Mark toEval) {
		int blocks = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			int totalMarks = 0;
			int otherMarks = 0;
			for (int j = 0; j < Board.SIZE; j++) {
				Mark mark = b.get(i, j); 
				if (mark != Mark.NONE) {
					if (mark != toEval) {
						otherMarks++;
					}
					totalMarks++;
				}
			}
			if (otherMarks == 2 && totalMarks == 3) {
				blocks++;
			}
		}
		return blocks;
	}
	
	private float countBlocksColumns(Board b, Mark toEval) {
		int blocks = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			int totalMarks = 0;
			int otherMarks = 0;
			for (int j = 0; j < Board.SIZE; j++) {
				Mark mark = b.get(j, i); 
				if (mark != Mark.NONE) {
					if (mark != toEval) { 
						otherMarks++;
					}
					totalMarks++;
				}
			}
			if (otherMarks == 2 && totalMarks == 3) {
				blocks++;
			}
		}
		return blocks;
	}

	private int countBlockDiagonals(Board b, Mark toEval) {
		int[][][] diagonals = {{{0,0} , {1,1}, {2,2}}, {{0,2} , {1,1}, {2,0}}};
		int totalBlocks = 0;
		for(int[][] diagonal: diagonals) {
			int blocks = 0;
			int totalMarks = 0;
			for (int i = 0; i < diagonal.length; i++) {
				Mark mark = b.get(diagonal[i][0], diagonal[i][1]); 
				if (mark != Mark.NONE) {
					if (mark != toEval) {
						blocks++;
					}
					totalMarks++;
				}
			}
			if (blocks == 2 && totalMarks == 3) {
				totalBlocks++;
			}
		}
		return totalBlocks;
	}
}
