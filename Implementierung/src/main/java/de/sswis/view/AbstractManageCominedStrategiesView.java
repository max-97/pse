package de.sswis.view;

import de.sswis.view.model.VMStrategy;

public interface AbstractManageCominedStrategiesView {


    void addStrategy(VMStrategy vmStrategy);
    void removeStrategy(String strategyName);

    void update();
}
