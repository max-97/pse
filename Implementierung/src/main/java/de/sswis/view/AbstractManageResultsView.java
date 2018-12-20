package de.sswis.view;

import de.sswis.view.model.VMResult;

public interface AbstractManageResultsView {

    void addResult(VMResult vmResult);
    void removeResult(String resultName);

    void update();

}
