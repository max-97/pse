package de.sswis.view;

import de.sswis.view.model.VMStrategy;

public interface AbstractManageStrategiesView {

    void addResult(VMStrategy vmStrategy);
    void removeResult(String strategyName);

    void update();
}
