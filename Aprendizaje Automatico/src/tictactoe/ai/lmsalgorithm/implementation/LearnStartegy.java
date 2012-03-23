package tictactoe.ai.lmsalgorithm.implementation;

import java.io.Serializable;

import tictactoe.Board;
import tictactoe.Mark;

public abstract class LearnStartegy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected static final float EPS = 0.0001f;
	protected static int[][] SIDE_NON_DIAGONAL = {{ -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }};
	protected static int[][] SIDE_ALL = {{-1, -1}, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, 
		{ 1, -1 }, { 1, 0 }, { 1, 1 }};
	
	protected Board temp;
	protected float[] w, x;
	
	public LearnStartegy(int n) {
		temp = new Board();
		x = new float[n];
		w = new float[n];
		for (int i = 0; i < w.length; i++) {
			w[i] = 1;
		}
	}
	
	public void restart() {
		x = new float[x.length];
		w = new float[w.length];
	}
	
	/**
	 * Returns the xi values for this board according to the definition of each xi
	 * in this class.
	 * This methods ensures that x[0] = 1 always.
	 */
	public abstract void calcX(Board b, Mark toEval);
	
	/**
	 * Calculates Vsombero(b) from the actual x y w values.
	 */
	public float calcVAprox(Board board, Mark toEval) {
		calcX(board, toEval);
		float vAprox = 0;
		for(int i = 0; i < w.length; i++) {
			vAprox += w[i] * x[i];
		}
		return vAprox;
	}
	
	/**
	 * updates all w values doing
	 * 		wi <- wi + nu (Vtrain(b) - Vaprox(b)) xi
	 * and then normalizes all wi so that length(w) = 1:
	 */
	public void updateW(float nu, float vTrain, float vAprox) {
		for(int i = 0; i < w.length; i++) {
			w[i] = w[i] + nu * (vTrain - vAprox) * x[i];
		}
		normalize(w);
	}
	
	private void normalize(float[] w) {
		float length = 0;
		for(int i = 0; i < w.length; i++) {
			length += w[i] * w[i] ;
		}
		length = (float) Math.sqrt(length);
		if (Math.abs(length) > EPS) {
			for(int i = 0; i < w.length; i++) {
				w[i] /= length;
			}
		}
	}

	public abstract String getName();

	public float[] getW() {
		return w;
	}
}
