package tictactoe;


public class PlayerStats {

	public static final int STATUS_TIE = 0;
	public static final int STATUS_WIN = 1;
	public static final int STATUS_LOOSE = 2;
	
	private int wins, losts, ties;
	
	public PlayerStats() {
		restart();
	}
	
	public void restart() {
		wins = 0;
		losts = 0;
		ties = 0;
	}
	
	public void notifyStatus(int status) {
		switch(status) {
			case STATUS_LOOSE:
				losts++;
					break;
			case STATUS_WIN:
				wins++;
				break;
			case STATUS_TIE:
				ties++;
				break;
			default:
				throw new IllegalArgumentException("Invalid status: " + status);
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		int totalGames = wins + losts + ties;
		s += "Total Games: " + totalGames + "\n";
		float winPercent = (wins / (float) totalGames) * 100;
		s += "Wins: " + wins + " (" + winPercent + "%)\n";
		float lostPercent = (losts / (float) totalGames) * 100;
		s += "Lost: " + losts + " (" + lostPercent + "%)\n";
		float tiesPercent = 100 - winPercent - lostPercent;
		s += "Ties: " + losts + " (" + tiesPercent + "%)\n";
		return s;
	}
}
