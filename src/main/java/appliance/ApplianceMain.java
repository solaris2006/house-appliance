package appliance;

import appliance.model.*;
import appliance.ui.HouseFrameFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ApplianceMain {

    public static void main(String[] args) {
        PowerSource grid = new PowerSource(12_000, "Grid");
        PowerSource solar = new PowerSource(700, "Solar");
        PowerSource battery = new PowerSource(1_500, "Battery");

        List<PowerSource> powerSources = new ArrayList<>();
        powerSources.add(grid);
        powerSources.add(solar);
        powerSources.add(battery);

        SimpleHouse house = new SimpleHouse(powerSources);

        SimpleLight light1 = new SimpleLight("Simple light 1");
        SimpleLight light2 = new SimpleLight("Simple light 2");
        VacuumCleaner vacuumCleaner1 = new VacuumCleaner("Vacuum cleaner 1");

        house.addAppliance(light1);
        house.addAppliance(light2);
        house.addAppliance(vacuumCleaner1);

        light1.setPowerSource(grid);
        light2.setPowerSource(solar);
        vacuumCleaner1.setPowerSource(grid);

        HouseFrameFactory.create(house);
    }

}
