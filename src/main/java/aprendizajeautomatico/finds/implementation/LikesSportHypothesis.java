package aprendizajeautomatico.finds.implementation;

import aprendizajeautomatico.finds.Hypothesis;
import aprendizajeautomatico.finds.HypothesisField;

public class LikesSportHypothesis extends Hypothesis {

	public LikesSportHypothesis() {
	}
	
	public LikesSportHypothesis(String[] values, boolean value) {
		super(values, value);
	}
	
	@Override
	protected void initializeFields() {
		fields = new HypothesisField[6];
		fields[0] = LikesSportHypothesysFactory.getHypothesis(LikesSportHypothesysFactory.FIELD_SKY);
		fields[1] = LikesSportHypothesysFactory.getHypothesis(LikesSportHypothesysFactory.FIELD_AIR);
		fields[2] = LikesSportHypothesysFactory.getHypothesis(LikesSportHypothesysFactory.FIELD_HUMIDITY);
		fields[3] = LikesSportHypothesysFactory.getHypothesis(LikesSportHypothesysFactory.FIELD_WIND);
		fields[4] = LikesSportHypothesysFactory.getHypothesis(LikesSportHypothesysFactory.FIELD_WATER);
		fields[5] = LikesSportHypothesysFactory.getHypothesis(LikesSportHypothesysFactory.FIELD_FORECAST);
	}

}
