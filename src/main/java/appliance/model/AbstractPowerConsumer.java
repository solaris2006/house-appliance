package appliance.model;

public abstract class AbstractPowerConsumer implements PowerConsumer {

	protected PowerSource powerSource;
	
	public PowerSource getPowerSource() {
		return powerSource;
	}
	
	public void setPowerSource(PowerSource powerSource) {
		this.powerSource = powerSource;
	}

	public abstract int getMaxWatts();

}
