package appliance.model;

public class Refrigerator extends AbstractPowerConsumer implements Appliance{
    private final String name;
    private double targetTemperature ;

    public Refrigerator(String name){
        this.name = name;
        this.targetTemperature = 8;
    }

    public void colder(){
        // TO DO
        this.targetTemperature -= 0.5;
    }

    public void warmer(){
        // TO DO
        this.targetTemperature += 0.5;
    }

    @Override
    public int getMaxWatts() {
        return 800;
    }

    public int getCurrentWatts() {
        System.out.println(this.targetTemperature);
        return (int) (15 * (20 - this.targetTemperature));
    };

    public String getName() { return  name;}

    @Override
    public String toString() {
        return "Refrigerator{" +
                "name='" + name + '\'' +
                ", targetTemperature=" + targetTemperature +
                '}';
    }
}


