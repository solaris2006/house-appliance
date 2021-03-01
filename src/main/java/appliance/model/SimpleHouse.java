package appliance.model;

import java.util.*;

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
        for (Appliance appliance : appliances){
            if (appliance instanceof AbstractLight){
                ((AbstractLight) appliance).on();
            }
        }
    }

    public void switchOffAllLights() {
        //TODO implement
        for (Appliance appliance : appliances){
            if (appliance instanceof  AbstractLight){
                ((AbstractLight) appliance).off();
            }
        }
    }

    public PowerSource findPowerSourceByName(String name){
        for (PowerSource powerSource : powerSources){
            if (powerSource.getName().toLowerCase().equals(name)){
                return powerSource;
            }

        }
        return null;
    }

    public void switchAllToGrid(){
      for (Appliance appliance: appliances)
          if (appliance instanceof PowerConsumer) {
              if (findPowerSourceByName("grid") != null){
                  ((PowerConsumer) appliance).setPowerSource(findPowerSourceByName("grid"));
              }
          }

    }

    public void switchOffGrid(){
        int currentBatteryWatts = getLoadInWatts(findPowerSourceByName("battery"));
        int currentSolarWatts = getLoadInWatts(findPowerSourceByName("solar"));
        System.out.println("current battery watts "  + currentSolarWatts);
        System.out.println("current solar watts" + currentBatteryWatts);

      for (Appliance appliance : appliances){
          if (appliance instanceof  PowerConsumer){
              if (((PowerConsumer) appliance).getPowerSource().getName().equalsIgnoreCase("grid")){
               if (((PowerConsumer) appliance).getCurrentWatts() + currentSolarWatts <
                       findPowerSourceByName("solar").getMaxPowerWatt()){
                   ((PowerConsumer) appliance).setPowerSource(findPowerSourceByName("solar"));
                   currentSolarWatts += getLoadInWatts(findPowerSourceByName("solar"));
                   System.out.println("current solar watts" + currentSolarWatts);
               }else if (((PowerConsumer) appliance).getCurrentWatts() + currentBatteryWatts <
                       findPowerSourceByName("battery").getMaxPowerWatt()){
                   ((PowerConsumer) appliance).setPowerSource(findPowerSourceByName("battery"));
                   currentBatteryWatts += getLoadInWatts(findPowerSourceByName("battery"));
                   System.out.println("current solar watts" + currentBatteryWatts);



               }
              }
          }
      }


    }
}
