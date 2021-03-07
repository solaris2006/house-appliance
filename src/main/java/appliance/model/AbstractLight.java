package appliance.model;

public abstract class AbstractLight extends AbstractPowerConsumer implements Light {

    protected boolean active = false;
    protected float brightness;
    private String name;

    public AbstractLight(String name) {
        this(name, 5, null);
    }

    public AbstractLight(String name, float initialBrightness) {
        this(name, initialBrightness, null);
    }

    public AbstractLight(String name, float brightness, PowerSource powerSource) {
        this.name = name;
        this.brightness = brightness;
        this.powerSource = powerSource;
    }

    public void on() {
        this.active = true;
    }

    public void off() {
        this.active = false;
    }

    public void brighten() {
        brightness += 5;
    }

    public void dim() {
        brightness -= 5;
    }

    public boolean isOn() {
        return active;
    }

    public float getBrightnessInLumens() {
        return active ? brightness : 0;
    }

    public int getCurrentWatts() {
        return active ? 2 + (int) Math.ceil(brightness * 0.5) : 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractLight{" +
                "active=" + active +
                ", brightness=" + brightness +
                ", name='" + name + '\'' +
                '}';
    }
}
