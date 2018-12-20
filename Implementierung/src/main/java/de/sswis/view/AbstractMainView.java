package de.sswis.view;

import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;

public interface AbstractMainView {

    void addConfiguration(VMConfiguration configuration);

    void removeConfiguration(String configurationName);

    void  addResult(String NameConfiguration, VMResult result);

    void setSimulationFinished (String NameConfiguration);

    void update();



}
