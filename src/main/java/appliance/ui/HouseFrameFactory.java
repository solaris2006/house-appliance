package appliance.ui;

import appliance.model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

public class HouseFrameFactory {

    private static JFrame frame;

    public static JFrame create(House house) {

        List<Runnable> modelUpdates = new ArrayList<>();
        JPanel panel = createContentPanel(house, modelUpdates);
        doModelUpdates(modelUpdates);

        frame = new JFrame("House");
        frame.getContentPane().add(panel);
        frame.setSize(1280, panel.getComponentCount() * 80);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.doLayout();
        frame.setVisible(true);

        return frame;
    }

    private static JPanel createContentPanel(House house, List<Runnable> modelUpdates) {
        JPanel panel = new JPanel();
        addControlPanel(house, panel, modelUpdates);
        addPowerSourcePanels(house, panel, modelUpdates);
        addAppliancePanels(house.getAppliances(), panel, modelUpdates);
        panel.setLayout(new GridLayout(panel.getComponentCount(), 1, 10, 10));
        return panel;
    }

    private static void addControlPanel(House house, JPanel panel, List<Runnable> modelUpdates) {
        JPanel comp = new JPanel(new FlowLayout());
        addMthodButtons(house, comp, modelUpdates);
        comp.setBorder(new TitledBorder("Control Panel"));
        panel.add(comp);
    }

    private static void addPowerSourcePanels(final House house, JPanel panel, List<Runnable> modelUpdates) {
        for (PowerSource powerSource : house.getPowerSources()) {
            JProgressBar progressBar = new JProgressBar(0, powerSource.getMaxPowerWatt());
            setTitleBorder(progressBar, powerSource, modelUpdates);
            panel.add(progressBar);

            modelUpdates.add(new Runnable() {
                public void run() {
                    progressBar.setValue(house.getLoadInWatts(powerSource));
                }
            });
        }
    }

    private static void addAppliancePanels(Collection<Appliance> appliances, JPanel panel, List<Runnable> modelUpdates) {
        for (Appliance appliance : appliances) {
            JPanel comp = new JPanel(new FlowLayout());
            addMthodButtons(appliance, comp, modelUpdates);
            setTitleBorder(comp, appliance, modelUpdates);
            panel.add(comp);
        }
    }

    private static void addMthodButtons(Object appliance, JPanel comp, List<Runnable> modelUpdates) {
        for (Method m : getMethodMethods(appliance.getClass())) {
            JButton button = new JButton(m.getName());
            button.addActionListener(invokeAndUpdateModel(m, appliance, modelUpdates));
            comp.add(button);
        }
    }

    private static List<Method> getMethodMethods(Class<?> clazz) {
        List<Method> result = new ArrayList<>();
        for (Method m : clazz.getMethods()) {
            if (m.getDeclaringClass() != Object.class && m.getParameterCount() == 0) {
                result.add(m);
            }
        }
        Collections.sort(result, new Comparator<Method>() {
            public int compare(Method o1, Method o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return result;
    }

    private static void setTitleBorder(JComponent component, NameIdentifiable nameIdentifiable, List<Runnable> modelUpdates) {
        modelUpdates.add(new Runnable() {
            public void run() {
                TitledBorder border = new TitledBorder(
                        nameIdentifiable.getName() + "     " + nameIdentifiable.toString());
                component.setBorder(border);
            }
        });
    }

    private static ActionListener invokeAndUpdateModel(Method method, Object target, List<Runnable> modelUpdates) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Object result = method.invoke(target);
                    doModelUpdates(modelUpdates);
                    if (result != null) {
                        JOptionPane.showMessageDialog(frame, String.valueOf(result));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, String.valueOf(ex.getMessage()));
                }
            }
        };
    }

    private static void doModelUpdates(List<Runnable> runnables) {
        for (Runnable runnable : runnables) {
            runnable.run();
        }
    }

}
