package neuronalnetwork;

public class Connection {

	private Neuron from;
	public float weight;
	
	public Connection(Neuron from) {
		this.from = from;
		weight = (float) (Math.random() - 0.5f);	// Random from [-0.5, 0.5]
	}
	
	@Override
	public String toString() {
		String s = "";
		if (from != null) {
			s += from.toString();
		} else {
			s += "(null)";
		}
		return s + " == " + weight + " ==> ";
	}
}
