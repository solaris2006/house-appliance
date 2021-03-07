package appliance.model;

public class SimpleLight extends AbstractLight {

	public SimpleLight(String name) {
		super(name);
	}

	public SimpleLight(String name, float initialBrightness) {
		super(name, initialBrightness);
	}

	public SimpleLight(String name, float brightness, PowerSource powerSource) {
		super(name, brightness, powerSource);
	}

	public int getMaxWatts() {
		return 100;
	}

	public String toString() {
		return "SimpleLight{} " + super.toString();
	}
}
