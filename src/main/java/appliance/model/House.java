package appliance.model;

import java.util.Collection;

public interface House {

    Collection<Appliance> getAppliances();

    Collection<PowerSource> getPowerSources();

    int getLoadInWatts(PowerSource powerSource);

}
