package appliance.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleHouse implements House {

    private List<Appliance> appliances = new ArrayList<>();

    private final List<PowerSource> powerSources;

    public SimpleHouse(List<PowerSource> powerSources) {
        this.powerSources = Collections.unmodifiableList(powerSources);
    }

    public List<Appliance> getAppliances() {
        return Collections.unmodifiableList(appliances);
    }

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    public Collection<PowerSource> getPowerSources() {
        return powerSources;
    }

    public int getLoadInWatts(PowerSource powerSource) {
        //TODO implement correctly

        int power = 0;
        for (Appliance appliance : appliances){
            if (appliance instanceof PowerConsumer){
                if (((PowerConsumer) appliance).getPowerSource().getName().equals(powerSource.getName())){
                    power += ((PowerConsumer) appliance).getCurrentWatts();
                }
            }
        }
        return power;

    }

    public void switchOnAllLights() {
        //TODO implement
    }

    public void switchOffAllLights() {
        //TODO implement
    }
}
