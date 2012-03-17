package tictactoe.ai.lmsalgorithm;

import java.util.HashMap;
import java.util.Map;

import tictactoe.Board;
import tictactoe.Mark;
import tictactoe.ai.IntelligentPlayer;
import tictactoe.ai.Player;

public class LMSTrainer {

	public Player createTrainedPlayer(Mark mark) {
		IntelligentPlayer p = new IntelligentPlayer(mark);
		train(p);
		return p;
	}

	private void train(IntelligentPlayer p) {
		p.train(createTrainSet());
	}
	
	private Map<Board, Float> createTrainSet() {
		Map<Board, Float> trainSet = new HashMap<Board, Float>();
		Board b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 1, 1);
		trainSet.put(b1, 50f);
		
		b1 = new Board();
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.O, 1, 1);
		b1.set(Mark.X, 0, 2);
		b1.set(Mark.O, 0, 1);
		trainSet.put(b1, 100f);
		
		b1 = new Board();
		b1.set(Mark.O, 0, 1);
		b1.set(Mark.O, 1, 1);
		b1.set(Mark.O, 2, 1);
		b1.set(Mark.X, 0, 0);
		b1.set(Mark.X, 1, 2);
		trainSet.put(b1, 100f);
		
		return trainSet;
	}
}
