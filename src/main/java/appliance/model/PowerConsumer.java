package appliance.model;

public interface PowerConsumer {

	PowerSource getPowerSource();
	
	int getMaxWatts();

	int getCurrentWatts();

	void setPowerSource(PowerSource powerSource);
}
