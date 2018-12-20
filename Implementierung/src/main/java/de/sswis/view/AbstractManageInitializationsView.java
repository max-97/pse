package de.sswis.view;

import de.sswis.view.model.VMInitialization;

public interface AbstractManageInitializationsView {

    void addInit(VMInitialization vmInitialization);
    void removeInit(String initName);

    void update();
}
