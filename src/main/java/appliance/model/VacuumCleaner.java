package appliance.model;

public class VacuumCleaner extends AbstractPowerConsumer implements Switchable, Appliance {

	private boolean active;

	private String name;

	public VacuumCleaner(String name) {
		this.name = name;
	}

	public int getMaxWatts() {
		return 2500;
	}

	public int getCurrentWatts() {
		return active ? getMaxWatts() : 0;
	}

	public void on() {
		active = true;
	}

	public void off() {
		active = false;
	}

	public boolean isOn() {
		return false;
	}

	public String toString() {
		return "VacuumCleaner{" +
				"active=" + active +
				'}';
	}

	public String getName() {
		return name;
	}
}
