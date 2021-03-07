package appliance.model;

public interface Light extends Appliance, Switchable {

    void brighten();

    void dim();

    float getBrightnessInLumens();
}
