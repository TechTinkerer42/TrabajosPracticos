package tictactoe;

import java.text.DecimalFormat;


public class PlayerStats {

	private int wins, losts, ties;
	
	public PlayerStats() {
		restart();
	}
	
	public void restart() {
		wins = 0;
		losts = 0;
		ties = 0;
	}
	
	public void notifyStatus(Mark mark, Mark winner) {
		if (winner == mark) {
			wins++;
		} else if (winner == Mark.NONE) {
			ties++;
		} else {
			losts++;
		}
	}
	
	@Override
	public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
		String s = "";
		int totalGames = wins + losts + ties;
		s += "Total Games: " + totalGames + "\n";
		float winPercent = (wins / (float) totalGames) * 100;
		s += "Wins: " + wins + " (" + df.format(winPercent) + "%)\n";
		float lostPercent = (losts / (float) totalGames) * 100;
		s += "Lost: " + losts + " (" + df.format(lostPercent) + "%)\n";
		float tiesPercent = 100 - winPercent - lostPercent;
		s += "Ties: " + ties + " (" + df.format(tiesPercent) + "%)\n";
		return s;
	}

	public int getWins() {
		return wins;
	}
	
	public int getLosts() {
		return losts;
	}
	
	public int getTies() {
		return ties;
	}
}
