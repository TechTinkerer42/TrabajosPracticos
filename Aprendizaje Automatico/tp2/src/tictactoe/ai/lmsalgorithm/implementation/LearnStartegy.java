package tictactoe.ai.lmsalgorithm.implementation;

import java.io.Serializable;

import tictactoe.Board;

public abstract class LearnStartegy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected static final float EPS = 0.0001f;
	
	protected Board temp;
	protected float[] w, x;
	
	public LearnStartegy(int n) {
		temp = new Board();
		x = new float[n];
		w = new float[n + 1];
	}
	
	public void restart() {
		throw new IllegalArgumentException();
	}
	
	/**
	 * Returns the xi values for this board according to the definition of each xi
	 * in this class.
	 */
	public abstract void calcX(Board b);
	
	/**
	 * Calculates Vsombero(b) from the actual x y w values.
	 */
	public float calcVAprox(Board board) {
		calcX(board);
		float vAprox = w[0];
		for(int i = 0; i < x.length; i++) {
			vAprox += w[i + 1] * x[i];
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
			float xi = ((i == 0) ? 1 : x[i - 1]);	// TODO: x0 == ????
			w[i] = w[i] + nu * (vTrain - vAprox) * xi;
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
