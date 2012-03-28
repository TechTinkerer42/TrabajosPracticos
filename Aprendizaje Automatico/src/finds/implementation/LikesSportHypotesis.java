package finds.implementation;

import finds.Hypotesis;
import finds.HypotesisField;
import finds.implementation.field.LikesSportHypotesysFactory;

public class LikesSportHypotesis extends Hypotesis {

	public LikesSportHypotesis() {
	}
	
	public LikesSportHypotesis(String[] values) {
		super(values);
	}
	
	@Override
	protected void initializeFields() {
		fields = new HypotesisField[6];
		fields[0] = LikesSportHypotesysFactory.getHypotesis(LikesSportHypotesysFactory.FIELD_SKY);
		fields[1] = LikesSportHypotesysFactory.getHypotesis(LikesSportHypotesysFactory.FIELD_AIR);
		fields[2] = LikesSportHypotesysFactory.getHypotesis(LikesSportHypotesysFactory.FIELD_HUMIDITY);
		fields[3] = LikesSportHypotesysFactory.getHypotesis(LikesSportHypotesysFactory.FIELD_WIND);
		fields[4] = LikesSportHypotesysFactory.getHypotesis(LikesSportHypotesysFactory.FIELD_WATER);
		fields[5] = LikesSportHypotesysFactory.getHypotesis(LikesSportHypotesysFactory.FIELD_FORECAST);
	}

}
