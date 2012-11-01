package aprendizajeautomatico.finds.implementation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import aprendizajeautomatico.finds.HypothesisField;


public class LikesSportHypothesysFactory {

	public static final int FIELD_SKY 		= 0;
	public static final int FIELD_AIR 		= 1;
	public static final int FIELD_HUMIDITY 	= 2;
	public static final int FIELD_WIND 		= 3;
	public static final int FIELD_WATER 	= 4;
	public static final int FIELD_FORECAST 	= 5;
	
	public static HypothesisField getHypothesis(int field) {
		List<String> allValues = new LinkedList<String>();
		switch(field) {
			case FIELD_SKY:
				setSkyValues(allValues); break;
			case FIELD_AIR:
				setAirValues(allValues); break;
			case FIELD_HUMIDITY:
				setHumidityValues(allValues); break;
			case FIELD_WIND:
				setWindValues(allValues); break;
			case FIELD_WATER:
				setWaterValues(allValues); break;
			case FIELD_FORECAST:
				setForecastValues(allValues); break;
			default:
				throw new IllegalArgumentException("Unknown field: "  + field);
		}
		return new HypothesisField(allValues);
	}

	protected static void setSkyValues(Collection<String> allValues) {
		allValues.add("soleado");
		allValues.add("nublado");
		allValues.add("lluvioso");
	}

	protected static void setAirValues(Collection<String> allValues) {
		allValues.add("calido");
		allValues.add("fria");
	}

	protected static void setHumidityValues(Collection<String> allValues) {
		allValues.add("normal");
		allValues.add("alta");
	}

	protected static void setWindValues(Collection<String> allValues) {
		allValues.add("fuerte");
		allValues.add("debil");
	}
	
	protected static void setWaterValues(Collection<String> allValues) {
		allValues.add("calida");
		allValues.add("fria");
	}

	protected static void setForecastValues(Collection<String> allValues) {
		allValues.add("igual");
		allValues.add("cambiante");
	}
}
