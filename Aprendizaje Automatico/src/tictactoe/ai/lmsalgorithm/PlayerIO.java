package tictactoe.ai.lmsalgorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Logger;

public class PlayerIO {

	private static final String fileName = "experience.exp";
	private static final String path = "./Aprendizaje Automatico/res";

	public static void save(LMSAlgorithm strategy) throws IOException {
		FileOutputStream fos = new FileOutputStream(getFullPath());
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(strategy);
		oos.flush();
		oos.close();
	}

	public static LMSAlgorithm load() throws IOException {
		FileInputStream fis = new FileInputStream(getFullPath());
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

	private static String getFullPath() {
		return path + "/" + fileName;
	}
}
