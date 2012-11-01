package aprendizajeautomatico.tictactoe.ai;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import aprendizajeautomatico.tictactoe.PlayerStats;
import aprendizajeautomatico.util.Logger;

public class PlayerIO {

	private static final String path = "./Aprendizaje Automatico/res";
	static {
		// create folder to store information
		new File(path).mkdirs();
	}
	
	private static String getFullPath(String file) {
		return path + "/" + file;
	}
	
	private String fileName, fileStatsName;
	private transient BufferedWriter meditionsFileWriter;
	
	public PlayerIO(String playerName) {
		fileName = playerName + ".exp";
		fileStatsName = playerName + ".log";
		try {
			meditionsFileWriter = new BufferedWriter(new FileWriter(new File(getFullPath(fileStatsName))));
		} catch (IOException e) {
			Logger.log("Error", "Could not initialize file for meditions!", Logger.LEVEL_ERROR);
		}
	}
	
	public void save(LMSAlgorithm strategy) throws IOException {
		FileOutputStream fos = new FileOutputStream(getFullPath(fileName));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(strategy);
		oos.flush();
		oos.close();
	}

	public LMSAlgorithm load() throws IOException {
		FileInputStream fis = new FileInputStream(getFullPath(fileName));
		ObjectInputStream ois = new ObjectInputStream(fis);
		LMSAlgorithm algorithm;
		try {
			algorithm = (LMSAlgorithm) ois.readObject();
		} catch(ClassNotFoundException e) {
			Logger.log("ERROR", "Class not found!" + e.getMessage(), Logger.LEVEL_ERROR);
			algorithm = null;
		}
		ois.close();
		return algorithm;
	}
	
	public void logStats(PlayerStats stats) {
		if (meditionsFileWriter != null) {
			String line = stats.getWins() + "\t" + stats.getTies() + "\t" + stats.getLosts() + "\n";
			try {
				meditionsFileWriter.append(line);
			} catch (IOException e) {
				Logger.log("Error", "Could not append line to file: " + fileStatsName + 
					". " + e.getMessage(), Logger.LEVEL_ERROR);
			}
		}
	}
	
	public void closeFiles() {
		if (meditionsFileWriter != null) {
			try {
				meditionsFileWriter.flush();
				meditionsFileWriter.close();
			} catch (IOException e) {
			}
		}
	}
}
