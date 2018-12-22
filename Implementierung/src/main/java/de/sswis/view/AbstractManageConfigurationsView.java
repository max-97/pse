package de.sswis.view;

import de.sswis.view.model.VMConfiguration;

public interface AbstractManageConfigurationsView {

    void addConfiguration(VMConfiguration configuration);
    void removeConfiguration(String configName);

    void update();
}
